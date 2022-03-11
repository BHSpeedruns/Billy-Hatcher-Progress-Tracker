package graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.awt.image.BufferedImage;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.imageio.*;

import data.GameDataLookup;
import data.GameState;

enum WindowState { EggGallery, LevelSelect }

public class Dashboard extends JFrame implements MouseListener {
	
	private GraphicsState graphics;
	private GameState game;
	
	static EggGallery eggGallery = new EggGallery();
	static LevelSelect levelSelect = new LevelSelect();
	
	public void initialize(GraphicsState g, GameState gm) {
		graphics = g;
		game = gm;
		eggGallery.initialize(graphics,game);
		levelSelect.initialize(graphics,game);
		
		setTitle("Billy Hatcher 100% Progress Tracker");
		pack();
		add(eggGallery);
		add(levelSelect);
		setSize(g.getDashboardDimensions());
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	public void update() { 
		if(graphics.getScreen() == WindowState.EggGallery) {
			levelSelect.setVisible(false);
			eggGallery.repaint();
			eggGallery.setVisible(true);
		}
		else if(graphics.getScreen() == WindowState.LevelSelect) {
			eggGallery.setVisible(false);
			levelSelect.repaint();
			levelSelect.setVisible(true);
		}
	}

	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}

class EggGallery extends JPanel{
	
	private GraphicsState graphics;
	private GameState game;
	
	public void initialize(GraphicsState g, GameState gm) {
		graphics = g;
		game = gm;
		
		JButton levelSelectButton = new JButton();
		levelSelectButton.setText("Level Select");
		levelSelectButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	levelSelectButtonPressed(e);
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
                        .addComponent(levelSelectButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
            .addGap(0, 0, 0)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                //.addComponent(canvas, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(levelSelectButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
        );
	}

	protected void levelSelectButtonPressed(ActionEvent e) { graphics.setScreen(WindowState.LevelSelect); }
	
	protected void paintComponent(Graphics g) {		
		super.paintComponent(g);
		//TODO:Content
	}
}

class LevelSelect extends JPanel{
	
	GraphicsState graphics;
	GameState game;

