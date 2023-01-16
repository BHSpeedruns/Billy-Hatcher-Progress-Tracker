package graphics;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.GameDataLookup;
import main.ProgressTracker;

public class SummaryPanel {
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
	
	JLabel emblemCount = new JLabel();
	JLabel levelCount = new JLabel();
	JLabel coinCount = new JLabel();
	JLabel eggCount = new JLabel();
	JLabel sRankCount = new JLabel();
	
	public void initialize() {
		initializeFrame();
		initializeComponents();
		frame.setVisible(true);
	}
	
	private void initializeFrame() {
		frame.setUndecorated(true);
		frame.setTitle("CHOOSE THIS WINDOW FOR CAPTURE");
		frame.add(panel);
		frame.pack();
		frame.setSize(new Dimension(510,310));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(layout);
	}
	
	private void initializeComponents() {		
		update();
		
		panel.add(emblemCount);
		panel.add(levelCount);
		panel.add(coinCount);
		panel.add(eggCount);
		panel.add(sRankCount);
	}
	
	public void update() {
		emblemCount.setText("Emblem Count: "+ProgressTracker.gamestate.getNumCourageEmblems()+" / "+GameDataLookup.MAX_EMBLEMS);
		levelCount.setText("Levels: "+ProgressTracker.gamestate.getNumLevelsCompleted()+" / "+GameDataLookup.MAX_LEVELS);
		coinCount.setText("Coins: "+ProgressTracker.gamestate.getNumChickCoins()+" / "+GameDataLookup.MAX_CHICK_COINS);
		eggCount.setText("Eggs: "+ProgressTracker.gamestate.getNumEggsHatched()+" / "+GameDataLookup.MAX_EGGS);
		sRankCount.setText("S Ranks: "+ProgressTracker.gamestate.getNumSRanks()+" / "+GameDataLookup.MAX_SRANKS);
	}
}
