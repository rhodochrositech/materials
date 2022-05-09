package application;

import java.time.LocalTime;

// imports here a
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;


public class Main extends Application {
	public static int easy;
	public static int medium;
	public static int hard;
	public static LocalTime starttime;
	public static LocalTime endtime;
	public static int easyw=0;
	public static int easyl=0;
	public static int medw=0;
	public static int medl=0;
	public static int hardw=0;
	public static int hardl=0;
	private static BoardBrain brain=new BoardBrain();
	private static int max = brain.getMax();
	private static double diff = brain.getDiff();
	private static Board board;
	
	public static void setstarttime() { // Updates static start timer when called
		starttime = LocalTime.now();
	}
	
	public static void setendtime() { // Updates static end timer when called
		endtime = LocalTime.now();
	}
	
	public static int calctime() { // Finds the difference in seconds, between the start and end times. Goes up to the hour incase user starts game at X:59, as it would throw value to the negative otherwise.
		return (endtime.getSecond()-starttime.getSecond())+((endtime.getMinute()-starttime.getMinute())*60)+((endtime.getHour()-starttime.getHour())*360);
	}
	
	public static void settime(int npos) { // sets the selected time from selected difficulty, pos selected from UserDataController.startclicked()
		if (pos==0) {
			easy=calctime();
		} else if (pos==1) {
			medium=calctime();
		} else if (pos==2) {
			hard=calctime();
		}
	}
	
	public static int pos;
	public static void setpos(int npos) { // Sets the position, used in UserDataController.startclicked()
		pos = npos;
	}
	
	public static void winloss(int pos) { // Stores local game win and lose values
		if (pos==0) {
			easyw+=1;
		} else if (pos==1) {
			medw+=1;
		} else if (pos==2) {
			hardw+=1;
		} else if (pos==3) {
			easyl+=1;
		} else if (pos==4) {
			medl+=1;
		} else if (pos==5) {
			hardl+=1;
		}
		
	}
	
	
	private static void initboard() { // Statically creates a board
		board = new Board(true,brain);
	}
	private static void setMax(int max) { // Updates static Max value (size of board)
		Main.max = max;
		brain.setMax(max);
	}
	private static void setDiff(double diff) { // Updates static Diff value (float % chance to be a bomb) 
		Main.diff = diff;
		brain.setDiff(diff);
	}
	
	public static void restart() { // Called to restart the game
		Main.closeCurStage();
		Main.loading();
	}
	
	
	private static Button  gameLabel = new Button();

	public static void Run(int max, double diff) { // This creates the actual board
		Stage primaryStage = new Stage();
		Main.setDiff(diff);
		Main.setMax(max);
		Main.initboard();
		// initialize creation and opening of the gameboard
		primaryStage.setTitle("Minesweeper");
		VBox pane = new VBox();
		pane.setPrefHeight(500);
		Cell[][] cells = board.getAllCells();
		pane.getChildren().addAll(board);
		pane.getChildren().addAll(gameLabel);
		gameLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
			// This handles the mouse click for wither right or left mouse button is clicked
			@Override
			public void handle(MouseEvent m) {
				Main.setCurStage(primaryStage);
				Main.setendtime();
				Main.calctime();
				System.out.println(Main.calctime());
				Main.restart();
			}
		});
		Main.GameRestartStyle();
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static Stage curStage;
	public static void setCurStage(Stage stage) { // Just holds which visual page is active
		curStage = stage;
	}
	public static void closeCurStage() { // Closes that currently primary screen
		curStage.close();
	}
	public static void loading() { // Begins all work of the program
		try {
			Stage primaryStage = new Stage();
			primaryStage.setTitle("Events...");
			
			// get an FXML Loader and read in the fxml code
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/UserData.fxml"));
			AnchorPane sampleLayout = (AnchorPane)loader.load();
			// Create the scene with the layout in the fxml code, set the scene and show it
			Scene scene = new Scene(sampleLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			Main.setCurStage(primaryStage);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) { // Program begins
		Main.setCurStage(primaryStage);
		loading();
	}
	
	public static void GameRestartStyle() {
		// game is reset, resetting the style
		gameLabel.setText("RESTART");
		Font font = Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 24);
		gameLabel.setFont(font);
		gameLabel.setAlignment(Pos.CENTER);
		gameLabel.setMinWidth(400);
		gameLabel.setMinHeight(95);
		gameLabel.setTextFill(Color.BLACK);
	}
	
	public static void GameOverStyle() {
		// game is lost, ending the game
		gameLabel.setText("GAME OVER \n");
		Font font = Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 24);
		gameLabel.setFont(font);
		gameLabel.setAlignment(Pos.CENTER);
		gameLabel.setMinWidth(400);
		gameLabel.setMinHeight(95);
		gameLabel.setTextFill(Color.RED);
		}
	public static void GameWonStyle() {
		// game is won
		// this sets the design elements if game is won
		gameLabel.setText("CONGRATULATIONS, YOU WON!");
		Font font = Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 24);
		gameLabel.setFont(font);
		gameLabel.setAlignment(Pos.CENTER);
		gameLabel.setMinWidth(400);
		gameLabel.setMinHeight(95);
		gameLabel.setTextFill(Color.GREEN);
	}
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void GameOver() {
		// game is over - Ends game reveals all remaining tiles
		for (int i =0;i<max;i++) {
			for (int j =0 ;j<max;j++) {
				board.getAllCells()[i][j].unveilAll();
			}
		}
		winloss(pos+3);
		GameOverStyle();
	}
	
	public static void GameWon() {
		// game is won - shows win message - reveals all remaining tiles
		for (int i =0;i<max;i++) {
			for (int j =0 ;j<max;j++) {
				board.getAllCells()[i][j].unveilAll();
			}
		}
		winloss(pos);
		GameWonStyle();
	}
}
