import java.io.PrintWriter;
import java.io.File;

public class Mediator
{
	private GameMaster game;
	private Board board;
	private BoardView boardView;
	private PlayerView playerView;
	private Controller controller;

	public Mediator(GameMaster g, Board b, BoardView bv, PlayerView pv, Controller c)
	{
		game = g;
		board = b;
		playerView = pv;
		boardView = bv;
		controller = c;
	}

	public boolean moveDeveloper(Developer d, int x, int y)
	{
		game.moveDeveloper(game.getDeveloper(d), board.getSpaces()[x][y]));
	}

	public boolean placeTile(int tileSize, int x, int y)
	{

	}

	public boolean removeDeveloper()
	{

	}

	public void saveGame()
	{

	}

	public void loadGame()
	{

	}

}
