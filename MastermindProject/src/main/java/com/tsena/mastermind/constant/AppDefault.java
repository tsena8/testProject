package com.tsena.mastermind.constant;

/**
 * Class for holding application constants
 * @author tsena
 *
 */
public class AppDefault {

	public static final String SESSION_CSV = "game_session.csv";
	public static final String INTERACTION_CSV = "game_interaction.csv";
	
	public static final String COLOR_SEPARATION = ",";
	public static final String CSV_SEPARATION = "\\|";
	public static final char CSV_SEPARATION_CHAR = '|';
	public static final String COLOR_SEPARATION_ESCAPED = "\\,";
	
	public enum FeedbackType {
		BLACK, WHITE
	};

}
