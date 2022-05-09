package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class LeaderboardController {
	@FXML
	private TextArea gameLog;
	@FXML
	private Button restBoardButton;
    
	@FXML
	public void initialize( ) {
		gameLog.setText(database.getInstance().readClick());
	}

	@FXML
    void resetBoardButton(ActionEvent event) throws IOException {
		// clears the text within the log when button is clicked 
		gameLog.clear();
		PrintWriter out = new PrintWriter("MinesweeperLog.txt");
		out.print("");
		out.close();
	}

}
