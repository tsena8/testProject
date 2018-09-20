package com.tsena.mastermind.service.impl;

import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsena.mastermind.component.log.LogGameInteraction;
import com.tsena.mastermind.component.log.LogGameSession;
import com.tsena.mastermind.model.GameModel;
import com.tsena.mastermind.service.LogService;

@Service("LogService")
public class LogServiceImpl implements LogService {

	private static final Logger logger = Logger.getLogger(LogServiceImpl.class);
	
	@Autowired
	private LogGameSession logGameSession;
	
	@Autowired
	private LogGameInteraction logGameInteraction;

	@Override
	public void logSession(String gameId, GameModel newGame) {
		String colorRowStr = newGame.getCodemakerColorRow().stream().map(Enum::name).collect(Collectors.joining(","));
		logGameSession.trace(gameId, colorRowStr);
		
	}

	@Override
	public void logInteraction(GameModel interaction) {
		String colorRowStr = interaction.getGuessColorRow().stream().map(Enum::name).collect(Collectors.joining(","));
		String feedbackStr = interaction.getFeedback().stream().map(Enum::name).collect(Collectors.joining(","));
		
		logger.debug(" -- gameId: "  + interaction.getGameId());
		logger.debug(" -- colorRowStr: "  + colorRowStr);
		logger.debug(" -- feedbackStr: "  + feedbackStr);
		
		logGameInteraction.trace(interaction.getGameId(), colorRowStr, feedbackStr);
		
	}

}