	public void initialize(GraphicsState g, GameState gm) {
		graphics = g;
		game = gm;
		
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
        
        JButton level1 = new JButton();
        level1.setText("Mission 1");
        level1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		levelButtonPressed(e,0);
        	}
        });
        
        JButton level2 = new JButton();
        level2.setText("Mission 2");
        level2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		levelButtonPressed(e,1);
        	}
        });
        
        JButton level3 = new JButton();
        level3.setText("Mission 3");
        level3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		levelButtonPressed(e,2);
        	}
        });
        
        JButton level4 = new JButton();
        level4.setText("Mission 4");
        level4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		levelButtonPressed(e,3);
        	}
        });
        
        JButton level5 = new JButton();
        level5.setText("Mission 5");
        level5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		levelButtonPressed(e,4);
        	}
        });
        
        JButton level6 = new JButton();
        level6.setText("Mission 6");
        level6.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		levelButtonPressed(e,5);
        	}
        });
        
        JButton level7 = new JButton();
        level7.setText("Mission 7");
        level7.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		levelButtonPressed(e,6);
        	}
        });
        
        JButton level8 = new JButton();
        level8.setText("Mission 8");
        level8.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		levelButtonPressed(e,7);
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
                        .addComponent(giantPalaceButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(level1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(level2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(level3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(level4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(level5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(level6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(level7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(level8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGap(430)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(level1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(level2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(level3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(level4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(level5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(level6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(level7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(level8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
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

	private void eggGalleryButtonPressed(ActionEvent e) { graphics.setScreen(WindowState.EggGallery); graphics.update(); }
	private void worldButtonPressed(ActionEvent e, int i) { graphics.setWorldNum(i); graphics.update(); }
	private void levelButtonPressed(ActionEvent e, int i) { graphics.setLevelNum(i); graphics.update(); } 

    
    BufferedImage img = null;
    //TODO: move these filenames into GraphicsDriver
    private static final String[] worldNamePNGs = {
        "assets/misc/forest-village2.png",
        "assets/misc/pirates-island.png",
        "assets/misc/dino-mountain.png",
        "assets/misc/blizzard-castle3.png",
        "assets/misc/circus-park1.png",
        "assets/misc/sand-ruin1.png",
        "assets/misc/giant-palace2.png"
    };
    private static final String[] eggPNGs = {
        "assets/numbered eggs 650x650/1.png",
        "assets/numbered eggs 650x650/2.png",
        "assets/numbered eggs 650x650/3.png",
        "assets/numbered eggs 650x650/4.png",
        "assets/numbered eggs 650x650/5.png",
        "assets/numbered eggs 650x650/6.png",
        "assets/numbered eggs 650x650/7.png",
        "assets/numbered eggs 650x650/8.png",
        "assets/numbered eggs 650x650/9.png",
        "assets/numbered eggs 650x650/10.png",
        "assets/numbered eggs 650x650/11.png",
        "assets/numbered eggs 650x650/12.png",
        "assets/numbered eggs 650x650/13.png",
        "assets/numbered eggs 650x650/14.png",
        "assets/numbered eggs 650x650/15.png",
        "assets/numbered eggs 650x650/16.png",
        "assets/numbered eggs 650x650/17.png",
        "assets/numbered eggs 650x650/18.png",
        "assets/numbered eggs 650x650/19.png",
        "assets/numbered eggs 650x650/20.png",
        "assets/numbered eggs 650x650/21.png",
        "assets/numbered eggs 650x650/22.png",
        "assets/numbered eggs 650x650/23.png",
        "assets/numbered eggs 650x650/24.png",
        "assets/numbered eggs 650x650/25.png",
        "assets/numbered eggs 650x650/26.png",
        "assets/numbered eggs 650x650/27.png",
        "assets/numbered eggs 650x650/28.png",
        "assets/numbered eggs 650x650/29.png",
        "assets/numbered eggs 650x650/30.png",
        "assets/numbered eggs 650x650/31.png",
        "assets/numbered eggs 650x650/32.png",
        "assets/numbered eggs 650x650/33.png",
        "assets/numbered eggs 650x650/34.png",
        "assets/numbered eggs 650x650/35.png",
        "assets/numbered eggs 650x650/36.png",
        "assets/numbered eggs 650x650/37.png",
        "assets/numbered eggs 650x650/38.png",
        "assets/numbered eggs 650x650/39.png",
        "assets/numbered eggs 650x650/40.png",
        "assets/numbered eggs 650x650/41.png",
        "assets/numbered eggs 650x650/42.png",
        "assets/numbered eggs 650x650/43.png",
        "assets/numbered eggs 650x650/44.png",
        "assets/numbered eggs 650x650/45.png",
        "assets/numbered eggs 650x650/46.png",
        "assets/numbered eggs 650x650/47.png",
        "assets/numbered eggs 650x650/48.png",
        "assets/numbered eggs 650x650/49.png",
        "assets/numbered eggs 650x650/50.png",
        "assets/numbered eggs 650x650/51.png",
        "assets/numbered eggs 650x650/52.png",
        "assets/numbered eggs 650x650/53.png",
        "assets/numbered eggs 650x650/54.png",
        "assets/numbered eggs 650x650/55.png",
        "assets/numbered eggs 650x650/56.png",
        "assets/numbered eggs 650x650/57.png",
        "assets/numbered eggs 650x650/58.png",
        "assets/numbered eggs 650x650/59.png",
        "assets/numbered eggs 650x650/60.png",
        "assets/numbered eggs 650x650/61.png",
        "assets/numbered eggs 650x650/62.png",
        "assets/numbered eggs 650x650/63.png",
        "assets/numbered eggs 650x650/64.png",
        "assets/numbered eggs 650x650/65.png",
        "assets/numbered eggs 650x650/66.png",
        "assets/numbered eggs 650x650/67.png",
        "assets/numbered eggs 650x650/68.png",
        "assets/numbered eggs 650x650/69.png",
        "assets/numbered eggs 650x650/70.png",
        "assets/numbered eggs 650x650/71.png",
        "assets/numbered eggs 650x650/72.png"
    };

	protected void paintComponent(Graphics g) {		
		super.paintComponent(g);
		
		Dimension window = graphics.getDashboardDimensions();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, window.width, window.height);
		g.setColor(Color.WHITE);
		g.drawString("World "+(graphics.getWorldNum()+1), 30, 300);
		g.drawString("Level "+(graphics.getLevelNum()+1), 30, 350);
		if(graphics.getWorldNum() != -1 && graphics.getLevelNum() != -1) {
            g.drawString(GameDataLookup.getFullLevelName(graphics.getWorldNum()*8 + graphics.getLevelNum()), 30, 400);
            
            //title of current world 
            try{
                img = ImageIO.read(new File(worldNamePNGs[graphics.getWorldNum()])); 
            }
            catch(Exception e){
                System.out.println("Could not open file");
            }            
            g.drawImage(img, 30, 30, null);

            //all eggs in level
            //TODO: can only print eggs that are in Forest levels regardless of world
            int horizOffset = 0;
            for(int egg : GameDataLookup.getEggsInLevel(graphics.getLevelNum())) {
                try{
                    img = ImageIO.read(new File(eggPNGs[egg]));
                }
                catch(Exception e){
                    System.out.println("Could not open file");
                }
                g.drawImage(img, 30 + horizOffset, 150, null);
                horizOffset += 50;
            }
		}
		
		//DO THE BUTTON HIGHLIGHTING
		
	}
}

