package com.tsena.mastermind.service;

import java.util.List;

import com.tsena.mastermind.constant.PegColor;
import com.tsena.mastermind.constant.AppDefault.FeedbackType;

public interface GameService {

	public void initializeGame(String gameId) throws Exception;
	public String giveFeedback(List<PegColor> pegs) throws Exception;
}
