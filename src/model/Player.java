package model;

public class Player {
	
	private char character;
	private int amountMovements;
	private int index;
	private Player next;
	
	
	public Player(char p) {
		character = p;
	}
	

	public char getCharacter() {
		return character;
	}

	public void setCharacter(char character) {
		this.character = character;
	}

	public Player getNext() {
		return next;
	}

	public void setNext(Player next) {
		this.next = next;
	}

	public int getAmountMovements() {
		return amountMovements;
	}

	public void setAmountMovements() {
		this.amountMovements ++;
	}


	public int getIndex() {
		return index;
	}


	public void setIndex(int index) {
		this.index = index;
	}

}
