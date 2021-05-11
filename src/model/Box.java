package model;

public class Box {
	
	private Box next;
	private Box prev;
	private Box up;
	private Box down;
	
	private Player first;
	
	private int boxNumber;
	private int row;
	private int col;
	private char snake;
	private int ladder;
	
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

	public Player getPlayers() {
		return first;
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
	
	//Set index to players
	
	private void setIndex() {
		setIndex(first, 0);
	}
	
	private void setIndex(Player current, int i) {
		if(first.getNext() == first) {
			first.setIndex(0);
		} else if(current.getNext() != first){
			current.setIndex(i);
			i++;
			setIndex(current.getNext(), i++);
		} else {
			current.setIndex(i);
		}
	}
	
	//Circular linked list of player
	public void addPlayer(char p) {
		Player player = new Player(p);
		if(first == null) {
			first = player;
			first.setNext(player);
		} else {
			addPlayer(first, player);
		}
	}
	
	private void addPlayer(Player current, Player newPlayer) {
		if(current.getNext() == first) {
			newPlayer.setNext(first);
			current.setNext(newPlayer);
			setIndex();
		} else {
			addPlayer(current.getNext(), newPlayer);
		}
	}
	
	public void removePlayer(char p) {
		if (first != null) {
			removePlayer(first, p);
		}
	}
	
	private void removePlayer(Player current, char p) {
		if(first.getCharacter() == p) {
			removeFirst(first);
		} else if(current.getNext().getCharacter() == p) {
			current.setNext(current.getNext().getNext());
		} else {
			removePlayer(current.getNext(), p);
		}
	}
	
	private void removeFirst(Player current){
		if(first.getNext() == first) {
			first = null;
		} else if(current.getNext() == first){
			first = current.getNext().getNext();
			current.setNext(first);
		}else {
			removeFirst(current.getNext());
		}
	}
	
	public boolean contain(char p) {
		if (first != null) {
			return contain(first, p);
		} else {
			return false;
		}
	}
	
	private boolean contain(Player current ,char p) {
		
		if (current.getCharacter() == p) {
			return true;
		} else if(current.getNext() != first) {
			if(contain(current.getNext(), p)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public String printPlayers() {
		String players = (first != null)?printPlayers(first, new StringBuilder()):"";
		
		return players;
	}
	
	private String printPlayers(Player current, StringBuilder pl) {
		StringBuilder players = pl;
		if(current.getNext() != first) {
			players.append(current.getCharacter());
			printPlayers(current.getNext(), players);
		} else {
 			players.append(current.getCharacter());
		}
		
		return players.toString();
	}
	
	public String toString() {
		String l = (ladder != 0)?ladder+"":" ";
		String s = (snake != 0)?snake+"":" ";
		return "( " + s + l + printPlayers() + boxNumber +" )";
	}
	
	public String toStringGame() {
		String l = (ladder != 0)?ladder+"":" ";
		String s = (snake != 0)?snake+"":" ";
		return "( " + s + l + printPlayers() +" )";
	}
}
