package com.tsena.mastermind.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.tsena.mastermind.api.config.ApplicationConfig;
import com.tsena.mastermind.component.CsvHandler;
import com.tsena.mastermind.constant.PegColor;
import com.tsena.mastermind.model.GameModel;

/**
 * Test class to run Junit ests for CSVHandler class
 * @author tsena
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@WebAppConfiguration
public class CsvHandlerTest {

	@Autowired
	private CsvHandler csvHandler;
	
	/**
	 * Test readGameSession method of CSVHandler class
	 * Expected: the game_session file is read, the last line is parsed correctly,
	 * codemaker's color row contains only defined by the application PegColor enum. 
	 */
	@Test
	public void readLastGameTest() {
		try {
			GameModel gameModel = csvHandler.readGameSession();
			
			assertNotNull(gameModel);
			assertNotNull(gameModel.getCodemakerColorRow());
			
			assertEquals(4, gameModel.getCodemakerColorRow().size());
			
			assertTrue(contains(gameModel.getCodemakerColorRow().get(0)));
			assertTrue(contains(gameModel.getCodemakerColorRow().get(1)));
			assertTrue(contains(gameModel.getCodemakerColorRow().get(2)));
			assertTrue(contains(gameModel.getCodemakerColorRow().get(3)));
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	/**
	 * Private method to check if PegColor is contained in defined PegColor values
	 * @param color
	 * @return
	 */
	private static boolean contains(PegColor color) {
		
	    for (PegColor enumValue : PegColor.values()) {
	        if (enumValue.equals(color)) {
	            return true;
	        }
	    }

	    return false;
	}
}
