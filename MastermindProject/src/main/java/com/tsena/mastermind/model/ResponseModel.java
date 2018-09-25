package com.tsena.mastermind.model;

import java.io.Serializable;
import java.util.List;

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
	private List<HistoryLine> data;
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
	public List<HistoryLine> getData() {
		return data;
	}
	public void setData(List<HistoryLine> data) {
		this.data = data;
	}
	
	

}
