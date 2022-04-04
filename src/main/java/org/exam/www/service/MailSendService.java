package org.exam.www.service;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Random;

import javax.mail.MessagingException;

import org.exam.www.utils.MailUtils;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class MailSendService {
	private JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

	public MailSendService() {
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.starttls.required", true);
		properties.put("mail.debug", true);
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("bookperi99@gmail.com");
		mailSender.setPassword("비밀번호 입력");
		mailSender.setDefaultEncoding("utf-8");
		mailSender.setJavaMailProperties(properties);
	}

	private String getAuthCode(int size) {
		Random random = new Random();
		StringBuffer buffer = new StringBuffer();
		int num = 0;

		while (buffer.length() < size) {
			num = random.nextInt(10);
			buffer.append(num);
		}
		return buffer.toString();
	}

	// 인증메일 보내기
	public String sendAuthMail(String email) {
		String authKey = getAuthCode(6);

		try {
			MailUtils sendMail = new MailUtils(mailSender);
			sendMail.setSubject("회원가입 이메일 인증");
			sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>").append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
					.append("<a href='http://localhost:8080/springexam13_EMAIL/confirm?email=").append(email)
					.append("&authkey=").append(authKey).append("' target='_blenk'>이메일 인증 확인</a>").toString());
			sendMail.setFrom("bookperi99@gmail.com", "관리자");
			sendMail.setTo(email);
			sendMail.send();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return authKey;
	}
}
