package com.project.Utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailToStudents {


	static String from_konto = "***";
	static String haselko = "***";

	public static int Wyslij_maila(String mail_do_kogo, String temat,
			String wiadomosc, String od_kogo) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(from_konto, haselko);
					}
				});

		try {
			Message message = new MimeMessage(session);
			message.addFrom(InternetAddress
					.parse("Zarzadzanie projektami PWR <+" + from_konto + ">"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(from_konto));
			 message.setRecipients(Message.RecipientType.TO,
			 InternetAddress.parse(mail_do_kogo));

			message.setSubject(temat + "###" + mail_do_kogo + "###");
			message.setText("Mail wygenerowany przez system zarz�dzania projektami PWR\n"
					+ "---------------------------------------------------------\n\n"
					+ "Wiadomo�� od "
					+ od_kogo
					+ ":\n"
					+ "Wiadomo��:"
					+ wiadomosc
					+ "\n\n---------------------------------------------------------\n"
					+ "Wiadomo�� wygenerowana automatycznie, prosimy nie odpowiada�");
			try {
				Transport.send(message);
			} catch (SendFailedException e) {
				e.getMessage();
				e.getCause();
				e.getLocalizedMessage();
				e.getStackTrace();

			}
			// dziala
			return 1;

		} catch (MessagingException e) {
			e.getMessage();
			e.getCause();
			e.getLocalizedMessage();
			e.getStackTrace();
			// nie dziala
			
			System.console().writer().println(e.getMessage());
			return -1;
		}

	}
}
