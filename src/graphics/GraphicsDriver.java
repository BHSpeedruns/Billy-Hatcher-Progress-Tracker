package graphics;

import javax.swing.UIManager;
import java.awt.Font;
import java.io.File;

import data.GameState;

public final class GraphicsDriver {

	private GraphicsState graphicsstate = new GraphicsState();
	private GameState gamestate;
	
	private Dashboard dashboard = new Dashboard();
	private SummaryPanel summary = new SummaryPanel();
	
	public static Font regular;
	
	public void initialize(GameState game) {
		graphicsstate.initialize();
		System.out.println("GraphicsDriver gs = \t"+graphicsstate);
		gamestate = game;
		initializeResources();
		initializeFrames();
	}
	private void initializeResources() {
		try {
			int x = 1/0; //FIXME: Super Dumb
			regular = Font.createFont(0,new File("assets/fonts/regular.ttf"));
			regular.deriveFont(1,18);
		}
		catch (Exception e) {
			regular = new Font("Times New Roman", Font.BOLD, 18);
		}
	}
	private void initializeFrames() {
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		}
		catch (Exception e) { e.printStackTrace(); }
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		
        dashboard.initialize(graphicsstate,gamestate);
        summary.initialize(graphicsstate,gamestate);
	}
	
	private void drawFrames() {
		dashboard.update();
		summary.update();
	}
	public void redrawIfNeeded() { 
		if(graphicsstate.needsRefresh()) { 
			drawFrames(); 
			graphicsstate.done(); 
		}
	}
}
