package com.tsena.mastermind.service;

import com.tsena.mastermind.model.GameModel;

/**
 * Interface that holds service of logging of the game session and interactions
 * @author tsena
 *
 */
public interface LogService {

	/**
	 * method that logs game session
	 * @param gameId - game id 
	 * @param newGame - game model 
	 */
	public void logSession(String gameId, GameModel newGame);
	
	/**
	 * method that logs game interaction
	 * @param interaction - game model 
	 */
	public void logInteraction(GameModel interaction);
}
