package graphics;

import javax.swing.UIManager;
import java.awt.Font;
import java.io.File;

import data.GameState;

public final class GraphicsDriver {

	private GameState gamestate;
	
	private Dashboard dashboard;
	private SummaryPanel summary;
	
	public static Font regular;
	
	public void initialize(GameState game) {
		gamestate = game;
		
		try {
			if(true) {throw new Exception();} //FIXME: temporary stupidity
			
			regular = Font.createFont(0,new File("assets/fonts/regular.ttf"));
			regular.deriveFont(1,18);
		}
		catch (Exception e) {
			regular = new Font("Times New Roman", Font.BOLD, 18);
		}
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		}
		catch (Exception e) { e.printStackTrace(); }
		UIManager.put("swing.boldMetal", Boolean.FALSE);

		dashboard = new Dashboard(gamestate);
		summary = new SummaryPanel(gamestate);
	}
}
