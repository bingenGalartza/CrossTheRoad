import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.Image;

public class ObstacleManager{
	static int lowestSpeed = 10;
	static int highestSpeed = 15;
	List<Image> images;
	List<Integer> roads;
	List<Obstacle> obstacles;
	long lastTimeNewObstacle, lastTimeUpdate;
	int newObstacleSpeed,updateSpeed;
	
	public ObstacleManager(List<String> imagePaths, List<Integer> roads,int newObstacleSpeed){
		obstacles = new ArrayList<>();
		this.roads = roads;
		images = new ArrayList<>();
		for(String path : imagePaths)
			images.add(Util.getImage(path));
		lastTimeNewObstacle = System.currentTimeMillis();
		lastTimeUpdate = System.currentTimeMillis();
		updateSpeed = 20;
		this.newObstacleSpeed = newObstacleSpeed;
	}

	public void addRandomObstacles() {
		for(Integer road : roads) {
			Random rand = new Random();
			Image img = images.get(rand.nextInt(images.size()));
			int speed = rand.nextInt(highestSpeed-lowestSpeed)+lowestSpeed;
			if(rand.nextBoolean()) {
				//Obstacle obstacle = new Obstacle(img,img.getWidth(),new Position(-img.getWidth(),road*Map.TILE_RENDER_SIZE));
				//obstacles.add(obstacle);
			}
		}
	}
	
	public void update() {
		Iterator<Obstacle> it = obstacles.iterator();
		while(it.hasNext()) {
			Obstacle obs = it.next();
			if(!obs.isMoving()) it.remove();
			else {
				obs.draw();
			}
		}
		clock();
	}
	
	public void clock() {
		long time = System.currentTimeMillis();
		if(time > (lastTimeNewObstacle + newObstacleSpeed)) {
			lastTimeNewObstacle = time;
			addRandomObstacles();
		}
		if(time > (lastTimeUpdate + updateSpeed)) {
			lastTimeUpdate = time;
			for(Obstacle obs : obstacles) {
				obs.move();
			}
		}
	}
	
	public List<Obstacle> getObstacles() {
		return obstacles;
	}
	
}
