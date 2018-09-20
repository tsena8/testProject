package com.tsena.mastermind.model;

import java.util.List;

import com.tsena.mastermind.constant.AppDefault.FeedbackType;
import com.tsena.mastermind.constant.PegColor;

/**
 * Java class for holding data of Mastermind game model
 * @author tsena
 *
 */
public class GameModel {
	
	private String gameId;
	
	private List<PegColor> codemakerColorRow;
	
	private List<PegColor> guessColorRow;
	
	private List<FeedbackType> feedback;

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public List<PegColor> getCodemakerColorRow() {
		return codemakerColorRow;
	}

	public void setCodemakerColorRow(List<PegColor> codemakerColorRow) {
		this.codemakerColorRow = codemakerColorRow;
	}

	public List<PegColor> getGuessColorRow() {
		return guessColorRow;
	}

	public void setGuessColorRow(List<PegColor> guessColorRow) {
		this.guessColorRow = guessColorRow;
	}

	public List<FeedbackType> getFeedback() {
		return feedback;
	}

	public void setFeedback(List<FeedbackType> feedback) {
		this.feedback = feedback;
	}
}
