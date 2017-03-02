package nextfixturesms;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser {
 	private static final String SOURCE_URL = "http://www.matchendirect.fr/equipe/*****.html"; 
	// change this with your team's html page on this website
	
	public static Fixture getNextFixture()
	{
		Fixture nextFixture;
		Document doc;
		try {
			doc = Jsoup.connect(SOURCE_URL).get();
			Elements matchGridElements = doc.getElementsByClass("tableau");
			
			Iterator<Element> usefulElementsIterator = matchGridElements.first().select("td").iterator();
			String fixtureTime = usefulElementsIterator.next().text();
			String fixtureDate = usefulElementsIterator.next().text();
			String fixtureTeams = usefulElementsIterator.next().text();
			
			nextFixture = new Fixture(fixtureTime, fixtureDate, fixtureTeams);
			return nextFixture;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("No fixture found!");
		return null;
	}

}
