
public class Score {
	int punctuation;
	String playerName;
	
	public Score(int punctuation, String playerName) {
		this.punctuation=punctuation;
		this.playerName=playerName;
	}

	public int getPunctuation() {
		return punctuation;
	}

	public String getPlayerName() {
		return playerName;
	}

	@Override
	public String toString() {
		String out=new String(punctuation+"-"+playerName);
		return out;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		Score s = (Score) obj;
		
		return s.getPlayerName().equals(playerName) && (s.getPunctuation() == punctuation);
	}
	
	
	
}

