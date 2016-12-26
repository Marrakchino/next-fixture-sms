package nextfixturesms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class Main {

	public static final String ACCOUNT_SID = "<put your account sid here>";
	public static final String AUTH_TOKEN = "<put your authentificatio token here>";

	public static void main(String[] args) {
		Fixture nextFixture = Parser.getNextFixture();
	    String messageText = prepareMessage(nextFixture);
	    		
	    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	    Message message = Message.creator(new PhoneNumber(""),
	        new PhoneNumber(""), // change these with your personal phone number and twilio's number
	        messageText).create();
		System.out.println(message.getSid());
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
}
