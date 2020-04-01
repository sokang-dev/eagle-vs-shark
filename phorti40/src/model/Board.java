package model;

public class Board {
	public static final int WIDTH = 10;
	public static final int HEIGHT = 10;
	
	private Tile[][] boardMatrix;

	// Initialises board with initial piece positions
	public Board()
	{
		boardMatrix = new Tile[WIDTH][HEIGHT];

		Tile tile1 = new Tile(new Coord(0, 0));

		// Instaniate empty tiles
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				boardMatrix[i][j] = new Tile(new Coord(i, j));
			}
		}

		DummyShark dummyShark1 = new DummyShark(boardMatrix[2][3]);
		DummyEagle dummyEagle1 = new DummyEagle(boardMatrix[5][5]);
	}

//	public void removePiece(int i, int j) {
//		this.boardMatrix[i][j].setPiece(null);
//	}


//	public void setPiece(Piece piece, int i, int j) {
//		this.boardMatrix[i][j].setPiece(piece);
//	}

	public void movePiece(int oldI, int oldJ, int newI, int newJ) {
		this.boardMatrix[newI][newJ].setPiece(this.boardMatrix[oldI][oldJ].getPiece());
		this.boardMatrix[oldI][oldJ].setPiece(null);
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
