package com.tsena.mastermind.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsena.mastermind.component.CsvHandler;
import com.tsena.mastermind.constant.AppDefault;
import com.tsena.mastermind.model.GameModel;
import com.tsena.mastermind.model.HistoryLine;
import com.tsena.mastermind.service.HistoryService;

@Service("HistoryService")
public class HistoryServiceImpl implements HistoryService {

	private static final Logger logger = Logger.getLogger(HistoryServiceImpl.class);
	
	@Autowired
	private CsvHandler csvHandler;
	
	public List<HistoryLine> getHistory() throws Exception {

		GameModel gameModel = csvHandler.readGameSession();
		
		String codeMasterCombination = gameModel.getCodemakerColorRow().stream().map(Enum::name).collect(Collectors.joining(AppDefault.COLOR_SEPARATION));
		List<HistoryLine> historyList = csvHandler.readGameInteractions(gameModel.getGameId(), codeMasterCombination);
		
		return historyList;
	}

}
