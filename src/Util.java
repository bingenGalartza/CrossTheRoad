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
			// TODO Auto-generated catch block
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
}
