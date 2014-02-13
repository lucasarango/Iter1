public class Mediator {
	public Mediator() {}
	public void moveDeveloper(Developer d, int[] coords) {
		System.out.println("moveDeveloper called.");
	}
	public void switchDeveloper(Developer oldGuy, Developer NewGuy) {
		System.out.println("switchDeveloper called.");
	}
	public Block getIrrigationTile() {
		System.out.println("getIrrigationTile called.");
		return new OneBlock(new IrrigationTile());
	}
	public Block getThreeBlock() {
		System.out.println("getThreeBlock called.");
		return new ThreeBlock();
	}
	public void placeBlock(Block block, int[] coord) {
		System.out.println("placeBlock called.");
	}
	public void returnBlock(Block block) {
		System.out.println("returnBlock called.");
	}
	public void rotateBlock(Block block) {
		System.out.println("rotateBlock called.");
		block.rotate();
	}
	public void upgradePalace(int[] coord, int level) {
		System.out.println("upgradePalace called.");
	}
}

/*import java.io.PrintWriter;
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
*/