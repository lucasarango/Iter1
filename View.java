import java.awt.Color;

public class View {
    
    private BoardView boardView;
    private PlayerView playerView;
    private String[] playerNames;
    
    public View (String[] playerNames) {
        this.playerNames = playerNames;
        boardView = new BoardView(playerNames);
        playerView = new PlayerView(playerNames);
    }
    
    public void updateInventory() {
        playerView.updatePlayer();
    }
    
    //Order of display on single space is 
	//Type, Height, Developer, Palace Height (if there)
	
	//Update space w/ no palace
    public void updateSpace(int x, int y, String type, int height) {
        boardView.updateSpace(x, y, type, height);
    }
    
    //Update space w/ palace
    public void updateSpace(int x, int y, String type, int height,
                            int palaceLevel) {
        boardView.updateSpace(x, y, type, height, palaceLevel);
    }
    
    //Put a developer here
    public void updateDeveloper(int x, int y, String playerName) {
        boardView.updateDeveloper(x, y, getColor(playerName));
    }
    
    //Remove a developer from here
    public void removeDeveloper(int x, int y) {
        boardView.removeDeveloper(x, y);
    }
    
    //When tabbing through developers, switch to this one
    public void switchToDeveloper(int x, int y) {
        boardView.switchToDeveloper(x, y);
    }
    
    //When tabbing through developers, switch away from this one
    public void switchFromDeveloper(int x, int y) {
        boardView.switchFromDeveloper(x, y);
    }
    
    //Map players to their colors
    private Color getColor(String playerName) {
        if (playerName.equals(playerNames[0]))
            return Color.RED;
        else if (playerName.equals(playerNames[1]))
            return Color.ORANGE;
        else if (playerName.equals(playerNames[2]))
            return Color.YELLOW;
        else if (playerName.equals(playerNames[3]))
            return Color.BLUE;
        else 
            return Color.BLACK;
    }
}
