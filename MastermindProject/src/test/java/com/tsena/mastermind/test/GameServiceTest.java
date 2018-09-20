package com.tsena.mastermind.test;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tsena.mastermind.api.config.ApplicationConfig;
import com.tsena.mastermind.service.GameService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class GameServiceTest {

	@Autowired
	private GameService gameService;
	
	@Test
	public void testGameService() {
		assertEquals("class com.tsena.mastermind.service.impl.GameServiceImpl",
				this.gameService.getClass().toString());
	}
	
	@Test 
	public void testInitializeGame() {
		try {
			gameService.initializeGame("123");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
