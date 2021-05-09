package model;

public class Board {

	
	private Box first;
	private Player players;
	
	private int numRows;
	private int numColumns;
	private int numSnakes;
	private int numLadders;
	private int score;
	private char playerWinner;
	private String nicknameWinner;
	
	private Board right;
	private Board left;
	private Board p;
	
	

	public Board(int n, int m, int numSnakes, int numLadders, char[] players) {
		numRows = n;
		numColumns = m;
		this.numSnakes = numSnakes;
		this.numLadders = numLadders;
		makeBoard();
		char a = 65;
		makeSnakes(a, numSnakes);
		int i = 1;
		makeLadders(numLadders, i);
		putPlayers(players);
	}
	
	public Board(int score, String nickname, Player players, char playerWinner, int n, int m, int s, int l) {
		
	}
	
	//Put the players in initial position
	
	
	private void putPlayers(char[] players) {
		putPlayers(0, players);
	}
	
	private void putPlayers(int i, char[] players) {
		if(i <  players.length) {
			putPlayers(numRows, first, players[i]);
			i++;
			putPlayers(i, players);
		}
	}
	
	private void putPlayers(int n, Box current, char p) {
		if(current.getDown().getRow() == n-1) {
			current.getDown().addPlayer(p);
			players = current.getDown().getPlayers();
		}else {
			putPlayers(n, current.getDown(), p);
		}
	}
	
	//Player movement
	
	public void playerMove(int move, char p) {
		playerMove(numRows, first, p, move);
	}
	
	private void playerMove(int n, Box current, char p, int move) {
		if(current.getDown().getRow() == n-1) {
			moveToRigth(current.getDown(), p, move);
		}else {
			playerMove(n, current.getDown(), p, move);
		}
	}

	private void moveToRigth(Box current, char p, int move) {
		int i = move;
		
		if (current != null) {
			
			if (current.getUp() != null) {
				
				if (current.contain(p)) {
					
					current.removePlayer(p);
					System.out.println("1");
					if ((current.getCol() + move) < numColumns) {
						
						if (i == 0) {
							current.addPlayer(p);
							addAmountMovement(p);
						} else {
							i--;
							moveToRigth(current.getNext(), p, i);
						}
						
					} else {
						moveToLeft(current.getUp(), p, i - (numColumns - 1));
					}
					
				} else {
					moveToRigth(current.getNext(), p, move);
				}

			} else {
				//moveToRigthWitouUp(current, i);
			} 
			
		}else {
			//moveToLeft(current.getUp(), p, move);
		}
	}
	
	

	private void addAmountMovement(char p) {
		addAmountMovement(p, players);
	}
	
	private void addAmountMovement(char p, Player current) {
		if(current.getCharacter() == p) {
			current.setAmountMovements();
		}else {
			addAmountMovement(p, current.getNext());
		}
	}

	private void moveToLeft(Box current, char p, int move) {
		int i = move;
		if(current.getUp() != null) {
			if(current.getPrev() != null) {			
				if(i == 0) {
					current.addPlayer(p);
					addAmountMovement(p);
				} else {
					current.removePlayer(p);
					i--;
					moveToLeft(current.getPrev(), p, i);
				}
			}else {
				//current.getUp().setBoxNumber(i+1);
				//enumToRigth(current.getUp(), i+1);
			}
			
		}else {
			//enumToLeftWithouUp(current, i);
		}
	}

	/*
	private void moveToLeftWithouUp(Box current, int i) {
		if(current.getPrev() != null) {
			current.getPrev().setBoxNumber(i+1);
			enumToLeftWithouUp(current.getPrev(), i+1);
		}
	}
	

	private void moveToRigthWitouUp(Box current, int i) {
		if(current.getNext() != null) {
			current.getNext().setBoxNumber(i+1);
			enumToRigthWitouUp(current.getNext(), i+1);
		}
	}
	*/
	//---------------------------------------Make Snakes---------------------------------------
	
	private void makeSnakes(char a, int numSnakes) {
		
		if(numSnakes > 0) {
			int random = (int) (Math.random()*numColumns);
			makeHeadSnakeRight(first, random, a);
			makeSnakes((char) (a+1), numSnakes-1);
		}
		
	}
	
	
	
