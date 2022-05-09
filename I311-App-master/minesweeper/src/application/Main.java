package application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Main extends Application {


	@Override
	public void start(Stage primaryStage) { 
		try {
			//Begin loading of program
			// Load game setup pane
			FXMLLoader loadSetUp = new FXMLLoader(); // FXMLLoader is initialized
			loadSetUp.setLocation(Main.class.getResource("GameLevels.fxml")); // Takes in first resource, main page
			AnchorPane setUpPane = (AnchorPane)loadSetUp.load();  
			
			GameLevelsController glc = loadSetUp.getController();
			glc.setStage(primaryStage);
			
			
			// Loads those resources
			Stage setUpStage = new Stage(); // Generate a stage
			Scene setUpScene = new Scene(setUpPane); // Generate a scene populated by loader
			setUpStage.setScene(setUpScene); // redirect
			setUpStage.show(); // Display 
		

		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		launch(args);
	}
}

