package com.bigbrain.utils;

import java.io.FileNotFoundException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.bigbrain.properties.PropertyReader;

public class EmailUtils {
    
	private final static String SMTP_FILE_PATH = "src/resources/smtp.properties"; 
	private Properties properties;

	public EmailUtils() throws FileNotFoundException {
		this.properties = PropertyReader.readProperties(SMTP_FILE_PATH);
	}
	
	public void sendEmail(String destinator, String subject, String body) {
			final String username = properties.getProperty("smtp.email");
			final String password = properties.getProperty("smtp.password");

			Session session = Session.getInstance(properties,
			  new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			  });

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(properties.getProperty("smtp.from")));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(destinator));
				message.setSubject(subject);
				message.setText(body);

				Transport.send(message);

				System.out.println("Done");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
	}
	
	public static void main(String[] args) {
		EmailUtils e;
		try {
			e = new EmailUtils();
			e.sendEmail("khaireddine.rezgui@gmail.com", "Subject test", "Body test");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
}
