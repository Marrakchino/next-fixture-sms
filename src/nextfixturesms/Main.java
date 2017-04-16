package nextfixturesms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message.Status;
//import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.impl.Log4JLogger;

public class Main {

	public static final String MAIL_FROM = "";
	public static final String MAIL_TO = "";
	public static final String ACCOUNT_SID = System.getenv("TWILIO_SID");
	public static final String AUTH_TOKEN = System.getenv("TWILIO_TOKEN");

	private static final Logger LOGGER = Logger.getLogger(Class.class.getName());

	public static void main(String[] args) {
		Fixture nextFixture = Parser.getNextFixture();
	    String messageText = prepareMessage(nextFixture);
	    LOGGER.log(Level.FINE, "Message: ", messageText);
	    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	    com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber(""),
	    	  new PhoneNumber(""),	
	    	  messageText).create();
		Status messageStatus = message.getStatus();
		if (messageStatus==Status.FAILED||
			messageStatus==Status.UNDELIVERED){
			LOGGER.log(Level.SEVERE, "Status if FAILED or UNDELIVERED");
			// sendMail(messageStatus.toString()); // couldn't provoque this case.. untested
		}
	}

	// change the sent text message as you want it to be
	public static String prepareMessage(Fixture nextFixture)
	{
	    String messageText = new String("");
	    messageText += nextFixture.getDate().toString();
	    messageText += nextFixture.getTime();
	    messageText += nextFixture.getOpponent();
	    return messageText;
	}
	
	/*
	public static void sendMail(String messageStatus){
	    final String username = "";
	    final String password = "";

	    String host = "smtp.gmail.com"; // replace with your mail smtp server

	    Properties props = new Properties();
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.port", "587");


	    Session session = Session.getInstance(props,
	    new javax.mail.Authenticator() {
	    	protected PasswordAuthentication getPasswordAuthentication() {
	    		return new PasswordAuthentication(username, password);
	         }
	      });

	    try {
	    	Message message = new MimeMessage(session);
	    	message.setFrom(new InternetAddress(MAIL_FROM));
	    	message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(MAIL_TO));
	    	message.setSubject("[LOG] next-fixture-sms");
	    	message.setText("I failed to send the SMS to your phone. Status: " + messageStatus + ".");
	    	// Send message
	    	Transport.send(message);
	    } catch (MessagingException e) {
	    	  System.out.println("Erorr while sending the mail...");
	    }
	}
	*/
}
