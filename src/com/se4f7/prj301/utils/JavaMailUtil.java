// khai b�o package cho class JavaMailUtil. Class n�y thu?c package com.se4f7.prj301.utils
package com.se4f7.prj301.utils;

// Import c�c class v� package c?n thi?t t? lib Java.
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

// Import c�c l?p t? th? vi?n Jakarta Mail API, m� l?p JavaMailUtil s? d?ng ?? g?i email.
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class JavaMailUtil {

	// Ph??ng th?c sendMail ???c khai b�o l� protected (kh? d?ng cho c�c l?p con) v�
	// l� static (c� th? g?i m� kh�ng c?n t?o ??i t??ng).
	protected static void sendMail(String recipient, String subject, String htmlTextMessage) throws MessagingException {

		// T?o m?t ??i t??ng Properties ?? l?u tr? c�c c�i ??t li�n quan ??n g?i email.
		Properties properties = new Properties();

		// Thi?t l?p c�c thu?c t�nh c?n thi?t cho g?i email, bao g?m m�y ch? SMTP, giao
		// th?c v?n chuy?n, x�c th?c, TLS, v� c?ng.
		String host = "smtp.gmail.com";
		properties.put("mail.smtp.host", host);
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port", "587");

		// T?o m?t ??i t??ng ResourceBundle ?? ??c th�ng tin ??ng nh?p (email v� m?t
		// kh?u) t? file c?u h�nh c� t�n l� "application".
		ResourceBundle rb = ResourceBundle.getBundle("application");

		// Thi?t l?p th�ng tin ??ng nh?p v�o Properties.
		String emailId = rb.getString("mailer.email");
		String passWord = rb.getString("mailer.password");
		properties.put("mail.user", emailId);
		properties.put("mail.password", passWord);

		// T?o m?t ??i t??ng Session s? d?ng th�ng tin c?u h�nh v� m?t Authenticator ??
		// x�c th?c ng??i g?i email.
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailId, passWord);
			}

		});

		// G?i ph??ng th?c prepareMessage ?? chu?n b? tin nh?n email.
		Message message = prepareMessage(session, emailId, recipient, subject, htmlTextMessage);

		// S? d?ng l?p Transport ?? g?i tin nh?n ?� chu?n b?.
		Transport.send(message);

		System.out.println("Message Sent Successfully!");

	}

	// Ph??ng th?c prepareMessage ???c khai b�o l� private v� nh?n c�c th�ng tin c?n
	// thi?t ?? chu?n b? tin nh?n.
	private static Message prepareMessage(Session session, String myAccountEmail, String recipientEmail, String subject,
			String htmlTextMessage) {

		try {

			// T?o m?t ??i t??ng MimeMessage ?? bi?u di?n tin nh?n email.
			Message message = new MimeMessage(session);

			// Thi?t l?p ng??i g?i, ng??i nh?n, ch? ?? v� n?i dung c?a tin nh?n email.
			// InternetAddress l� m?t l?p trong th? vi?n JavaMail API ???c s? d?ng ?? ??i
			// di?n cho ??a ch? email.
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
			message.setSubject(subject);
			message.setContent(htmlTextMessage, "text/html");
			return message;

		} catch (Exception exception) {
			Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, exception);
		}
		// Trong tr??ng h?p c� l?i, tr? v? gi� tr? null.
		return null;
	}
}
