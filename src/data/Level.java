package data;

public class Level implements java.io.Serializable {
	private int			ID;
	private boolean[] 	chickCoinsCollected = new boolean[5];
	private Rank 		highestRank;
	private LevelState 	completionState;
	
	public Level(int ID) {
		this.ID					= 	ID;
		highestRank 			= 	Rank.NORANK;
		completionState 		= 	LevelState.INACCESSIBLE;
	}
	
	public int getNumChickCoins() { 
		int number = 0;
		for(boolean coin : chickCoinsCollected) { if(coin) { number++; } }
		return number;
	}
	
	public void setChickCoin       (int c, boolean v){ chickCoinsCollected[c] = v; }
	public void toggleChickCoin    (int coin)        { chickCoinsCollected[coin] = !chickCoinsCollected[coin]; }
	public void setChickCoins      (boolean[] coins) { chickCoinsCollected = coins; }
	public boolean getChickCoin   (int coin)        { return chickCoinsCollected[coin]; }
	public boolean[] getChickCoins ()				 { return chickCoinsCollected; }
	public Rank getRank            ()                { return highestRank; }
	public void setRank            (Rank rank)       { highestRank = rank; } 
	public LevelState getState     ()                { return completionState; }
	public void setState           (LevelState state){ completionState = state; }
	public int getID               ()                { return ID; }
	public String toString         ()                { return "#"+ID+": ("+((ID/8)+1)+"-"+((ID%8)+1)+") "+GameDataLookup.getLevelName(ID); }
}
