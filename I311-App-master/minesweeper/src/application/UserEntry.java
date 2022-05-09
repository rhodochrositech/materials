package application;

import java.util.ArrayList;
import java.util.Date;

public class UserEntry {

	// create class attributes
	// must set variable names (and make private)
	private String gameDif;
	private String gameSize;
	private double userTime;
	private Date currentDateLabel;
	
		
	// create class constructor = initialization
	public UserEntry(String gameDif, String gameSize, Date currentDateLabel, double userTime) {
		this.gameDif = gameDif;
		this.gameSize = gameSize;
		this.currentDateLabel = currentDateLabel;
		this.userTime = userTime;
		//userTime = userTime/1000;
		//String display = "User- difficulty:" + gameDif + ", size:" + gameSize + ", time:" + userTime + "\n";
	}
	

	public String getGameDif() {
		return gameDif;
	}


	public void setGameDif(String gameDif) {
		this.gameDif = gameDif;
	}


	public String getGameSize() {
		return gameSize;
	}


	public void setGameSize(String gameSize) {
		this.gameSize = gameSize;
	}


	//Override the toString() method
	@Override
	public String toString() {
		return "Game Difficulty: " + gameDif + ", Game Size: " + gameSize + ", Date: " + currentDateLabel + ", Time: " + userTime +"\n" ;
	}
		
}
