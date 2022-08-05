package graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.ToolTipManager;

import data.GameDataLookup;
import data.GameState;
import data.Rank;

public class LevelSelect extends JPanel{
	
	Dashboard frame;
	GameState game;

    BufferedImage[] worldNames = new BufferedImage[GameDataLookup.NUM_WORLDS];
    BufferedImage[] rankIcons = new BufferedImage[6];
    BufferedImage checkmark, chickCoin;
    
    static ChickCoinButton[] chickCoins = new ChickCoinButton[GameDataLookup.NUM_COINS_PER_LEVEL];
    static EggButton[] eggs = new EggButton[GameDataLookup.MAX_EGGS_PER_LEVEL];
    //static RankComboBox rankBox;
    static JComboBox<ImageIcon> rankBox;
    
    private int worldNum = -1, levelNum = -1;

	public LevelSelect(Dashboard parent, GameState gm) {
		frame = parent;
		game = gm;
		
		initializeData();
		initializeFrame();
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
        
        for(int i = 0; i < GameDataLookup.NUM_COINS_PER_LEVEL; i++) {
			chickCoins[i] = new ChickCoinButton(this, game, i);
			this.add("ChickCoinButton#"+(i+1),chickCoins[i]);
		}
        
        for(int i = 0; i < GameDataLookup.MAX_EGGS_PER_LEVEL; i++) {
        	eggs[i] = new EggButton(this, game, i);
        	this.add("EggButton#"+(i+1),eggs[i]);
        }
        
        rankBox = new JComboBox(rankIcons);
        this.add("RankDropdown",rankBox);
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
		
		for(int i = 0; i < 6; i++) {
			try { rankIcons[i] = ImageIO.read(new File("assets/misc/rank"+(Rank.values()[i].name())+".png")); }
			catch(Exception e) { System.out.println("Failed to load rank icons"); }
		}
		
		
		//***********TESTING CODE***********//
		/*	for(int i=1;i<GameDataLookup.MAX_LEVELS;i++) {
				for(int j=0;j<GameDataLookup.NUM_COINS_PER_LEVEL;j++) {
					game.toggleCoinCollected(i, j);
				}
			}
			
			for(int i = 0; i < GameDataLookup.MAX_EGGS;i++) {
				if(i!=47) {
					game.toggleEggHatched(i);
				}
			}
		*/
	}

	protected void paintComponent(Graphics g) {		
		super.paintComponent(g);
		
		System.out.println("Painting");
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
		g.setColor(Color.WHITE);
		g.drawString("World "+(worldNum + 1), 30, 300);
		g.drawString("Level "+(levelNum + 1), 30, 350);
		
		
		//TODO: somewhat stupid to do this. Marked true in painting
			for(int i=0;i< GameDataLookup.NUM_COINS_PER_LEVEL; i++) {
				chickCoins[i].setVisible(false);
			}
			
			for(int i=0;i<GameDataLookup.MAX_EGGS_PER_LEVEL;i++) {
				eggs[i].setVisible(false);
			}
			
			rankBox.setVisible(false);
		
		if(worldNum != -1 && levelNum != -1) {

			//Draw World & Level Information
				g.drawString(GameDataLookup.getFullLevelName(getLevelIndex()), 30, 400);
				//FIXME: fixed width w/ constants etc.
				g.drawImage(worldNames[worldNum], 30, 30, null);
			
			//Draw Rank
				final int rankStartX = 600, rankStartY = 85, rankWidth = 64, rankHeight = 64;
				final Rank rank = game.getLevel(getLevelIndex()).getRank();
				g.setColor(Color.WHITE);
				g.drawRect(rankStartX, rankStartY, rankWidth, rankHeight);
				if(rank!=Rank.NORANK) { // rankX.pngs are same size as rank box
					//TODO: probably not best to use the ordinal index. Maybe add methods to go both ways in Rank
					g.drawImage(rankIcons[rank.ordinal() - 1], rankStartX, rankStartY, rankWidth, rankHeight, null);
				}
			
			//Draw Coins
				g.setColor(Color.WHITE);
				for(int i = 0; i < GameDataLookup.NUM_COINS_PER_LEVEL; i++) {
					chickCoins[i].setVisible(true);
					chickCoins[i].paintComponent(g);
				}
				
			//Draw Eggs			
				g.setColor(Color.WHITE);
				for(int i = 0; i < GameDataLookup.MAX_EGGS_PER_LEVEL; i++) {
					eggs[i].paintComponent(g);
				}
		}
		
		//DO THE BUTTON HIGHLIGHTING
		
	}
	
	public int getLevelIndex() { return worldNum*8 + levelNum; }
	
	private void eggGalleryButtonPressed(ActionEvent e) { frame.setScreen(WindowState.EggGallery); }
	private void worldButtonPressed(ActionEvent e, int i) { worldNum = i; frame.update(); }
	private void levelButtonPressed(ActionEvent e, int i) { levelNum = i; frame.update(); } 
	
}

class EggButton extends JButton {
	
	static BufferedImage checkmark;
	
	final int eggStartX = 30, eggStartY = 160, eggWidth = 64, eggHeight = 64;
	
	GameState game;
	LevelSelect pane;
	
	int eggNum;
	
