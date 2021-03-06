package com.tsena.mastermind.component.log;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Component class that saves game interactions to CSV using log4j Logger
 * extends LogResult 
 * @author tsena
 *
 */
@Component
public class LogGameInteraction extends LogResult implements ILogResult {
	
	private static final Logger logger = Logger.getLogger(LogGameInteraction.class);


	public void trace(String gameId, String colors) {
		trace(logger, gameId, colors);
	}


	public void trace(String gameId, String message1, String message2) {
		trace(logger, gameId, message1, message2);
		
	}
	

}
