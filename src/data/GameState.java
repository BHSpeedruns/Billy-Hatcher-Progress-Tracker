package data;

import java.util.ArrayList;

public final class GameState implements java.io.Serializable {
	
	private int courageEmblems 	= 0;
	
	private int levelsCompleted = 0;
	private int chickCoins 		= 0;
	private int eggsHatched 	= 0;
	private int sRanks 			= 0;
	
	private boolean[] eggList = new boolean[72];
	private World[] worlds = new World[7];
	
	public void initialize() {
		for(int i = 0; i < worlds.length; i++) { worlds[i] = new World(i); }
		getLevel(0).setState(LevelState.INCOMPLETE); //first level is accessible by default
		for(int i = 1; i < 56; i++) { getLevel(i).setState(LevelState.INACCESSIBLE); };
	}

	public int getNumCourageEmblems    (){ return courageEmblems;  }
	public int getNumLevelsCompleted   (){ return levelsCompleted; }
	public int getNumChickCoins        (){ return chickCoins;      }
	public int getNumEggsHatched       (){ return eggsHatched;     }
	public int getNumSRanks            (){ return sRanks;          }
	
	public int[] getSummaryData() { 
		return new int[]{courageEmblems,levelsCompleted,chickCoins,eggsHatched,sRanks};
	}
	
	public Level getLevel(int level) { return worlds[level/8].getLevel(level%8); }	
	
	public Level[] completeLevel(int level, Rank rank) {
		if(rank == Rank.NORANK) { return incompleteLevel(level); }
		
		Level levelCompleted = getLevel(level);
		
		if(levelCompleted.getRank() != Rank.S && rank == Rank.S)      { incrementSRanks(); } //New S Rank
		else if(levelCompleted.getRank() == Rank.S && rank != Rank.S) { decrementSRanks(); } //Removing Faulty/Accidental S Rank
		
		levelCompleted.setRank(rank);
		
		if(levelCompleted.getState() != LevelState.COMPLETE) { incrementCourageEmblems(); incrementLevelsCompleted(); }
		return propagateLevelChange(level, LevelState.COMPLETE);
	}
	private Level[] incompleteLevel(int level) {
		Level levelCompleted = getLevel(level);

		if(levelCompleted.getRank() != Rank.NORANK) {
			
			if(levelCompleted.getRank() == Rank.S) { decrementSRanks(); }
			
			levelCompleted.setRank(Rank.NORANK);
			decrementCourageEmblems(); decrementLevelsCompleted();
		}
		
		return propagateLevelChange(level, LevelState.INCOMPLETE);
	}
	private Level[] propagateLevelChange(int level, LevelState state) {
		
		int worldNum = level/8, levelNum = level%8;
		ArrayList<Level> levelList = new ArrayList<Level>();
		
		//Do not propagate already completed levels
		if(getLevel(level).getState() == state) { return null; }
		
		getLevel(level).setState(state);
		
		//Billy Level unlocks next billy level (1->2->3->4->5)
		if(levelNum <= 3) {
			//Making sand 3 available is special
			if(!(worldNum == 5 && levelNum == 2) || (courageEmblems >= 25)) {
				levelList.add(getLevel(worldNum*8+levelNum+1)); 
			}
		}	
		
		//First Level unlocks friend levels in that world
		if(levelNum == 0) { 
			for(int friendNum = 0; friendNum < 3; friendNum++) {
				if(getLevel((1+friendNum)*8 + 3).getState() == LevelState.COMPLETE) { 
					levelList.add(getLevel(worldNum*8+friendNum+5)); 
				}
			}
		}
		
		//Boss Level unlocks next world elder mission
		else if(worldNum <= 4 && levelNum == 1 ) { levelList.add(getLevel((worldNum+1)*8)); } 
		
		//Friend unlocked
		else if(worldNum >= 1 && worldNum <= 3 && levelNum == 3) { 
			for(int world = 0; world < 7; world++) {
				if(getLevel(world*8).getState() == LevelState.COMPLETE) { levelList.add(getLevel(world*8+(4+worldNum))); }
			}
		}
		
		//Sand 3 unlocks Palace 1
		else if(worldNum == 5 && levelNum == 2) { levelList.add(getLevel(48)); } 
		
		LevelState followUpState = LevelState.INCOMPLETE;
		if(state == LevelState.INCOMPLETE) { followUpState = LevelState.INACCESSIBLE; }
		
		for(int i = 0; i < levelList.size(); i++) {
			levelList.get(i).setState(followUpState);
		}
		
		return levelList.toArray(new Level[0]);
	}
	
