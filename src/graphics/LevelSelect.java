package graphics;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.GameDataLookup;
import data.Level;
import data.Rank;
import main.ProgressTracker;
import util.Utils;

public class LevelSelect {
	private JPanel panel = new JPanel();
	public JPanel getPanel() {return panel;}
	
	int currentWorld = -1;
	int currentMission = -1;
	int currentLevel = -1;

	JLabel currentWorldIcon;
	JLabel currentWorldText;
	JLabel currentLevelText;
	JLabel currentLevelTitleText;
	JButton[] eggsInLevel = new JButton[15]; // The level with the most distinct eggs has 15 eggs
	JComboBox<Rank> rankSelectDropdown;
	JButton[] coinSelectButtons = new JButton[5];
	JButton missionMapButton;
	JButton[] missionSelectButtons = new JButton[8];
	JButton eggGalleryButton;
	JButton[] worldSelectButtons = new JButton[7];
	
	public void initialize() {
		currentWorldIcon = new JLabel();
		panel.add(currentWorldIcon);
		
		currentWorldText = new JLabel();
		panel.add(currentWorldText);
		
		currentLevelText = new JLabel();
		panel.add(currentLevelText);
		
		currentLevelTitleText = new JLabel();
		panel.add(currentLevelTitleText);
		
		for(int i = 0; i < 15; i++) {
			final int j = i; // Use a final copy for listener
			eggsInLevel[i] = new JButton();
			eggsInLevel[i].addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {eggButtonSelected(j);}});
			panel.add(eggsInLevel[i]);
			eggsInLevel[i].setEnabled(false);
			eggsInLevel[i].setVisible(false);
		}
		
		rankSelectDropdown = new JComboBox<Rank>(new Rank[] {Rank.NORANK, Rank.D, Rank.C, Rank.B, Rank.A, Rank.S});
		rankSelectDropdown.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {missionRankSelected();}});
		panel.add(rankSelectDropdown); //TODO: probably need a custom renderer?
		rankSelectDropdown.setEnabled(false);
		rankSelectDropdown.setVisible(false);
		
		for(int i = 0; i < 5; i++) {
			final int j = i; // Use a final copy for listener
			coinSelectButtons[i] = new JButton();
			coinSelectButtons[i].setPreferredSize(new Dimension(64,64));
			coinSelectButtons[i].addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {coinButtonSelected(j);}});
			panel.add(coinSelectButtons[i]);
			coinSelectButtons[i].setEnabled(false);
			coinSelectButtons[i].setVisible(false);
		}
		missionMapButton = new JButton("Open Mission Map");
		missionMapButton.setEnabled(false);
		missionMapButton.setVisible(false);
		panel.add(missionMapButton);
		missionMapButton.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {GraphicsDriver.openMissionMap(currentLevel);}});
		
		for(int i = 0; i < 8; i++) {
			final int j = i; // Use a final copy for listener
			missionSelectButtons[i] = new JButton("Mission "+(i+1));
			missionSelectButtons[i].addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {missionButtonSelected(j);}});
			panel.add(missionSelectButtons[i]);
		}
		eggGalleryButton = new JButton("Egg Gallery");
		panel.add(eggGalleryButton);
		eggGalleryButton.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {clear(); Dashboard.switchPane();}});
		
		for(int i = 0; i < 7; i++) {
			final int j = i; // Use a final copy for listener
			worldSelectButtons[i] = new JButton(GameDataLookup.getWorldName(i));
			worldSelectButtons[i].addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {worldButtonSelected(j);}});
			panel.add(worldSelectButtons[i]);
		}
	}

	public void update() {
		// FIXME: picking level counts as completing it?
		
		// Update World Icon, World Text & Level Text
		currentWorldIcon.setIcon(GraphicsDriver.worldIcons[currentWorld]);
		currentWorldText.setText("World: "+(currentWorld + 1));
		currentLevelText.setText("Mission: "+(currentMission + 1));
		
		if(currentWorld == -1 || currentMission == -1) { return; }
		
		// Bookkeeping Current Level
		boolean swappedLevels = currentLevel != currentWorld * 8 + currentMission;
		currentLevel = currentWorld * 8 + currentMission;
		Level thisLevel = ProgressTracker.gamestate.getLevel(currentLevel);
			
		if(swappedLevels) {
			// FIXME: I believe this fires the actionlistener.
			
			rankSelectDropdown.setSelectedItem(thisLevel.getRank());
			
			// Update World Banner
			if(swappedLevels) {
				currentWorldIcon.setEnabled(true);
				currentWorldIcon.setIcon(GraphicsDriver.worldIcons[currentWorld]);
			}
			
			// Update Level Title
			if(swappedLevels) {
				currentLevelTitleText.setEnabled(true);
				currentLevelTitleText.setText(GameDataLookup.getFullLevelName(currentLevel));
			}
		}
		
		// Update Chick Coins
		String[] chickCoinNotes = GameDataLookup.getChickCoinNotes(currentLevel);
		boolean[] coinsCollected = thisLevel.getChickCoins();
		for(int i = 0; i < coinsCollected.length; i++) {
			coinSelectButtons[i].setEnabled(true);
			coinSelectButtons[i].setVisible(true);
			if(coinsCollected[i]) {
				coinSelectButtons[i].setIcon(GraphicsDriver.chickCoin);
			}
			else {
				coinSelectButtons[i].setIcon(null);
			}
			coinSelectButtons[i].setToolTipText(chickCoinNotes[i]);
		}
		
		// Update Eggs
		int[] eggs = GameDataLookup.getEggsInLevel(currentLevel);
		for(int i = 0; i < 15; i++) {
			if(i < eggs.length) {
				eggsInLevel[i].setEnabled(true);
				eggsInLevel[i].setVisible(true);
				eggsInLevel[i].setIcon(Utils.scaleIcon(GraphicsDriver.eggIcons[eggs[i]], 64, 64));
			}
			else {
				eggsInLevel[i].setEnabled(false);
				eggsInLevel[i].setVisible(false);
			}
		}
		
		// Update Mission Rank Button
		rankSelectDropdown.setEnabled(true);
		rankSelectDropdown.setVisible(true);
		
		// Update Mission Map Button
		missionMapButton.setEnabled(true);
		missionMapButton.setVisible(true);
	}
	
	public void clear() {
		//TODO: implement
	}
	
	private void worldButtonSelected(int worldID) 		{ currentWorld = worldID; 		GraphicsDriver.update(); }
	private void missionButtonSelected(int missionID) 	{ currentMission = missionID; 	GraphicsDriver.update(); }
	private void eggButtonSelected(int eggID) {
		ProgressTracker.gamestate.toggleEggHatched(GameDataLookup.getEggsInLevel(currentLevel)[eggID]); 
		GraphicsDriver.update();
	}
	private void coinButtonSelected(int coinID) {
		ProgressTracker.gamestate.toggleCoinCollected(currentLevel, coinID);
		GraphicsDriver.update();
	}
	private void missionRankSelected() { 
		ProgressTracker.gamestate.completeLevel(currentLevel, (Rank) rankSelectDropdown.getSelectedItem());
		GraphicsDriver.update();
	}
}
