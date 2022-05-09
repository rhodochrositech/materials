package application;

import java.util.ArrayList;

import application.gameController.mineButton;

public class BuildBoard {

}

class Board { // A board of variable size that generates the board state. Populates itself with tiles according to dimensions.
	// Getters and setters for variables
	public int getMx() {
		return mx;
	}

	public void setMx(int mx) {
		this.mx = mx;
	}

	public int getMy() {
		return my;
	}

	public void setMy(int my) {
		this.my = my;
	}
	int mx;
	int my;
	
	public String getDifficulty() {
		return gameDif;
	}
	public void setDifficulty(String gameDif) {
		this.gameDif = gameDif;
	}
	String gameDif;
	
	ArrayList<ArrayList<Tile>> BoardArray = new ArrayList<>(); // ArrayList of ArrayLists each containing a tile
	Board(int mx, int my, String gameDif){ // Constructor with given dimensions
		this.mx = mx;
		this.my = my;
		this.gameDif = gameDif;
		for(int i=0;i<mx;i++) { // For each row, generate a List for that row
			ArrayList<Tile> TileArray = new ArrayList<>();
			for(int j=0;j<my;j++) { // For each list, generate tiles in that list = to size
				TileArray.add(new Tile(i,j, gameDif)); // Generate tile with coordinate i,j
			}
			BoardArray.add(TileArray); // Add line 16's list to our board once it is populated
		}
		genVal(this); // Run genVal and give each tile a value of bombs surrounding it
	}
	
	// Accessors
	public int maxx() {
		return this.mx;
	}
	public int maxy() {
		return this.my;
	}
	
	
	public void genVal(Board B) { // Sets each tile's val variable to # of bombs surrounding it
		ArrayList<ArrayList<Tile>> tiles = B.getArray(); // Collect array of arrays from Board
		//tile A.get().get();
		for (ArrayList<Tile> Array:tiles) { // For every nested list in the board of lists
			for (Tile tile:Array) { // For every tile in each nested list
				int x = tile.getX(); // Gets the X and Y coordinates of the tile
				int y = tile.getY();
				int [] xPos = new int[]{x+1,x,x-1}; // List all possible mutations to x and y coord {
				int [] yPos = new int[] {y+1,y,y-1}; // }
				int count = 0; // Count to set var to. Updated for each bomb.
				for (int xC:xPos) { // For each X position mutation
					for (int yC:yPos) { // For each Y position mutation
						if (xC>=0&&xC<this.maxx() && yC >=0 && yC < this.maxy()) { // As long as that mutation is within bounds
							if(tiles.get(xC).get(yC).isBomb()) { // If that tile is a bomb
								count += 1; // Increment
							}
						}
					}
				}
			//System.out.println(count); - Testing output
			tile.setVal(count); // Set tile's val
			count=0; // Reset for next tile
			}
		}
	}
	public static int getBombCounts() {
		//sets variable to count # of bombs in gridpane
		int bombCountMain = 0;
		for (ArrayList<mineButton>Array:gameController.mineButton.getTiles()) {
			for (mineButton cell : Array ) {
				if (cell.isBomb) {
					bombCountMain += 1;
				}
			}
		}
		return bombCountMain;
	}
	
	public static int getTotalMaskedCell() {
		int totalMaskedCell = 0;
		for (ArrayList<mineButton>Array:gameController.mineButton.getTiles()) {
			for (mineButton cell : Array ) {
				if (cell.flag) {
					totalMaskedCell += 1;
				}
			}
		}
		return totalMaskedCell;
	}
	
	public int getSize() { // Returns size of entire board
		int size = 0;
		for (ArrayList<Tile> List: BoardArray) { // For each nested list
			size+=List.size(); // Add it's size to a counter
		}
		return size;
	}
	public ArrayList<ArrayList<Tile>> getArray(){ // Returns nested list
		return BoardArray;
	}
}