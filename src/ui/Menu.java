package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.Game;

public class Menu implements Runnable{
	
	
	private Game savedGames;
	private Game newGame;
	private Thread simulation;
	
	public void startProgram() throws IOException {

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
			if (gameIn.length == 5) {
				createGame(gameIn);
				System.out.println(newGame.printBoard());
				String turn = br.readLine();
				currentGame(turn);
			} else {
				startProgram();
			}
			break;
				
			case 2:
				savedGames.showInformation();
				System.out.println();
				startProgram();
				break;
				
			case 3:
				System.out.println("Gracias por jugar.");
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
		
		if(savedGames == null) {
			savedGames = newGame;
		}
	}
	
	private void currentGame(String in) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		
		if(in.equals("")) {
			
			System.out.println(newGame.throwDice());
			
			if(newGame.thereWinner() != null) {
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

			startSimulation();
			
		} else if(in.equals("menu")) {
			
			startProgram();
			
		} else if(in.equals("leave")) {
			saveGame();
		}
		
		br.close();
	}
	
	public void saveGame() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Digita el nickName del ganador: ");
		String nick = br.readLine();
		savedGames.addPlayerWinner(newGame.getCurrentGame(),nick);
		startProgram();
	}
	
	public void startSimulation() throws IOException {
		simulation = new Thread(this);
		simulation.start();
	}
	
	@Override
	public void run() {
		
		Thread current = Thread.currentThread();
		if(current == simulation) {
			if (newGame.thereWinner() == null) {
				System.out.println(newGame.throwDice());
				try {
					Thread.sleep(0);
					run();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			} else {
				try {
					saveGame();
				} catch (IOException e) {

				}
			}
		}
	}
}
