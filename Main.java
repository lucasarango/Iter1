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
		// Initialize Game with list of players
		GameMaster game = new GameMaster(players);

		boolean endgame = false;
		// This while true should be the end game condition. In otherwords,
		// while !endgame
		while (!endgame) {
			System.out.println("It is currently " + game.getPlayerName()
					+ " turn");

			// Player 1's Turn
			// FLOW IS:
			// 1. MUST PLACE LAND TILE
			System.out.println("Which land tile would you like to place?");
			System.out
					.println("1. One block tile 2. Two block tile 3. Three Block TIle?");
			int k = in.nextInt();
			System.out.println("Where in the board? x y coordinate");
			printBoard(board);
			// Potential to fix something here. Might not be the best to read in
			// two rows, up to you guys
			// This part could also use the numPad as a way to move it around.
			int x = in.nextInt();
			int y = in.nextInt();

			int q;
			// Place 1 Block Tile
			if (k == 1) {
				System.out.println("Do you want to place");
				System.out.println("1. Village 2.Irrigation");

				// FIX ME HERE PLZ
				Block OneBlock = game.getOneBlock();
				printBlock(OneBlock);
				board.placeBlock(OneBlock, board.getSpaces()[x][y]);
			}
			// Place 2 Block Tile

			if (k == 2) {
				// Get a TwoBlock from Game Master
				Block TwoBlock = game.getTwoBlock();
				printBlock(TwoBlock);
				System.out.println("Rotate tile? 1. Yes 2. No");
				q = in.nextInt();
				// DO WE MAKE IT DANCE?
				while (q != 2) {
					if (q == 1) {
						TwoBlock.rotate();
						printBlock(TwoBlock);
					}
					System.out.println("Rotate tile? 1. Yes 2. No");
					q = in.nextInt();
				}
				// Place block on coordinates.
				board.placeBlock(TwoBlock, board.getSpaces()[x][y]);
			}
			// Place 3 Block Tile

			if (k == 3) {
				Block ThreeBlock = board.getThreeBlock();
				printBlock(ThreeBlock);
				System.out.println("Rotate tile? 1. Yes 2. No");
				q = in.nextInt();
				while (q != 2) {
					if (q == 1) {
						ThreeBlock.rotate();
						printBlock(ThreeBlock);
					}
					System.out.println("Rotate tile? 1. Yes 2. No");
					q = in.nextInt();
				}
				board.placeBlock(ThreeBlock, board.getSpaces()[x][y]);
			}
			printBoard(board);

			// Mandatory Step is over, the rest is "optional" from here
			boolean endTurn = false;
			int d;
			// Check if player has enough action points and if he declared his
			// end of turn
			while (game.getActionPoints() != 0 && !endTurn) {
				System.out.println("You have " + game.getActionPoints()
						+ " action Points left");

				System.out.println("What else would you like to do?");
				System.out
						.println("1. Move Developer to Board 2. Move Developer on Board");
				System.out
						.println("3. Build Palace 4. Place another block 5. End Turn");

				switch (in.nextInt()) {
				case 1:
					// Move Developer to Board
					System.out.println("Which Developer do you want to move?");
					// FIX METHOD BELOW
					printDevelopers(game.getPlayerDevelopers(), board);
					// Developer list starts at 0, but I offset it when printing
					// the developers out
					d = in.nextInt() - 1;
					System.out.println("Where in the board? x y coordinate");
					printBoard(board);
					// Same as with block, this section could be improved.
					x = in.nextInt();
					y = in.nextInt();
					System.out.println("Moving Developer to Board");

					game.moveDeveloper(game.getDeveloper(d),
							board.getSpaces()[x][y]);
					printBoard(board);

					break;
				case 2:
					// Move Developer on Board

					System.out.println("Which Developer do you want to move?");
					printDevelopers(game.getPlayerDevelopers(), board);
					d = in.nextInt() - 1;
					System.out.println("Where in the board? x y coordinate");
					printBoard(board);
					x = in.nextInt();
					y = in.nextInt();

					int confirm = 0;
					// I tried to make it so that the player could choose wether
					// to confirm the move they wanted or not.

					while (confirm != 1) {
						// The point here is to emphasize that moving from
						// thisSpace to thatSpace will cost X action points. I
						// was thinking of a greedy algorithm, but it's not
						// necessary if the player is dictating the moves
						System.out
								.println("It will take 3 AP points. Confirm? 1. Yes 2. No");
						confirm = in.nextInt();
						if (confirm == 1) {
							System.out.println("Moving Developer on Board");

							game.moveDeveloper(game.getDeveloper(d),
									board.getSpaces()[x][y]);
							printBoard(board);

						} else {
							// Else, try another set of coordinates
							System.out
									.println("Where in the board? x y coordinate");
							printBoard(board);
							x = in.nextInt();
							y = in.nextInt();
						}
					}

					break;
				case 3:

					// This should be highlited in red if I could

					System.out.println("Building Palace");

					// Place another Block
					break;
				case 4:
					// Same as placing a land tile the first time around, no
					// creative innovations :( Maybe make this a method? It
					// would make the code look nicer
					System.out.println("Placing another block");
					System.out
							.println("What land tile would you like to place?");
					System.out.println("1. One block tile 2. Two block tile?");
					k = in.nextInt();
					System.out.println("Where in the board? x y coordinate");
					printBoard(board);
					x = in.nextInt();
					y = in.nextInt();
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
						q = in.nextInt();
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
					if (k == 3) {
						// Place Block Tile
						Block ThreeBlock = board.getThreeBlock();
						printBlock(ThreeBlock);
						System.out.println("Rotate tile? 1. Yes 2. No");
						q = in.nextInt();
						while (q != 2) {
							if (q == 1) {
								ThreeBlock.rotate();
								printBlock(ThreeBlock);
							}
							System.out.println("Rotate tile? 1. Yes 2. No");
							q = in.nextInt();
						}
						board.placeBlock(ThreeBlock, board.getSpaces()[x][y]);
					}
					printBoard(board);
					break;
				case 5:
					System.out.println("End Turn");
					// set endTurn to true to exit the while loop
					endTurn = true;
					game.endTurn();
					break;

				}
			}
		}
	}

	private static void printDevelopers(List<Developer> playerDevelopers,
			Board board) {
		int i = 1;
		for (Developer d : playerDevelopers) {
			System.out.print("Developer " + i + ": is in ");
			if (d.getSpace() == null) {
				System.out.println("Inventory ");
			} else {
				int[] array = board.findSpace(d.getSpace());
				System.out.println("space x: " + array[0] + " y: " + array[1]);
			}
			i++;
		}
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
				if (Position.isThereDeveloper(board.getSpaces()[j][i]))
					System.out.print("D");
				else if (board.getSpaces()[j][i].getTile() == null)
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
