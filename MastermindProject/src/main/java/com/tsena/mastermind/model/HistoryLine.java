package com.tsena.mastermind.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/**
 * Class for holding data of game history model
 * @author tsena
 *
 */
@Component
public class HistoryLine implements Serializable {

		private static final long serialVersionUID = 1L;
		
		private String gameId;
		private String date;
		private String codeMasterCombination;
		private String guessCombination;
		private String feedback;
		
		public String getGameId() {
			return gameId;
		}
		public void setGameId(String gameId) {
			this.gameId = gameId;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		
		public String getCodeMasterCombination() {
			return codeMasterCombination;
		}
		public void setCodeMasterCombination(String codeMasterCombination) {
			this.codeMasterCombination = codeMasterCombination;
		}
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
