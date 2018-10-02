import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.Image;

public class ObstacleRow {
	int row;
	List<Image> images;
	List<Obstacle> obstacles;
	long timeUpdated,timeAdded;
	int speedUpdate, speedAdd;
	public ObstacleRow(List<Image> images, int row, int speedUpdate, int speedAdd) {
		this.images  = images;
		this.row = row;
		this.speedAdd = speedAdd;
		this.speedUpdate =  speedUpdate;
		timeUpdated = System.currentTimeMillis();
		timeAdded = System.currentTimeMillis();
		obstacles = new ArrayList<>();
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
		if(time > (timeAdded + speedAdd)) {
			timeAdded = time;
			addRandomObstacles();
		}
		if(time > (timeUpdated + speedUpdate)) {
			timeUpdated = time;
			for(Obstacle obs : obstacles) {
				obs.move();
			}
		}
	}
	
	public void addRandomObstacles() {
		Random rand = new Random();
		Image img = images.get(rand.nextInt(images.size()));
		//if(rand.nextBoolean()) {
			Obstacle obstacle = new Obstacle(img,img.getWidth(),new Position(-img.getWidth(),row*Map.TILE_RENDER_SIZE));
			obstacles.add(obstacle);
		//}
	}
	
}
