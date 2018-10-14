import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FileManager {
	public static String scoreFile= "res/scores.txt";
	FileReader fr; 
	BufferedReader br; 
	FileWriter fw;
	PrintWriter pw;
	ArrayList<Score> scoreboard;
	
	public FileManager() {
		readScoreboard();
	}
	public void readScoreboard() {
		String str;
		Score singleScore;
		int i=0;
		try {	
			fr=new FileReader(scoreFile);
			br=new BufferedReader(fr);
			scoreboard=new ArrayList<Score>();
			while((str = br.readLine()) != null && i++<10) {
				String[] parts=str.split("-");
				singleScore=new Score(Integer.parseInt(parts[0]), parts[1]);
				scoreboard.add(singleScore);
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			try {
				FileWriter fw=new FileWriter(scoreFile);
				scoreboard=new ArrayList<Score>();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}

	}
	
	public void writeScoreboard(ArrayList<Score> scoreboard) {
		try {
			this.scoreboard=scoreboard;
			FileWriter fw = new FileWriter(scoreFile);
			PrintWriter pw= new PrintWriter(fw);
			for (Score score : scoreboard) { 		      
				pw.println(score.toString());
		    }
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Score> getScoreboard(){
		return scoreboard;
	}
	


}
