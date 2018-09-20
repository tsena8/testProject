package com.tsena.mastermind.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsena.mastermind.component.CsvHandler;
import com.tsena.mastermind.constant.PegColor;
import com.tsena.mastermind.constant.AppDefault;
import com.tsena.mastermind.constant.AppDefault.FeedbackType;
import com.tsena.mastermind.model.GameModel;
import com.tsena.mastermind.service.GameService;
import com.tsena.mastermind.service.LogService;

/**
 * Interface that holds service of game actions
 * @author tsena
 *
 */
@Service("GameService")
public class GameServiceImpl implements GameService {

	private static final Logger logger = Logger.getLogger(GameServiceImpl.class);
	
	@Autowired
	private LogService logService;

	@Autowired
	private CsvHandler csvHandler;
	
	/**
	 * Method that initialize a new game randomly picking the colors 
	 * @param gameId - unique id
	 */
	@Override
	public void initializeGame(String gameId) throws Exception {
		
		GameModel gameModel = new GameModel();
		/* old version, not good as gives duplications
		PegColor codemakerColorRow[] = new PegColor[] 
				{ PegColor.getRandomColor(), PegColor.getRandomColor(), PegColor.getRandomColor(), PegColor.getRandomColor() };
		*/
		List<PegColor> colors = PegColor.getRandom2Duplicates();
		
		gameModel.setCodemakerColorRow(colors);
		logService.logSession(gameId, gameModel);
	}

	@Override
	public String giveFeedback(List<PegColor> guessPegs) throws Exception {
		
		GameModel gameModel = csvHandler.readGameSession();
		logger.debug(" >> gameId: " + gameModel.getGameId());
		
		gameModel.setGuessColorRow(guessPegs);
		List<PegColor> cmakerPegColors = gameModel.getCodemakerColorRow(); 
		
		logger.debug(" >> cmakerPegColors: " + cmakerPegColors);
		
		List<FeedbackType> feedback  = new ArrayList<FeedbackType>();
		
		for(int i=0; i < guessPegs.size(); i++) {
		    if (guessPegs.get(i).equals(cmakerPegColors.get(i))) {
		    	feedback.add(AppDefault.FeedbackType.BLACK);
		    } else if (cmakerPegColors.contains(guessPegs.get(i))) {
		    	feedback.add(AppDefault.FeedbackType.WHITE);
		    }
		}
		logger.debug(" >> feedback: " + feedback);
		
		gameModel.setFeedback(feedback);
		
		logService.logInteraction(gameModel);
		
		String returnStr  = feedback.stream().map(Object::toString).collect(Collectors.joining(","));
		
		logger.debug(" >> feedbackStr: " + returnStr);
		
		return returnStr;
	}

}
