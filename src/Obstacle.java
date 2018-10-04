import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class Obstacle{
	Image image;
	Position pos;
	
	int length;
	boolean moving;
	
	public Obstacle(Image image, int length, Position pos) {
		this.image = image;
		this.length = length;
		this.pos = pos;
		this.moving =  true;
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
	
	public Position getPosition() {
		return pos;
	}
	
	public boolean checkIfInside(int x2, int y2, int length2) {
		//FIXME 
		Rectangle frog = new Rectangle(x2,y2,length2,length2);
		Rectangle obstacle = new Rectangle(pos.getX(),pos.getY()+30,length-20,20);
		
		return frog.intersects(obstacle);
	}
	
}
