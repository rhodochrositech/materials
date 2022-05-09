package application;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

import javax.management.timer.Timer;

public class CustomTimer{

	private static long startTime = 0;
	private static long endTime = 0;
	public static CustomTimer instance;
	private String gameDif;
	private String gameSize;
	public static long totalTime;
	private CustomTimer() {}
	
	// Getters and Setters
	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public static CustomTimer getInstance() {
		if (instance == null) {
			return new CustomTimer();
		}
		return instance;
	}
	
	// Save the best time
	public void saveCurrentTime() {
		// create a class that adds this time to a static variable

		NumberFormat formatter = new DecimalFormat("#0.00000");
		//System.out.print("Execution time is " + formatter.format((endTime - startTime) / 1000d));
		UserEntry newUser = new UserEntry(GameLevelsController.getDifficulty(), GameLevelsController.getSize(), new Date(), (endTime - startTime) / 1000d);  // must import date
		
		// Adds the information to the player array List
		GameLevelsController.addPlayer(newUser);
		
		//System.out.println((endTime - startTime)/1000 + " seconds");
		//database.getInstance().writeToFileButtonClick((endTime - startTime)/1000 + " seconds");
	}
	

	// Run the timer
	public void run() {
		//this.start();
		startTime = System.currentTimeMillis();
		//System.out.println("Start time:" + startTime);
	}
	
	// Stop the timer
	public void stop() {
		//this.stop();
		endTime = System.currentTimeMillis();
		//return endTime;
		//System.out.println("End time:" + endTime);
	}
	
}
