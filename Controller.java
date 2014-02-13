import java.util.Scanner;

public class Controller
{
	private Mediator mediator;
	private Scanner input;

	public Controller()
	{
		input = System.in();
		initializeGame();
	}

	public void gamePrompt()
	{
		


	}

	public void initializeGame()
	{
		//Create ALL THE THINGS -- view, model, controller
		Board board = new Board();

		ArrayList<String> players = new ArrayList<String>();
		System.out.println("How many players are playing?")
		int numPlayers = input.nextInt();

		for(int i = 0; i < numPlayers; i++)
		{
			System.out.println("What is the name of Player " + (i + 1));
			players.add(input.next());
		}

		//Initialize gamemaster
		GameMaster game = new GameMaster(players);

		mediator = new Mediator(game, board, playerView, boardView, this);
	}
}