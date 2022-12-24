package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JPanel;

import data.GameDataLookup;
import data.GameState;
import util.Utils;

public class EggGallery extends JPanel implements MouseListener {
	
	Dashboard frame;
	GameState game;
	
	int currentEgg = 4;
	
	final int xEggList = 0, eggWidth = 64, eggHeight = 64, xSpacer = 10, ySpacer = 5;
	
	public EggGallery(Dashboard p, GameState gm) {
		frame = p;
		game = gm;
		initializeFrame();
		addMouseListener(this);
	}

	private void initializeFrame() {
		JButton levelSelectButton = new JButton();
		levelSelectButton.setText("Level Select");
		levelSelectButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	levelSelectButtonPressed(e);
	            }
        });
		
	}
	
	protected void paintComponent(Graphics g) {
		final int leftWidth = 700;
		super.paintComponent(g);
		paintEggList(g, xEggList, leftWidth);
		paintCurrentEggInfo(g, leftWidth, 1020 - leftWidth);
		
	}
	private void paintEggList(Graphics g, int startX, int width) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, 600);
		
		for(int i=0;i<72;i++) {
			if(i%2==0) {game.setEggHatched(i,true);}
			g.setColor(Color.BLUE);
			g.drawRect(
				startX + 2*xSpacer + ((eggWidth +xSpacer)* (i%9)), 
				3*ySpacer + (eggHeight+ySpacer)*(i/9), 
				eggWidth, 
				eggHeight
			);
			g.drawImage(Dashboard.smallEggPngs[i], 
				startX + 2*xSpacer + ((eggWidth +xSpacer)* (i%9)), 
				3*ySpacer + (eggHeight+ySpacer)*(i/9), 
				eggWidth, 
				eggHeight,
				null
			);

			if(game.getEggHatched(i)) {
				g.drawImage(Dashboard.checkmark,
					startX + 2*xSpacer + ((eggWidth +xSpacer)* (i%9)), 
					3*ySpacer + (eggHeight+ySpacer)*(i/9), 
					eggWidth, 
					eggHeight,
					null
				);
			}
		}
	}
	private void paintCurrentEggInfo(Graphics g, int startX, int width) {
		g.setColor(Color.BLACK);
		g.fillRect(startX + 5, 10, width-10, 300);
		g.drawImage(Dashboard.smallEggPngs[currentEgg], startX + 5, 10, width-20, 300, null);
		
		g.drawString(GameDataLookup.getEggName(currentEgg), startX + 5, 325);
		
		g.drawString("Collected: "+game.getEggHatched(currentEgg), startX+5, 350);
		g.drawString("Found in: "+Arrays.toString(Utils.levelIndiciesToNames(GameDataLookup.getEggLocations(currentEgg))), startX+5, 400);
		
		g.setColor(Color.GRAY);
		g.fillRect(startX + 5, 525, width-25, 50);
		
		g.setColor(Color.CYAN);
		g.drawString("ADD BUTTON BACK TO LEVEL SELECT HERE", startX+10, 540);
		
	}
	protected void levelSelectButtonPressed(ActionEvent e) { System.out.println("Switching to LevelSelect"); }

	public void mouseClicked(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		// Adjustments to account for borders
		x -= xEggList + 2*xSpacer;
		y -= 3*ySpacer;
		
		// Left & Top
		if(x < 0 || y < 0) { return; } 
		
		// Right and Bottom
		if(x > ((eggWidth +xSpacer) * 8) + eggWidth) { return; }
		if(y > ((eggHeight+ySpacer) * 7) + eggHeight) { return; }
		
		x /= eggWidth +xSpacer;
		y /= eggHeight+ySpacer;
		
		currentEgg = x + 9*y;
		
		frame.update();
		
	}

	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
