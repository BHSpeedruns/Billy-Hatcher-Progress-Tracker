package graphics;

import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import data.GameDataLookup;
import data.Rank;
import main.ProgressTracker;
import util.Utils;

public class SummaryPanel {
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	
	JLabel emblemIcon = new JLabel();
	JLabel emblemCount = new JLabel();
	
	JLabel levelCount = new JLabel();
	JProgressBar levelProgress = new JProgressBar(0, GameDataLookup.MAX_LEVELS);
	JLabel levelEmblem = new JLabel();
	
	JLabel coinCount = new JLabel();
	JProgressBar coinProgress = new JProgressBar(0, GameDataLookup.MAX_CHICK_COINS);
	JLabel coinEmblem = new JLabel();
	
	JLabel eggCount = new JLabel();
	JProgressBar eggProgress = new JProgressBar(0, GameDataLookup.MAX_EGGS);
	JLabel eggEmblem = new JLabel();
	
	JLabel sRankCount = new JLabel();
	JProgressBar sRankProgress = new JProgressBar(0, GameDataLookup.MAX_SRANKS);
	JLabel sRankEmblem = new JLabel();
	
	JLabel eggMasterText = new JLabel();
	
	public void initialize() {
		initializeFrame();
		initializeComponents();
		frame.setVisible(true);
	}
	
	private void initializeFrame() {
		frame.setUndecorated(true);
		frame.setTitle("CHOOSE THIS WINDOW FOR CAPTURE");
		panel.setLayout(null);
		frame.add(panel);
		frame.pack();
		frame.setSize(new Dimension(510,310));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initializeComponents() {		
		update();

		levelCount.setLocation(10, 10);
		levelCount.setSize(100,16);
		levelProgress.setLocation(120,10);
		levelProgress.setSize(360,16);
		levelEmblem.setLocation(485,10);
		levelEmblem.setSize(16,16);
		panel.add(levelCount);
		panel.add(levelProgress);
		panel.add(levelEmblem);
		
		coinCount.setLocation(10, 30);
		coinCount.setSize(100,16);
		coinProgress.setLocation(120,30);
		coinProgress.setSize(360,16);
		coinEmblem.setLocation(485,30);
		coinEmblem.setSize(16,16);
		panel.add(coinCount);
		panel.add(coinProgress);
		panel.add(coinEmblem);
		
		eggCount.setLocation(10, 50);
		eggCount.setSize(100,16);
		eggProgress.setLocation(120,50);
		eggProgress.setSize(360,16);
		eggEmblem.setLocation(485,50);
		eggEmblem.setSize(16,16);
		panel.add(eggCount);
		panel.add(eggProgress);
		panel.add(eggEmblem);
		
		sRankCount.setLocation(10, 70);
		sRankCount.setSize(100,16);
		sRankProgress.setLocation(120,70);
		sRankProgress.setSize(360,16);
		sRankEmblem.setLocation(485,70);
		sRankEmblem.setSize(16,16);
		panel.add(sRankCount);
		panel.add(sRankProgress);
		panel.add(sRankEmblem);
		
		emblemCount.setLocation(10,90);
		emblemCount.setSize(150,16);
		panel.add(emblemCount);
		emblemIcon.setLocation(180, 90);
		emblemIcon.setSize(16,16);
		panel.add(emblemIcon);
		
		eggMasterText.setLocation(30, 110);
		eggMasterText.setSize(300,64);
		panel.add(eggMasterText);
	}
	
	public void update() {
		levelCount.setText("Levels: "+ProgressTracker.gamestate.getNumLevelsCompleted()+" / "+GameDataLookup.MAX_LEVELS);
		levelProgress.setValue(ProgressTracker.gamestate.getNumLevelsCompleted());
		if(ProgressTracker.gamestate.getNumLevelsCompleted() == GameDataLookup.MAX_LEVELS) {
			levelEmblem.setIcon(Utils.scaleIcon(GraphicsDriver.emblem,16,16));
		}else { levelEmblem.setIcon(null); }	
		
		coinCount.setText("Coins: "+ProgressTracker.gamestate.getNumChickCoins()+" / "+GameDataLookup.MAX_CHICK_COINS);
		coinProgress.setValue(ProgressTracker.gamestate.getNumChickCoins());
		if(ProgressTracker.gamestate.getNumChickCoins() == GameDataLookup.MAX_CHICK_COINS) {
			coinEmblem.setIcon(Utils.scaleIcon(GraphicsDriver.emblem,16,16));
		}else { coinEmblem.setIcon(null); }	
		
		eggCount.setText("Eggs: "+ProgressTracker.gamestate.getNumEggsHatched()+" / "+GameDataLookup.MAX_EGGS);
		eggProgress.setValue(ProgressTracker.gamestate.getNumEggsHatched());
		if(ProgressTracker.gamestate.getNumEggsHatched() == GameDataLookup.MAX_EGGS) {
			eggEmblem.setIcon(Utils.scaleIcon(GraphicsDriver.emblem,16,16));
		}else { eggEmblem.setIcon(null); }	
		
		sRankCount.setText("S Ranks: "+ProgressTracker.gamestate.getNumSRanks()+" / "+GameDataLookup.MAX_SRANKS);
		sRankProgress.setValue(ProgressTracker.gamestate.getNumSRanks());
		if(ProgressTracker.gamestate.getNumSRanks() == GameDataLookup.MAX_SRANKS) {
			sRankEmblem.setIcon(Utils.scaleIcon(GraphicsDriver.emblem,16,16));
		}else { sRankEmblem.setIcon(null); }
		
		emblemIcon.setIcon(Utils.scaleIcon(GraphicsDriver.emblem,16,16));
		emblemCount.setText("Emblem Count: "+ProgressTracker.gamestate.getNumCourageEmblems()+" / "+GameDataLookup.MAX_EMBLEMS);
		if(ProgressTracker.gamestate.getNumCourageEmblems() == GameDataLookup.MAX_EMBLEMS) {
			eggMasterText.setText("Egg Master");
		}else { eggMasterText.setText(""); }
	}
}
