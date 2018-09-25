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

@Service("GameService")
public class GameServiceImpl implements GameService {

	private static final Logger logger = Logger.getLogger(GameServiceImpl.class);
	
	@Autowired
	private CsvHandler csvHandler;
	
	@Override
	public void initializeGame(String gameId) throws Exception {
		
		GameModel gameModel = new GameModel();
		/* old version, not good as gives duplications
		PegColor codemakerColorRow[] = new PegColor[] 
				{ PegColor.getRandomColor(), PegColor.getRandomColor(), PegColor.getRandomColor(), PegColor.getRandomColor() };
		*/
		List<PegColor> colors = PegColor.getRandom2Duplicates();
		
		gameModel.setGameId(gameId);
		gameModel.setCodemakerColorRow(colors);
		csvHandler.logGameSession(gameModel);
		
	}

	@Override
	public String giveFeedback(List<PegColor> guessPegs) throws Exception {
		
		GameModel gameModel = csvHandler.readGameSession();
		
		gameModel.setGuessColorRow(guessPegs);
		List<PegColor> cmakerPegColors = gameModel.getCodemakerColorRow(); 
		
		List<FeedbackType> feedback  = new ArrayList<FeedbackType>();
		
		for(int i=0; i < guessPegs.size(); i++) {
		    if (guessPegs.get(i).equals(cmakerPegColors.get(i))) {
		    	feedback.add(AppDefault.FeedbackType.BLACK);
		    } else if (cmakerPegColors.contains(guessPegs.get(i))) {
		    	feedback.add(AppDefault.FeedbackType.WHITE);
		    }
		}
		gameModel.setFeedback(feedback);
		csvHandler.logGameInteraction(gameModel);
		
		
		String returnStr  = feedback.stream().map(Object::toString).collect(Collectors.joining(AppDefault.COLOR_SEPARATION));
		return returnStr;
	}

}
