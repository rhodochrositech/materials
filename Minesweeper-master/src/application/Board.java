package application;


// imports for board
import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.GridPane;
public class Board extends GridPane{
	private static BoardBrain brain;
	private static int max;
	private static double diff;
	
	private Cell[][] allCells;
	public void setAllCells() {
		allCells = new Cell[max][max];
	}
	private static int cellRemaining;
	
	public static void setBrain(BoardBrain brain) {
		Board.brain=brain;
	}
	
	public static void setMax(int max) {
		Board.max = brain.getMax();
	}
	public static void setDiff(double diff) {
		Board.diff = brain.getDiff();
	}
	
	
	public static int getMax() {
		return max;
	}
	
	public static int getCellRemaining() {
		return cellRemaining;
	}

	public void setCellRemaining(int cellRemaining) {
		this.cellRemaining = cellRemaining;
	}
	
	public static void decrementCellCount() {
		cellRemaining -= 1;
	}

	public Cell[][] getAllCells() {
		return cellList;
	}

	public void setAllCells(Cell[][] allCells) {
		this.allCells = cellList;
	}

	//method that creates our cells. Math.random() is used to give us a 12% chance of the cell being a bomb. 
	//~12 bombs / 100 cells. 
	public void createCells(boolean b) {
		 for (int row = 0; row < max; row++) {
		    	for (int col = 0; col < max; col++) {
		    		String id = "" + row + "," + col; // "5,4"
		    		Cell cell = new Cell(id, Math.random() < diff, row, col, b); // ######## Modular difficulty percentage
		    		
		    		cellList[row][col] = cell;
		    		this.add(cell, col, row, 1, 1);
		    	}
		    }
		 cellRemaining = (max*max) - max;


		 // iterates through the pane of cells to get neighbors
		   for (int row = 0; row < max; row++) {
		    	for (int col = 0; col < max; col++) {
		    		int bombCount = 0;
		    		Cell cell = cellList[row][col];
		    		neighbors =  Neighbors(cell);
		    		
		    		// iterates through neighbor cells to calculate # of bombs
		    		for (Cell c : neighbors) {
		    			if (c.isBomb()) {
		    				bombCount ++;
		    			}
		    			//System.out.println("Bomb Count: " + bombCount);
		    		}
		    		
		    		// set bomb count to cell text
		    		if (bombCount > 0 && !cell.isBomb())
		    			cell.setText(bombCount);
		    			cell.setBombNumbers(bombCount);
		    	}
			}
	}
	
	public static int getBombCounts() {
		// sets a variable to count bomb numbers in entire grid pane
		 int bombCountMain = 0;
		 for (int row = 0; row < max; row++) {
		    	for (int col = 0; col < max; col++) {
		    		Cell cell = cellList[row][col];
		    		if (cell.isBomb()) {
		    			bombCountMain += 1;
		    		}
		    	}
		 }
		 return bombCountMain;
	}
	
	public static int getTotalMaskedCell() {
		// counts the total mask cells in pane
		int totalMaskedCell = 0;
		 for (int row = 0; row < max; row++) {
		    	for (int col = 0; col < max; col++) {
		    		Cell cell = cellList[row][col];
		    		if (cell.isMasked()) {
		    			totalMaskedCell += 1;
		    		}
		    	}
		 }
		 return totalMaskedCell;
	}
	
	// cellList Array
	private static Cell[][] cellList;
	private void setcellList() {
		cellList=new Cell[max][max];
	}
	// List for neighboring cells 
	static List<Cell> neighbors;
	
	public Board(boolean b,BoardBrain brain) {
		super();
		Board.setBrain(brain);
		Board.setMax(brain.getMax());
		Board.setDiff(brain.getDiff());
		setAllCells();
		setcellList();
	    // Just to see that the lines are actually added
	    setGridLinesVisible(true);
	    setPrefSize(400, 400);
	    
// Code still in progress for setting exact bombs in pane at random
//	    if (b) {
//	    	java.util.Random random = new java.util.Random();
//	    	List<Integer> bombs = new ArrayList<Integer>();
//	    	while (bombs.size() < 10) {
//	    		int random_bomb = random.nextInt(100);
//	    		bombs.add(random_bomb);
//	    	} 
//	    }
	    	createCells(b);
	}
	
	public static List<Cell> Neighbors(Cell cell) {
		// creates neighbors array list to store neighbor cells 
		List<Cell> neighbors = new ArrayList<>();
		
		// a loop to go through xMIn to xMax
		// adds the cells in proximity to the clicked cell to the neighbors 
		// L= Left ; R = Right; U = Up; D = Down; X =  x-axis; Y = y-axis
		if (cell.getX() >= 0 && cell.getX() <= max-1 && cell.getY() >= 0 && cell.getY() <= max-1) { // ORIGINAL MODIFIED:::: Comparisons inclusive (>=)
			int LUX = cell.getX() - 1;
			int LUY = cell.getY() - 1;
			if (LUX  >= 0 && LUX  <= max-1 && LUY >= 0 && LUY <= max-1) { // ORIGINAL MODIFIED:::: Each of these add calls are wrapped with a new if post-muta
				neighbors.add(cellList[LUX][LUY]);
			}
			
			int UX = cell.getX() - 1;
			int UY = cell.getY();
			if (UX  >= 0 && UX  <= max-1 && UY >= 0 && UY <= max-1) {
				neighbors.add(cellList[UX][UY]);
			}
			int RUX = cell.getX() - 1;
			int RUY = cell.getY() + 1;
			if (RUX  >= 0 && RUX  <= max-1 && RUY >= 0 && RUY <= max-1) {
				neighbors.add(cellList[RUX][RUY]);
			}
			int LX = cell.getX();
			int LY = cell.getY() - 1;
			if (LX  >= 0 && LX  <= max-1 && LY >= 0 && LY <= max-1) {
				neighbors.add(cellList[LX][LY]);
			}
			int RX = cell.getX();
			int RY = cell.getY() + 1;
			if (RX  >= 0 && RX  <= max-1 && RY >= 0 && RY <= max-1) {
				neighbors.add(cellList[RX][RY]);
			}
			int LDX = cell.getX() + 1;
			int LDY = cell.getY() - 1;
			if (LDX  >= 0 && LDX  <= max-1 && LDY >= 0 && LDY <= max-1) {
				neighbors.add(cellList[LDX][LDY]);
			}
			int DX = cell.getX() + 1;
			int DY = cell.getY();
			if (DX  >= 0 && DX  <= max-1 && DY >= 0 && DY <= max-1) {
				neighbors.add(cellList[DX][DY]);
			}
			int RDX = cell.getX() + 1;
			int RDY = cell.getY() + 1;
			if (RDX  >= 0 && RDX  <= max-1 && RDY >= 0 && RDY <= max-1) {
				neighbors.add(cellList[RDX][RDY]);
			}
			
		}

		return neighbors;
		
	}
}
