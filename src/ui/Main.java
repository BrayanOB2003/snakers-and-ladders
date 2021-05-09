package ui;

import model.Board;
import model.Box;
import model.Game;

public class Main {
	
	
	
	public static void main(String[] args) {
		
		/*
		char[] players = {'&','%','#','*'};
		
		Board a = new Board(6,6,3,3,players);
		System.out.println(a);
		
		
		a.playerMove(5, '#');
		a.playerMove(5, '&');
		System.out.println(a);
		*/
		
		/*
		Box box = new Box(2,3);
		box.addPlayer('&');
		box.addPlayer('%');
		box.addPlayer('#');
		System.out.println(box.printPlayers());
		box.removePlayer('#');
		System.out.println(box.printPlayers());
		System.out.println(box.contain('#'));
		*/
		//char e = 0;
		//System.out.println(e);
		
		Game game = new Game();
		Board a = new Board(5000, "Pedro", null, (char) 0, 0, 0, 0, 0);
		Board b = new Board(8000, "Pedro", null, (char) 0, 0, 0, 0, 0);
		Board c = new Board(4000, "Pedro", null, (char) 0, 0, 0, 0, 0);
		Board d = new Board(7000, "Pedro", null, (char) 0, 0, 0, 0, 0);
		Board e = new Board(1000, "Pedro", null, (char) 0, 0, 0, 0, 0);
		
		game.addPlayerWinner(a, b);
		game.addPlayerWinner(a, c);
		game.addPlayerWinner(a, d);
		game.addPlayerWinner(a, e);
		
		game.showInformation();
	}
}
