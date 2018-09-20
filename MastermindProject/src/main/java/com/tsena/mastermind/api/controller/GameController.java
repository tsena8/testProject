package com.tsena.mastermind.api.controller;

import static java.util.Arrays.asList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tsena.mastermind.component.PegColorConverter;
import com.tsena.mastermind.constant.PegColor;
import com.tsena.mastermind.service.GameService;

/**
 * Rest API Spring controller class
 * 
 * @author tsena
 *
 */
@RestController
@RequestMapping("/game")
public class GameController {

	@Autowired
	private GameService gameService;

	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<String> create() {
		String gameId = UUID.randomUUID().toString();
		try {
			gameService.initializeGame(gameId);
			return new ResponseEntity<String>("Done", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/send/{peg}", method = RequestMethod.GET)
	public ResponseEntity<String> giveFeedbackOne(@PathVariable(value = "peg") PegColor peg) {
		logger.debug(" >>> " + peg);
		try {
//			String feedback = gameService.giveFeedback(pegs);
			return new ResponseEntity<String>("OK", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
	
	}
	
	@RequestMapping(value = "/guess/{peg1}/{peg2}/{peg3}/{peg4}", method = RequestMethod.GET)
	public ResponseEntity<String> giveFeedback(@PathVariable(value = "peg1") PegColor peg1,
			@PathVariable(value = "peg2") PegColor peg2,
			@PathVariable(value = "peg3") PegColor peg3,
			@PathVariable(value = "peg4") PegColor peg4 ) {
		/*
		if (pegs== null || pegs.size()!=4) {
			return new ResponseEntity<String>("Please provide 4 pegs guess", HttpStatus.BAD_REQUEST);
		}
		*/
		logger.debug(" >>> " + peg1 + " | " +  peg2 + " | " + peg3 + " | " +  peg4);
		List<PegColor> pegs = asList(peg1, peg2, peg3, peg4);
		try {
			String feedback = gameService.giveFeedback(pegs);
			return new ResponseEntity<String>(feedback, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
	
	}
	
	@InitBinder
	public void initBinder(final WebDataBinder webdataBinder) {
		webdataBinder.registerCustomEditor(PegColor.class, new PegColorConverter());
	}
	
	private static final Logger logger = Logger.getLogger(GameController.class);

}
