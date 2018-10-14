

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

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
		panel.add(crearBoton("PLAY"));
		panel.add(crearBoton("HOW TO PLAY"));
		panel.add(crearBoton("SCOREBOARD"));
		panel.add(crearBoton("SETTINGS"));
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

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "PLAY": 
			frame.dispose();
			try
			{
				AppGameContainer appgc;
				appgc = new AppGameContainer(new Game("CROSS THE ROAD"));
				appgc.setDisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT, false);
				appgc.setTargetFrameRate(250);
				appgc.start();
			}
			catch (SlickException ex)
			{
				Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
			}
			break;
		case "HOW TO PLAY":
		  frame.setContentPane(createPanelHowToPlay());
		  frame.repaint();
		  frame.revalidate();
		  break;
		case "SCOREBOARD": 
			frame.setContentPane(crearPanelScoreboard());
			frame.repaint();
			frame.revalidate();
			break;
		case "SETTINGS": 
			frame.setContentPane(crearPanelSettings());
			frame.repaint();
			frame.revalidate();
			break;
		case "CREDITS": 
			frame.setContentPane(crearPanelCredits());
			frame.repaint();
			frame.revalidate();
			break;
		case "MENU":
			frame.setContentPane(crearPanelVentana());
			frame.repaint();
			frame.revalidate();
		}
		
	}
	
/*<<<<<<< HEAD*/
	private Container crearPanelScoreboard() {
		MiPanel panel=new MiPanel(new BorderLayout(), fondo.getImage());
		JButton b=new JButton("MENU");
		JPanel panelBoton=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		/*JScrollPane panelScore = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);*/
		JPanel panelScore=new JPanel(new BorderLayout());
		JList<Score> listaDatos = new JList<>(scoreboard.toArray(new Score[0]));
		listaDatos.setOpaque(false);
		listaDatos.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listaDatos.setCellRenderer(new Renderer());
		panelScore.add(listaDatos);
		panelScore.setOpaque(false);
		panelScore.setBorder(BorderFactory.createEmptyBorder(100,50,100,50));

		b.addActionListener(this);
		b.setActionCommand("MENU");

		panelBoton.setOpaque(false);
		panelBoton.add(b);
		
		panel.add(panelScore, BorderLayout.CENTER);
		panel.add(panelBoton, BorderLayout.SOUTH);
		return panel;
	}
	private Container crearPanelSettings() {
		MiPanel panel=new MiPanel(new BorderLayout(), fondo.getImage());
		JButton b=new JButton("MENU");
		JButton remove=new JButton("REMOVE SOCOREBOARD");
		JPanel panelCentral=new JPanel(new BorderLayout());
		JPanel panelBoton=new JPanel(new FlowLayout(FlowLayout.RIGHT));

		b.addActionListener(this);
		b.setActionCommand("MENU");
		remove.addActionListener(this);
		remove.setActionCommand("REMOVE");
		remove.setFont(new Font ("arial", Font.BOLD, 18));
		panelCentral.setBorder(BorderFactory.createEmptyBorder(250,150,250,150));
		panelCentral.setOpaque(false);
		panelCentral.add(remove);
		panelBoton.setOpaque(false);
		panelBoton.add(b);
		
		panel.add(panelCentral, BorderLayout.CENTER);
		panel.add(panelBoton, BorderLayout.SOUTH);
		return panel;
	}
	private Container crearPanelCredits() {
		MiPanel panelPrincipal=new MiPanel(new BorderLayout(), fondo.getImage());
		JButton b=new JButton("MENU");
		JPanel panel=new JPanel(new GridLayout(8,1, 10,0));
		JPanel panelBoton=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel l1=new JLabel("SCRUM MASTER");
		JLabel l2=new JLabel("JULEN URIBARREN");
		JLabel l3=new JLabel("DEVELOPMENT TEAM");
		JLabel l4=new JLabel("IMANOL BADIOLA");
		JLabel l5=new JLabel("BINGEN GALARTZA");
		JLabel l6=new JLabel("IÑIGO ARENAZA");
		JLabel l7=new JLabel("JOSEBA CARNICERO");
		
		l1.setForeground(Color.WHITE);
		l2.setForeground(Color.WHITE);
		l3.setForeground(Color.WHITE);
		l4.setForeground(Color.WHITE);
		l5.setForeground(Color.WHITE);
		l6.setForeground(Color.WHITE);
		l7.setForeground(Color.WHITE);
		
		l1.setHorizontalAlignment(JLabel.CENTER);
		l2.setHorizontalAlignment(JLabel.CENTER);
		l3.setHorizontalAlignment(JLabel.CENTER);
		l4.setHorizontalAlignment(JLabel.CENTER);
		l5.setHorizontalAlignment(JLabel.CENTER);
		l6.setHorizontalAlignment(JLabel.CENTER);
		l7.setHorizontalAlignment(JLabel.CENTER);
		
		l1.setFont(new Font ("arial", Font.BOLD, 36));
		l2.setFont(new Font ("arial", Font.BOLD, 18));
		l3.setFont(new Font ("arial", Font.BOLD, 36));
		l4.setFont(new Font ("arial", Font.BOLD, 18));
		l5.setFont(new Font ("arial", Font.BOLD, 18));
		l6.setFont(new Font ("arial", Font.BOLD, 18));
		l7.setFont(new Font ("arial", Font.BOLD, 18));
		
		b.addActionListener(this);
		b.setActionCommand("MENU");
		panelBoton.setOpaque(false);
		panelBoton.add(b);
		
		panel.setBorder(BorderFactory.createEmptyBorder(50,0,0,0));
		panel.add(l1);
		panel.add(l2);
		panel.add(l3);
		panel.add(l4);
		panel.add(l5);
		panel.add(l6);
		panel.add(l7);
		
		panel.setOpaque(false);
		
		panelPrincipal.add(panel, BorderLayout.CENTER);
		panelPrincipal.add(panelBoton, BorderLayout.SOUTH);
		return panelPrincipal;
	}
	private Container createPanelHowToPlay() {
		MiPanel panel=new MiPanel(new BorderLayout(), fondo.getImage());
		JLabel image=new JLabel(new ImageIcon("res/keyboard.png"));
		JLabel text=new JLabel("CONTROLS");
		JButton b=new JButton("MENU");
		JPanel panelBoton=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		panel.setBorder(BorderFactory.createEmptyBorder(80,0,0,0));
		text.setForeground(Color.WHITE);
		text.setFont(new Font ("arial", Font.BOLD, 72));
		text.setHorizontalAlignment(JLabel.CENTER);
		image.setOpaque(false);
		b.addActionListener(this);
		b.setActionCommand("MENU");
		panelBoton.setOpaque(false);
		panelBoton.add(b);
		
		panel.add(text, BorderLayout.NORTH);
		panel.add(image, BorderLayout.CENTER);
		panel.add(panelBoton, BorderLayout.SOUTH);
		return panel;
	}
	
/*=======
>>>>>>> 3d28d3dd8d2502827a277849cc3273c36c0f02af*/
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
/*<<<<<<< HEAD
=======
	
>>>>>>> 3d28d3dd8d2502827a277849cc3273c36c0f02af*/
	public static void main(String[] args) {
		Menu menu=new Menu();

	}


}
