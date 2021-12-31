package graphics;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import data.GameDataLookup;

public class SummaryPanel extends JFrame{
	
	private final int WIDTH = 510, HEIGHT = 310;
	
	private SummaryPane pane = new SummaryPane();
	
	public void initialize() {
		setUndecorated(true);
		setTitle("CHOOSE THIS WINDOW FOR CAPTURE");
		add(pane);
		pack();
		setSize(WIDTH,HEIGHT);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void update(int[] data) { 
		pane.setData(data);
		repaint(); 
	}
}
class SummaryPane extends JPanel {
	
	private int[] data = {0,0,0,0,0};
	
	protected void paintComponent(Graphics g) {		
		super.paintComponent(g);
		g.clearRect(0, 0, WIDTH, HEIGHT);
		
		g.setFont(GraphicsDriver.regular);
		
		//Background
			g.setColor(new Color(186, 142, 74));
			g.fillRect(0, 0, WIDTH, HEIGHT); //FIXME: ???
		
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
	
	public void setData(int[] stateData) { data = stateData.clone(); }
	
	
}
