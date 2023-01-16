package graphics;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Dashboard {
	
	static JFrame frame = new JFrame();
	static JPanel panel = new JPanel();
	static CardLayout layout = new CardLayout();
	
	static EggGallery eggGallery = new EggGallery();
	static LevelSelect levelSelect = new LevelSelect();
	
	static boolean onLevelSelect;
	
	public void initialize() {		
		frame.setTitle("Billy Hatcher 100% Progress Tracker");
		frame.pack();
		
		frame.setSize(new Dimension(1020,620));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		
		eggGallery.initialize();
		levelSelect.initialize();
		
		panel.setLayout(layout);
		panel.add(eggGallery.getPanel(), "Egg Gallery");
		panel.add(levelSelect.getPanel(), "Level Select");
		
		layout.show(panel, "Level Select");
		onLevelSelect = true;
		
		frame.add(panel);
		
		frame.setVisible(true);
	}
	
	public static void update() {
		if(onLevelSelect) {levelSelect.update();}
		else {eggGallery.update();}
	}
	
	public static void switchPane() {
		layout.next(panel);
		onLevelSelect = !onLevelSelect;
		update();
	}
}
