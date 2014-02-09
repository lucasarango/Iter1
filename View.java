import javax.swing.*;
import java.awt.*;

public class View extends JFrame{
	/**
	 * This class represents the display. It creates a single 
	 * visible window, split into a grid display.
	 */
	 
	public View() {
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
        this.setTitle("Java");
		this.setBounds(50, 50, 910, 650);
        this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new GridLayout(12, 12));
		
		
	}
}