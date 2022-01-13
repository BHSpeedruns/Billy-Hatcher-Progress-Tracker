package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import data.GameDataLookup;
import data.GameState;
import data.Level;
import data.Rank;
import graphics.GraphicsDriver;
import util.Utils;

public class TestDriver {
	GraphicsDriver graphics;
	GameState gamestate;
	Random rand;
	
	public final void initialize(GraphicsDriver graphics, GameState gamestate) {
		this.graphics = graphics;
		this.gamestate = gamestate;
		rand = new Random();
		rand.setSeed(System.nanoTime());
	}
	
	//This function tests the level unlock system (gamestate.completeLevel)
	//Should generate a route through the game based off randomly picking available levels and beating them
	//Should end with all levels completed!
	public final void testLevelUnlockSystem() {
		ArrayList<Level> accessibleLevels = new ArrayList<Level>();
		ArrayList<Level> nonSRankedLevels = new ArrayList<Level>();
		accessibleLevels.add(gamestate.getLevel(0));
		
		while(accessibleLevels.size()!=0) {
			System.out.println("Currently Accessible Levels: "+Utils.listToString(accessibleLevels));
			
			int levelToBeat = accessibleLevels.get(rand.nextInt(accessibleLevels.size())).getID();
			Rank randomRank = Rank.values()[rand.nextInt(Rank.values().length - 1) + 1];
			
			String sRankStatus = " with S rank";
			if(randomRank != Rank.S) { nonSRankedLevels.add(gamestate.getLevel(levelToBeat)); sRankStatus = " without S rank"; }
			
			System.out.println("Beating Level ("+((levelToBeat/8)+1)+"-"+((levelToBeat%8)+1)+") "+GameDataLookup.getLevelName(levelToBeat)+sRankStatus);
			
			Level[] newLevels = gamestate.completeLevel(levelToBeat, randomRank);
			System.out.println("Adding Levels "+Arrays.toString(newLevels));
			
			System.out.println(gamestate.getNumLevelsCompleted()+" levels completed, "+gamestate.getNumSRanks()+" S ranks, "+gamestate.getNumCourageEmblems()+" emblems\n");
			accessibleLevels.remove(gamestate.getLevel(levelToBeat));
			
			for(int i = 0;i<newLevels.length;i++) {	if(!accessibleLevels.contains(newLevels[i])) { accessibleLevels.add(newLevels[i]); }}
		}
		
		
		//Maybe S ranks should be isolated. Always assign D rank or something on first pass then do S ranks after?
		System.out.println("Starting to do S ranks now");
		while(nonSRankedLevels.size()!=0) {
			
			System.out.println("Levels without S Rank "+Utils.listToString(nonSRankedLevels));
			
			int levelToBeat = nonSRankedLevels.get(0).getID();
			
			System.out.println("Earning S Rank on Level ("+((levelToBeat/8)+1)+"-"+((levelToBeat%8)+1)+") "+GameDataLookup.getLevelName(levelToBeat));
			gamestate.completeLevel(levelToBeat, Rank.S);
			
			System.out.println(gamestate.getNumLevelsCompleted()+" levels completed, "+gamestate.getNumSRanks()+" sranks, "+gamestate.getNumCourageEmblems()+" emblems\n");
			
			nonSRankedLevels.remove(0);
		}
	}
	
	public final void testEggHatchSystem() {
		ArrayList<Integer> eggsToHatch = new ArrayList<Integer>();
		for(int i = 0 ; i < GameDataLookup.MAX_EGGS; i++) { eggsToHatch.add(i); }
		
		while(eggsToHatch.size()!=0) {
			
			int index = rand.nextInt(eggsToHatch.size());
			int eggToHatch = eggsToHatch.get(index);
			
			System.out.println("Hatching "+GameDataLookup.getEggName(eggToHatch));
			gamestate.toggleEggHatched(eggToHatch);
			
			System.out.println(gamestate.getNumEggsHatched()+" eggs hatched, "+gamestate.getNumCourageEmblems()+" emblems\n");
			
			eggsToHatch.remove(index);
		}
	}
	
	
	//This should probably do some more complicated stuff, like how level system doesn't always assign S rank, this could assign random coins, then fill out the 5 coins later?
	public final void testChickCoinSystem() { 
		ArrayList<Level> levelsLeft = new ArrayList<Level>();
		for(int i = 0 ; i < GameDataLookup.MAX_LEVELS; i++) { levelsLeft.add(gamestate.getLevel(i)); }
		
		while(levelsLeft.size()!=0) {
			
			int index = rand.nextInt(levelsLeft.size());
			int level = levelsLeft.get(index).getID();
			
			System.out.println("Collecting 5 Coins on ("+((level/8)+1)+"-"+((level%8)+1)+") "+GameDataLookup.getLevelName(level));
			gamestate.setCoinsCollected(level,5);
			
			System.out.println(gamestate.getNumChickCoins()+" coins collected, "+gamestate.getNumCourageEmblems()+" emblems\n");
			
			levelsLeft.remove(index);
		}
	}
}
