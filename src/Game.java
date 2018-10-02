import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;



public class Game extends BasicGame{
	public static int WINDOW_WIDTH = 869;
	public static int WINDOW_HEIGHT = 711;
	Map map;
	Frog frog;
	Obstacle obstacle, obstacle2;
	ObstacleManager manager;
	long lastTicks = 0;
	public Game(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		map.draw();
		frog.draw();
		manager.update();
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		map = new Map();
		frog = new Frog(new Position(0,8));
		manager = new ObstacleManager();
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		frog.update();
	}
	
	@Override
	public void keyPressed(int key, char c) {
		switch(key) {
		case Input.KEY_DOWN:
			frog.move(2);
			break;
		case Input.KEY_LEFT:
			frog.move(3);
			break;
		case Input.KEY_UP:
			frog.move(0);
			break;
		case Input.KEY_RIGHT:
			frog.move(1);
			break;
		}
	}
	

	/*public void clock() {
		long ticks = System.currentTimeMillis();
		if(ticks > (lastTicks + 20)) {
			lastTicks = ticks;
			updateObstacles();
		}
	}*/
	

	
	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Game("Cross the street"));
			appgc.setDisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT, false);
			appgc.setTargetFrameRate(250);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
