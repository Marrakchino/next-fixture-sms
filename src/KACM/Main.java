package KACM;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class Main {

	public static final String ACCOUNT_SID = "AC93ebe79dcc74c372382e1333964d229b";
	public static final String AUTH_TOKEN = "df7b1fb35579093f2bf916fba57c311c";

	public static void main(String[] args) {
		Fixture nextFixture = Parser.getNextFixture();
	    String messageText = prepareMessage(nextFixture);
	    		
	    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	    Message message = Message.creator(new PhoneNumber("+33664444309"),
	        new PhoneNumber("+33644600250"), 
	        messageText).create();
		System.out.println(message.getSid());
	}

	public static String prepareMessage(Fixture nextFixture)
	{
	    String messageText = new String("Le prochain match du KACM se jouera le ");
	    messageText += nextFixture.getDate().toString();
	    messageText += " à ";
	    messageText += nextFixture.getTime();
	    messageText += " contre ";
	    messageText += nextFixture.getOpponent();
	    return messageText;
	}
}
