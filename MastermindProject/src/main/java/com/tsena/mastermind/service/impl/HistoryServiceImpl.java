package com.tsena.mastermind.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsena.mastermind.component.CsvHandler;
import com.tsena.mastermind.model.GameModel;
import com.tsena.mastermind.model.HistoryModel;
import com.tsena.mastermind.service.HistoryService;

@Service("HistoryService")
public class HistoryServiceImpl implements HistoryService {

	private static final Logger logger = Logger.getLogger(HistoryServiceImpl.class);
	
	@Autowired
	private CsvHandler csvHandler;
	
	public HistoryModel getHistory() throws Exception {

		GameModel gameModel = csvHandler.readGameSession();
		
		HistoryModel history = csvHandler.readGameInteractions(gameModel);
		
		return history;
	}

}
