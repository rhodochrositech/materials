package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javafx.event.ActionEvent;

public class database {

	public static database instance;
	
	private database() {}
	public static database getInstance() {
		if (instance == null) {
			return new database();
		}
		return instance;
	}
	
	// Write entries to a file
	public void writeToFileButtonClick ( String display) {  //String string,
		File file = new File("MinesweeperLog.txt");
		try {
			// file writer opens file to be written to
			FileWriter fileWriter = new FileWriter(file, true);
			PrintWriter out = new PrintWriter(fileWriter);
			out.write(display);
			// must close file writer
			out.close();	
		}
		catch (IOException e) {
			System.out.println("File not Found");
			}
		}
		
	// Reads the data in file
	public String readClick() {
		File fileReader = new File("MinesweeperLog.txt");
		String toReturn = "";
		try (Scanner fileScan = new Scanner(fileReader))
		// opens file
			{
			// sets the text from pet entry to display
			while(fileScan.hasNextLine()) {
				toReturn += fileScan.nextLine() + "\n";
				//System.out.print(toReturn);
			}
			fileScan.close();
		}
		catch (IOException e) {
			System.out.println("File not found");
		}
			
		return toReturn;
	}
}
