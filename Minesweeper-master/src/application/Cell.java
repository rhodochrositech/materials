package application;

// imports
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Cell extends StackPane {
	
	private boolean flag;
	private String _id;
	private boolean isBomb;
	private int x;
	private int y;
	private int bombNumbers;
	private Text text = new Text();
	private Rectangle tile = new Rectangle();
	private boolean isMasked;

	
	//Cell constructors
	public Cell(String _id, boolean isBomb, int x, int y, boolean isMasked) {
		super();
		this.flag = true;
		this._id = _id;
		this.isBomb = isBomb;
		this.x = x;
		this.y = y;
		this.isMasked = isMasked;

		setLayout();
		setMouseAction();

	}

	private void setMouseAction() {
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			// This handles the mouse click for wither right or left mouse button is clicked
			@Override
			public void handle(MouseEvent m) {
				if (m.getButton() == MouseButton.PRIMARY) {
					unveil();
					isMasked=false;
					if (isBomb) {
						// calls the GameOver method in main class when bomb is clicked
						Main.GameOver();
					}
					if (Board.getBombCounts() == Board.getTotalMaskedCell()) {
						// game is won, calls GameWon method
						Main.GameWon();
					}
					
					// call searchNeighbors method
					//searchNeighbors(); ##################Call on every click!
					
					
					//System.out.print(Board.getTotalMaskedCell());
					
				}
				
				//when the right mouse button is clicked, we want the tile to stay hidden but "flag" the tile.
				if(m.getButton() == MouseButton.SECONDARY) {
					text.setText("?");
					text.setVisible(true);
					//set text color ("?")
					text.setFill(Color.BLACK);
					//set tile color 
					tile.setFill(Color.YELLOW);
				}
			}
		});
	}
	
	//Unveils all tiles once a bomb has been clicked. This method is called in the main class when the game is over.
	public void unveilAll() {
		if(isMasked == true) {
			// do nothing
		} 
		// should be able to see the bomb number or the bomb
		tile.setFill(Color.WHITE);
		text.setVisible(true);
		if (!isBomb) {
		if (this.bombNumbers != 0) {
			//sets bomb proximity counter to cell
			text.setText("" + this.bombNumbers);
			
		}
		else {
			// sets cell to blank for no bombs
			text.setText(" ");
		}
		} 
		else {
			// sets text for bomb cells
			text.setText("BOMB");
			text.setStyle("-fx-font: 12 arial");
			text.setFill(Color.RED);
		}
		isMasked = false;
	
	}
	public void unveil() {
		//unveil all not bomb cells
		isMasked=false;
		if (!isBomb) {
			if(isMasked == true) {
				// do nothing
			}
			if (this.getBombNumbers() == 0) {
				searchNeighbors();
			}
			// should be able to see the bomb number or the bomb
			tile.setFill(Color.WHITE);
			if (this.bombNumbers != 0) {
				text.setText("" + this.bombNumbers);
			}
			else {
				text.setText(" ");
			}
			
			text.setVisible(true);

		}
	}
	
	private void searchNeighbors() {
		if(this.flag==true) {
			this.flag=false;
			for (Cell c : Board.Neighbors(this)) {
				c.unveil();
			}
		}
	}
			

		
	
	
		
	private void setLayout() {
		// sets the cell size and dimensions
		tile.setWidth(40);
		tile.setHeight(40);

		if (isBomb) {
			// sets the text for bomb cells
			text.setText("BOMB");
			text.setStyle("-fx-font: 12 arial");
			text.setFill(Color.RED);
		} else {
			text.setText("");
		}

		// sets properties for masked cells
		text.setVisible(!isMasked);
		tile.setFill(Color.LIGHTGRAY);
		tile.setStroke(Color.BLACK);
		this.getChildren().addAll(tile, text);
	}

	public String getTileId() {
		// coordinates for cell
		return _id;
	}
	public void setTileId(String id) {
		// calls variable 
		this._id = id;
	}
	public boolean isBomb() {
		// returns whether bomb or not
		return isBomb;
	}
	public void setBomb(boolean isBomb) {
		// calls variable 
		this.isBomb = isBomb;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setText(int count) {
		text.setText("" + count);
		this.bombNumbers = count;
	}

	public int getBombNumbers() {
		return bombNumbers;
	}

	public void setBombNumbers(int bombNumbers) {
		this.bombNumbers = bombNumbers;
	}

	public boolean isMasked() {
		return isMasked;
	}

	public void setMasked(boolean isMasked) {
		this.isMasked = isMasked;
	}
	
	
	
	

}
