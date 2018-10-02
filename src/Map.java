import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;



public class Map {
	final static String PATH = "/res/fondoa.png";
	final static int HEIGHT = 9;
	final static int WIDTH = 11;
	final static int TILE_SIZE = 81;
	final static int TILE_RENDER_SIZE = TILE_SIZE - 2;
	public static enum Tile {GRASS,WATER,ROAD,ROAD_UP,ROAD_DOWN,END,START,HOME};
	public static Tile[] TILES = {Tile.END,Tile.WATER,Tile.WATER,Tile.GRASS,Tile.ROAD_UP
			                     ,Tile.ROAD_DOWN,Tile.GRASS,Tile.WATER,Tile.START};
	
	SpriteSheet sprite;
	
	public Map () {
		Image image = Util.getImage(PATH);
		sprite = new SpriteSheet(image,TILE_SIZE,TILE_SIZE);
	}
	
	public SpriteSheet getSprite() {
		return sprite;
	}
	
	public void draw() {
		drawFirstRow();
		for(int row = 1; row < HEIGHT;row++) {
			Image img = sprite.getSprite(TILES[row].ordinal(), 0);
			for(int column = 0; column < WIDTH ; column++) {
				img.draw(column*TILE_RENDER_SIZE,row*TILE_RENDER_SIZE);
			}
		}
	}
	
	public void drawFirstRow() {
		Image end = sprite.getSprite(Tile.END.ordinal(), 0);
		Image home = sprite.getSprite(Tile.HOME.ordinal(), 0);
		for(int column = 0; column < WIDTH ; column++) {
			if((column % 2) == 0)
				end.draw(column*TILE_RENDER_SIZE,0);
			else
				home.draw(column*TILE_RENDER_SIZE,0);
		}
	}

	
	
}
