package model;

public class Board {
	public static final int WIDTH = 10;
	public static final int HEIGHT = 10; 
	public static final int TILESIZE = 100;
	
	private Tile[][] boardMatrix;
	
	public Board() {
		this.boardMatrix = new Tile[WIDTH][HEIGHT];
		Piece dummyShark = new DummyShark();
	
		for(int i = 0; i < WIDTH; i++) {
			for(int j = 0; j < HEIGHT; j++) {
			
				this.boardMatrix[i][j] = null;
			}
		}
		
		this.boardMatrix[0][0] = dummyShark;
	}
		
	public void removePiece(int i, int j) {
		this.boardMatrix[i][j] = null;
	}

	
	public void setPiece(Tile tile, int i, int j) {
		this.boardMatrix[i][j] = tile;
	}

	
	public Tile getPiece(int i, int j){
		return this.boardMatrix[i][j];
	}
	
	
	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}

	public int getTileSize() {
		return TILESIZE;
	}

	

}
