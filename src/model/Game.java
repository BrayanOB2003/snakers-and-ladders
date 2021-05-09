package model;

public class Game {

	
	Board root;
	
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
		board.setScore(score);
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
	
	public void showInformation() {
		inOrden(root);
	}
	
	public void inOrden(Board root) {
		if(root != null) {
			inOrden(root.getLeft());
			System.out.println(root.getNicknameWinner() + "  " + root.getScore());
			inOrden(root.getRight());
		}
	}
}
