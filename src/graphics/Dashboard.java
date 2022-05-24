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
import data.Rank;

enum WindowState { EggGallery, LevelSelect }

public class Dashboard extends JFrame implements MouseListener {
	
	private GraphicsState graphics;
	private GameState game;
	
	static EggGallery eggGallery = new EggGallery();
	static LevelSelect levelSelect = new LevelSelect();
	
	public static BufferedImage[] smallEggPngs = new BufferedImage[72];
	
	public void initialize(GraphicsState g, GameState gm) {
		graphics = g;
		game = gm;
		eggGallery.initialize(graphics,game);
		levelSelect.initialize(graphics,game);
		initializeFrame();
		initializeData();
		
	}
	
	private void initializeFrame() {
		setTitle("Billy Hatcher 100% Progress Tracker");
		pack();
		add(eggGallery);
		add(levelSelect);
		setSize(graphics.getDashboardDimensions());
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	private void initializeData() {
		for(int i = 1; i <= 72; i++) {
			try { smallEggPngs[i-1] = ImageIO.read(new File("assets/Egg PNGs/Numbered/"+i+".png")); } 
			catch(Exception e) { System.out.println("Failed to load numbered egg pngs"); }
		}
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

    BufferedImage[] worldNames = new BufferedImage[7];
    BufferedImage[] rankIcons = new BufferedImage[5];
    BufferedImage checkmark, chickCoin;
    
    

	public void initialize(GraphicsState g, GameState gm) {
		graphics = g;
		game = gm;
		
		initializeFrame();
		initializeData();
	}
	
	private void initializeFrame() {
		
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
	private void initializeData() {
		final String[] worldNamePNGs = {
		        "forest-village2",
		        "pirates-island",
		        "dino-mountain",
		        "blizzard-castle3",
		        "circus-park1",
		        "sand-ruin1",
		        "giant-palace2"
		};
		
		for(int i = 0; i < 7; i++) {
			try { worldNames[i] = ImageIO.read(new File("assets/misc/"+worldNamePNGs[i]+".png")); } 
			catch(Exception e) { System.out.println("Failed to load world name image"); }
		}
		
		for(int i = 0; i < 5; i++) {
			try { rankIcons[i] = ImageIO.read(new File("assets/misc/"+(Rank.values()[i+1].name())+"-rank.png")); }
			catch(Exception e) { System.out.println("Failed to load rank icons"); }
		}
		
		try { checkmark = ImageIO.read(new File("assets/misc/checkmark.png")); }
		catch(Exception e) { System.out.println("Failed to load checkmark image"); }
		
		try { chickCoin = ImageIO.read(new File("assets/misc/temporarychickcoin.png")); }
		catch(Exception e) { System.out.println("Failed to load chick coin image"); }
		
	}

	protected void paintComponent(Graphics g) {		
		super.paintComponent(g);
		
		Dimension window = graphics.getDashboardDimensions();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, window.width, window.height);
		g.setColor(Color.WHITE);
		g.drawString("World "+(graphics.getWorldNum()+1), 30, 300);
		g.drawString("Level "+(graphics.getLevelNum()+1), 30, 350);
		
		
		if(graphics.getWorldNum() != -1 && graphics.getLevelNum() != -1) {
			drawLevelInfo(g);            
		}
		
		//DO THE BUTTON HIGHLIGHTING
		
	}
	
	private void drawLevelInfo(Graphics g) {
		
		//***********TESTING CODE***********//
		
		/*
			game.getLevel(1).setRank(Rank.D); //forest 2
			game.getLevel(2).setRank(Rank.C);
			game.getLevel(3).setRank(Rank.B);
			game.getLevel(4).setRank(Rank.A);
			game.getLevel(5).setRank(Rank.S);
			
			game.setEggHatched(2,true); // Lightning Comb (seen on forest 5)
			
			game.getLevel(1).setChickCoin(0,true);
			game.getLevel(2).setChickCoin(1,true);
			game.getLevel(3).setChickCoin(2,true);
			game.getLevel(4).setChickCoin(3,true);
			game.getLevel(5).setChickCoin(4,true);
			
			game.getLevel(0).setChickCoins(new boolean[] {true,true,true,true,true});
		
		*/
		
		final int level = graphics.getLevelNum(), world = graphics.getWorldNum(), levelIndex = world*8 + level;

		//Draw World & Level Information
			g.drawString(GameDataLookup.getFullLevelName(levelIndex), 30, 400);
			//FIXME: fixed width w/ constants etc.
			g.drawImage(worldNames[world], 30, 30, null);
		
		//Draw Rank
			final int rankStartX = 600, rankStartY = 85, rankWidth = 64, rankHeight = 64;
			final Rank rank = game.getLevel(levelIndex).getRank();
			g.setColor(Color.WHITE);
			g.drawRect(rankStartX, rankStartY, rankWidth, rankHeight);
			if(rank!=Rank.NORANK) { //TODO: These rank images don't fit the box well
				//TODO: probably not best to use the ordinal index. Maybe add methods to go both ways in Rank
				g.drawImage(rankIcons[rank.ordinal() - 1], rankStartX, rankStartY, rankWidth, rankHeight, null);
			}
		
		//Draw Coins
			final int coinStartX = 675, coinStartY = 85, coinWidth = 64, coinHeight = 64;
			final boolean[] coins = game.getLevel(levelIndex).getChickCoins();
			g.setColor(Color.WHITE);
			for(int i = 0; i < 5; i++) { //TODO: I didn't have the chick coin image on hand, we need to update it
				g.drawRect(coinStartX+i*coinWidth, coinStartY, coinWidth, coinHeight);
				if(coins[i]) { g.drawImage(chickCoin, coinStartX+i*coinWidth, coinStartY, coinWidth, coinHeight, null); }
			}
			
		//Draw Eggs
			final int[] eggsToDraw = GameDataLookup.getEggsInLevel(levelIndex);
			final int eggStartX = 30, eggStartY = 160, eggWidth = 64, eggHeight = 64;
			
			g.setColor(Color.WHITE);
			for(int i = 0; i<eggsToDraw.length; i++) {
				g.drawImage(Dashboard.smallEggPngs[eggsToDraw[i]], eggStartX + i*eggWidth, eggStartY, eggWidth, eggHeight, null);
				g.drawRect(eggStartX + i*eggWidth, eggStartY, eggWidth, eggHeight);
				if(game.getEggHatched(eggsToDraw[i])) { //TODO: this checkmark looks awful lol, extremely temporary
					g.drawImage(checkmark,eggStartX + i*eggWidth,eggStartY,eggWidth,eggHeight,null);
				}
			}
		
	}
	
	
	private void eggGalleryButtonPressed(ActionEvent e) { graphics.setScreen(WindowState.EggGallery); graphics.update(); }
	private void worldButtonPressed(ActionEvent e, int i) { graphics.setWorldNum(i); graphics.update(); }
	private void levelButtonPressed(ActionEvent e, int i) { graphics.setLevelNum(i); graphics.update(); } 
}
