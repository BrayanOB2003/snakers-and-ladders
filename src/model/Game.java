package model;

public class Game {
	
	private Board currentGame;
	
	private int numPlayers;
	private int playerTurn;
	private String info;
	private int num;
	
	private Board root;
	
	public Game(int n, int m, int numSnakes, int numLadders, char[] players) {
		currentGame = new Board(n, m, numSnakes, numLadders, players);
		numPlayers = players.length;
		playerTurn = 0;
		info = "";
	}
	
	public String printCurrentGame() {
		return currentGame.toStringGame();
	}
	
	public String printBoard() {
		return currentGame.toString();
	}
	
	public String throwDice() {
		int dice = (int) (Math.random()*6 + 1);
		String game = PlayerTurn(dice) + "\n\n" + printCurrentGame() + "\n";
		String win = (thereWinner() != null)?thereWinner():"";
		win = game + win;
		
		return win;
	}
	
	public String thereWinner() {
		char p = currentGame.getPlayerWinner();
		if(p != ' ') {
			return "El jugador " + p + " ha ganado el juego con " + currentGame.searchPlayer(p).getAmountMovements() + " movimientos.";
		} else{
			return null;
		}
	}
	
	private String PlayerTurn(int move) {
		int index = playerTurn;
		String mg;
		if(index < numPlayers) {
			mg = PlayerMove(move, index);
			index++;
			playerTurn = index;
			return mg;
		} else {
			index = 0;
			playerTurn = 1;
			mg = PlayerMove(move, index);
			return mg;
		}
	}
	
	private String PlayerMove(int move, int i) {
		return currentGame.playerMove(move, i);
	}
	
	public void addPlayerWinner(Board board, String nickname) {
		addPlayerWinner(board.getPlayerWinner(), board.getPlayers(), nickname, board);
	}
	
	private void addPlayerWinner(char player, Player current, String nickname, Board board) {
		if(current.getCharacter() == player) {
			addPlayerWinner(current.getAmountMovements(), nickname, player, board);
		}else {
			addPlayerWinner(player, current.getNext(), nickname, board);
		}
		
	}

	private void addPlayerWinner(int amountMovements, String nickname, char playerWinner, Board board) {
		
		int score = amountMovements * (board.getNumColumns() * board.getNumRows());
		Board newPlayerWinner = new Board(score, nickname, board.getPlayers(), playerWinner, board.getNumRows(),
				board.getNumColumns(), board.getNumSnakes(), board.getNumLadders());
		
		if(root == null) {
			root = newPlayerWinner;
			
		} else {
			addPlayerWinner(root, newPlayerWinner);
		}
		
	}

	public void addPlayerWinner(Board current, Board newPlayerWinner) {
		
		if(newPlayerWinner.getScore() >= current.getScore()) {
			
			if(current.getLeft() == null) {
				current.setLeft(newPlayerWinner);
				newPlayerWinner.setP(current);
			} else {
				addPlayerWinner(current.getLeft(), newPlayerWinner);
			}
			
		} else {
			
			if(current.getRight() == null) {
				current.setRight(newPlayerWinner);
				newPlayerWinner.setP(current);
			} else {
				addPlayerWinner(current.getRight(), newPlayerWinner);
			}
		}
	}
	
	private void inOrden(Board root) {
		if(root != null) {
			inOrden(root.getLeft());
			num++;
			info += num + ". " + root.getPlayerWinner() + " : " + root.getNicknameWinner() + " : " + root.getScore() + "\n";
			inOrden(root.getRight());
		}
	}
	
	public Board getCurrentGame() {
		return currentGame;
	}
	
	public String getInfo() {
		inOrden(root);
		return info;
	}
}
