package graphics;

import javax.swing.UIManager;
import java.awt.Font;
import java.io.File;

import data.GameState;

public class GraphicsDriver {

	private Dashboard dashboard = new Dashboard();
	private SummaryPanel summary = new SummaryPanel();
	
	public static Font regular;
	
	
	public GraphicsDriver() {
		initializeResources();
		initializeFrames();
	}
	
	private final void initializeResources() {
		try {
			int x = 1/0; //FIXME: Super Dumb
			regular = Font.createFont(0,new File("assets/fonts/regular.ttf"));
			regular.deriveFont(1,36);
		}
		catch (Exception e) {
			regular = new Font("Times New Roman", Font.BOLD, 18);
		}
	}
	private final void initializeFrames() {
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		}
		catch (Exception e) { e.printStackTrace(); }
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		
        dashboard.initialize();
        summary.initialize();
	}
	
	public final void drawFrames(GameState state) {
		dashboard.update();
		summary.update(state.getSummaryData());
	}
}
