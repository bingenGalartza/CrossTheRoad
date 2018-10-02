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
}
