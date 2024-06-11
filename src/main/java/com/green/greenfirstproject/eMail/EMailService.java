package com.green.greenfirstproject.eMail;

import jakarta.mail.Authenticator;
import jakarta.mail.internet.*;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import jakarta.mail.*;

import org.springframework.mail.javamail.*;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
@RequiredArgsConstructor
@Slf4j

@Configuration
public class EMailService  {
    @Value("${spring.mail.password}") String password;
    @Value("${spring.mail.username}") String from;
    @Value("${spring.mail.host}") String host;
	@Value("${spring.mail.port}") String port ;

	private final JavaMailSenderImpl mailSender1;

	public void sendMail(String email, String title , String contents ) throws MessagingException {
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.host", host);
		props.setProperty("mail.smtp.port", port);
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.debug", "true");



		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setJavaMailProperties(props);

//		mailSender.setHost("smtp.gmail.com");
//		mailSender.setPort(587);
//		mailSender.setUsername("이메일 주소");
//		mailSender.setPassword("비밀번호");
//		mailSender.setDefaultEncoding("utf-8");
//		mailSender.setJavaMailProperties(props);

		Session session = Session.getInstance(props,new Authenticator(){
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from,password);
			}
		});
		MimeMessage mimeMessage = new MimeMessage(session);
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
		helper.setTo(email);
		helper.setFrom(from);
		helper.setSubject(title);
		helper.setText(contents);
		mailSender1.send(mimeMessage);

	}

}
