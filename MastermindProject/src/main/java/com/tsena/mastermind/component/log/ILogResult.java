package com.tsena.mastermind.component.log;

/**
 * Interface to log game session and interactions to CSV using log4j Logger
 * @author tsena
 *
 */
public interface ILogResult {
	
	public void trace(String gameId, String colors);
	
	public void trace(String gameId, String message1, String message2);

}
