package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleGroup;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.RadioButton;

public class GameLevelsController {
	@FXML
	private Label gameSize;
	@FXML
	private Label gameDifficulty;
	@FXML
	private RadioButton easyButton;
	@FXML
	private ToggleGroup difficultyGroup;
	@FXML
	private RadioButton mediumButton;
	@FXML
	private RadioButton hardButton;
	@FXML
	private RadioButton tenByTen;
	@FXML
	private ToggleGroup sizeGroup;
	@FXML
	private RadioButton fiftyByFifty;
	@FXML
	private RadioButton twentyByTwenty;
    @FXML
    private Button startGameButton;
    @FXML
    private Button leaderboardButton;
    
	private Stage stage;
	private long userTime;
	private static Label gameStatusLabel = new Label();
	private static Button restartButton = new Button();
	
	// Create Array List to hold the all entries
	private static ArrayList<UserEntry> player = new ArrayList<>();
	
	// Appends each run to the file
	public static void addPlayer(UserEntry p) {
		player.add(p);
		
		for (UserEntry pl: player) {
			database.getInstance().writeToFileButtonClick(pl.toString());	
		}
	}
	
	//getters and setters for variables to use in array list
	public static String getDifficulty () {
		return gameDif;
	}
	
	public static String getSize () {
		return boardSize;
	}
	
	// generates and stores info for creating the game
	public void setStage (Stage stage) {
			this.stage = stage;
	}
	
	static String boardSize = "";

	@FXML
	private Button startButton;
	@FXML
	private void continueClick(ActionEvent event) throws IOException {
		boardSize = ((RadioButton)sizeGroup.getSelectedToggle()).getText();
		//easier than writing ifs, but more to remember.
		startGame(boardSize);
	}
	
	long startingTime;
	static String gameDif = "";
	public void startGame(String size) throws IOException {
		CustomTimer.getInstance().run();
		
    	//gameDif = ((RadioButton) gameDifficulty.getSelectedToggle()).getText();
    	int mx = 0;
    	int my = 0;
    	// Use getters and setters to get the difficulty from this page and set it on BuildTile.java
    	if (easyButton.isSelected()) {
    		// Generate Less Bombs
    		gameDif = "easy";

    	}
    	else if (mediumButton.isSelected()) {
    		// generate more bombs 
    		gameDif = "medium";
    	}
    	
    	else if (hardButton.isSelected()){
    		// generate most bombs
    		gameDif = "hard";
    	}
    	
    	// Set the selected game size
    	String gameSize = "";
    	if (tenByTen.isSelected()) {
    		gameSize = "10 x 10";
    		mx = 10;
    		my = 10;
    	}
    	
    	else if (twentyByTwenty.isSelected()) {
    		gameSize = "20 x 20";
    		mx = 20;
    		my = 20;
    	}
    	
    	if (fiftyByFifty.isSelected()) {
    		gameSize = "30 x 30";
    		mx = 30;
    		my = 30;
    	}
		
    	// Generates creation of game board
		FXMLLoader loader = new FXMLLoader(); // FXMLLoader is initialized
		loader.setLocation(Main.class.getResource("game.fxml")); // Takes in first resource, main page
		VBox pane = new VBox();
		pane.setPrefHeight(my * 30 + 80);
		pane.setPrefWidth(mx * 30);
		gameStatusLabel.setText("start a new game");
		gameStatusLabel.setMinWidth(200);
		gameStatusLabel.setMinHeight(25);
		restartButton.setText("Restart");
		AnchorPane gamePane = (AnchorPane) loader.load(); // Loads those resources
		pane.getChildren().addAll(gamePane);
		pane.getChildren().addAll(gameStatusLabel);
		pane.getChildren().addAll(restartButton);
		Stage gameStage = new Stage(); // Generate a stage
		Scene gameScene = new Scene(pane); // Generate a scene populated by loader
		gameStage.setScene(gameScene); // redirect
		
		// restarts the game for user
		restartButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				gameStage.hide();
				Platform.runLater( () -> new Main().start( new Stage() ) );
			}
		});
		
		gameStage.show(); // Display
		gameController brain = loader.getController();
		brain.setDifficulty(gameDif);
		brain.Build(mx, my, gameDif); // Execute gameController method (handles game logic)
		
		
	}

	// Message styling for loss
	public static void GameOverStyle() {
		// game is lost, ending the game
		gameStatusLabel.setText("GAME OVER \n");
		gameStatusLabel.setPrefWidth(500);
		Font font = Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 20);
		gameStatusLabel.setFont(font);
		gameStatusLabel.setAlignment(Pos.CENTER);
		gameStatusLabel.setMinWidth(400);
		gameStatusLabel.setMinHeight(45);
		gameStatusLabel.setTextFill(Color.RED);
	}
	
	//Message styling for win
	public static void GameWinStyle() {
		gameStatusLabel.setText("YOU WON \n");
		gameStatusLabel.setPrefWidth(500);
		Font font = Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 20);
		gameStatusLabel.setFont(font);
		gameStatusLabel.setAlignment(Pos.CENTER);
		gameStatusLabel.setMinWidth(400);
		gameStatusLabel.setMinHeight(45);
		gameStatusLabel.setTextFill(Color.GREEN);
	}

	// Handler to open leaderboard GUI
    @FXML
    void leaderboardButtonClick(ActionEvent event) {
    	try {
    		// Loads the leaderboard GUI
	    	FXMLLoader loadLeaderboard = new FXMLLoader(); // FXMLLoader is initialized
	    	loadLeaderboard.setLocation(Main.class.getResource("Leaderboard.fxml")); // Takes in first resource, main page
			AnchorPane LeaderPane = (AnchorPane)loadLeaderboard.load(); 
		
			// Loads those resources
			Stage LeaderboardStage = new Stage(); // Generate a stage
			Scene LeaderboardScene = new Scene(LeaderPane); // Generate a scene populated by loader
			LeaderboardStage.setScene(LeaderboardScene); // redirect
			LeaderboardController lbc = loadLeaderboard.getController();
			//lbc.setStage(LeaderboardStage);
			LeaderboardStage.show(); // Display 
    	}
    	
    	catch(Exception e) {
			e.printStackTrace();
		}
    }
	
	
}
