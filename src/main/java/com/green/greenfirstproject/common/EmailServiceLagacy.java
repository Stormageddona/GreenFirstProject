package com.green.greenfirstproject.common;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Slf4j
@Service
public class EmailServiceLagacy
{
    /*
     * 필요 Dependency
     * // https://mvnrepository.com/artifact/jakarta.mail/jakarta.mail-api
     * // https://mvnrepository.com/artifact/com.sun.mail/jakarta.mail
     *
     *
     * application 프로퍼티에 email.host, port, username, password 를 작성
     */

    private final String HOST ;
    private final String PORT ;
    public final String USERNAME ;
    public final String PASSWORD ;

    public EmailServiceLagacy(@Value("${email.host}") String host, @Value("${email.port}")String port, @Value("${email.username}")String username, @Value("${email.password}")String password)
    {
        this.HOST = host;
        this.PORT = port;
        this.USERNAME = username;
        this.PASSWORD = password;
    }

    /**
     *
     * @param toEmail 보낼 이메일
     * @param name 메일 제목
     * @param content 메일 내용
     * @param fromEmail 보내는 이메일
     * @param fromName 보내는 사람 이름
     */
    public void sendEmail(String toEmail, String name , String content , String fromEmail, String fromName ) throws Exception
    {

        Properties props = new Properties();
        props.put("mail.smtp.host", this.HOST);
        props.put("mail.smtp.port", this.PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // TLS 사용 설정

        // 세션 생성 및 메시지 구성
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail,fromName));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(name);
            message.setContent(content,"text/html; charset=utf-8");

            // 메일 전송
            Transport.send(message);

        } catch (MessagingException | UnsupportedEncodingException e) {
            log.error("-----메일 전송 실패-----");
            log.error(e.getMessage());
            log.error("----------------------");
            throw e ;
        }


    }
}
