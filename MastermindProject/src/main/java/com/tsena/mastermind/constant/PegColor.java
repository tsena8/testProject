package com.tsena.mastermind.constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum PegColor {
	
	GREEN("green"), 
	BLUE("blue"),
	RED("red"), 
	VIOLET("violet");
	
	private String value;

	private PegColor(String value) {
		this.value = value;
	}

	public static PegColor fromValue(String value) {
		for (PegColor color : values()) {
			if (color.value.equalsIgnoreCase(value)) {
				return color;
			}
		}
		throw new IllegalArgumentException(
				"Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
	}
	
	public static PegColor getRandomColor() {
		Random random = new Random();
		return PegColor.values()[random.nextInt(PegColor.values().length)];
	}
	
	public static List<PegColor>  getRandomNoRepeat() {
		
		List<PegColor> colors = new ArrayList<>();
		Collections.addAll(colors, PegColor.values());
		Collections.shuffle(colors);
		
		return colors;
	}
	
	public static List<PegColor>  getRandom2Duplicates() {
		
		List<PegColor> colors = new ArrayList<>();
		Collections.addAll(colors, PegColor.values());
		Collections.addAll(colors, PegColor.values());
		Collections.shuffle(colors);
		
		return colors.subList(0, 4);
	}

}

