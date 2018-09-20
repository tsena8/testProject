package com.tsena.mastermind.service.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.opencsv.CSVReader;
import com.tsena.mastermind.component.CsvHandler;
import com.tsena.mastermind.model.GameModel;
import com.tsena.mastermind.model.HistoryLine;
import com.tsena.mastermind.service.HistoryService;

@Service("HistoryService")
public class HistoryServiceImpl implements HistoryService {

	private static final Logger logger = Logger.getLogger(HistoryServiceImpl.class);
	
	@Autowired
	private CsvHandler csvHandler;
	
	public List<HistoryLine> getHistory() {

		GameModel gameModel = csvHandler.readGameSession();
		
		

		

		return list;
	}

}