	private void makeHeadSnakeRight(Box current, int n, char a) {
		
		if(n > 0) {
			
			if(current.getNext() != null) {
				makeHeadSnakeRight(current.getNext(), n-1, a);
			}else {
				makeSnakeHeadLeft(current, n, a);
			}
			
		}else {
			int random = (int) (Math.random()*(numRows-1));
			goToDownRandom(current, random, a);
			
		}
	}
	
	
	
	private void makeSnakeHeadLeft(Box current, int n, char a) {
		
		if(n > 0) {
			if(current.getPrev() != null) {
				makeSnakeHeadLeft(current.getPrev(), n-1, a);
			}else {
				makeHeadSnakeRight(current, n-1, a);
			}
			
		}else {
			int random = (int) (Math.random()*numRows);
			goToDownRandom(current, random, a);
			
		}
	}

	private void goToDownRandom(Box current, int n, char a) {
		
		if(n > 0 ) {
			
			if(current.getDown() != null) {
				goToDownRandom(current.getDown(), n-1, a);
			}else {
				
			}
		}else {
			
			if(current.getSnake() > 0 || current.getLadder() > 0) {
				makeHeadSnakeRight(first, (int) (Math.random()*numColumns), a);
			}else {
				
				current.setSnake(a);
				int random = (int) (Math.random()*numColumns);
				makeTailSnakeRigth(current, random, a );
			}
			
		}
	}
	
	private void makeTailSnakeRigth(Box current, int n, char a) {
		if(n > 0) {
			if(current.getNext() != null) {
				makeTailSnakeRigth(current.getNext(), n-1, a);
			}else {
				makeTailSnakeLeft(current, n-1, a);
			}
			
		}else {
			
			int random = (int) (Math.random()*(numRows - current.getRow()));
			if(random == 0) {
				random = 1;
				makeTailSnakeDown(current, random, a);
			}else {
				makeTailSnakeDown(current, random, a);
			}
			
			
		}
	}

	private void makeTailSnakeLeft(Box current, int n, char a) {
		if(current.getPrev() != null) {
			if(n > 0) {
				makeTailSnakeLeft(current.getPrev(), n-1, a);
			}else {
				
				int random = (int) (Math.random()*(numRows - current.getRow()));
				if(random == 0) {
					random = 1;
					makeTailSnakeDown(current, random, a);
				}else {
					makeTailSnakeDown(current, random, a);
				}
			}
		}else {
			makeTailSnakeRigth(current, n-1, a);
		}
		
	}

	private void makeTailSnakeDown(Box current, int n, char a) {
		if(n > 0) {
			
			if(current.getDown() != null) {
				makeTailSnakeDown(current.getDown(), n-1, a);
			}else {
				
				if(current.getSnake() > 0) {
					ifBoxSnakeIsRepeatRigth(current, a);
				}else {
					current.setSnake(a);
				}
			}
			
		}else {
			
			if(current.getSnake() > 0) {
				ifBoxSnakeIsRepeatRigth(current, a);
			}else {
				current.setSnake(a);
			}
		}
		
	}
	
	private void ifBoxSnakeIsRepeatRigth(Box current, char a) {
		
		if(current.getNext() != null) {
			if(current.getNext().getSnake() < 65) {
				current.getNext().setSnake(a);
			}else {
				ifBoxSnakeIsRepeatRigth(current.getNext(), a);
			}
		}else {
			ifBoxSnakeIsRepeatLeft(current, a);
		}
		
	}

	
	private void ifBoxSnakeIsRepeatLeft(Box current, char a) {
		
		if(current.getPrev() != null) {
			if(current.getPrev().getSnake() < 65) {
				current.getPrev().setSnake(a);
			}else {
				ifBoxSnakeIsRepeatLeft(current.getPrev(), a);
			}
		}else {
			System.out.println("Demasiadas serpientes :(");
		}
	}
			
