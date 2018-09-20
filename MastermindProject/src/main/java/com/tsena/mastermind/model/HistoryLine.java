package com.tsena.mastermind.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class HistoryLine implements Serializable {

		private static final long serialVersionUID = 1L;
		
		private String gameId;
		private String date;
		private String codeMaster;
		private String codeGuess;
		private String feedback;
		
		public String getGameId() {
			return gameId;
		}
		public void setGameId(String gameId) {
			this.gameId = gameId;
		}
		public String getCodeMaster() {
			return codeMaster;
		}
		public void setCodeMaster(String codeMaster) {
			this.codeMaster = codeMaster;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getCodeGuess() {
			return codeGuess;
		}
		public void setCodeGuess(String codeGuess) {
			this.codeGuess = codeGuess;
		}
		public String getFeedback() {
			return feedback;
		}
		public void setFeedback(String feedback) {
			this.feedback = feedback;
		}
		
}
