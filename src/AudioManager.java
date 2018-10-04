import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioManager {
	public static String CRUSHED_PATH = "/res/crushed.wav";
	public static String DROWNED_PATH = "/res/drowned.wav";
	public static String FROG_PATH = "/res/frog.wav";
	public static String GAME_PATH = "/res/game.wav";
	Music gameMusic;
	Sound crushed,drowned,frog;
	
	public AudioManager() {
		try {
			gameMusic = new Music(GAME_PATH);
			crushed = new Sound(CRUSHED_PATH);
			drowned= new Sound(DROWNED_PATH);
			frog = new Sound(FROG_PATH);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void playMusic() {
		gameMusic.loop();
	}
	
	public void playFrog() {
		frog.play();
	}
	
	public void playCrushed() {
		crushed.play();
	}
	
	public void playDrowned() {
		drowned.play();
	}
	

}
