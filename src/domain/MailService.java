package domain;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {

	public static void sendMail(String address, String message) throws Exception {
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.user", "schoofs.kelvin@gmail.com");
		props.put("mail.smtp.password", System.getenv("password"));
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", true);
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom("example@example.com");
		msg.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress("schoofs.kelvin@gmail.com"));
		msg.setSubject("Onderwerp");
		msg.setText("Hi");
		Transport trans = session.getTransport("smtp");
		String mfrom = "schoofs.kelvin@gmail.com";
		trans.connect("smtp.gmail.com", mfrom, System.getenv("password"));
		trans.sendMessage(msg, msg.getAllRecipients());
	}

	public static void main(String[] args) throws Exception {
		sendMail("", "");
		System.out.println("done");
	}
}
