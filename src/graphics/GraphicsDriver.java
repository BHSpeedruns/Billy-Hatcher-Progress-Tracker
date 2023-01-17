package graphics;

import java.awt.Color;
import java.awt.Toolkit;

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
	public static ImageIcon emblem;
	public static ImageIcon[] worldIcons = new ImageIcon[7];
	public static ImageIcon[] eggIcons = new ImageIcon[72];
	public static ImageIcon[] itemIcons = new ImageIcon[72];
	public static ImageIcon[] rankIcons = new ImageIcon[6];
	public static ImageIcon[] fruitIcons = new ImageIcon[7];
	public static ImageIcon[] missionMaps = new ImageIcon[56];
	
	// Graphics Settings
	public static boolean darkMode;
	public static boolean warningsEnabled;
	public static boolean largeMaps;
	
	static {
		chickCoin = new ImageIcon("assets/misc/chickcoin.png","Chick Coin");
		checkmark = new ImageIcon("assets/misc/checkmark.png","Checkmark");
		emblem = new ImageIcon("assets/misc/emblem.png","Emblem");
		
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
		
		// Global Settings
		javax.swing.ToolTipManager.sharedInstance().setInitialDelay(100);
		darkMode = true;
		warningsEnabled = true;
		largeMaps = true;
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
		double tWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double tHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		if(!largeMaps) {tWidth *= 0.6; tHeight *= 0.6;}
		else {tWidth *= 0.9; tHeight *= 0.9;}

		final int width = (int) tWidth;
		final int height = (int) tHeight;

		JLabel map = new JLabel();
		map.setIcon(Utils.scaleIcon(missionMaps[level],width-64,height-64));
		
		JPanel pane = new JPanel();
		pane.add(map);
		
		JFrame popup = new JFrame();
		popup.add(pane);
		popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popup.setSize(width, height);
		popup.setLocationRelativeTo(null);
		popup.setVisible(true);
	}
	
	public static Color getBackgroundColor() {
		if(darkMode) { return Color.BLACK; }
		return Color.WHITE;
	}
	public static Color getTextColor() {
		if(darkMode) { return Color.WHITE; }
		return Color.BLACK;
	}
}
