package util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import data.GameDataLookup;

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
	public final static ImageIcon scaleIcon(ImageIcon image, int newMaxWidth, int newMaxHeight) {
		double factor = Math.max(image.getIconHeight()*1.0 / newMaxHeight, image.getIconWidth()*1.0 / newMaxWidth);
		
		int newWidth = (int) Math.floor(image.getIconWidth() / factor);
		int newHeight = (int) Math.floor(image.getIconHeight() / factor);
	
		return new ImageIcon(image.getImage().getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH));
	}
	public final static ImageIcon forceFitIcon(ImageIcon image, int newWidth, int newHeight) {
		return new ImageIcon(image.getImage().getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH));
	}
	
	public final static void overlayImageOn(ImageIcon background, ImageIcon foreground, int xPos, int yPos) {
		BufferedImage temp = new BufferedImage(background.getIconWidth(), background.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = temp.createGraphics();
		g.drawImage(background.getImage(), 0, 0, null);
		g.drawImage(foreground.getImage(), xPos, yPos, null);
		background.setImage(temp);
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
