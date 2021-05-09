package ui;

import model.Box;

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
		
		
		Box box = new Box(2,3);
		box.addPlayer('&');
		box.addPlayer('%');
		box.addPlayer('#');
		System.out.println(box.printPlayers());
		box.removePlayer('#');
		System.out.println(box.printPlayers());
		System.out.println(box.contain('#'));
		
		//char e = 0;
		//System.out.println(e);
	}
}
