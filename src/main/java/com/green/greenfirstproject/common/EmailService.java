package com.green.greenfirstproject.common;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;
import com.google.api.services.gmail.model.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Service
public class EmailService {
    //애플리케이션 이름.
    public static final String APPLICATION_NAME = "Gmail API Java Quickstart";
    /**
     * JSON 팩토리의 글로벌 인스턴스.
     */
    public static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    /**
     * 이 애플리케이션을 위한 인증 토큰을 저장할 디렉터리.
     */
    public static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * 요구하는 범위의 글로벌 인스턴스.
     * 이 범위를 수정할 경우, 이전에 저장된 tokens/ 폴더를 삭제하세요.
     */
    private static final List<String> SCOPES = List.of(
            GmailScopes.GMAIL_SEND
    );

    private static final String CREDENTIALS_FILE_PATH = "src/main/resources/credentials.json" ;


//    public EmailService(@Value("${email.credentials}") String path){
//        CREDENTIALS_FILE_PATH = path;
//    }



    /**
     * 권한이 부여된 Credential 객체를 생성합니다.
     *
     * @param HTTP_TRANSPORT 네트워크 HTTP Transport.
     * @return 권한이 부여된 Credential 객체.
     * @throws IOException credentials.json 파일을 찾을 수 없는 경우.
     */
    public static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
            throws IOException {
        // 클라이언트 비밀 정보를 로드합니다.
        System.out.println(CREDENTIALS_FILE_PATH);
        InputStream in = EmailService.class.getClassLoader().getResourceAsStream("credentials.json");
        if (in == null) {
            throw new FileNotFoundException("리소스를 찾을 수 없습니다: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // 사용자 인증 요청을 트리거하고 흐름을 빌드합니다.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        // 권한이 부여된 Credential 객체를 반환합니다.
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }


    /**
     * MIME 메시지를 생성합니다.
     *
     * @param to      수신자 이메일 주소
     * @param from    발신자 이메일 주소
     * @param subject 이메일 제목
     * @param bodyText 이메일 본문
     * @return 생성된 MimeMessage
     * @throws MessagingException 이메일 메시지 생성 중 오류가 발생한 경우
     */
    public static MimeMessage createEmail(String to, String from, String subject, String bodyText)
            throws MessagingException
    {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress(from));
        email.addRecipient(jakarta.mail.Message.RecipientType.TO,
                new InternetAddress(to));
        email.setSubject(subject);

        // HTML 본문을 설정하는 부분
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(bodyText, "text/html; charset=utf-8");

        // 멀티파트 설정
        MimeMultipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        // MimeMessage에 콘텐츠를 설정합니다.
        email.setContent(multipart);
        return email;
    }

    /**
     * MIME 메시지를 Gmail API로 전송할 수 있도록 변환합니다.
     *
     * @param emailContent MIME 메시지
     * @return Gmail API로 전송할 수 있는 메시지 객체
     * @throws IOException 메시지를 변환하는 중 오류가 발생한 경우
     * @throws MessagingException 메시지를 변환하는 중 오류가 발생한 경우
     */
    public static Message createMessageWithEmail(MimeMessage emailContent)
            throws MessagingException, IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        emailContent.writeTo(buffer);
        byte[] bytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(bytes);
        Message message = new Message();
        message.setRaw(encodedEmail);
        return message;
    }

    /**
     * 이메일을 전송합니다.
     *
     * @param service Gmail 서비스 객체
     * @param userId  사용자 ID. 기본적으로 "me" 사용
     * @param email   전송할 이메일 메시지
     * @throws MessagingException 이메일 전송 중 오류가 발생한 경우
     * @throws IOException 이메일 전송 중 오류가 발생한 경우
     */
    public static void sendMessage(Gmail service, String userId, MimeMessage email)
            throws MessagingException, IOException {
        Message message = createMessageWithEmail(email);
        service.users().messages().send(userId, message).execute();
    }

//    public static String getCredentials() {
//        return CREDENTIALS_FILE_PATH ;
//    }


}
