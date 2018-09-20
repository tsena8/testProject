package com.tsena.mastermind.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class LogLine implements Serializable {

		private static final long serialVersionUID = 1L;
		
		private String date;
		private String codeGuess;
		private String feedback;
		
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
