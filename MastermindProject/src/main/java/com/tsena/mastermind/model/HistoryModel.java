package com.tsena.mastermind.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Class for holding data of game history model
 * @author tsena
 *
 */
@Component
public class HistoryModel implements Serializable {

		private static final long serialVersionUID = 1L;
		
		private String gameId;
		private Date date;
		private String codeMasterCombination;
		private List<GuessModel> guessCombination;
		
		public String getGameId() {
			return gameId;
		}
		public void setGameId(String gameId) {
			this.gameId = gameId;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public String getCodeMasterCombination() {
			return codeMasterCombination;
		}
		public void setCodeMasterCombination(String codeMasterCombination) {
			this.codeMasterCombination = codeMasterCombination;
		}
		public List<GuessModel> getGuessCombination() {
			return guessCombination;
		}
		public void setGuessCombination(List<GuessModel> guessCombination) {
			this.guessCombination = guessCombination;
		}
		
}
