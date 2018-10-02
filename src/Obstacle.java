import org.newdawn.slick.Image;

public class Obstacle extends Thread{
	public static int HEIGHT = 81;
	public static int WIDTH = 81;
	Image image;
	Position pos;
	int length;
	int speed;
	
	public Obstacle(Image image, int length, Position pos, int speed) {
		this.image = image;
		this.length = length;
		this.pos = pos;
		this.speed = speed;
	}
	
	public synchronized void draw() {
		int targetX = (pos.getX()-length)*(WIDTH);
		
		image.draw(targetX,pos.getY()*(HEIGHT-2));
	}
	
	public void move() {
		pos.setX(pos.getX()+1);
		
		if((pos.getX()-length) > Map.WIDTH) {
			System.out.println("Destroy");
			pos.setX(0);
		}
			
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			move();
		}
		

	}
	
}
