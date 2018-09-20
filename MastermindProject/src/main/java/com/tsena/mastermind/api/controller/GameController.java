package com.tsena.mastermind.api.controller;

import static java.util.Arrays.asList;

import java.util.Arrays;
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

	
	/**
	 * Post method to initialize new game 
	 * @return ResponseEntity
	 */
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

	/**
	 * Get method to test if correct PegColor enum type variable is read from REST request
	 * @param peg - PegColor
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/send/{peg}", method = RequestMethod.GET)
	public ResponseEntity<String> giveFeedbackOne(@PathVariable(value = "peg") PegColor peg) {
		logger.debug(" >>> " + peg);
		try {
			return new ResponseEntity<String>("OK", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	
	}
	
	/**
	 * Get method to receive feedback from codemarker
	 * @param peg1  - PegColor on the first position
	 * @param peg2  - PegColor on the second position
	 * @param peg3  - PegColor on the third position
	 * @param peg4  - PegColor on the forth position
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/guess/{peg1}/{peg2}/{peg3}/{peg4}", method = RequestMethod.GET)
	public ResponseEntity<String> giveFeedback(@PathVariable(value = "peg1") PegColor peg1,
			@PathVariable(value = "peg2") PegColor peg2,
			@PathVariable(value = "peg3") PegColor peg3,
			@PathVariable(value = "peg4") PegColor peg4 ) {

		logger.debug(" >>> " + peg1 + " | " +  peg2 + " | " + peg3 + " | " +  peg4);
		List<PegColor> pegs = asList(peg1, peg2, peg3, peg4);
		try {
			String feedback = gameService.giveFeedback(pegs);
			return new ResponseEntity<String>(feedback, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	
	}
	
	/**
	 * Get method to receive the last game's history
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getHistory() {
		try {
			List<String> history = gameService.getHistory();
			return new ResponseEntity<List<String>>(history, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<String>>(Arrays.asList(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	
	}
	
	/**
	 * Binding method to register peg color enum converter for REST request 
	 * @param webdataBinder
	 */
	@InitBinder
	public void initBinder(final WebDataBinder webdataBinder) {
		webdataBinder.registerCustomEditor(PegColor.class, new PegColorConverter());
	}
	
	private static final Logger logger = Logger.getLogger(GameController.class);

}