	public void toggleCoinCollected(int level, int coin) {
		
		boolean collected = worlds[level/8].getLevel(level%8).getChickCoin(coin);
		if(collected) {
			decrementChickCoins();
		}
		else {
			incrementChickCoins();
		}
		
		worlds[level/8].getLevel(level%8).setChickCoin(coin, !collected);
	}

	public void toggleEggHatched(int egg) {
		if(eggList[egg]) { decrementEggsHatched(); }
		else { incrementEggsHatched(); }
		eggList[egg] = !eggList[egg];
	}
	
	//FIXME: this duplicate style code annoys me
	private void incrementCourageEmblems() {
		courageEmblems++;
		if(courageEmblems == GameDataLookup.MAX_EMBLEMS) { } //100% !!!
	}
	private void decrementCourageEmblems() {
		if(courageEmblems == GameDataLookup.MAX_EMBLEMS) { } //Not 100% :(
		courageEmblems--;
	}
	
	private void incrementLevelsCompleted() {
		levelsCompleted++;
		if(levelsCompleted == GameDataLookup.MAX_LEVELS) { incrementCourageEmblems(); } //Set "All Levels" Emblem
	}
	private void decrementLevelsCompleted() {
		if(levelsCompleted == GameDataLookup.MAX_LEVELS) { decrementCourageEmblems(); } //Unset "All Levels" Emblem
		levelsCompleted--;
	}
	
	private void incrementSRanks() {
		sRanks++;
		if(sRanks == GameDataLookup.MAX_SRANKS) { incrementCourageEmblems(); } //Set "All S Ranks" Emblem
	}
	private void decrementSRanks() {
		if(sRanks == GameDataLookup.MAX_SRANKS) { decrementCourageEmblems(); } //Unset "All S Ranks" Emblem
		sRanks--;
	}
	
	private void incrementEggsHatched() {
		eggsHatched++; 
		if(eggsHatched == GameDataLookup.MAX_EGGS) { incrementCourageEmblems(); } //Set "All Eggs" Emblem
	}
	private void decrementEggsHatched() {
		if(eggsHatched == GameDataLookup.MAX_EGGS) { decrementCourageEmblems(); } //Unset "All Eggs" Emblem
		eggsHatched--;
	}
	
	private void incrementChickCoins() {
		chickCoins++;
		if(chickCoins == GameDataLookup.MAX_CHICK_COINS) { incrementCourageEmblems(); } //Set "All Coins" Emblem
	}
	private void decrementChickCoins() {
		if(chickCoins == GameDataLookup.MAX_CHICK_COINS) { decrementCourageEmblems(); } //Unset "All Coins" Emblem
		chickCoins--;
	}
	
	
	//////////////////////////////////
	// Testing Functions
	//////////////////////////////////
	public void setCoinsCollected(int level, boolean[] coins) {
		int numCoins = 0;
		for(boolean coin : coins) { if(coin) { numCoins++; } }
		
		if(chickCoins == GameDataLookup.MAX_CHICK_COINS && numCoins!=5) { decrementCourageEmblems(); } //Unset "All Coins" Emblem
		
		chickCoins -= worlds[level/8].getLevel(level%8).getNumChickCoins();
		chickCoins += numCoins;
		
		//FIXME: I am not convinced of this logic. Technically works cause all buttons are toggles and this is just for testing?
		if(chickCoins == GameDataLookup.MAX_CHICK_COINS) { incrementCourageEmblems(); } //Set "All Coins" Emblem
		
		worlds[level/8].getLevel(level%8).setChickCoins(coins);
	}
	public void setCoinCollected(int level, int coin, boolean value) {
		
		if(chickCoins == GameDataLookup.MAX_CHICK_COINS && !value) { decrementCourageEmblems(); } //Unset "All Coins" Emblem
		
		if(value && !worlds[level/8].getLevel(level%8).getChickCoin(coin)) {
			chickCoins++;
		}
		else if(!value && worlds[level/8].getLevel(level%8).getChickCoin(coin) ) {
			chickCoins--;
		}
		
		if(chickCoins == GameDataLookup.MAX_CHICK_COINS) { incrementCourageEmblems(); } //Set "All Coins" Emblem
		
		worlds[level/8].getLevel(level%8).setChickCoin(coin,value);
	}
	
	public boolean getEggHatched(int egg) { return eggList[egg]; }
	public void setEggHatched(int egg, boolean hatched) { eggList[egg] = hatched; }
}
