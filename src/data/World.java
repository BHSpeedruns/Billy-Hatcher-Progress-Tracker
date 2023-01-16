package data;

public class World implements java.io.Serializable {
	
	private int ID;
	private Level[] levels = new Level[GameDataLookup.LEVELS_PER_WORLD];
	
	public World(int ID) {
		this.ID = ID;
		for(int i = 0; i < levels.length; i++) { levels[i] = new Level(ID*8 + i); }
	}
	
	public int getID() { return ID; }
	
	public LevelState[] getLevelStates() {
		LevelState[] states = new LevelState[levels.length];
		for(byte i = 0; i < levels.length; i++) { states[i] = levels[i].getState(); }
		return states;
	}

	public Level getLevel(int level) { return levels[level]; }
}
