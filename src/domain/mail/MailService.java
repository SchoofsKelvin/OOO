package domain.mail;

import java.util.Properties;

import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {

	private Session					session;
	private String					host, user, mail, pass;
	private Transport				trans;

	public final static MailService	GMail	= new MailService("smtp.gmail.com",
		"schoofs.kelvin@gmail.com", null, getPassword());
	public final static MailService	UCLL	= new MailService("outlook.office365.com",
		"r0620441@ucll.be", "kelvin.schoofs@student.ucll.be", getPassword());

	public MailService(String host, String user, String mail, String pass) {
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", user);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", true);
		session = Session.getDefaultInstance(props, null);
		this.host = host;
		this.user = user;
		this.mail = mail == null ? user : mail;
		this.pass = pass;
		try {
			trans = session.getTransport("smtp");
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
	}

	private static String getPassword() {
		return System.getenv("password");
	}

	public void sendMail(String address, String subject, String message)
		throws Exception {
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(mail);
		msg.addRecipient(javax.mail.Message.RecipientType.TO,
			new InternetAddress(address));
		msg.setSubject(subject);
		msg.setText(message);
		trans.connect(host, user, pass);
		trans.sendMessage(msg, msg.getAllRecipients());
	}

	public static void main(String[] args) throws Exception {
		UCLL.sendMail("schoofs.kelvin@gmail.com", "Subject", "Message1");
		UCLL.sendMail("schoofs.kelvin@gmail.com", "Subject", "Message2");
		UCLL.sendMail("schoofs.kelvin@gmail.com", "Subject", "Message3");
		System.out.println("done");
	}
}
