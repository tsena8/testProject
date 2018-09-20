package com.tsena.mastermind.component.log;

public interface ILogResult {
	
	
	public void trace(String gameId, String colors);
	
	public void trace(String gameId, String message1, String message2);

}
