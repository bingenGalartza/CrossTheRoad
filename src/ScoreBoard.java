
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;



public class ScoreBoard extends BasicGameState implements ComponentListener{
	public static String BACKGROUND = "res/scoreboard_background.png";
	public static String BUTTON = "res/submit_button.png";
	public static int SCORE_X = 335;
	public static int SCORE_Y = 40;
	public static int NAME_X = 317;
	public static int NAME_Y = 102;
	public static int NAME_WIDTH = 224;
	public static int NAME_HEIGHT = 34;
	public static int BUTTON_X = 617;
	public static int BUTTON_Y = 99;
	public static int SCORE_PLAYER_X = 238;
	public static int SCORE_PLAYER_Y = 260;
	public static int SCORE_POINTS_X = 539;
	public static int SCORE_POINTS_Y = 260;
	
	Image background,button;
	TrueTypeFont font;
	TextField text;
	MouseOverArea bArea;
	FileManager manager;
	ArrayList<Score> scores;
	int points;
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		background = Util.getImage(BACKGROUND);
		button = Util.getImage(BUTTON);
		font=new TrueTypeFont(new Font("Arial",Font.BOLD,25),true);
		points = 10000;
		
		text = new TextField(container,font,NAME_X,NAME_Y,NAME_WIDTH,NAME_HEIGHT);
		text.setBackgroundColor(Color.white);
		text.setTextColor(Color.black);
		
		bArea = new MouseOverArea(container,button,BUTTON_X,BUTTON_Y,this);
		
		manager = new FileManager();
		manager.readScoreboard();
		scores = manager.getScoreboard();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		background.draw();
		font.drawString(SCORE_X, SCORE_Y, String.valueOf(points),Color.black);
		text.render(container, g);
		bArea.render(container, g);
		for(int i = 0; i < scores.size();i++) {
			Score score = scores.get(i);
			font.drawString(SCORE_PLAYER_X ,SCORE_PLAYER_Y+ (30*i), score.getPlayerName(),Color.black);
			font.drawString(SCORE_POINTS_X ,SCORE_POINTS_Y+ (30*i), String.valueOf(score.getPunctuation()),Color.black);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		
	}

	@Override
	public int getID() {
		
		return 1;
	}

	@Override
	public void componentActivated(AbstractComponent source) {
		String user = text.getText();
		Score score = new Score(points,user);
		updateScoreboard(score);
	
	}

	public void setPoints(int score) {
		points = score;
	}
	
	public void updateScoreboard(Score score) {
		if(!scores.contains(score))
			scores.add(score);
		Collections.sort(scores, new Comparator<Score>() {
		    @Override
		    public int compare(Score s1, Score s2) {
		    	int p1=s1.getPunctuation();
		    	int p2=s2.getPunctuation();
		        return Integer.compare(p2, p1);
		    }
		});
		if (scores.size()>10) {
			scores.remove(10);
		}
		manager.writeScoreboard(scores);
	}
	
	

}