	//-------------------------------------------Make Ladders--------------------------------------------

	
	private void makeLadders(int numLadders, int character) {
		
		if(numLadders > 0) {
			
			int random = (int) (Math.random()*numColumns);
			makeHeadLaddersRigth(first, random, character);
			makeLadders(numLadders - 1, character + 1);
		}

		
	}
	
	
	private void makeHeadLaddersRigth(Box current, int n, int character) {
		
		if(n > 0) {
			if(current.getNext() != null) {
				makeHeadLaddersRigth(current.getNext(), n-1, character);
			}else {
				makeHeadLaddersLeft(current, n, character);
			}
		}else {
			int random = (int) (Math.random()*(numRows-1));
			goToDownLaddersRandom(current, random, character);
		}
	}

	private void goToDownLaddersRandom(Box current, int n, int character) {
		if(n > 0 ) {
			
			if(current.getDown() != null) {
				goToDownLaddersRandom(current.getDown(), n-1, character);
			}else {
				
			}
		}else {
			
			if(current.getSnake() > 0 || current.getLadder() > 0) {
				makeHeadLaddersRigth(first, (int) (Math.random()*numColumns), character);
			}else {
				
				current.setLadder(character);
				int random = (int) (Math.random()*numColumns);
				makeTailLadderRigth(current, random, character);
			}
			
		}
		
	}

	private void makeTailLadderRigth(Box current, int n, int character) {
		if(n > 0) {
			if(current.getNext() != null) {
				makeTailLadderRigth(current.getNext(), n-1, character);
			}else {
				makeTailLadderLeft(current, n-1, character);
			}
			
		}else {
			
			int random = (int) (Math.random()*(numRows - current.getRow()));
			if(random == 0) {
				random = 1;
				makeTailLadderDown(current, random, character);
			}else {
				makeTailLadderDown(current, random, character);
			}
			
			
		}
		
	}

	private void makeTailLadderLeft(Box current, int n, int character) {
		if(current.getPrev() != null) {
			if(n > 0) {
				makeTailLadderLeft(current.getPrev(), n-1, character);
			}else {
				
				int random = (int) (Math.random()*(numRows - current.getRow()));
				if(random == 0) {
					random = 1;
					makeTailLadderDown(current, random, character);
				}else {
					makeTailLadderDown(current, random, character);
				}
			}
		}else {
			makeTailLadderRigth(current, n-1, character);
		}
		
	}

	private void makeTailLadderDown(Box current, int n, int character) {
		if(n > 0) {
			
			if(current.getDown() != null) {
				makeTailLadderDown(current.getDown(), n-1, character);
			}else {
				
				if(current.getSnake() > 0 || current.getLadder() > 0) {
					ifBoxLadderIsRepeatRigth(current, character);
				}else {
					current.setLadder(character);
				}
			}
			
		}else {
			
			if(current.getSnake() > 0 || current.getLadder() > 0) {
				ifBoxLadderIsRepeatRigth(current, character);
			}else {
				current.setLadder(character);
			}
		}
		
	}

	private void makeHeadLaddersLeft(Box current, int n, int character) {
		
		if(n > 0) {
			if(current.getPrev() != null) {
				makeHeadLaddersLeft(current.getPrev(), n-1, character);
			}else {
				makeHeadLaddersRigth(current, n-1, character);
			}
			
		}else {
			int random = (int) (Math.random()*numRows);
			goToDownLaddersRandom(current, random, character);
			
		}
	}

	private void ifBoxLadderIsRepeatRigth(Box current, int character) {
		
		if(current.getNext() != null) {
			if(current.getNext().getSnake() < 65 && current.getNext().getLadder() == 0) {
				current.getNext().setLadder(character);
			}else {
				ifBoxLadderIsRepeatRigth(current.getNext(), character);
			}
		}else {
			ifBoxLadderIsRepeatLeft(current, character);
		}
	}

	private void ifBoxLadderIsRepeatLeft(Box current, int character) {
		if(current.getPrev() != null) {
			if(current.getPrev().getSnake() < 65 && current.getPrev().getLadder() == 0) {
				current.getPrev().setLadder(character);
			}else {
				ifBoxLadderIsRepeatLeft(current.getPrev(), character);
			}
		}else {
			System.out.println("Demasiadas escaleras :(");
		}
		
	}

	//----------------------------------------Hacer Matriz-----------------------------------------------
	public void makeBoard() {
		first = new Box(0,0);
		createRow(0,0, first);
		searchInitialPosition(numRows, first, 1);
	}



