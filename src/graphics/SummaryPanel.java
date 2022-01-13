package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import data.GameDataLookup;
import data.GameState;

public class SummaryPanel extends JFrame{
	
	GraphicsState graphics;
	GameState game;
	
	private SummaryPane pane = new SummaryPane();
	
	public void initialize(GraphicsState graphicsstate, GameState gamestate) {
		graphics = graphicsstate;
		game = gamestate;
		
		setUndecorated(true);
		setTitle("CHOOSE THIS WINDOW FOR CAPTURE");
		add(pane);
		pack();
		setSize(WIDTH,HEIGHT);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		pane.initialize(graphicsstate, gamestate);
		
	}
	
	public void update() { repaint(); }
}
class SummaryPane extends JPanel {
	
	GameState game;
	GraphicsState graphics;
	
	public void initialize(GraphicsState graphicsstate, GameState gamestate) { graphics = graphicsstate; game = gamestate; }
	
	protected void paintComponent(Graphics g) {		
		super.paintComponent(g);
		
		int[] data = game.getSummaryData();
		
		Dimension window = graphics.getSummaryPanelDimensions();
		
		g.clearRect(0, 0, window.width, window.height);
		
		g.setFont(GraphicsDriver.regular);
		
		//Background
			g.setColor(new Color(186, 142, 74));
			g.fillRect(0, 0, window.width, window.height); //FIXME: ???
		
		//Text
			g.setColor(Color.BLACK);
			
			int initialY = 30;
			int verticalSpacer = 30;
			
			g.drawString("Emblems: "+data[0]+" / "+GameDataLookup.MAX_EMBLEMS, 30, initialY);
			g.drawString("Levels: "+data[1]+" / "+GameDataLookup.MAX_LEVELS, 30, initialY+verticalSpacer);
			g.drawString("Coins: "+data[2]+" / "+GameDataLookup.MAX_CHICK_COINS, 30, initialY+(2*verticalSpacer));
			g.drawString("Eggs: "+data[3]+" / "+GameDataLookup.MAX_EGGS, 30, initialY+(3*verticalSpacer));
			g.drawString("S Ranks: "+data[4]+" / "+GameDataLookup.MAX_SRANKS, 30, initialY+(4*verticalSpacer));
	}	
}
