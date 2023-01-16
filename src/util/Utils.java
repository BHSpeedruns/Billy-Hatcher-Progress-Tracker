package util;

import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			levelNames[level] = GameDataLookup.getLevelName(levels[level]);
		}
		return levelNames;
	}
	public final static String[] levelIndiciesToWorldMissionPairs(int[] levels) {
		String[] levelNames = new String[levels.length];
		for(int level = 0; level<levels.length; level++) {
			levelNames[level] = GameDataLookup.getLevelNumberFormat(levels[level]);
		}
		return levelNames;
	}
	
	public final static Icon scaleIcon(ImageIcon imageIcon, Dimension eggButton) {
		return scaleIcon(imageIcon, eggButton.width, eggButton.height);
	}
	public final static ImageIcon scaleIcon(ImageIcon image, int newWidth, int newHeight) {
		return new ImageIcon(image.getImage().getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH));
	}
	
	public static String[] splitAndKeepDelimiters(String s, String split, boolean regex){
		if(regex){return splitAndKeepDelimitersRegex(s,split);}
		return splitAndKeepDelimitersString(s,split);
	}

	private static String[] splitAndKeepDelimitersString(String s, String split){
		String[] arr1 = s.split(split);
		String[] arr2 = new String[arr1.length*2 - 1];
		
		for(int i = 0;i<arr2.length;i++){
			if(i%2 == 0) arr2[i] = arr1[i/2];
			else arr2[i] = split; 
		}
		return arr2;
	}

	private static String[] splitAndKeepDelimitersRegex(String s, String pattern){
		String[] arr1 = s.split(pattern);
		String[] arr2 = new String[arr1.length*2 - 1];

		Pattern regexPattern = Pattern.compile(pattern);
		ArrayList<String> list = new ArrayList<String>();
		Matcher m = regexPattern.matcher(s);
		while (m.find()) {
			list.add(m.group());
		}

		for(int i = 0;i<arr2.length;i++){
			if(i%2 == 0) arr2[i] = arr1[i/2];
			else arr2[i] = list.get(i/2);
		}
		return arr2;
	}
}
