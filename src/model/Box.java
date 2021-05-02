package model;

public class Box {
	
	private Box next;
	private Box prev;
	private Box up;
	private Box down;
	
	private int boxNumber;
	private int row;
	private int col;
	private char snake;
	private int ladder;
	private String players;
	
	public Box(int r, int c) {
		row = r;
		col = c;
	}
	
	public int getBoxNumber() {
		return boxNumber;
	}
	
	public void setBoxNumber(int boxNumber) {
		this.boxNumber = boxNumber;
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
	
	public Box getNext() {
		return next;
	}

	public void setNext(Box next) {
		this.next = next;
	}

	public Box getPrev() {
		return prev;
	}

	public void setPrev(Box prev) {
		this.prev = prev;
	}

	public Box getUp() {
		return up;
	}

	public void setUp(Box up) {
		this.up = up;
	}

	public Box getDown() {
		return down;
	}

	public void setDown(Box down) {
		this.down = down;
	}

	public void setPlayers(String players) {
		this.players = players;
	}
	
	public String toString() {
		//return "(" + row + "," + col +")";
		return "( " + snake + ladder + " )  ";
	}
}
