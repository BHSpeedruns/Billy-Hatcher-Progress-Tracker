package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import data.GameDataLookup;

enum WindowState { EggGallery, LevelSelect }

public class Dashboard extends JFrame implements MouseListener {
	
	private WindowState screen;
	
	EggGallery eggGallery = new EggGallery();
	LevelSelect levelSelect = new LevelSelect();
	
	private final int WIDTH = 1020, HEIGHT = 620;
	
	public void initialize() {
		screen = WindowState.LevelSelect;
		setTitle("Billy Hatcher 100% Progress Tracker");
		pack();
		add(eggGallery);
		add(levelSelect);
		setSize(WIDTH,HEIGHT);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		eggGallery.initialize();
		levelSelect.initialize();
	}
	
	public void update() { 
		if(screen == WindowState.EggGallery) { 
			eggGallery.setVisible(true);
			levelSelect.setVisible(false);
		}
		else if(screen == WindowState.LevelSelect) {
			levelSelect.setVisible(true);
			eggGallery.setVisible(false);
		}
	}

	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}

class EggGallery extends JPanel{
	
	public void initialize() {
		
	}
	
}

class LevelSelect extends JPanel{
	
	public boolean isActive = false;
	
	private int worldNum = -1, levelNum = -1;
	
	public void initialize() {
		JButton eggGalleryButton = new JButton();
        eggGalleryButton.setText("Egg Gallery");
        eggGalleryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	eggGalleryButtonPressed(e);
            }
        });

        JButton forestVillageButton = new JButton();
        forestVillageButton.setText("Forest Village");
        forestVillageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	worldButtonPressed(e,0);
            }
        });
        
        JButton piratesIslandButton = new JButton();
        piratesIslandButton.setText("Pirates Island");
        piratesIslandButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	worldButtonPressed(e,1);
            }
        });
        
        JButton dinoMountainButton = new JButton();
        dinoMountainButton.setText("Dino Mountain");
        dinoMountainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	worldButtonPressed(e,2);
            }
        });
        
        JButton blizzardCastleButton = new JButton();
        blizzardCastleButton.setText("Blizzard Castle");
        blizzardCastleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	worldButtonPressed(e,3);
            }
        });
        
        JButton circusParkButton = new JButton();
        circusParkButton.setText("Circus Park");
        circusParkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	worldButtonPressed(e,4);
            }
        });
        
        JButton sandRuinButton = new JButton();
        sandRuinButton.setText("Sand Ruin");
        sandRuinButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	worldButtonPressed(e,5);
            }
        });
        
        JButton giantPalaceButton = new JButton();
        giantPalaceButton.setText("Giant Palace");
        giantPalaceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	worldButtonPressed(e,6);
            }
        });
        
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    //.addComponent(canvas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(eggGalleryButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(forestVillageButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(piratesIslandButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dinoMountainButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(blizzardCastleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(circusParkButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sandRuinButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(giantPalaceButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                //.addComponent(canvas, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(eggGalleryButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(piratesIslandButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(forestVillageButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dinoMountainButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(blizzardCastleButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sandRuinButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(circusParkButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(giantPalaceButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
	}
	
	private void eggGalleryButtonPressed(ActionEvent e) {
		isActive = false; //TODO: not sure how to tell Dashboard to switch
		repaint();
	}

	private void worldButtonPressed(ActionEvent e, int i) {
		
	}

	protected void paintComponent(Graphics g) {		
		super.paintComponent(g);
		
		
		//DO THE BUTTON HIGHLIGHTING AND THE CANVAS PAINTING
	}
}

