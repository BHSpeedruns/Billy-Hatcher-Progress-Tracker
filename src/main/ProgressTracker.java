package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import data.GameState;
import graphics.GraphicsDriver;

public final class ProgressTracker {
	
	private static double VERSION = 0.6;
	
	
//	private final static int FPS = 60;// Change this to affect fps of the application, currently 60.
	
//	private final int FRAMEDELTA = (int) (1000000000 / FPS);
//	
//	private long lastTime = System.nanoTime();
//	private long newTime;
	
	
	public static GraphicsDriver graphics = new GraphicsDriver(); 
	public static GameState gamestate = new GameState();
	
	public static void main(String[]args) { new ProgressTracker(); }

	public ProgressTracker() {
		initialize();
		//runTests();
		runMain();
	}
	
	private void initialize() {
		
		checkVersion();
		
		gamestate.initialize();
		graphics.initialize();
		
//		lastTime = System.nanoTime();
	}
	
	private void checkVersion() {
		double upversion;
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(new URL("https://exeloar.github.io/billytracker").openConnection().getInputStream()));
			upversion = Double.parseDouble(input.readLine().substring(17)); // Current Version: ##
			input.close();
			
			if (VERSION == upversion) {
				System.out.println("Tracker is Up-To-Date");
			}
			else {
				System.out.println("Tracker is Out-Of-Date");
			}
		}
		// If can't connect, say offline
		catch (Exception e) {
			System.out.println("Cannot check tracker version, offline mode");
			e.printStackTrace();
		}
	}

	private void runMain() { 
		while(true) {
			//FIXME: a lock??
			try { Thread.sleep(10); } catch(Exception e) {}
		}
	}
}
