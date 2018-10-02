import org.newdawn.slick.Image;

public class Obstacle{
	Image image;
	Position pos;
	
	int length;
	int speed;
	boolean moving;
	long lastTime;
	
	public Obstacle(Image image, int length, Position pos, int speed) {
		this.image = image;
		this.length = length;
		this.pos = pos;
		this.speed = speed;
		this.moving =  true;
		this.lastTime = System.currentTimeMillis();
	}
	
	public void update() {
		long time = System.currentTimeMillis();
		if(time > (lastTime + speed)) {
			lastTime = time;
			move();
		}
		draw();
	}
	public void draw() {
		image.draw(pos.getX(),pos.getY());
	}
	
	public void move() {
		pos.setX(pos.getX()+5);
	     if(pos.getX() > (Map.WIDTH*Map.TILE_SIZE)) {
			moving=false;
		}
	}

	public boolean isMoving() {
		return moving;
	}
	
}
