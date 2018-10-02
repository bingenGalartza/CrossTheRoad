
public class Position {
	int x;
	int y;
	int rotation;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
		rotation = 0;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getRotation() {
		return rotation;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setRotation(int direction) {
		rotation = direction;
		
	}
	
	
	
	
}
