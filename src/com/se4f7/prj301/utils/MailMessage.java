package com.se4f7.prj301.utils;

import jakarta.mail.MessagingException;

public class MailMessage {

	public static void sendToken(String recipientEmail, String token) {
		String subject = "OTP for Your Account";
		String htmlTextMessage = "<html>" + "<body>" + "<h2 style='color:blue;'>OTP for Your Account</h2>"
				+ "Dear User," + "<br/><br/>" + "HERE OTP:" + "<br/><br/>"
				+ "<font style='color:green;font-weight:bold;'>" + token + "</font>" + "<br/><br/>" + "Best regards,"
				+ "<br/>" + "FG1." + "</body>" + "</html>";

		try {
			JavaMailUtil.sendMail(recipientEmail, subject, htmlTextMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public static void sendDueDate(String recipientEmail, String due) {
		String subject = "Your work is time out";
		String htmlTextMessage = "<html>" + "<body>" + "<h2 style='color:red;'>Hi there, your work is warning here</h2>"
				+ "Dear User," + "<br/><br/>" + "Your work is timed out." + "<br/><br/>"
				+ "<p style='color:red;font-weight:bold;'>Your work due to: " + due + ". Now it was timed out.</p>"
				+ "<br>" + "<p style='color:red;font-weight:bold;'>Please visit out website to update that.</p>"
				+ "<br/><br/>" + "Best regards," + "<br/>" + "FG1." + "</body>" + "</html>";

		try {
			JavaMailUtil.sendMail(recipientEmail, subject, htmlTextMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public static String sendMessage(String toEmailId, String subject, String htmlTextMessage) {
		try {
			JavaMailUtil.sendMail(toEmailId, subject, htmlTextMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
			return "FAILURE";
		}
		return "SUCCESS";
	}
}
