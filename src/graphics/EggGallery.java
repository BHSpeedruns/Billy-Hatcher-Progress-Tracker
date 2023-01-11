package graphics;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.GameDataLookup;
import util.Utils;

public class EggGallery {

	private JPanel panel = new JPanel();
	public JPanel getPanel() {return panel;}
	
	int currentEgg = -1;
	
	JButton[] eggButtons = new JButton[72];
	JLabel selectedEggImage;
	JLabel selectedEggName;
	JLabel selectedEggLevels; // TODO: JButtons to open maps?
	JLabel selectedEggFruitCount;
	JLabel[] selectedEggFruitPreferences = new JLabel[7];
	
	JButton levelSelectButton;
	
	public void initialize() {
		for(int i = 0; i < 72; i++) {
			final int j = i; // Use a final copy for listener
			eggButtons[i] = new JButton();
			eggButtons[i].setPreferredSize(new Dimension(64,64));
			eggButtons[i].setIcon(Utils.scaleIcon(GraphicsDriver.eggIcons[i], 64, 64));
			eggButtons[i].addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {eggButtonSelected(j);}});
			panel.add(eggButtons[i]);
		}
		selectedEggImage = new JLabel();
		selectedEggImage.setPreferredSize(new Dimension(300,300));
		panel.add(selectedEggImage);
		
		selectedEggName = new JLabel();
		panel.add(selectedEggName);
		
		selectedEggLevels = new JLabel();
		panel.add(selectedEggLevels);
		
		selectedEggFruitCount = new JLabel();
		panel.add(selectedEggFruitCount);
		
		for(int i = 0; i < 7; i++) {
			selectedEggFruitPreferences[i] = new JLabel();
			selectedEggFruitPreferences[i].setIcon(GraphicsDriver.fruitIcons[i]);
			panel.add(selectedEggFruitPreferences[i]);
		}
		
		levelSelectButton = new JButton("Level Select");
		levelSelectButton.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {clear(); Dashboard.switchPane();}});
		panel.add(levelSelectButton);
	}

	public void update() {
		if(currentEgg == -1) { return; }
		
		selectedEggImage.setIcon(Utils.scaleIcon(GraphicsDriver.eggIcons[currentEgg], 300, 300));
		selectedEggName.setText(GameDataLookup.getEggName(currentEgg));
		//selectedEggLevels.setText(Arrays.toString(Utils.levelIndiciesToNames(GameDataLookup.getEggLocations(currentEgg))));
		
		selectedEggFruitCount.setText("Fruit Required to Hatch: "+GameDataLookup.getEggFruitCount(currentEgg));
		
		boolean[] preferences = GameDataLookup.getEggFruitPreferences(currentEgg);
		for(int i = 0; i < 7; i++) {
			selectedEggFruitPreferences[i].setEnabled(preferences[i]);
		}
	}
	
	public void clear() {
		//TODO: implement
	}
	
	private void eggButtonSelected(int eggID) { currentEgg = eggID; GraphicsDriver.update(); }
}

//TODO: implement
class EggGalleryLayout implements LayoutManager {
	public void addLayoutComponent(String name, Component comp) {
		
	}

	public void removeLayoutComponent(Component comp) {
		
	}

	public Dimension preferredLayoutSize(Container parent) {
		return null;
	}

	public Dimension minimumLayoutSize(Container parent) {
		return null;
	}

	public void layoutContainer(Container parent) {
		parent.getComponents();
	}
	
}

