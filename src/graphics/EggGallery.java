package graphics;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.GameDataLookup;
import main.ProgressTracker;
import util.Utils;

public class EggGallery {

	private JPanel panel = new JPanel();
	public JPanel getPanel() {return panel;}
	
	int currentEgg = -1;
	
	JButton[] eggButtons = new JButton[72];
	JLabel selectedEggImage;
	JLabel selectedEggLevels; // TODO: JButtons to open maps?
	JLabel selectedEggFruitCount;
	JLabel[] selectedEggFruitPreferences = new JLabel[7];
	
	JButton levelSelectButton;
	
	public void initialize() {
		panel.setPreferredSize(new Dimension(1020,620));
		panel.setBackground(GraphicsDriver.getBackgroundColor());
		panel.setLayout(null);
		
		for(int i = 0; i < 72; i++) {
			final int j = i; // Use a final copy for listener
			eggButtons[i] = new JButton();
			eggButtons[i].setPreferredSize(new Dimension(64,64));
			
			final int startX = 0, eggWidth = 64, eggHeight = 64, xSpacer = 10, ySpacer = 5;
			eggButtons[i].setIcon(Utils.scaleIcon(GraphicsDriver.eggIcons[i], eggWidth, eggHeight));
			eggButtons[i].setSize(new Dimension(eggWidth,eggHeight));
			eggButtons[i].setLocation(startX + 2*xSpacer + ((eggWidth +xSpacer)* (i%9)), 3*ySpacer + (eggHeight+ySpacer)*(i/9));
			if(GameDataLookup.getIsSonicEgg(i)) { 
				eggButtons[i].setToolTipText("Requires "+GameDataLookup.getSonicEggChickCoinRequirement(i)+" Chick Coins");
			}
			
			eggButtons[i].addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {eggButtonSelected(j);}});
			panel.add(eggButtons[i]);
		}
		selectedEggImage = new JLabel();
		selectedEggImage.setSize(new Dimension(300,300));
		selectedEggImage.setLocation(680, 0);
		panel.add(selectedEggImage);
		
		selectedEggLevels = new JLabel();
		selectedEggLevels.setSize(new Dimension(320,110));
		selectedEggLevels.setLocation(680,260);
		panel.add(selectedEggLevels);
		
		selectedEggFruitCount = new JLabel();
		selectedEggFruitCount.setSize(new Dimension(320,16));
		selectedEggFruitCount.setLocation(680,370);
		panel.add(selectedEggFruitCount);
		
		for(int i = 0; i < 7; i++) {
			selectedEggFruitPreferences[i] = new JLabel();
			selectedEggFruitPreferences[i].setIcon(Utils.scaleIcon(GraphicsDriver.fruitIcons[i], 64, 64));
			selectedEggFruitPreferences[i].setSize(64,64);
			selectedEggFruitPreferences[i].setLocation(680 + ((i%4)*70), 386 + ((i/4)*70));
			selectedEggFruitPreferences[i].setEnabled(false);
			panel.add(selectedEggFruitPreferences[i]);
		}
		
		levelSelectButton = new JButton("Level Select");
		levelSelectButton.setSize(320,48);
		levelSelectButton.setLocation(680,530);
		levelSelectButton.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {clear(); Dashboard.switchPane();}});
		panel.add(levelSelectButton);
	}

	public void update() {
		panel.setBackground(GraphicsDriver.getBackgroundColor());
		
		ImageIcon check = Utils.scaleIcon(GraphicsDriver.checkmark, 48, 48);
		for(int i = 0; i < 72; i++) {
			if(ProgressTracker.gamestate.getEggHatched(i)) {
				ImageIcon eggIcon = Utils.scaleIcon(GraphicsDriver.eggIcons[i],64,64);
				Utils.overlayImageOn(eggIcon, check, 24, 16);
				eggButtons[i].setIcon(eggIcon);
			}
		}
		
		if(currentEgg == -1) { return; }
		
		ImageIcon egg = Utils.scaleIcon(GraphicsDriver.eggIcons[currentEgg], 300, 300);
		ImageIcon item = Utils.scaleIcon(GraphicsDriver.itemIcons[currentEgg], 100, 100);
		Utils.overlayImageOn(egg, item, 200, 180);
		selectedEggImage.setIcon(egg);
		selectedEggImage.setEnabled(true);
		
		String levels = Arrays.toString(Utils.levelIndiciesToWorldMissionPairs(GameDataLookup.getEggLocations(currentEgg)));
		selectedEggLevels.setText("<html><p>"+GameDataLookup.getEggName(currentEgg)+"</p><p>"+levels+"</p></html>");
		selectedEggLevels.setForeground(GraphicsDriver.getTextColor());
		selectedEggLevels.setEnabled(true);
		selectedEggFruitCount.setText("Fruit Required to Hatch: "+GameDataLookup.getEggFruitCount(currentEgg));
		selectedEggFruitCount.setForeground(GraphicsDriver.getTextColor());
		selectedEggFruitCount.setEnabled(true);
		boolean[] preferences = GameDataLookup.getEggFruitPreferences(currentEgg);
		for(int i = 0; i < 7; i++) {
			selectedEggFruitPreferences[i].setEnabled(preferences[i]);
		}
	}
	
	public void clear() {
		currentEgg = -1;
		
		selectedEggImage.setIcon(null);
		selectedEggLevels.setText("");
		selectedEggFruitCount.setText("");
		for(int i = 0; i < 7; i++) {
			selectedEggFruitPreferences[i].setEnabled(false);
		}
	}
	
	private void eggButtonSelected(int eggID) { currentEgg = eggID; GraphicsDriver.update(); }
}

