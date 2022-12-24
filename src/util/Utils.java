package util;

import java.util.Arrays;
import java.util.Collection;

import data.GameDataLookup;

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
}
