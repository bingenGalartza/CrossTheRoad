import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;



public class Map {
	final static String PATH = "/res/fondoa.png";
	final static String FROGATHOME = "/res/frogLillyPad.png";
	final static int HEIGHT = 9;
	final static int WIDTH = 11;
	final static int TILE_SIZE = 81;
	final static int TILE_RENDER_SIZE = TILE_SIZE - 2;
	public static enum Tile {GRASS,WATER,ROAD,ROAD_UP,ROAD_DOWN,END,START,HOME};
	public static Tile[] TILES = {Tile.END,Tile.WATER,Tile.WATER,Tile.GRASS,Tile.ROAD_UP
			                     ,Tile.ROAD_DOWN,Tile.GRASS,Tile.WATER,Tile.START};
	public static Position spawnpoint = new Position(4*TILE_SIZE,8*TILE_SIZE);
	SpriteSheet sprite;
	HashMap<Integer,Boolean> occupedPads;
	Image frogAtHome;
	public Map () {
		Image image = Util.getImage(PATH);
		frogAtHome = Util.getImage(FROGATHOME);
		sprite = new SpriteSheet(image,TILE_SIZE,TILE_SIZE);
		initHashMap();
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
		int j=1;
		Image end = sprite.getSprite(Tile.END.ordinal(), 0);
		Image home = sprite.getSprite(Tile.HOME.ordinal(), 0);
		for(int column = 0; column < WIDTH ; column++) {
			if((column % 2) == 0)
				end.draw(column*TILE_RENDER_SIZE,0);
			else {
				home.draw(column*TILE_RENDER_SIZE,0);
				if(occupedPads.get(column-(j++)))
					frogAtHome.draw(column*TILE_RENDER_SIZE,0);
					
			}
		}
	}
	public void initHashMap() {
		occupedPads= new HashMap<>();
		
		for (int i=0; i<5; i++) {
			occupedPads.put(i, true);
		}
	}
	public List<Position> getHomeBlocks(){
		List<Position> homes= new ArrayList<>();
		for(int i=0;i<5;i++) {
			Position p=new Position((i*2+1)*TILE_SIZE,0*TILE_SIZE);
			homes.add(p);
		}	
		return homes;
	}

	
	
}
