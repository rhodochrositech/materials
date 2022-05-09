package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.ToggleGroup;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.util.Arrays;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.stage.Stage;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;


public class UserDataController {
	@FXML
	private Label easyTime;
	@FXML
	private Label mediumTime;
	@FXML
	private Label hardTime;
	@FXML
	private RadioButton easyRadio;
	@FXML
	private ToggleGroup difficulty;
	@FXML
	private RadioButton medRadio;
	@FXML
	private RadioButton hardRadio;
	@FXML
	private RadioButton smallRadio;
	@FXML
	private ToggleGroup size;
	@FXML
	private RadioButton mediumRadio;
	@FXML
	private RadioButton largeRadio;
	@FXML
	private RadioButton customRadio;
	@FXML
	private Button startButton;
	@FXML
	private Button historyButton;
	@FXML
	private TextField Cust;
	
	@FXML
	private Label easytime;
	
	
	@FXML
	private Label medtime;
	
	@FXML
	private Label hardtime;
	

	public void setTimers() { // Unimplemented timer update function
		easytime.setText(Integer.toString(Main.easy)+" Seconds");
		medtime.setText(Integer.toString(Main.medium)+" Seconds");
		hardtime.setText(Integer.toString(Main.hard)+" Seconds");
	}
	
	// Event Listener on Button[#startButton].onAction
	@FXML
	public void startClicked(ActionEvent event) { // When the game is started, select what difficulty and set the size and difficulty float
		int max = 8;
		int pos=0;
		double diff = 0.04;
		if (smallRadio.isSelected()) {
			max = 10;
		}
		else if (mediumRadio.isSelected()) {
			max = 15;
		}
		else if (largeRadio.isSelected()) {
			max = 20;
		}
		else if (customRadio.isSelected()) {
			max = Integer.valueOf(Cust.getText());
		}
		
		if (easyRadio.isSelected()) {
			diff = 0.10;
			pos = 0;
		}
		else if (medRadio.isSelected()) {
			diff = 0.20;
			pos = 1;
		}
		else if (hardRadio.isSelected()) {
			diff = 0.30;
			pos = 2;
		}
		Main.setstarttime();
		Main.setpos(pos);
		Main.Run(max, diff);
		Main.closeCurStage();
		setTimers();
	}
	// Event Listener on Button[#historyButton].onAction
	/*
	 * https://www.tutorialspoint.com/javafx/bar_chart.htm - NOAH WEHNER
	 * This is the website where I found the tutorial for creating a bar graph
	 * It works by using code from the javafx library to create a bar graph that automatically
	 * adjusts to the variables you entered
	 * I modified the bar graph by changing the x and y axis, and tile to variables suited for our 
	 * minesweeper data, then we created two variables for each of the three difficulties to track our users 
	 * wins and loses which is the values the bar graph displays
	 */
	@FXML
	public void historyClicked(ActionEvent event) {
		  
		  Stage stage = new Stage();
		 //Defining the axes              
	      CategoryAxis xAxis = new CategoryAxis();  
	      xAxis.setCategories(FXCollections.<String>
	      observableArrayList(Arrays.asList("Easy", "Medium", "Hard")));
	      xAxis.setLabel("Difficulty");
	       
	      NumberAxis yAxis = new NumberAxis();
	      yAxis.setLabel("total");
	     
	      //Creating the Bar chart
	      BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis); 
	      barChart.setTitle("Difficulty Level Wins/Losses");
	        
	      //Prepare XYChart.Series objects by setting data       
	      XYChart.Series<String, Number> series1 = new XYChart.Series<>();
	      series1.setName("Wins");
	      series1.getData().add(new XYChart.Data<>("Easy", Main.easyw));
	      series1.getData().add(new XYChart.Data<>("Medium", Main.medw));
	      series1.getData().add(new XYChart.Data<>("Hard", Main.hardw));
	      
	        
	      XYChart.Series<String, Number> series2 = new XYChart.Series<>();
	      series2.setName("Loses");
	      series2.getData().add(new XYChart.Data<>("Easy", Main.easyl));
	      series2.getData().add(new XYChart.Data<>("Medium", Main.medl));
	      series2.getData().add(new XYChart.Data<>("Hard", Main.hardl));
	      

	      //Setting the data to bar chart       
	      barChart.getData().addAll(series1, series2);
	        
	      //Creating a Group object 
	      Group root = new Group(barChart);
	        
	      //Creating a scene object
	      Scene scene = new Scene(root, 600, 400);

	      //Setting title to the Stage
	      stage.setTitle("Bar Chart");
	        
	      //Adding scene to the stage
	      stage.setScene(scene);
	        
	      //Displaying the contents of the stage
	      stage.show();        
	}
}
