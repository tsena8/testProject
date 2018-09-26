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
import com.tsena.mastermind.model.HistoryModel;
import com.tsena.mastermind.model.ResponseModel;
import com.tsena.mastermind.service.GameService;
import com.tsena.mastermind.service.HistoryService;

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
	
	@Autowired
	private HistoryService historyService;

	
	/**
	 * Post method to initialize new game 
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Object> create() {
		ResponseModel response = new ResponseModel();
		
		String gameId = UUID.randomUUID().toString();
		
		try {
			gameService.initializeGame(gameId);
			response.setSuccess(true);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.setSuccess(true);
			response.setMessage(e.getMessage());
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
	}

	/**
	 * Get method to test if correct PegColor enum type variable is read from REST request
	 * @param peg - PegColor
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/send/{peg}", method = RequestMethod.GET)
	public ResponseEntity<Object> giveFeedbackOne(@PathVariable(value = "peg") PegColor peg) {
		try {
			return new ResponseEntity<Object>("OK", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
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
	public ResponseEntity<Object> giveFeedback(@PathVariable(value = "peg1") PegColor peg1,
			@PathVariable(value = "peg2") PegColor peg2,
			@PathVariable(value = "peg3") PegColor peg3,
			@PathVariable(value = "peg4") PegColor peg4 ) {
		
		ResponseModel response = new ResponseModel();
		List<PegColor> pegs = asList(peg1, peg2, peg3, peg4);
		
		try {
			String feedback = gameService.giveFeedback(pegs);
			response.setSuccess(true);
			response.setMessage(feedback);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.setSuccess(true);
			response.setMessage(e.getMessage());
			return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
		}
	
	}
	
	/**
	 * Get method to receive the last game's historyreadGameInteractions
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/history", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getHistory() {
		ResponseModel response = new ResponseModel();
		
		try {
			HistoryModel history = historyService.getHistory();
			response.setSuccess(true);
			response.setData(history);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			response.setSuccess(false);
			response.setMessage(e.getMessage());
			
			return new ResponseEntity<Object>(response,HttpStatus.SERVICE_UNAVAILABLE);
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
