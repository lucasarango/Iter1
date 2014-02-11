import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		// 1. Initialize Board
		Board board = new Board();

		ArrayList<String> players = new ArrayList<String>();
		System.out.println("How many players are playing?");
		int p = in.nextInt();

		// 2. Initialize a playerList for GameMaster

		for (int i = 0; i < p; i++) {
			System.out.println("What is the name of Player " + (i + 1));
			players.add(in.next());
		}

		// START GAME MASTER TEST

		// 3. Initializing a Game
		GameMaster game = new GameMaster(players);

		while (true) {
			System.out.println("It is currently " + game.getPlayerName()
					+ " turn");

			// 4. Player 1's Turn
			// FLOW IS: MUST PLACE LAND TILE
			System.out.println("What land tile would you like to place?");
			System.out.println("1. One block tile 2. Two block tile?");
			int k = in.nextInt();
			System.out.println("Where in the board? x y coordinate");
			printBoard(board);
			int x = in.nextInt();
			int y = in.nextInt();
			if (k == 1) {
				// Place 1 Block Tile
				Block OneBlock = game.getOneBlock();
				printBlock(OneBlock);
				board.placeBlock(OneBlock, board.getSpaces()[x][y]);
			}
			if (k == 2) {
				// Place Block Tile
				Block TwoBlock = game.getTwoBlock();
				printBlock(TwoBlock);
				System.out.println("Rotate tile? 1. Yes 2. No");
				int q = in.nextInt();
				while (q != 2) {
					if (q == 1) {
						TwoBlock.rotate();
						printBlock(TwoBlock);
					}
					System.out.println("Rotate tile? 1. Yes 2. No");
					q = in.nextInt();
				}
				board.placeBlock(TwoBlock, board.getSpaces()[x][y]);
			}
			printBoard(board);
			System.out.println(game.getActionPoints());
			boolean endTurn = false;
			while (game.getActionPoints() != 0 && !endTurn) {
				System.out.println("What else would you like to do?");
				System.out
						.println("1. Move Developer to Board 2. Move Developer on Board");
				System.out
						.println("3. Build Palace 4. Place another block 5. End Turn");

				switch (in.nextInt()) {
				case 1:
					// Move Developer to Board
					System.out.println("Moving Developer to Board");
					break;
				case 2:
					// Move Developer on Board
					System.out.println("Moving Developer on Board");

					break;
				case 3:
					System.out.println("Building Palace");

					// Place another Block
					break;
				case 4:
					System.out.println("Placing another block");

					break;
				case 5:
					System.out.println("End Turn");
					endTurn = true;
					game.endTurn();
					break;

				}
			}

			/*
			 * game.endTurn(); System.out.println("End Turn");
			 * System.out.println("Current Player: " + game.getPlayerName());
			 * System.out.println(game.getPlayerBlocks());
			 * 
			 * game.endTurn(); System.out.println("Current Player: " +
			 * game.getPlayerName());
			 * 
			 * // END GAME MASTER TEST
			 * 
			 * // START BOARD TEST printBoard(board);
			 * System.out.println("There are " + board.getNumThreeBlocks() +
			 * " Three-Blocks left.");
			 * 
			 * Block three = board.getThreeBlock(); printBlock(three);
			 * three.rotate(); printBlock(three); three.rotate();
			 * printBlock(three);
			 * 
			 * board.placeBlock(three, board.getSpaces()[0][0]);
			 * 
			 * printBoard(board);
			 * 
			 * Block three2 = board.getThreeBlock(); printBlock(three2);
			 * 
			 * board.placeBlock(three2, board.getSpaces()[11][11]);
			 * 
			 * printBoard(board);
			 * 
			 * // END BOARD TEST
			 * 
			 * // START PLAYER TEST
			 * 
			 * // END PLAYER TEST
			 */}
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
