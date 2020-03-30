package model;

public class Board {
	public static final int WIDTH = 10;
	public static final int HEIGHT = 10;
	
	private Tile[][] boardMatrix;

	// Initialises board with initial piece positions
	public Board()
	{
		boardMatrix = new Tile[WIDTH][HEIGHT];

		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				boardMatrix[i][j] = new Tile();

				if (j < 2)
					boardMatrix[i][j].setPiece(new DummyShark());

				if(j > HEIGHT - 3)
					boardMatrix[i][j].setPiece(new DummyEagle());
			}
		}
	}
		
	public void removePiece(int i, int j) {
		this.boardMatrix[i][j].setPiece(null);
	}

	
	public void setPiece(Piece piece, int i, int j) {
		this.boardMatrix[i][j].setPiece(piece);
	}

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
