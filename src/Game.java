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

	public Game(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		map.draw();
		frog.draw();
		obstacle.draw();
		obstacle2.draw();
		
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		map = new Map();
		frog = new Frog(new Position(0,8));
		obstacle = new Obstacle(Util.getImage("/res/car1.png"),2, new Position(11,4),100);
		obstacle.start();
		obstacle2 = new Obstacle(Util.getImage("/res/car2.png"),2, new Position(5,5),200);
		obstacle2.start();
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
