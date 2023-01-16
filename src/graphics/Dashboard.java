package graphics;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import io.SaveDriver;
import main.ProgressTracker;

public class Dashboard {
	
	static JFrame frame = new JFrame();
	static JPanel panel = new JPanel();
	static CardLayout layout = new CardLayout();
	static JMenuBar menuBar = new JMenuBar();
	
	static EggGallery eggGallery = new EggGallery();
	static LevelSelect levelSelect = new LevelSelect();
	
	static boolean onLevelSelect;
	
	public void initialize() {		
		frame.setTitle("Billy Hatcher 100% Progress Tracker");
		frame.pack();
		
		frame.setSize(new Dimension(1020,640));
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
		
		menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem menuItem = new JMenuItem("Load Gamestate");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				SaveDriver.loadGameState();
			}
		});
		menu.add(menuItem);
		menuItem = new JMenuItem("Save Gamestate");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				SaveDriver.saveGameState(ProgressTracker.gamestate);
			}
		});
		menu.add(menuItem);
		menuBar.add(menu);

		menu = new JMenu("View");
		menuItem = new JMenuItem("Toggle Dark Mode");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				GraphicsDriver.darkMode = !GraphicsDriver.darkMode;
				GraphicsDriver.update();
			}
		});
		menu.add(menuItem);
		menuItem = new JMenuItem("Toggle Warnings");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				GraphicsDriver.warningsEnabled = !GraphicsDriver.warningsEnabled;
				GraphicsDriver.update();
			}
		});
		menu.add(menuItem);
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);
		
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
