package graphics;

import java.awt.Color;
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
	JLabel[] eggsHatchedCheckmarks = new JLabel[15];
	JComboBox<Rank> rankSelectDropdown;
	JButton[] coinSelectButtons = new JButton[5];
	JButton missionMapButton;
	JButton[] missionSelectButtons = new JButton[8];
	JButton eggGalleryButton;
	JButton[] worldSelectButtons = new JButton[7];
	
	public void initialize() {
		panel.setLayout(null);
		
		currentWorldIcon = new JLabel();
		currentWorldIcon.setSize(550,100);
		currentWorldIcon.setLocation(10, 20);
		panel.add(currentWorldIcon);
		
		currentWorldText = new JLabel();
		currentWorldText.setSize(64, 32);
		currentWorldText.setLocation(580, 20);
		panel.add(currentWorldText);
		
		currentLevelText = new JLabel();
		currentLevelText.setSize(64, 32);
		currentLevelText.setLocation(665, 20);
		panel.add(currentLevelText);
		
		currentLevelTitleText = new JLabel();
		currentLevelTitleText.setSize(500, 32);
		currentLevelTitleText.setLocation(580, 94);		
		panel.add(currentLevelTitleText);
		
		for(int i = 0; i < 15; i++) {
			final int j = i; // Use a final copy for listener
			eggsInLevel[i] = new JButton();
			eggsInLevel[i].addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {eggButtonSelected(j);}});
			eggsInLevel[i].setSize(64,64);
			eggsInLevel[i].setLocation(10 + ((i%8)*64), 120 + ((i/8)*64));
			panel.add(eggsInLevel[i]);
			eggsInLevel[i].setEnabled(false);
			eggsInLevel[i].setVisible(false);
			
			
			eggsHatchedCheckmarks[i] = new JLabel();
			eggsHatchedCheckmarks[i].setSize(64,64);
			eggsHatchedCheckmarks[i].setIcon(Utils.scaleIcon(GraphicsDriver.checkmark,64,64));
			eggsHatchedCheckmarks[i].setEnabled(false);
			eggsHatchedCheckmarks[i].setVisible(false);
		}
		
		rankSelectDropdown = new JComboBox<Rank>(new Rank[] {Rank.NORANK, Rank.D, Rank.C, Rank.B, Rank.A, Rank.S});
		rankSelectDropdown.setSize(64,64);
		rankSelectDropdown.setLocation(580, 200);
		rankSelectDropdown.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {missionRankSelected();}});
		panel.add(rankSelectDropdown); //TODO: probably need a custom renderer?
		rankSelectDropdown.setEnabled(false);
		rankSelectDropdown.setVisible(false);
		
		for(int i = 0; i < 5; i++) {
			final int j = i; // Use a final copy for listener
			coinSelectButtons[i] = new JButton();
			coinSelectButtons[i].setSize(new Dimension(64,64));
			coinSelectButtons[i].setLocation(580 + (64*i), 120);
			coinSelectButtons[i].addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {coinButtonSelected(j);}});
			panel.add(coinSelectButtons[i]);
			coinSelectButtons[i].setEnabled(false);
			coinSelectButtons[i].setVisible(false);
		}
		missionMapButton = new JButton("Open Mission Map");
		missionMapButton.setSize(200, 32);
		missionMapButton.setLocation(750, 32);
		missionMapButton.setEnabled(false);
		missionMapButton.setVisible(false);
		panel.add(missionMapButton);
		missionMapButton.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {GraphicsDriver.openMissionMap(currentLevel);}});
		
		for(int i = 0; i < 8; i++) {
			final int j = i; // Use a final copy for listener
			missionSelectButtons[i] = new JButton("Mission "+(i+1));
			missionSelectButtons[i].setSize(new Dimension(120,64));
			missionSelectButtons[i].setLocation(10 + (i*124), 440);
			missionSelectButtons[i].addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {missionButtonSelected(j);}});
			panel.add(missionSelectButtons[i]);
		}
		eggGalleryButton = new JButton("Egg Gallery");
		eggGalleryButton.setSize(new Dimension(120,64));
		eggGalleryButton.setLocation(10, 510);
		panel.add(eggGalleryButton);
		eggGalleryButton.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {clear(); Dashboard.switchPane();}});
		
		for(int i = 0; i < 7; i++) {
			final int j = i; // Use a final copy for listener
			worldSelectButtons[i] = new JButton(GameDataLookup.getWorldName(i));
			worldSelectButtons[i].setSize(new Dimension(120,64));
			worldSelectButtons[i].setLocation(10 + ((i+1)*124), 510);
			worldSelectButtons[i].addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {worldButtonSelected(j);}});
			panel.add(worldSelectButtons[i]);
		}
	}

	public void update() {
		// FIXME: picking level counts as completing it?
		
		// Update World Text & Level Text
		currentWorldText.setText("World: "+(currentWorld + 1));
		currentLevelText.setText("Mission: "+(currentMission + 1));
		
		if(currentWorld == -1 || currentMission == -1) { return; }
		
		// Update World Icon for Level
		currentWorldIcon.setIcon(GraphicsDriver.worldIcons[currentWorld]);
		
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
				
				if(GameDataLookup.getIsSonicEgg(eggs[i])) {
					eggsInLevel[i].setToolTipText("Requires "+GameDataLookup.getSonicEggChickCoinRequirement(eggs[i])+" Chick Coins");
				}
				
				if(ProgressTracker.gamestate.getEggHatched(eggs[i])) {
					eggsHatchedCheckmarks[i].setEnabled(true);
					eggsHatchedCheckmarks[i].setVisible(true);
				}
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
