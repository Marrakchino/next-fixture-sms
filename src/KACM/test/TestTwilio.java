package KACM.test;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import KACM.Parser;

public class TestTwilio {
  
	public static final String ACCOUNT_SID = "AC93ebe79dcc74c372382e1333964d229b";
	public static final String AUTH_TOKEN = "df7b1fb35579093f2bf916fba57c311c";

	public static void main(String[] args) {
	    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

	    Message message = Message.creator(new PhoneNumber("+33664444309"),
	        new PhoneNumber("+33644600250"), 
	        "This is a test!").create();
		System.out.println(message.getSid());
	    
	}

}
