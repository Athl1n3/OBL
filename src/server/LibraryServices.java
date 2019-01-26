package server;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.activation.FileDataSource;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;
/**
 * Library threading services that keep track of user late returns and send emails to notify users for book returns
 * @author Adam Mahameed
 * @version 1.2
 * @category LibraryServices
 */

public class LibraryServices {
	Mailer mailConnection;
	MySQLConnection dbCon;

	public LibraryServices(MySQLConnection dbCon) {
		this.dbCon = dbCon;
		//emailService();// Initiate book returns email service
		lateReturnsService();//Initiate late returns service
		
	}

	/**
	 * Email scheduled task to run every 24 hours 
	 */
	private void emailService() {
		initMailConnection();
		TimerTask returnsMailsTask = new TimerTask() {
			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				ArrayList<String> lentReturns = (ArrayList<String>) dbCon.executeSelectQuery(
						"SELECT userID, bookID FROM lentbook WHERE dueDate = '" + LocalDate.now().plusDays(1) + "' AND returned = 0;");
				for (int i = 0; i < lentReturns.size(); i++) {
					ArrayList<String> lenderData = ((ArrayList<String>) dbCon.executeSelectQuery(
							"SELECT firstName,lastName,eMail FROM account WHERE userID = '" + lentReturns.get(i)
									+ "';"));
					String eMail = lenderData.get(2);
					String firstName = lenderData.get(0);
					String lastName = lenderData.get(1);
					i++;
					String bookName = ((ArrayList<String>) dbCon
							.executeSelectQuery("SELECT name FROM book WHERE bookID = '" + lentReturns.get(i) + "';"))
									.get(0);
					sendEmail(eMail, "Lent Book Return", "Hello "+firstName +" "+ lastName+"\nYour lent book '" + bookName
							+ "' has to be returned until tomorrow " + LocalDate.now().plusDays(1));
					System.out.println("Email has been sent => " + eMail + " to return '" + bookName + "'");
				}
				if (lentReturns.size() == 0) {
					System.out.println("No returns");
				}
			}
		};
		Timer returnMailsTimer = new Timer();
		returnMailsTimer.schedule(returnsMailsTask, TimeUnit.SECONDS.toMillis(10), TimeUnit.DAYS.toMillis(1));
	}

	/** 
	 * Checks database for books late returns (books that should have been returned yesterday) once every 24 hours
	 */
	private void lateReturnsService() {
		TimerTask lateReturnsTask = new TimerTask() {
			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				ArrayList<String> lateReturns = (ArrayList<String>) dbCon.executeSelectQuery(
						"SELECT userID, bookID FROM lentbook WHERE dueDate = '" + LocalDate.now().minusDays(1) + "';");//Get books that should have been returned yesterday
				for (int i = 0; i < lateReturns.size(); i++) {
					dbCon.executeQuery(
							"UPDATE account SET delays = delays + 1, status = 'Suspended' WHERE userID = '" + lateReturns.get(i) + "';");//Increment return delay by 1 for this user
					
					if (((ArrayList<String>) dbCon.executeQuery("SELECT EXISTS(SELECT * FROM account WHERE userID = '"
							+ lateReturns.get(i) + "' AND delays = 3);")).get(0).equals("0") ? false : true) {//If user has got 3 delays notify manager
						Calendar calendar = Calendar.getInstance();
						java.sql.Timestamp notificationTimeStamp = new java.sql.Timestamp(calendar.getTime().getTime());//get time stamp for notification
						dbCon.executeQuery("INSERT INTO notification(date, message, usertype)VALUES ('"
								+ notificationTimeStamp + "','User ID: " + lateReturns.get(i)
								+ " has 3 return delays account lock approval is required','Manager')");//Manager notification message
					}
					dbCon.executeQuery("UPDATE lentbook SET late = 1 WHERE bookID = '" + lateReturns.get(i+1) + "' AND userID = '" + lateReturns.get(i) +"';");
					i++;
				}
				if (lateReturns.size() == 0) {
					System.out.println("No late returns are found");
				}
			}
		};
		Timer lateReturnsTimer = new Timer();
		lateReturnsTimer.schedule(lateReturnsTask, TimeUnit.SECONDS.toMillis(70), TimeUnit.DAYS.toMillis(1));
	}

	/*
	 * private final ScheduledExecutorService scheduler =
	 * Executors.newScheduledThreadPool(1);
	 * 
	 * public void beepForAnHour() { final Runnable beeper = new Runnable() {
	 * 
	 * @Override public void run() { System.out.println("beep"); } }; final
	 * ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 10,
	 * 10, SECONDS); scheduler.schedule(new Runnable() {
	 * 
	 * @Override public void run() { beeperHandle.cancel(true); } }, 60 * 60,
	 * SECONDS); }
	 */
//smtp-mail.outlook.com	obl-project@outlook.com		adam@braude
	
	/**
	 * Initiate mailing service over SMT protocol via TLS@587
	 */
	private void initMailConnection() {
		mailConnection = MailerBuilder
				.withSMTPServer("smtp.sendgrid.net", 587, "apikey",
						"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")
				.withTransportStrategy(TransportStrategy.SMTP_TLS).withSessionTimeout(10 * 1000)
				.clearEmailAddressCriteria().withDebugLogging(false).buildMailer();
	}

	public void sendEmail(String recipient, String subject, String content) {
		Email email = EmailBuilder.startingBlank().from("athl1n3@sendgrid.net").to(recipient).withSubject(subject)
				.withPlainText(content)
				.withEmbeddedImage("Signature", new FileDataSource(new File(String
						.valueOf(LibraryServices.class.getClassLoader().getResource("images/Logo.png")).substring(6))))
				.buildEmail();
		mailConnection.sendMail(email);
	}
}

/*
 * THIS CODE WORKS TOO public static void main(String[] args) { final String
 * username = "obl-project@outlook.com"; final String password = "adam@braude";
 * 
 * Properties props = new Properties(); props.put("mail.smtp.starttls.enable",
 * "true"); props.put("mail.smtp.auth", "true"); props.put("mail.smtp.host",
 * "smtp-mail.outlook.com"); props.put("mail.smtp.port", "587");
 * 
 * Session session = Session.getInstance(props, new javax.mail.Authenticator() {
 * 
 * @Override protected PasswordAuthentication getPasswordAuthentication() {
 * return new PasswordAuthentication(username, password); } });
 * 
 * try { Message message = new MimeMessage(session); message.setFrom(new
 * InternetAddress("obl-project@outlook.com"));
 * message.setRecipients(Message.RecipientType.TO,
 * InternetAddress.parse("adam.spark199@gmail.com"));
 * message.setSubject("Testing Subject"); message.setText("Dear Mail Crawler," +
 * "\n\n No spam to my email, please!");
 * 
 * Transport.send(message);
 * 
 * System.out.println("Done");
 * 
 * } catch (MessagingException e) { throw new RuntimeException(e); } }
 */