package ui;

import java.io.IOException;

import model.*;

public class Main {
	
	
	
	public static void main(String[] args) throws IOException {
		
		Menu start = new Menu();
		start.startProgram();
		//6 6 3 3 &%#*
		/*
		char[] players = {'&','%','#','*'};
		
		Game a = new Game(6,6,3,3,players);
		//System.out.println(a);
		
		a.throwDice();
		System.out.println(a.printCurrentGame());
		*/
		
		/*
		Box box = new Box(2,3);
		box.addPlayer('&');
		System.out.println(box.printPlayers());
		box.removePlayer('&');
		System.out.println(box.printPlayers());
		System.out.println(box.contain('#'));
		*/
		//char e = 0;
		//System.out.println(e);
		/*
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
		*/
	}
}
