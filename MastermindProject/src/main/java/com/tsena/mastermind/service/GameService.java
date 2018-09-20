package com.tsena.mastermind.service;

import java.util.List;

import com.tsena.mastermind.constant.PegColor;

/**
 * Class for services for the game
 * @author tsena
 *
 */
public interface GameService {

	/**
	 * method to initilize a new game
	 * @param gameId - unique id
	 * @throws Exception
	 */
	public void initializeGame(String gameId) throws Exception;
	
	/**
	 * method to receive feedback for the given parameter list
	 * @param pegs - list of variables of type PegColor
	 * @return string that represents feedback
	 * @throws Exception
	 */
	public String giveFeedback(List<PegColor> pegs) throws Exception;
	
}
