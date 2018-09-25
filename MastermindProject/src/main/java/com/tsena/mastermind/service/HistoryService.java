package com.tsena.mastermind.service;

import com.tsena.mastermind.model.HistoryModel;

/**
 * Class for services for getting history of the game
 * @author tsena
 *
 */
public interface HistoryService {

	/**
	 * method that generates history of the last game
	 * @return HistoryModel
	 * @throws Exception
	 */
	public HistoryModel getHistory() throws Exception;
}
