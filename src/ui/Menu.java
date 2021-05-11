package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.Game;

public class Menu {
	
	Game newGame;
	
	public void startProgram() throws IOException {
		//6 6 3 3 &%#*
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("1. Iniciar juego\n" + "2. Tablero de posiciones\n" + "3. Salir");
		int option = 0;
		try {
			option = Integer.parseInt(br.readLine());
		}catch(NumberFormatException e) {
			startProgram();
		}
		
		switch(option) {
			case 1:
				
				System.out.println("Digita la entrada del juego: ");
				String[] gameIn = br.readLine().split(" ");
				createGame(gameIn);
				System.out.println(newGame.printBoard());
				
				String turn = br.readLine();
				currentGame(turn);
				
				break;
				
			case 2:
				
				break;
				
			case 3:
				
				break;
				
			default:
				
				break;
		}
		
		br.close();
	}
	
	private void createGame(String[] gameIn) {
		int n = Integer.parseInt(gameIn[0]);
		int m = Integer.parseInt(gameIn[1]);
		int numSnake = Integer.parseInt(gameIn[2]);
		int numLadders = Integer.parseInt(gameIn[3]);
		char[] players = gameIn[4].toCharArray();
		
		newGame = new Game(n, m, numSnake, numLadders, players);
	}
	
	private void currentGame(String in) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		
		if(in.equals("")) {
			
			System.out.println(newGame.throwDice());
			System.out.println(newGame.printCurrentGame());
			if(newGame.thereWinner() != null) {
				System.out.println(newGame.thereWinner());
				line = "leave";
				currentGame(line);
			} else {
				line = br.readLine();
				currentGame(line);
			}
			
		} else if(in.equals("num")) {
			
			System.out.println(newGame.printBoard());
			line = br.readLine();
			currentGame(line);
			
		} else if(in.equals("simul")) {
			
			newGame.startSimulation();
			
		} else if(in.equals("menu")) {
			
			startProgram();
			
		} else if(in.equals("leave")) {
			saveGame();
			startProgram();
		}
		
		br.close();
	}
	
	public void saveGame() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Digita el nickName del ganador: ");
		String nick = br.readLine();
		newGame.addPlayerWinner(nick);
	}
}
