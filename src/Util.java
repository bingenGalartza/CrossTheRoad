import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Util {

	
	public static Image getImage(String path) {
	    Image image = null;
		try {
			image = new Image(path);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	    return image;
	}
	
	public static List<Image> getImages(List<String> paths){
		List<Image> list = new ArrayList<>();
		for(String p : paths)
			list.add(getImage(p));
		return list;
	}
	
	public static int getValidPosition(int x) {
		int validX;
		int xMinus = x;
		while(xMinus % Map.TILE_RENDER_SIZE != 0) {
			xMinus -= 1;
		}
		int xPlus = x;
		while(xPlus % Map.TILE_RENDER_SIZE != 0) {
			xPlus += 1;
		}
		if((x - xMinus) < (xPlus - x))
			validX = xMinus;
		else
			validX = xPlus;	
		return validX;
	}
}
