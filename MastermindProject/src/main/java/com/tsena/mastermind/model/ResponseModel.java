package com.tsena.mastermind.model;

import java.io.Serializable;
import org.springframework.stereotype.Component;

/**
 * Java class for holding data of game history response model
 * @author tsena
 *
 */
@Component
public class ResponseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private boolean success;
	private String message;
	private HistoryModel data;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HistoryModel getData() {
		return data;
	}
	public void setData(HistoryModel data) {
		this.data = data;
	}
}