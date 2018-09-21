package com.tsena.mastermind.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tsena.mastermind.api.config.ApplicationConfig;
import com.tsena.mastermind.component.CsvHandler;
import com.tsena.mastermind.constant.PegColor;
import com.tsena.mastermind.model.GameModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class CsvHandlerTest {

	@Autowired
	private CsvHandler csvHandler;
	
	@Test
	public void readLastGameTest() {
		try {
			GameModel gameModel = csvHandler.readGameSession();
			assertTrue(contains(gameModel.getCodemakerColorRow().get(0)));
			assertTrue(contains(gameModel.getCodemakerColorRow().get(1)));
			assertTrue(contains(gameModel.getCodemakerColorRow().get(2)));
			assertTrue(contains(gameModel.getCodemakerColorRow().get(3)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	private static boolean contains(PegColor color) {

	    for (PegColor enumValue : PegColor.values()) {
	        if (enumValue.name().equals(color)) {
	            return true;
	        }
	    }

	    return false;
	}
}
