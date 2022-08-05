package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import data.GameDataLookup;
import data.GameState;

public class SummaryPanel extends JFrame{
	private final Dimension SIZE = new Dimension(510, 310);
	
	GameState game;
	
	SummaryPane pane;
	
	public SummaryPanel(GameState gamestate) {
		game = gamestate;
		pane = new SummaryPane(this, gamestate);
		
		setUndecorated(true);
		setTitle("CHOOSE THIS WINDOW FOR CAPTURE");
		add(pane);
		pack();
		setSize(SIZE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	public void update() { pane.repaint(); }
	
	public int getWidth() { return SIZE.width; }
	public int getHeight() { return SIZE.height; }
}
class SummaryPane extends JPanel {
	
	SummaryPanel frame;
	GameState game;
	
	public SummaryPane(SummaryPanel parent, GameState gamestate) { frame = parent; game = gamestate; }
	
	protected void paintComponent(Graphics g) {		
		super.paintComponent(g);
		
		// Game/Graphics States not loaded yet? FIXME
		int[] data = game.getSummaryData(); 
		
		
		g.clearRect(0, 0, frame.getWidth(), frame.getHeight());
		
		g.setFont(GraphicsDriver.regular);
		
		//Background
			g.setColor(new Color(186, 142, 74));
			g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
		
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
