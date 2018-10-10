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
	//for staying at trunk after first hit
	boolean firstHit=false;
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
		//
		counters.draw();
		checkCollisions();
		checkTrunks();
		checkWater();
		checkHome();
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
			   
				if(hit && !frog.isMoving()) {
					if(!firstHit) {
						firstHit=true;
						dif=(frog.getPosition().getX()-obs.getPosition().getX());
						System.out.println(dif);
					}
					frog.getDrawPosition().setX(obs.getPosition().getX()+dif);
					frog.getDrawPosition().setY(obs.getPosition().getY());
					frog.getBlockPosition().setX(obs.getPosition().getX()+dif);
					frog.getBlockPosition().setY(obs.getPosition().getY());
					
				}
				if (!hit && frog.isMoving()) {
					firstHit=false;
					//System.out.println("reset firsthit");
				}
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
		for(ObstacleRow rows : riverObstacles) {
			for(Obstacle obs : rows.getObstacles()) {
				boolean hit = obs.checkIfInside(frog.getDrawPosition().getX()-15, frog.getDrawPosition().getY(),Frog.WIDTH);
				if(!firstHit && frogInWaterRow(obs) && !frog.isMoving()) {
					frog.kill();
					audioManager.playDrowned();
				}
			}
		}
	}
	public boolean frogInWaterRow(Obstacle obs) {
		return(frog.getPosition().getY()+frog.HEIGHT/2>obs.getPosition().getY() 
				&& frog.getPosition().getY()+frog.HEIGHT/2<obs.getPosition().getY()+Map.TILE_RENDER_SIZE);
	}
				
	public void checkHome() {
		List<Position> homes=map.getHomeBlocks();
		
		for(Position h : homes) {
			int frogX=frog.getPosition().getX()+frog.WIDTH/2;
			int frogY=frog.getPosition().getY()+frog.HEIGHT/2;
			boolean inside=(frogX>h.getX() && frogX<h.getX()+Map.TILE_RENDER_SIZE)
					&& (frogY>h.getY() && frogY<h.getY()+Map.TILE_RENDER_SIZE);
			if(inside && !frog.isMoving()) {
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
