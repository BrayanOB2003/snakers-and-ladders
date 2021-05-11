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
		
	}
}
