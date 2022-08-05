package graphics;

import java.awt.Dimension;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.imageio.*;

import data.GameDataLookup;
import data.GameState;

enum WindowState { EggGallery, LevelSelect }

public class Dashboard extends JFrame {
	private final Dimension SIZE = new Dimension(1020, 620);
	
	private WindowState screen;
	private static GameState game;
	
	EggGallery eggGallery;
	LevelSelect levelSelect;
	
	public static BufferedImage[] smallEggPngs = new BufferedImage[72];
	
	public Dashboard(GameState gm) {
		game = gm;
		
		// Initialize Panes
			eggGallery = new EggGallery(this,game);
			levelSelect = new LevelSelect(this,game);
		
		// Initialize Data
			for(int i = 1; i <= GameDataLookup.MAX_EGGS; i++) {
				try { smallEggPngs[i-1] = ImageIO.read(new File("assets/Egg PNGs/Numbered/"+i+".png")); } 
				catch(Exception e) { System.out.println("Failed to load numbered egg pngs"); }
			}
						
		// Initialize Frame
			setTitle("Billy Hatcher 100% Progress Tracker");
			pack();
			add(eggGallery);
			add(levelSelect);
			setSize(SIZE);
			setResizable(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLocationRelativeTo(null);
			setVisible(true);
	}
	
	public void update() {
		getContentPane().repaint();
		//if(screen == WindowState.EggGallery) 		{ eggGallery.revalidate(); eggGallery.repaint();  }
		//else if(screen == WindowState.LevelSelect)  { levelSelect.revalidate(); levelSelect.repaint(); }
	}
	
	public int getWidth()  { return SIZE.width;  }
	public int getHeight() { return SIZE.height; }
	
	public void setScreen(WindowState state) { 
		screen = state; 
	
		if(screen == WindowState.EggGallery) {
			levelSelect.setVisible(false);
			eggGallery.setVisible(true);
		}
		else if(screen == WindowState.LevelSelect) {
			eggGallery.setVisible(false);
			levelSelect.setVisible(true);
		}
		
		update();
	}
}
