package model;

import java.util.HashSet;
import java.util.Set;

public class Board {
	public static final int WIDTH = 10;
	public static final int HEIGHT = 10;
	
	private Tile[][] boardMatrix;

	// Initialises board with initial piece positions
	public Board()
	{
		boardMatrix = new Tile[WIDTH][HEIGHT];

		// Instaniate empty tiles
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				boardMatrix[i][j] = new Tile(i, j);
			}
		}

		DummyShark dummyShark1 = new DummyShark(boardMatrix[5][4]);
		DummyEagle dummyEagle1 = new DummyEagle(boardMatrix[5][5]);
	}

	public Tile getTile(int x, int y) {
		return this.boardMatrix[x][y];
	}
	
	public Piece getPiece(int i, int j){
		return this.boardMatrix[i][j].getPiece();
	}

	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}
}
