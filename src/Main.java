import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {
	public static final int GAME = 0;
	public static final int SCOREBOARD = 1;
	
	public Main(String name) {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		
		this.addState(new ScoreBoard());
		this.addState(new Game());
	}
	

}
