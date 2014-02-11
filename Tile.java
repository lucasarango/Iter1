public abstract class Tile {
	protected Tile.TileType type;

	public enum TileType {
		PALACE, VILLAGE, RICE, IRRIGATION
	};

	public Tile.TileType getType() {
		return type;
	}

	public int getValue() {
		return 0;
	}
}
