import java.awt.Font;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.TextField;

public class Counters {
	long initialTime;
	Frog frog;
	TrueTypeFont font;
	
	public Counters(Frog frog) {
		initialTime=System.currentTimeMillis();
		font=new TrueTypeFont(new Font("Arial",Font.BOLD,16),true);
		this.frog=frog;
	}
	
	public long getTime() {
		long time = (System.currentTimeMillis() - this.initialTime) /1000;
		frog.setTime(time);
		return time;
	}
	
	public void draw() {
		font.drawString(780, 5, "Time: "+getTime());
		font.drawString(780, 20, "Deaths: "+frog.getDeaths());
		font.drawString(780, 35, "Bonuses: "+frog.getBonuses());
	}
}
