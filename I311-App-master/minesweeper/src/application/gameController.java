package application;

import java.util.ArrayList;

import application.gameController.mineButton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class gameController {
	static class mineButton extends Button{ // Creates an intelligent button that adapts traits from tiles
		int x; // X coord
		int y; // Y coord
		boolean isBomb; // T is bomb, F is not bomb
		int val; // Value storing # of bombs in 8 tiles around it
		Board board; // Passed the board it's generated from
		boolean flag; // Recursion breaker
		static ArrayList<ArrayList<mineButton>> List; // Class attrib: Holds collection of generated buttons 
		mineButton(int nx, int ny, boolean bomb, int val, ArrayList<ArrayList<mineButton>> List, Board board){
			// Constructor setting all values
			this.x = nx;
			this.y = ny;
			this.isBomb = bomb;
			this.val = val;
			this.List = List;
			this.board = board;
			this.flag = true;
		}
		
		
		
		public static ArrayList<ArrayList<mineButton>> getTiles() {
			return List;
		}



		public void setList(ArrayList<ArrayList<mineButton>> list) {
			List = list;
		}

		public static void GameOver() {
			// game is over - Reveals all remaining tiles once a bomb is clicked.
			ArrayList<ArrayList<mineButton>> mineButtonList = gameController.mineButton.getTiles();
			for (int i = 0; i < mineButtonList.size(); i++) {
				for (int j = 0; j <  mineButtonList.get(0).size(); j++) {
					mineButtonList.get(i).get(j).update();
				}
			}
		}
		
		public static void GameWon() {
			ArrayList<ArrayList<mineButton>> mineButtonList = gameController.mineButton.getTiles();
			for (int i = 0; i < mineButtonList.size(); i++) {
				for (int j = 0; j <  mineButtonList.get(0).size(); j++) {
					mineButtonList.get(i).get(j).update();
				}
			}
		}
		
		public void update() {
			if(isBomb) { // Handling if tile is a bomb
				this.setText("Bomb");
				//add smaller font so it fits in tile. More user friendly
				Font font = Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 7);
				this.setFont(font);
				this.setStyle("-fx-background-color: #ff0000;"); // Colors tile red if it is a bomb
				// TODO: Calculate loss condition
			}
			// TODO: Calculate what to do if empty
			else if(val == 0)  // Handling empty square
			{
				this.setOpacity(0.0); // Hide it
				int [] xPos = new int[]{x+1,x,x-1}; // These lines generate arrays of all 9 coords around square
				int [] yPos = new int[] {y+1,y,y-1};
				this.flag = false; // Prevent re=running method
				for (int xC:xPos) { // For each X coord
					for (int yC:yPos) { // And for each Y coord
						if (xC>=0&&xC<board.maxx() && yC >=0 && yC < board.maxy()) { // If it is within bounds
							if(List.get(xC).get(yC).flag) { // If that tile hasn't been updated
								List.get(xC).get(yC).update(); // Update it and do not let it get tagged again
								List.get(xC).get(yC).flag=false;
							}
						}
					}
				}
				
			}
			
			else { // Else if it's a 1-8 value, just display that as text
				this.setText(Integer.toString(val));
			}
		}
		
		// Getter Gallery: passing values out
		public boolean getBomb() {
			return this.isBomb;
		}
		public int getVal() {
			return this.val;
		}

		// ActionEvent Method, detecting left clicked
		private void clicked(MouseEvent event) {
			if (event.getButton() == MouseButton.SECONDARY) { // If right clicked instead
				this.setText("FLAG"); // Change label to flag
				Font font1 = Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 7);
				this.setFont(font1);
				this.setStyle("-fx-background-color: #ffff00;"); // Yellow color
				
			}
			else if(this.getBomb()) {
				CustomTimer.getInstance().stop();
				CustomTimer.getInstance().saveCurrentTime();
				GameOver();
				GameLevelsController.GameOverStyle();
			}
			else {
				if
				(Board.getBombCounts()==Board.getTotalMaskedCell()) {
					GameWon();
					GameLevelsController.GameWinStyle();
				}
				this.update();
			}
			
		}
	}
	@FXML
	private AnchorPane gamePane;

//	@FXML
//	public void Build(int x, int y) 
//	{
//		
//		Board Board = new Board();//(x,y); // Generates 100 tiles
//		System.out.println(Board.getSize());
//
	public void Build(int x, int y, String gameDif) 
	{
		
		Board Board = new Board(x,y, gameDif); // Generates 100 tiles
		//System.out.println(Board.getSize());

		ArrayList<ArrayList<mineButton>> XList = new ArrayList<>();
		for(ArrayList<Tile> Array :Board.getArray()) {
			ArrayList<mineButton> YList = new ArrayList<>();
			for(Tile tile:Array) { // For each tile in the array
				//System.out.println(tile.isBomb());
				mineButton tileButton = new mineButton(tile.getX(),tile.getY(),tile.isBomb(),tile.getVal(), XList, Board); // Initializing mineButton values passed with dummy tile info
				tileButton.setLayoutX(tile.getX()*30); // Each button is spaced 50 Pixels away from each other
				tileButton.setLayoutY(tile.getY()*30);
				tileButton.setPrefSize(30, 30); // Sets the size for button
				YList.add(tileButton); // Add that new tile to the list
				if(YList.size() == Board.maxx()) { // When it's full,
					XList.add(YList); // nest that list
				}
				tileButton.setOnMouseClicked(tileButton::clicked); // Sets default action on click
				gamePane.getChildren().add(tileButton); // Add each object to the display
				}
		}
		//System.out.print(XList.size()); Printing test for size
		//XList.get(6).get(5).update(); Example selecting one tile by coord
		//for(int i = 0;i < XList.size();i+=1) {
			//for (int j = 0; j < XList.get(i).size(); j+=1) {
				//XList.get(i).get(j).update();
			//}
		//}
		
	}

	public void setDifficulty(String gameDif) {
		gameDif = "";
	}

	
	
}

