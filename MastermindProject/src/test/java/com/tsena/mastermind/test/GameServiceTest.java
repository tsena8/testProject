package com.tsena.mastermind.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.tsena.mastermind.api.config.ApplicationConfig;
import com.tsena.mastermind.service.GameService;

/**
 * Test class to run Junit ests for Game Service
 * @author tsena
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@WebAppConfiguration
public class GameServiceTest {

	@Autowired
	private GameService gameService;
	
	@Test
	public void testGameService() {
		assertEquals("class com.tsena.mastermind.service.impl.GameServiceImpl",
				this.gameService.getClass().toString());
	}
	
	/**
	 * test method of game initialize method in the Game service
	 * Expected the method initializeGame executes without exception 
	 */
	@Test 
	public void testInitializeGame() {
		try {
			String gameId = UUID.randomUUID().toString();
			gameService.initializeGame(gameId);
			assertTrue(true);
			
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
		
	}
	
}
