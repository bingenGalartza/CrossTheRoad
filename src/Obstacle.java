import org.newdawn.slick.Image;

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
		int x = pos.getX();
		int y = pos.getY();
		return x < x2 + length2 && x + length > x2 && y < y2 + Map.TILE_RENDER_SIZE && y + Map.TILE_RENDER_SIZE > y2;
	}
	
}
