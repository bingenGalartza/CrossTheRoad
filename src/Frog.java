import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class Frog {
	public static String PATH = "/res/frog.png";
	public static int HEIGHT = 81;
	public static int WIDTH = 81;
	SpriteSheet sprite;
	Animation animation;
	Position pos;
	Position drawPos;
	boolean moving, onTrunk;
	
	public Frog() {
		initAnimation();
		resetPos();
	}
	
	public void initAnimation() {
		Image image = Util.getImage(PATH);
		sprite = new SpriteSheet(image,WIDTH,HEIGHT);
	    animation = new Animation(sprite,0,0,5,0,true,100,true);
	}
	
	public void draw() {
		int drawX = drawPos.getX();
		int drawY = drawPos.getY();
		int targetX = pos.getX();
		int targetY = pos.getY();

		if(drawX != targetX) {
			drawPos.setX(drawX + ((drawX > targetX)?-1:1));
			moving = true;
		}
		else if(drawY != targetY) {
			drawPos.setY(drawY + ((drawY > targetY)?-1:1));
			moving = true;
		}else {
			animation.stopAt(0);
			moving = false;
		}

	    animation.getCurrentFrame().setRotation(90*pos.getRotation());
	    animation.draw(drawPos.getX(),drawPos.getY());
	}

	public void move(int direction) {
		if(!canMove()) return;
		int x = pos.getX();
		int y = pos.getY();
		switch(direction) {
		case 0:
			y-=Map.TILE_RENDER_SIZE;
			break;
		case 1:
			x+=Map.TILE_RENDER_SIZE;
			break;
		case 2:
			y+=Map.TILE_RENDER_SIZE;
			break;
		case 3:
			x-=Map.TILE_RENDER_SIZE;
			break;
		}
		if(isValidMovement(x,y)){
			pos.setX(x);
			pos.setY(y);
		}
		pos.setRotation(direction);
		animation.restart();
		animation.stopAt(-1);	
	}
	
	public boolean isValidMovement(int x, int y) {
		return ((x >= 0) && 
				(y >= 0) &&
			    (x < (Map.WIDTH*Map.TILE_RENDER_SIZE)) && 
			    (y < (Map.HEIGHT*Map.TILE_RENDER_SIZE))
			    //(!((y==0) && (x % 2 == 0))
			    		);
	}
	
	public boolean canMove() {
		return (Math.abs(pos.getX()-drawPos.getX()) < Map.TILE_RENDER_SIZE &&
				Math.abs(pos.getY()-drawPos.getY()) < Map.TILE_RENDER_SIZE);
	}
	
	public void update() {
		animation.draw();
	}
	
	public Position getDrawPosition() {
		return drawPos;
	}
	
	public Position getBlockPosition() {
		return pos;
	}
	
	public void resetPos() {
		pos = new Position(Map.spawnpoint.getX(),Map.spawnpoint.getY());
		drawPos = new Position(pos.getX(),pos.getY());
	}
	
	public void kill() {
		resetPos();
	}
	
	public boolean isMoving() {
		return moving;
	}
	
	public boolean isOnTrunk() {
		return onTrunk;
	}
	
	public void setOnTrunk(boolean bool) {
		onTrunk = bool;
	}

	public Position getPosition() {
		return pos;
	}
	
}