	EggButton(LevelSelect parent, GameState gm, int num) { 
		super.setContentAreaFilled(false);
		pane = parent;
		game = gm;
		eggNum = num; 
		
		if(checkmark == null) {
			try { checkmark = ImageIO.read(new File("assets/misc/checkmark.png")); }
			catch(Exception e) { System.out.println("Failed to load checkmark image"); }
		}
		
		this.setBounds(1+eggStartX+eggNum*eggWidth, eggStartY+1, eggWidth-1, eggHeight-1);
		
		this.addActionListener(new ActionListener() {
			
	    	public void actionPerformed(ActionEvent e) {
	    		eggPressed(e);
	    	}
	    });
	}
	
	protected void eggPressed(ActionEvent e) {
		try {
			game.toggleEggHatched(GameDataLookup.getEggsInLevel(pane.getLevelIndex())[eggNum]);
		}
		catch(Exception noegg) {
			//there are not this many eggs in the level
		}
	}

	protected void paintComponent(Graphics g) {
		
		int levelIndex = pane.getLevelIndex();
		
		if(levelIndex < 0) { return; }
		
		final int[] eggsToDraw = GameDataLookup.getEggsInLevel(levelIndex);
		
		if(eggNum > eggsToDraw.length -1 ) { return; }
		
		this.setVisible(true);
		super.paintComponent(g);
		
		g.setColor(Color.WHITE);
		g.drawImage(Dashboard.smallEggPngs[eggsToDraw[eggNum]], eggStartX + eggNum*eggWidth, eggStartY, eggWidth, eggHeight, null);
		
		g.drawRect(eggStartX + eggNum*eggWidth, eggStartY, eggWidth, eggHeight);

		this.setToolTipText("Egg #"+(eggsToDraw[eggNum]+1)+": "+GameDataLookup.getEggName(eggsToDraw[eggNum]));
		
		if(game.getEggHatched(eggsToDraw[eggNum])) { //TODO: this checkmark looks awful lol, extremely temporary
			g.drawImage(checkmark,eggStartX + eggNum*eggWidth,eggStartY,eggWidth,eggHeight,null);
		}
	}
}

class ChickCoinButton extends JButton {
	
	static BufferedImage coinImage;
	
	final int coinStartX = 675, coinStartY = 85, coinWidth = 64, coinHeight = 64;
	
	GameState game;
	LevelSelect pane;
	
	int coinNum;
	
	ChickCoinButton(LevelSelect parent, GameState gm, int num) { 
		super.setContentAreaFilled(false);
		
		game = gm;
		pane = parent;
		coinNum = num;
		
		if(coinImage == null) {
			try { coinImage = ImageIO.read(new File("assets/misc/chickCoin.png")); } 
			catch(Exception e) { System.out.println("Failed to load chick coin image"); }
		}
		
		this.setBounds(1+coinStartX+coinNum*coinWidth, coinStartY+1, coinWidth-1, coinHeight-1);
		
		this.addActionListener(new ActionListener() {
			
	    	public void actionPerformed(ActionEvent e) {
	    		coinPressed(e);
	    	}
	    });
		
		this.setToolTipText("");
	}
	
	protected void coinPressed(ActionEvent e) { 
		game.toggleCoinCollected(pane.getLevelIndex(), coinNum);
	}

	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		g.drawRect(coinStartX+coinNum*coinWidth, coinStartY, coinWidth, coinHeight); 
		
		if(game.getLevel(pane.getLevelIndex()).getChickCoins()[coinNum]) { g.drawImage(coinImage, coinStartX+coinNum*coinWidth, coinStartY, coinWidth, coinHeight, null); }
	}
	
	public String getToolTipText(MouseEvent evt){
        ToolTipManager.sharedInstance().setInitialDelay(100);
        ToolTipManager.sharedInstance().setDismissDelay(60000);
        
        int levelIndex = pane.getLevelIndex();
        
        if(levelIndex < 0) {return "";}
        
        return GameDataLookup.chickCoinNotes[levelIndex][coinNum];
    }
}

class RankComboBox extends JLabel implements ListCellRenderer {
	final int rankStartX = 30, rankStartY = 160, rankWidth = 64, rankHeight = 64;
	
	String[] rankNames = null;
	
	GameState game;
	LevelSelect pane;
	
	RankComboBox(LevelSelect parent, GameState gm) {
		
		game = gm; 
		pane = parent;
		rankNames = GameDataLookup.getRanks();
		
		setFont(GraphicsDriver.regular);
		
		Object[] items = new Object[6];
		items[0] = new ImageIcon();
		((ImageIcon) items[0]).setDescription("NO RANK");
		for(int i=0; i< GameDataLookup.NUM_RANKS-1;i++) {
			if(items[i+1] == null) {
				try { items[i+1] = new ImageIcon("assets/misc/"+rankNames[i]+"-rank.png", rankNames[i]+" Rank"); }
				catch(Exception e) { System.out.println("Failed to load rank "+rankNames[i]+" image"); }
			}
		}
			
		this.setBounds(1+rankStartX, rankStartY+1, rankWidth-1, rankHeight-1);
	}
	
	protected void paintComponent(Graphics g) {
		
		int levelIndex = pane.getLevelIndex();
		
		if(levelIndex < 0) { return; }
		
		this.setVisible(true);
		super.paintComponent(g);
		
		g.setColor(Color.WHITE);
		g.fillRect(rankStartX, rankStartY, rankWidth, rankHeight);
	}

	@Override
	public Component getListCellRendererComponent(JList arg0, Object arg1, int arg2, boolean arg3, boolean arg4) {
		// TODO Auto-generated method stub
		return null;
	}
}
