package nextfixturesms;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Fixture {

	private LocalDate date;
	private String opponent;
	private String time;
	public String[] POSSIBLE_OPPONENTS = {""};

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


	public boolean isNotYetPlayed(){
		Calendar cal = Calendar.getInstance();
		int hourOfDay = cal.get(Calendar.HOUR_OF_DAY);
		int minOfHour = cal.get(Calendar.MINUTE);
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		int monthOfYear = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);

		int fixtureDay = this.getDate().getDayOfMonth();
		int fixtureMonth = this.getDate().getMonthValue();
		// int fixtureYear = this.getDate().getYear();

		int fixtureHour = Integer.parseInt(this.getTime().split(":")[0]);
		int fixtureMin = Integer.parseInt(this.getTime().split(":")[1]);

		if (fixtureMonth < monthOfYear){
			return false;
		}
		else if (fixtureMonth > monthOfYear) {
			return true;
		}
		else {
			if (fixtureDay < dayOfMonth) {
				return false;
			}
			else if (fixtureDay > dayOfMonth) {
				return true;
			}
			else {  meme jour
				if (fixtureHour < hourOfDay){
					return false;
				}
				else {
					if (fixtureMin > minOfHour){
						return true;
					}
					else {
						return false;
					}
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Fixture [date=" + date + ", opponent=" + opponent + ", time=" + time + ", atHome=" + atHome + "]";
	}
