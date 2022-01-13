package graphics;

import java.awt.Dimension;

public final class GraphicsState {
	
	private boolean needsRefresh;
	
	private final int DASHWIDTH = 1020, DASHHEIGHT = 620;
	private final int SUMMWIDTH = 510, SUMMHEIGHT = 310;
	
	private WindowState screen;
	private int worldNum, levelNum;
	
	public void initialize() {
		worldNum = -1;
		levelNum = -1;
		screen = WindowState.LevelSelect;
		needsRefresh = true;
	}
	
	public int getWorldNum() { return worldNum; }
	public int getLevelNum() { return levelNum; }
	public WindowState getScreen() { return screen; }
	
	public void setWorldNum(int world) { worldNum = world; }
	public void setLevelNum(int level) { levelNum = level; }
	public void setScreen(WindowState screenState) { screen = screenState; }
	
	public void update() { needsRefresh = true; }
	public boolean needsRefresh() { return needsRefresh; }

	public void done() { needsRefresh = false; }
	
	public Dimension getDashboardDimensions() { return new Dimension(DASHWIDTH, DASHHEIGHT); }
	public Dimension getSummaryPanelDimensions() { return new Dimension(SUMMWIDTH, SUMMHEIGHT); }
}
