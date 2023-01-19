package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.GameDataLookup;
import data.Level;
import data.LevelState;
import data.Rank;
import main.ProgressTracker;
import util.Utils;

public class LevelSelect {
	private JPanel panel = new JPanel();
	public JPanel getPanel() {return panel;}
	
	int currentWorld = -1;
	int currentMission = -1;
	int currentLevel = -1;

	boolean rankEditListening = true;
	
	JLabel currentWorldIcon;
	JLabel currentWorldText;
	JLabel currentMissionText;
	JLabel currentLevelTitleText;
	JButton[] eggsInLevel = new JButton[15]; // The level with the most distinct eggs has 15 eggs
	JComboBox<ImageIcon> rankSelectDropdown;
	JButton[] coinSelectButtons = new JButton[5];
	JButton missionMapButton;
	JButton[] missionSelectButtons = new JButton[8];
	JButton eggGalleryButton;
	JButton[] worldSelectButtons = new JButton[7];
	
	JLabel levelUnlockWarningText;
	
	
	public void initialize() {
		panel.setBackground(GraphicsDriver.getBackgroundColor());
		panel.setLayout(null);
		
		currentWorldIcon = new JLabel();
		currentWorldIcon.setSize(550,100);
		currentWorldIcon.setLocation(10, 20);
		panel.add(currentWorldIcon);
		
		currentWorldText = new JLabel();
		currentWorldText.setSize(64, 32);
		currentWorldText.setLocation(580, 20);
		panel.add(currentWorldText);
		
		currentMissionText = new JLabel();
		currentMissionText.setSize(64, 32);
		currentMissionText.setLocation(665, 20);
		panel.add(currentMissionText);
		
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
		}
		
		rankSelectDropdown = new JComboBox<ImageIcon>(GraphicsDriver.rankIcons);
		rankSelectDropdown.setSize(84,64);
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
		
		levelUnlockWarningText = new JLabel();
		levelUnlockWarningText.setText("WARNING: This level is not currently unlocked/beatable");
		levelUnlockWarningText.setForeground(Color.RED);
		levelUnlockWarningText.setSize(600, 32);
		levelUnlockWarningText.setLocation(10,400);
		levelUnlockWarningText.setVisible(false);
		panel.add(levelUnlockWarningText);
	}

	public void update() {
		panel.setBackground(GraphicsDriver.getBackgroundColor());
		
		// Update World Text & Level Text
		if(currentWorld != -1) {
			currentWorldText.setText("World: "+(currentWorld + 1));
			currentWorldText.setForeground(GraphicsDriver.getTextColor());
		}
		if(currentMission != -1) {
			currentMissionText.setText("Mission: "+(currentMission + 1));
			currentMissionText.setForeground(GraphicsDriver.getTextColor());
		}
		
		if(currentWorld == -1 || currentMission == -1) { return; }
		
		// Update World Icon for Level
		currentWorldIcon.setIcon(GraphicsDriver.worldIcons[currentWorld]);
		
		// Bookkeeping Current Level
		boolean swappedLevels = currentLevel != currentWorld * 8 + currentMission;
		currentLevel = currentWorld * 8 + currentMission;
		Level thisLevel = ProgressTracker.gamestate.getLevel(currentLevel);
			
		if(swappedLevels) {
			rankEditListening = false;
			Rank r = thisLevel.getRank();
			int index = 0;
			for(index = 0; index< Rank.values().length; index++) {
				if(r == Rank.values()[index]) {
					break;
				}
			}
			rankSelectDropdown.setSelectedItem(GraphicsDriver.rankIcons[index]);
			rankEditListening = true;
			
			// Update World Banner
			currentWorldIcon.setEnabled(true);
			currentWorldIcon.setIcon(GraphicsDriver.worldIcons[currentWorld]);
			
			// Update Level Title
			currentLevelTitleText.setEnabled(true);
			currentLevelTitleText.setText(GameDataLookup.getFullLevelName(currentLevel));
		}
		
		currentLevelTitleText.setForeground(GraphicsDriver.getTextColor());
		
		// Warning Text
		if(GraphicsDriver.warningsEnabled && thisLevel.getState() == LevelState.INACCESSIBLE) {
			levelUnlockWarningText.setVisible(true);
		}
		else {
			levelUnlockWarningText.setVisible(false);
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
				ImageIcon egg = Utils.scaleIcon(GraphicsDriver.eggIcons[eggs[i]], 64, 64);
				eggsInLevel[i].setEnabled(true);
				eggsInLevel[i].setVisible(true);
				
				
				if(GameDataLookup.getIsSonicEgg(eggs[i])) {
					eggsInLevel[i].setToolTipText("Requires "+GameDataLookup.getSonicEggChickCoinRequirement(eggs[i])+" Chick Coins");
				}
				else {
					eggsInLevel[i].setToolTipText(null);
				}
				
				if(ProgressTracker.gamestate.getEggHatched(eggs[i])) {
					Utils.overlayImageOn(egg, Utils.scaleIcon(GraphicsDriver.checkmark, 64, 64), 0, 0);
				}
				
				eggsInLevel[i].setIcon(egg);
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
		currentWorld = -1;
		currentMission = -1;
		currentLevel = -1;
		
		currentWorldText.setText("");
		currentMissionText.setText("");
		currentWorldIcon.setIcon(null);
		rankSelectDropdown.setVisible(false);
		rankSelectDropdown.setEnabled(false);
		currentLevelTitleText.setText("");
		for(int i = 0; i < 5; i++) {
			coinSelectButtons[i].setVisible(false);
			coinSelectButtons[i].setEnabled(false);
		}
		for(int i = 0; i < 15; i++) {
			eggsInLevel[i].setEnabled(false);
			eggsInLevel[i].setVisible(false);
		}
		missionMapButton.setEnabled(false);
		missionMapButton.setVisible(false);
		levelUnlockWarningText.setVisible(false);
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
		if(!rankEditListening) { return; }
		ImageIcon selected = (ImageIcon) rankSelectDropdown.getSelectedItem();
		int index = 0;
		for(index = 0; index < GraphicsDriver.rankIcons.length; index++) {
			if(GraphicsDriver.rankIcons[index] == selected) {
				break;
			}
		}
		
		ProgressTracker.gamestate.completeLevel(currentLevel, Rank.values()[index]);
		GraphicsDriver.update();
	}
}
