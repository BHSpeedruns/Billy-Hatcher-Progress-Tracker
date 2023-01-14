package util;

import java.awt.Dimension;
import java.awt.Image;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import data.GameDataLookup;
import graphics.GraphicsDriver;

public class Utils {
	
	public final static <E> String listToString(Collection<E> list) {
		return Arrays.toString((E[]) list.toArray());
	}
	
	public final static String[] levelIndiciesToNames(int[] levels) {
		String[] levelNames = new String[levels.length];
		for(int level = 0; level<levels.length; level++) {
			levelNames[level] = GameDataLookup.getLevelName(level);
		}
		return levelNames;
	}
	public final static String[] levelIndiciesToWorldMissionPairs(int[] levels) {
		String[] levelNames = new String[levels.length];
		for(int level = 0; level<levels.length; level++) {
			levelNames[level] = GameDataLookup.getLevelNumberFormat(level);
		}
		return levelNames;
	}
	
	public final static Icon scaleIcon(ImageIcon imageIcon, Dimension eggButton) {
		return scaleIcon(imageIcon, eggButton.width, eggButton.height);
	}
	public final static ImageIcon scaleIcon(ImageIcon image, int newWidth, int newHeight) {
		return new ImageIcon(image.getImage().getScaledInstance(64,64, java.awt.Image.SCALE_SMOOTH));
	}
}
