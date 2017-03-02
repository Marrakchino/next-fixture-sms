package nextfixturesms;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Fixture {

	private LocalDate date;
	private String opponent;
	private String time;
// 	public String[] POSSIBLE_OPPONENTS = {"Wydad Casablanca", "Khouribga",
			"Chabab Atlas Khénifra", "FUS Rabat", "Olympic Safi",
			"Hassania Agadir", "Ittihad Tanger", "Raja Casablanca",
			"Kasba Tadla", "KAC Kénitra", "Difaâ El Jadida", "FAR Rabat",
			"Chabab Rif Hoceima", "RSB Berkane", "Moghreb Tétouan"};
	
	public LocalDate getDate() {
		return date;
	}

	public String getOpponent() {
		return opponent;
	}

	public String getTime() {
		return time;
	}

	private void setOpponent(String opponent) {
		this.opponent = opponent;
	}
	
	private void setLocalDate(LocalDate date)
	{
		this.date = date;
	}
	
	
	public Fixture(String time, String date, String teams)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy", Locale.FRANCE); // e.g: 30.12.16
		LocalDate fixtureDate = LocalDate.parse(date, formatter);
		this.setLocalDate(fixtureDate);
		
		String opponent = this.parseOpponent(teams);
		this.setOpponent(opponent);
		
		this.time = time;	
	}
	
	private String parseOpponent(String teams)
	{
		String possibleOpponent;
		if (teams.startsWith("<your team's name's first word>"))
		{
			possibleOpponent = teams.substring(16); //change this to your team's name size
		}
		else
		{
			possibleOpponent =  teams.substring(0, teams.indexOf("<your team's first word"));
		}
		return possibleOpponent;
	}
	
	private boolean isAPossibleOpponent(String opponent)
	{
		for (String possibleOpponent : POSSIBLE_OPPONENTS)
		{
			if (possibleOpponent.equals(opponent))
				return true;
		}
		return false;
	}
	
	public Fixture()
	{
	}
}
