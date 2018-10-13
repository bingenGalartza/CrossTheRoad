import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame{
	public static int WINDOW_WIDTH = 869;
	public static int WINDOW_HEIGHT = 711;
	static final String[] cars = {"/res/kotxeHoria.png","/res/kotxeUrdina.png","/res/kotxeBerdea.png", "/res/suhiltzailea.png", "/res/kamioia.png"};
	static final Integer[] roads = {4,5};
	static final Integer[] rivers = {1,2,7};
	static final String[] trunks = {"/res/enborra3x1.png","/res/enborra4x1.png","/res/enborra5x1.png"};
	Map map;
	Frog frog;
	Counters counters;
	List<ObstacleRow> roadObstacles, riverObstacles;
	
	long lastTicks = 0;
	int dif=0;
	AudioManager audioManager;
	
	public Game(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		map.draw();
		for(ObstacleRow row : roadObstacles)
			row.update();
		for(ObstacleRow row : riverObstacles)
			row.update();
		frog.draw();
		counters.draw();
		checkCollisions();
		checkTrunks();
		checkWater();
		checkWin();
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		map = new Map();
		frog = new Frog();
		counters=new Counters(frog);
		loadRows();
		audioManager = new AudioManager();
		audioManager.playMusic();
		
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		frog.update();
	}
	
	@Override
	public void keyPressed(int key, char c) {
		audioManager.playFrog();
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
	
	public void loadRows() {
		roadObstacles = new ArrayList<>();
		List<Image> carImages = Util.getImages(Arrays.asList(cars));
		roadObstacles.add(new ObstacleRow(carImages,4,30,3000));
		roadObstacles.add(new ObstacleRow(carImages,5,20,3000));
		riverObstacles = new ArrayList<>();
		List<Image> trunkImages = Util.getImages(Arrays.asList(trunks));
		riverObstacles.add(new ObstacleRow(trunkImages,1,20,2000));
		riverObstacles.add(new ObstacleRow(trunkImages,2,30,4000));
		riverObstacles.add(new ObstacleRow(trunkImages,7,40,4000));
	}
	
	public void checkTrunks() {
		for(ObstacleRow rows : riverObstacles) {
			for(Obstacle obs : rows.getObstacles()) {
				boolean hit = obs.checkIfInside(frog.getDrawPosition().getX()-15, frog.getDrawPosition().getY(),Frog.WIDTH);
				if(hit)
					obs.attachFrog(frog);
				else
					obs.dettachFrog();
			}
		}
	}
	
	public void checkCollisions() {
		for(ObstacleRow rows : roadObstacles) {
			for(Obstacle obs : rows.getObstacles()) {
				boolean hit = obs.checkIfInside(frog.getDrawPosition().getX(), frog.getDrawPosition().getY(),Frog.WIDTH);
				if(hit) {
					frog.kill();
					audioManager.playCrushed();
				}
			}
		}
	}
	
	public void checkWater() {
		if(!frog.isMoving() && !frog.isOnTrunk() && (Map.getTile(frog.getDrawPosition()) == Map.Tile.WATER)) {
			frog.kill();
			audioManager.playDrowned();
		}
	}
	
	public void checkWin() {
		int xBlock = Util.getValidPosition(frog.getDrawPosition().getX())/ Map.TILE_RENDER_SIZE;
		int yBlock = Util.getValidPosition(frog.getDrawPosition().getY()) / Map.TILE_RENDER_SIZE;
		if(yBlock == 0) {
			if(map.setWin(xBlock/2))
				frog.win();
			else {
				frog.kill();
			}
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
