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
	
	
	public Frog(Position pos) {
		this.pos = pos;
		initAnimation();
		drawPos = new Position(pos.getX()*(WIDTH-2),pos.getY()*(HEIGHT-2));
	}
	
	public void initAnimation() {
		Image image = Util.getImage(PATH);
		sprite = new SpriteSheet(image,WIDTH,HEIGHT);
	    animation = new Animation(sprite,0,0,5,0,true,100,true);
	}
	
	public void draw() {
		int drawX = drawPos.getX();
		int drawY = drawPos.getY();
		int targetX = pos.getX() * (WIDTH-2);
		int targetY = pos.getY() * (HEIGHT-2);

		if(drawX != targetX) {
			drawPos.setX(drawX + ((drawX > targetX)?-1:1));
		}
		else if(drawY != targetY) {
			drawPos.setY(drawY + ((drawY > targetY)?-1:1));
		}else {
			animation.stopAt(0);
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
			y-=1;
			break;
		case 1:
			x+=1;
			break;
		case 2:
			y+=1;
			break;
		case 3:
			x-=1;
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
			    (x < Map.WIDTH) && 
			    (y < Map.HEIGHT));
	}
	
	public boolean canMove() {
		return (Math.abs((pos.getX()*81)-drawPos.getX()) < 81);
	}
	
	public void update() {
		animation.draw();
	}
	
}
