package util;

import java.util.Arrays;
import java.util.Collection;

public class Utils {
	
	public final static <E> String listToString(Collection<E> list) {
		return Arrays.toString((E[]) list.toArray());
	}
}
