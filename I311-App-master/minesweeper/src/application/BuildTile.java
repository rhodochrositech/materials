package application;

public class BuildTile { 
}
class Tile { // Dummy tiles that are populated by a 20% or variable chance to be a bomb, stores X, Y coordinates. To be passed to MineButton.
	int x; // X Coord
	int y; // Y Coord
	boolean bomb; // Bomb attribute, T = Bomb, F = Safe tile
	int val; // Value storing # of bombs in 8 square vacinity
	String gameDif;
	
	/*
	 * Set a string difficulty variable that is transferred between the files
	 */
	
	
	Tile(int nx, int ny, String gameDif){ // Takes a x/y value to identify tile position
		this.x = nx; // Setting X and Y values
		this.y = ny;
		int max = 100; // Generates num between 1 and 100 {
		int min = 1;
		int gen = (int)Math.floor(Math.random()*(max-min+1)+min); // }
		this.gameDif = gameDif;
		
		//Sets num of bombs based on conditions selected
		if (gameDif.equals("hard")) {
			if (gen <= 30) {
				bomb = true;
			}
			else {
				bomb = false;
			}
		}
		
		else if (gameDif.equals("medium")) {
			if (gen <= 20) {
				bomb = true;
			}
			else {
				bomb = false;
			}
		}
		
		else if (gameDif.equals("easy")) {
			if (gen <= 10) {
				bomb = true;
			}
			else {
				bomb = false;
			}
		}	
	}	
	
	// Accessors
	public void setVal(int v) {
		this.val=v;
	}
	
	public int getVal() {
		return this.val;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public boolean isBomb() {
		return bomb;
	}
	
}
