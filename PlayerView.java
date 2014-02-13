import javax.swing.*;
import java.awt.*;

public class PlayerView extends JFrame{
	/**
	 * This class represents the player info display. It creates a single 
	 * visible window, split into a grid, 1 x 4, wherein each grid component
	 * will display the info for a different player. 
	 */
	 
	private JTextArea[] playerTextArea;
	private String[] playerNames;
	private int[] oneCityBlkCounts;
	private int[] oneVillageBlkCounts;
	private int[] twoCityBlkCounts;
	private int[] twoBlkCounts;
	private int[] actionTokens;
	private int[] developerCounts;
	private int[] score;
	
	 
	public PlayerView(String[] playerNames) {
	    playerTextArea = new JTextArea[4];
	    oneCityBlkCounts = new int[4];
	    oneVillageBlkCounts = new int[4];
	    twoBlkCounts = new int[4];
	    actionTokens = new int[4];
	    developerCounts = new int[4];
	    score = new int[4];
	    
	    //Start of game value initialization
	    for (int i = 0; i < 4; i++) {
	        oneCityBlkCounts[i] = 2;
	        oneVillageBlkCounts[i] = 3;
	        twoBlkCounts[i] = 5;
	        actionTokens[i] = 3;
	        developerCounts[i] = 12;
	        score[i] = 0;
	    }
	    
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
        this.setTitle("Player Info: ");
		this.setBounds(50, 50, 600, 300);
        this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new GridLayout(1, 4, 1 , 1));
		
		for (int i = 0; i < 4; i++) {
		    playerTextArea[i] = new JTextArea("");
		    updatePlayer(i);
		    playerTextArea[i].setEditable(false);
		    this.add(playerTextArea[i]);
		}
	}
	
	//Method for decrementing 1 block counts
	public void updateOneBlockCount(String playerName, boolean isCity) {
	    int playerNum = getPlayerNumber(playerName);
	    if (isCity)
	        oneCityBlkCounts[playerNum]--;
	    else
	        oneVillageBlkCounts[playerNum]--;
	    updatePlayer(playerNum);
	}
	
	//Method for decrementing 2 block counts
	public void updateTwoBlockCount(String playerName) {
	    int playerNum = getPlayerNumber(playerName);
	    twoBlkCounts[playerNum]--;
	    updatePlayer(playerNum);
	}
	
	//Method for incrementing/decremeting developer counts
	public void developerCount(String playerName, int increment) {
	    int playerNum = getPlayerNumber(playerName);
	    developerCounts[playerNum] = developerCounts[playerNum] + increment;
	    updatePlayer(playerNum);
	}
	
	//Maps player names to the number in the data structure
	private int getPlayerNumber(String playerName) {
	    if (playerName.equals(playerNames[0]))
	        return 0;
	    else if (playerName.equals(playerNames[1]))
	        return 1;
	    else if (playerName.equals(playerNames[2]))
	        return 2;
	    else if (playerName.equals(playerNames[3]))
	        return 3;
	    else
	        return 4;
	}
	
	//Update a given player
	public void updatePlayer(int i) {
	    playerTextArea[i] = new JTextArea(playerNames[i] + "\n" +
		                              "1 Blks (City): " + oneCityBlkCounts[i] +
		                              "\n1 Blks (Rice): " + 
		                              oneVillageBlkCounts[i] +
		                              "\n2 Blks: " + twoBlkCounts[i] +
		                              "\nAction Tokens: " + actionTokens[i] +
		                              "\nDevelopers: " + developerCounts[i] +
		                              "\nScore: " + score[i]);
	}
}
