package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;

import data.GameState;
import graphics.GraphicsDriver;
import main.ProgressTracker;

public class SaveDriver {
	public static void saveGameState(GameState state) {
		JFileChooser pane = new JFileChooser(new File("./saves"));
		pane.setDialogTitle("Save Gamestate");
		
		int value = pane.showSaveDialog(null);
		
		if(value == JFileChooser.APPROVE_OPTION) {
			File outFile = pane.getSelectedFile();
			ObjectOutputStream output;
			try {
				output = new ObjectOutputStream(new FileOutputStream(outFile));
				output.writeObject(state);
				output.close();
			}
			catch (FileNotFoundException e) { e.printStackTrace(); } 
			catch (IOException e) { e.printStackTrace(); }
		}
	}
	public static void loadGameState() {
		JFileChooser pane = new JFileChooser(new File("./saves"));
		pane.setDialogTitle("Load Gamestate");
		
		int value = pane.showOpenDialog(null);
		
		if(value == JFileChooser.APPROVE_OPTION) {
			File outFile = pane.getSelectedFile();
			ObjectInputStream input;
			try {
				input = new ObjectInputStream(new FileInputStream(outFile));
				ProgressTracker.gamestate = (GameState) input.readObject();
				GraphicsDriver.update();
				input.close();
			}
			catch (FileNotFoundException e) { e.printStackTrace(); } 
			catch (IOException e) { e.printStackTrace(); } 
			catch (ClassNotFoundException e) { e.printStackTrace(); }
		}
	}
}
