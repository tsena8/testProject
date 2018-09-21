package com.tsena.mastermind.constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Class for defining PegColor enum and holding methods to be used in game application
 * @author tsena
 *
 */
public enum PegColor {
	
	GREEN("green"), 
	BLUE("blue"),
	RED("red"), 
	VIOLET("violet");
	
	private String value;

	/**
	 * Setting value method
	 * @param value - string value 
	 */
	private PegColor(String value) {
		this.value = value;
	}

	/**
	 * Constructor method
	 * @param value - string value
	 * @return PegColor enum 
	 */
	public static PegColor fromValue(String value) {
		for (PegColor color : values()) {
			if (color.value.equalsIgnoreCase(value)) {
				return color;
			}
		}
		throw new IllegalArgumentException(
				"Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
	}
	
	/**
	 * Get random RegColor 
	 * @return PegColor enum
	 */
	public static PegColor getRandomColor() {
		Random random = new Random();
		return PegColor.values()[random.nextInt(PegColor.values().length)];
	}
	
	/**
	 * Get random list of not repeating RegColor values 
	 * @return List<PegColor> enum available values list 
	 */
	public static List<PegColor>  getRandomNoRepeat() {
		
		List<PegColor> colors = new ArrayList<>();
		Collections.addAll(colors, PegColor.values());
		Collections.shuffle(colors);
		
		return colors;
	}
	
	/**
	 * Get random list RegColor values with maximum of 2 duplications 
	 * @return List<PegColor> enum values list of size 4 
	 */
	public static List<PegColor>  getRandom2Duplicates() {
		
		List<PegColor> colors = new ArrayList<>();
		Collections.addAll(colors, PegColor.values());
		Collections.addAll(colors, PegColor.values());
		Collections.shuffle(colors);
		
		return colors.subList(0, 4);
	}

}

