package com.tsena.mastermind.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/**
 * Class for holding data of game history model
 * @author tsena
 *
 */
@Component
public class GuessModel implements Serializable {

		private static final long serialVersionUID = 1L;
		
		private String guessCombination;
		private String feedback;
		
		public String getGuessCombination() {
			return guessCombination;
		}
		public void setGuessCombination(String guessCombination) {
			this.guessCombination = guessCombination;
		}
		public String getFeedback() {
			return feedback;
		}
		public void setFeedback(String feedback) {
			this.feedback = feedback;
		}
		
}
