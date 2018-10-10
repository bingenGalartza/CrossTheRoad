

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Menu implements ActionListener{
	FileManager fileManager;
	ArrayList<Score> scoreboard;
	ImageIcon fondo;
	JFrame frame;
	public static int WINDOW_WIDTH = 869;
	public static int WINDOW_HEIGHT = 711;
	
	public Menu() {
		fondo=new ImageIcon("res/menu_background.png");
		frame = new JFrame("Menu");
		frame.setLocation(300,10);
		frame.setSize(600,700);
		frame.setContentPane(crearPanelVentana());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		fileManager=new FileManager();
		scoreboard=fileManager.getScoreboard();
		
	}
	private Container crearPanelVentana() {
		MiPanel panel=new MiPanel(new BorderLayout(), fondo.getImage());
		panel.add(crearPanelTitulo(), BorderLayout.NORTH);
		panel.add(crearPanelCentro(), BorderLayout.CENTER);
		return panel;
	}
	private Component crearPanelTitulo() {
		JPanel panel=new JPanel(new BorderLayout());
		JLabel label=new JLabel();
		label.setIcon(new ImageIcon("res/logo2.png"));

		label.setHorizontalAlignment(JLabel.CENTER);
		panel.setBorder(BorderFactory.createEmptyBorder(0,25,0,0));
		panel.add(label);
		
		panel.setOpaque(false);
		return panel;
	}
	private Component crearPanelCentro() {
		JPanel panel=new JPanel(new GridLayout(5,1, 20, 20));
		
		panel.setBorder(BorderFactory.createEmptyBorder(0,80,20,80));
		panel.setOpaque(false);
		panel.add(crearPanelBotones());
		panel.add(crearBoton("HOW TO PLAY"));
		panel.add(crearBoton("SCOREBOARD"));
		panel.add(crearBoton("SETINGS"));
		panel.add(crearBoton("CREDITS"));
		return panel;
	}
	private Component crearBoton(String string) {
		JPanel panel=new JPanel(new BorderLayout());
		JButton b=new JButton(string);
		
		b.setBackground(Color.green);
		b.addActionListener(this);
		b.setActionCommand(string);
		
		panel.setBorder(BorderFactory.createEmptyBorder(0,110,0,110));
		panel.setOpaque(false);
		panel.add(b);
		return panel;
	}
	private Component crearPanelBotones() {
		JPanel panel=new JPanel(new GridLayout(1,2,40,0));
		JButton bOneP=new JButton("ONE PLAYER");
		JButton bTwoP=new JButton("TWO PLAYER");
		
		bOneP.setBackground(Color.green);
		bTwoP.setBackground(Color.green);
		bOneP.addActionListener(this);
		bOneP.setActionCommand("ONE PLAYER");
		bTwoP.addActionListener(this);
		bTwoP.setActionCommand("TWO PLAYER");
		
		panel.setOpaque(false);
		panel.add(bOneP);
		panel.add(bTwoP);
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "ONE PLAYER": 
			frame.dispose();
			try
			{
				AppGameContainer appgc;
				appgc = new AppGameContainer(new Game("Cross the street"));
				appgc.setDisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT, false);
				appgc.setTargetFrameRate(250);
				appgc.start();
			}
			catch (SlickException ex)
			{
				Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
			}
			break;
		case "TWO PLAYER": break;
		case "HOW TO PLAY": break;
		case "SCOREBOARD": break;
		case "SETTINGS": break;
		case "CREDITS": break;
		}
		
	}
	
	public void updateScoreboard(Score score) {
		scoreboard.add(score);
		Collections.sort(scoreboard, new Comparator<Score>() {
		    @Override
		    public int compare(Score s1, Score s2) {
		    	int p1=s1.getPunctuation();
		    	int p2=s2.getPunctuation();
		        return Integer.compare(p2, p1);
		    }
		});
		if (scoreboard.size()>10) {
			scoreboard.remove(10);
		}
		fileManager.writeScoreboard(scoreboard);
	}
	
	public static void main(String[] args) {
		Menu menu=new Menu();

	}


}
