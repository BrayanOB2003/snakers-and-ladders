package model;

public class Box {
	private int boxNumber;
	private int row;
	private int col;
	private char snake;
	private int ladder;
	private String players;
	
	public Box(int r, int c, int b) {
		row = r;
		col = c;
		boxNumber = b;
	}
	
	public int getBoxNumber() {
		return boxNumber;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public char getSnake() {
		return snake;
	}
	
	public void setSnake(char s) {
		snake = s;
	}

	public int getLadder() {
		return ladder;
	}

	public void setLadder(int ladder) {
		this.ladder = ladder;
	}

	public String getPlayers() {
		return players;
	}

	public void setPlayers(String players) {
		this.players = players;
	}
}
