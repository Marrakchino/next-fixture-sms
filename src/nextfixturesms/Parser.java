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

			SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yy");
			Date today = Calendar.getInstance().getTime(); 

			List<Fixture> allFixtures = new ArrayList<Fixture>();
			String fixtureTime, fixtureDate, fixtureTeams;
			for (int i = 0; i < matchGridElements.first().getElementsByClass("lm1").size(); i++){
				fixtureTime = matchGridElements.first().getElementsByClass("lm1").get(i).text();
				fixtureDate = matchGridElements.first().getElementsByClass("lm2_0").get(i).text();
				if (fixtureDate.equals("Aujourd'hui")){ // J'espere qu'il y a pas de demain...
					fixtureDate = sdfDate.format(today);
				}
				fixtureTeams = matchGridElements.first().getElementsByClass("lm3").get(i).text();
				allFixtures.add(new Fixture(fixtureTime, fixtureDate, fixtureTeams));
			}

			nextFixture = allFixtures.get(0);
			int i = 1;
			while (nextFixture.isNotYetPlayed()){
				nextFixture = allFixtures.get(i);
				i++;
			}

			return nextFixture;

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("No fixture found!");
		return null; // ...

	}
}
}
}
}

}
