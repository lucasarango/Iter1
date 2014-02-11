import java.util.*;

public class Main {

	public static void main(String[] args) {
		// Steps to start a new game and flow of the game!
		Player player1 = new Player("Jon");

		// 1. Initialize Board
		Board board = new Board();

		// START GAME MASTER TEST

		// 2. Initialize a playerList for GameMaster
		ArrayList<String> players = new ArrayList<String>();
		players.add("Lucas");
		players.add("Kevin");
		players.add("Fink");

		// 3. Initializing a Game
		GameMaster game = new GameMaster(players);
		System.out.println("Current Player: " + game.getPlayerName());
		System.out.println(game.getPlayerDevelopers());
		System.out.println(game.getPlayerBlocks());
		System.out.println(game.getPlayerScore());

		// 4. Player 1's Turn
		// FLOW IS: MUST PLACE LAND TILE

		// game.placeBlock();

		// PLAYER MAY:
		// 4.a Move Developer to Board
		// game.moveDeveloper(developer, newSpace);

		// 4.b Move Developer on Board
		// game.moveDeveloper(developer, newSpace);

		// 4.c Build Palace --- Skipping for now
		// 4.d Place Irrigation Tile/ 3 block

		// 5. End Turn

		// 6. New turn with new player
		game.endTurn();
		System.out.println("End Turn");
		System.out.println("Current Player: " + game.getPlayerName());
		System.out.println(game.getPlayerBlocks());

		game.endTurn();
		System.out.println("Current Player: " + game.getPlayerName());

		// END GAME MASTER TEST

		// START BOARD TEST
		printBoard(board);
		System.out.println("There are " + board.getNumThreeBlocks()
				+ " Three-Blocks left.");

		Block three = board.getThreeBlock();
		printBlock(three);
		three.rotate();
		printBlock(three);
		three.rotate();
		printBlock(three);

		board.placeBlock(three, board.getSpaces()[1][2]);

		printBoard(board);

		Block three2 = board.getThreeBlock();
		printBlock(three2);

		board.placeBlock(three2, board.getSpaces()[11][11]);

		printBoard(board);

		// END BOARD TEST

		// START PLAYER TEST

		// Action Token Count
		System.out.println(player1.getActionTokens());
		System.out.println(player1.useActionToken());
		System.out.println(player1.getActionTokens());

		// END PLAYER TEST

	}

	private static void printBlock(Block b) {
		System.out.println("============================");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (b.getGrid()[j][i] == null)
					System.out.print("+");
				else if (b.getGrid()[j][i] instanceof PalaceTile)
					System.out.print("P");
				else if (b.getGrid()[j][i] instanceof VillageTile)
					System.out.print("V");
				else if (b.getGrid()[j][i] instanceof IrrigationTile)
					System.out.print("I");
				else if (b.getGrid()[j][i] instanceof RiceTile)
					System.out.print("R");
				System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println("============================");
	}

	private static void printBoard(Board board) {
		for (int j = 0; j < board.getSpaces()[0].length; j++)
			System.out.print("==");
		System.out.println();
		for (int i = 1; i < board.getSpaces().length - 1; i++) {
			System.out.print("||");
			for (int j = 1; j < board.getSpaces()[i].length - 1; j++) {
				if (board.getSpaces()[j][i].getTile() == null)
					System.out.print("+");
				else if (board.getSpaces()[j][i].getTile() instanceof PalaceTile)
					System.out.print("P");
				else if (board.getSpaces()[j][i].getTile() instanceof VillageTile)
					System.out.print("V");
				else if (board.getSpaces()[j][i].getTile() instanceof IrrigationTile)
					System.out.print("I");
				else if (board.getSpaces()[j][i].getTile() instanceof RiceTile)
					System.out.print("R");
				System.out.print(" ");
			}
			System.out.println("||");
		}
		for (int j = 0; j < board.getSpaces()[0].length; j++)
			System.out.print("==");
		System.out.println();
	}
}
