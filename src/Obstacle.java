import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class Obstacle{
	Image image;
	Position pos;
	Frog attachedFrog;
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
		if(attachedFrog != null)
			attachedFrog.draw();
	}
	
	public void move() {
		pos.setX(pos.getX()+5);
		if(attachedFrog != null)
			attachedFrog.getPosition().setX(attachedFrog.getPosition().getX()+ 5);
	     if(pos.getX() > (Map.WIDTH*Map.TILE_SIZE)) {
	    	if(attachedFrog != null)
	    		attachedFrog.kill();
			moving=false;
		}
	}

	public boolean isMoving() {
		return moving;
	
	}
	
	public void attachFrog(Frog frog) {
	    frog.setOnTrunk(true);
		this.attachedFrog = frog;
	}
	
	public void dettachFrog() {
		if(attachedFrog != null)
			attachedFrog.setOnTrunk(false);
		attachedFrog = null;
	}
	
	public Position getPosition() {
		return pos;
	}
	
	public boolean checkIfInside(int x2, int y2, int length2, boolean trunk) {
		//FIXME 
		Rectangle frog = new Rectangle(x2,y2,length2,length2);
		int width = length-20;
		if(trunk)
			width -= Map.TILE_RENDER_SIZE; 
		Rectangle obstacle = new Rectangle(pos.getX(),pos.getY()+30,width,20);
		
		return frog.intersects(obstacle);
	}
	
}
