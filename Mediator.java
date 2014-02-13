import java.io.PrintWriter;
import java.io.File;

public class Mediator
{
	private GameMaster game;
	private Board board;
	private BoardView boardView;
	private PlayerView playerView;
	private Controller controller;

	public Mediator(Controller c)
	{
		game = new GameMaster(this);
		board = new Board(this);
		playerView = new PlayerView();
		boardView = new BoardView();
		controller = c;
	}

	public void moveDeveloper(Developer d, int[] offset)
	{
		if(board.moveDeveloper(d, offset))
			//notify bv
		else
			//notify error
	}

	public void placeBlock(Block b, int[] coord)
	{
		if(board.placeBlock(b, board.getSpaces()[coord[0]][coord[1]]))
			//notify bv and pv
		else
			//notify error
	}

	public Block rotateBlock(Block b){
		if(b.rotate())
			return b;
		else
			return b;
	}

	public Block getThreeBlock(){
		Block b = board.getThreeBlock();
		if(b != null)
			return b;
		else{
			board.returnThreeBlock(b);
			return null;
		}
	}

	public boolean returnThreeBlock(Block b){
		return board.returnThreeBlock(b);
	}

	public Block getIrrigationTile(){
		Block b = board.getIrrigationTile();
		if(b != null)
			return b;
		else{
			board.returnIrrigationTile(b);
			return null;
		}
	}

	public boolean returnIrrigationTile(Block b){
		return board.returnIrrigationTile(b);
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