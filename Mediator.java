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
		game = new GameMaster(this);
		board = new Board(this);
		playerView = new PlayerView();
		boardView = new BoardView();
		controller = c;
	}

	public void moveDeveloper(Developer d, int[] offset)
	{
		board.moveDeveloper(d, offset);
		//notify bv
	}

	public boolean placeBlock(Block b, int x, int y)
	{
		board.placeBlock(b, board.getSpaces()[x][y]);
		//notify bv and pv
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