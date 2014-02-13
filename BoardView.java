import javax.swing.*;
import java.awt.*;

public class BoardView extends JFrame{
	/**
	 * This class represents the board display. It creates a single 
	 * visible window, split into a grid display.
	 */
	 
	private JLabel[][] spaces;
	private String[] playerNames;
	 
	public BoardView(String[] playerNames) {
                     
        this.playerNames = playerNames;

        try {
            initialize();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        setVisible(true);
	}

	/**
	 * Initialize the contents of the this.
	 */
	private void initialize() throws Exception {
		//First we set up the window. 
        this.setTitle("Java");
		this.setBounds(50, 50, 910, 700);
        this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new GridLayout(14, 14, 4, 4));

		//Now we initialize the 144 game spaces
		spaces = new JLabel[14][14];
		for (int i = 0; i < 14; i++)
			for (int x = 0; x < 14; x++) {
			    spaces[i][x] = new JLabel("____");
				if (x > 0 && x < 13 && i > 0 && i < 13)
				    spaces[i][x] = new JLabel("XXXX");
				add(spaces[i][x]);
			}
		
	}
	
	//Order of display on single space is 
	//Type, Height, Developer, Palace Height (if there)
	
	//Update space w/ no palace
	public void updateSpace(int x, int y, Tile tile, int height) {
		String type = spaces[x][y].getText().substring(0,1);
        if (tile instanceof VillageTile)
            type = "V";
        else if (tile instanceof PalaceTile)
            type = "P";
        else if (tile instanceof RiceTile)
            type = "R";
        else if (tile instanceof IrrigationTile)
            type = "I";
	    String isDevHere = spaces[x][y].getText().substring(2,3);
	    spaces[x][y].setText(type + Integer.toString(height) + isDevHere + 
	    					 spaces[x][y].getText().substring(3,4)); 
	}
	
	//Update space w/ palace
	public void updateSpace(int x, int y, Tile tile, 
	                        int height, int palaceLevel) {
		String type = spaces[x][y].getText().substring(0,1);
        if (tile instanceof VillageTile)
            type = "V";
        else if (tile instanceof PalaceTile)
            type = "P";
        else if (tile instanceof RiceTile)
            type = "R";
        else if (tile instanceof IrrigationTile)
            type = "I";
	    String isDevHere = spaces[x][y].getText().substring(2,3);
	    spaces[x][y].setText(type + Integer.toString(height) + isDevHere + 
	                        Integer.toString(palaceLevel)); 
	}
	
	//Put a developer here
    public void updateDeveloper(int x, int y, Color color) {
        spaces[x][y].setText(spaces[x][y].getText().substring(0,2) + "d" + 
                             spaces[x][y].getText().substring(3));       
        spaces[x][y].setForeground(color);
        
    }
    
    //Remove a developer from here
    public void removeDeveloper(int x, int y) {
        spaces[x][y].setText(spaces[x][y].getText().substring(0,2) + "X" + 
                             spaces[x][y].getText().substring(3));
        spaces[x][y].setForeground(Color.BLACK);
    }
    
    //When tabbing through developers, switch to this one
    public void switchToDeveloper(int x, int y) {
        spaces[x][y].setText(spaces[x][y].getText().substring(0,2) + "D" + 
                             spaces[x][y].getText().substring(3));
    }
    
    //When tabbing through developers, switch away from this one
    public void switchFromDeveloper(int x, int y) {
        spaces[x][y].setText(spaces[x][y].getText().substring(0,2) + "d " + 
                             spaces[x][y].getText().substring(3));
    }
    
    //Highlights a given space (if it's not highlighted)
    public void highlightSpace(int x, int y) {
        if (!spaces[x][y].getText().substring(0,3).equals("<b>"))
            spaces[x][y].setText("<html><h2>" + spaces[x][y].getText() +
                                 "</h2></html>");
    }
    
    //DeHighlights a given space (if it's higlighted)
    public void unHighlightSpace(int x, int y) {
        if (spaces[x][y].getText().substring(0,3).equals("<ht"))
            spaces[x][y].setText(spaces[x][y].getText().substring(10,
                spaces[x][y].getText().length() - 12));
    }
    
}

