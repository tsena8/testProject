package com.tsena.mastermind.component.log;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class LogResult {

	private static final String MESSAGE = "{0}|{1}|{2}";
	private static final String MESSAGE_ITERACTION = "{0}|{1}|{2}|{3}";
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void trace(Logger logger, String gameId, String colors) {
		logger.trace(MessageFormat.format(MESSAGE, new Object[] {
				sdf.format(new Date()), gameId, colors }));
		
	}

	public void trace(Logger logger, String gameId, String message1, String message2) {
		logger.trace(MessageFormat.format(MESSAGE_ITERACTION, new Object[] {
				sdf.format(new Date()), gameId, message1, message2}));
	}
}
