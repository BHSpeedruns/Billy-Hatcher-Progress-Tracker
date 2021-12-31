package data;

public class Level {
	private int			ID;
	private int 		chickCoinsCollected;
	private Rank 		highestRank;
	private LevelState 	completionState;
	
	public Level(int ID) {
		this.ID					= 	ID;
		chickCoinsCollected 	= 	0;
		highestRank 			= 	Rank.NORANK;
		completionState 		= 	LevelState.INACCESSIBLE;
	}
	
	public int getNumChickCoins    ()                { return chickCoinsCollected; }
	public void setNumChickCoins   (int coins)       { chickCoinsCollected = coins; }
	public Rank getRank            ()                { return highestRank; }
	public void setRank            (Rank rank)       { highestRank = rank; } 
	public LevelState getState     ()                { return completionState; }
	public void setState           (LevelState state){ completionState = state; }
	public int getID               ()                { return ID; }
	public String toString         ()                { return "#"+ID+": ("+((ID/8)+1)+"-"+((ID%8)+1)+") "+GameDataLookup.getLevelName(ID); }
}
