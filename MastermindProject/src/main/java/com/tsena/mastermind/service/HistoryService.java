package com.tsena.mastermind.service;

import java.util.List;

import com.tsena.mastermind.model.HistoryLine;

/**
 * Class for services for getting history of the game
 * @author tsena
 *
 */
public interface HistoryService {

	/**
	 * method that generates history of the last game
	 * @return List<String>
	 */
	public List<HistoryLine> getHistory();
}