	private void createRow(int i, int j, Box currentFirstRow) {

		createCol(i,j+1, currentFirstRow, currentFirstRow.getUp());
		if(i+1 < numRows) {
			Box downFirstRow = new Box(i+1,j);
			downFirstRow.setUp(currentFirstRow);
			currentFirstRow.setDown(downFirstRow);
			createRow(i+1, j, downFirstRow);
		}
	}


	private void createCol(int i, int j, Box prev, Box rowPrev) {
		
		if(j < numColumns) {
			Box current = new Box(i,j);
			current.setPrev(prev);
			prev.setNext(current);
			
			if(rowPrev != null) {
				rowPrev = rowPrev.getNext();
				current.setUp(rowPrev);
				rowPrev.setDown(current);
			}
			createCol(i, j+1, current, rowPrev);
		}
	}
	
	
	//-----------------------------------Imprimir matriz---------------------------------------------------
	
	public String toString() {
		String msg;
		msg = toStringRow(first);
		return msg;
	}


	private String toStringRow(Box firstRow) {
		String msg = "";
		if(firstRow != null) {
			msg = toStringCol(firstRow) + "\n";
			msg += toStringRow(firstRow.getDown());
		}
		return msg;
	}


	private String toStringCol(Box current) {
		String msg = "";
		if(current != null) {
			msg = current.toString();
			msg += toStringCol(current.getNext());
		}
		return msg;
	}

	//---------------------------------------Enumerar Matriz----------------------------------------------
	private void searchInitialPosition(int n, Box current, int i) {
		if(current.getDown().getRow() == n-1) {
			current.getDown().setBoxNumber(i);
			enumToRigth(current.getDown(), i);
		}else {
			searchInitialPosition(n, current.getDown(), i);
		}
	}

	private void enumToRigth(Box current, int i) {

		if(current.getUp() != null) {
			if(current.getNext() != null) {
				current.getNext().setBoxNumber(i+1);
				enumToRigth(current.getNext(), i+1);
			}else {
				current.getUp().setBoxNumber(i+1);
				enumToLeft(current.getUp(), i+1);
			}
			
		}else {
			enumToRigthWitouUp(current, i);
		}
	}
	
	private void enumToLeft(Box current, int i) {
		
		if(current.getUp() != null) {
			if(current.getPrev() != null) {
				current.getPrev().setBoxNumber(i+1);
				enumToLeft(current.getPrev(), i+1);
			}else {
				current.getUp().setBoxNumber(i+1);
				enumToRigth(current.getUp(), i+1);
			}
			
		}else {
			enumToLeftWithouUp(current, i);
		}
	}


	private void enumToLeftWithouUp(Box current, int i) {
		if(current.getPrev() != null) {
			current.getPrev().setBoxNumber(i+1);
			enumToLeftWithouUp(current.getPrev(), i+1);
		}
	}
	

	private void enumToRigthWitouUp(Box current, int i) {
		if(current.getNext() != null) {
			current.getNext().setBoxNumber(i+1);
			enumToRigthWitouUp(current.getNext(), i+1);
		}
	}
	
	
	//-------------------------------------Getters and setters ----------------------------------------------
	
	


	public int getNumColumns() {
		return numColumns;
	}


	public int getNumRows() {
		return numRows;
	}


	public Box getFirst() {
		return first;
	}


	public void setFirst(Box first) {
		this.first = first;
	}

	public int getNumSnakes() {
		return numSnakes;
	}

	public int getNumLadders() {
		return numLadders;
	}
	
	public Board getRight() {
		return right;
	}

	public void setRight(Board right) {
		this.right = right;
	}

	public Board getLeft() {
		return left;
	}

	public void setLeft(Board left) {
		this.left = left;
	}

	public Board getP() {
		return p;
	}

	public void setP(Board p) {
		this.p = p;
	}

	public char getPlayerWinner() {
		return playerWinner;
	}

	public Player getPlayers() {
		return players;
	}

	public void setPlayers(Player players) {
		this.players = players;
	}

	public void setPlayerWinner(char playerWinner) {
		this.playerWinner = playerWinner;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getNicknameWinner() {
		return nicknameWinner;
	}

	public void setNicknameWinner(String nicknameWinner) {
		this.nicknameWinner = nicknameWinner;
	}
}
