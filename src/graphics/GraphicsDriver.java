package graphics;


import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.GameDataLookup;
import data.Rank;
import util.Utils;

public class GraphicsDriver {
	
	static Dashboard dash = new Dashboard();
	static SummaryPanel summary = new SummaryPanel();
	
	// Graphics Objects
	public static ImageIcon chickCoin;
	public static ImageIcon checkmark;
	public static ImageIcon[] worldIcons = new ImageIcon[7];
	public static ImageIcon[] eggIcons = new ImageIcon[72];
	public static ImageIcon[] itemIcons = new ImageIcon[72];
	public static ImageIcon[] rankIcons = new ImageIcon[6];
	public static ImageIcon[] fruitIcons = new ImageIcon[7];
	public static ImageIcon[] missionMaps = new ImageIcon[56];
	
	static {
		chickCoin = new ImageIcon("assets/misc/chickcoin.png","Chick Coin");
		checkmark = new ImageIcon("assets/misc/checkmark.png","Checkmark");
		
		worldIcons[0] = new ImageIcon("assets/misc/forest-village1.png"	, "Forest Village" 	);
		worldIcons[1] = new ImageIcon("assets/misc/pirates-island.png"	, "Pirate's Island"	);
		worldIcons[2] = new ImageIcon("assets/misc/dino-mountain.png"	, "Dino Mountain"	);
		worldIcons[3] = new ImageIcon("assets/misc/blizzard-castle1.png", "Blizzard Castle"	);
		worldIcons[4] = new ImageIcon("assets/misc/circus-park1.png"	, "Circus Park"		);
		worldIcons[5] = new ImageIcon("assets/misc/sand-ruin1.png"		, "Sand Ruin"		);
		worldIcons[6] = new ImageIcon("assets/misc/giant-palace1.png"	, "Giant Palace"	);
		
		for(int i = 0; i < eggIcons.length; i++) {
			eggIcons[i] = new ImageIcon("assets/Egg PNGs/Numbered/"+(i+1)+".png", GameDataLookup.getEggName(i));
		}
		
		for(int i = 0; i < itemIcons.length; i++) {
			itemIcons[i] = new ImageIcon("assets/animals items/"+(i+1)+".png", GameDataLookup.getEggName(i));
		}
		
		for(int i = 0; i < rankIcons.length; i++) {
			rankIcons[i] = new ImageIcon("assets/misc/rank"+(Rank.values()[i].name())+".png");
		}
		
		for(int i = 0; i < fruitIcons.length; i++) {
			fruitIcons[i] = new ImageIcon("assets/misc/"+GameDataLookup.getFruits()[i]+".png");
		}
		
		for(int i = 0; i < missionMaps.length; i++) {
			missionMaps[i] = new ImageIcon("assets/maps/"+GameDataLookup.getLevelNumberFormat(i).replaceAll(" ", "")+".png");
		}
	}
	
	public void initialize() {
		summary.initialize();
		dash.initialize();
	}
	
	public static void update() {
		//FIXME: static & instance update mixed due to checkmarks on eggs being shared... not great.
		dash.update();
		summary.update();
	}
	
	public static void openMissionMap(int level) {
		final int width = 1428;
		final int height = 816;
		
		JLabel map = new JLabel();
		map.setIcon(Utils.scaleIcon(missionMaps[level],width-64,height-64));
		
		JPanel pane = new JPanel();
		pane.add(map);
		
		JFrame popup = new JFrame();
		popup.add(pane);
		popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popup.setSize(width, height); // 1.75 factor. Might not fit all maps well.
		popup.setLocationRelativeTo(null);
		popup.setVisible(true);
	}
}
