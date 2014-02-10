import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class OldController {

	public static void main(String[] args) {
		View view = new View();
		addKeyListener(new KeyListener());
	}
	
	private class KeyListener extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				break;
			case KeyEvent.VK_DOWN:
				break;
			case KeyEvent.VK_LEFT:
				break;
			case KeyEvent.VK_RIGHT:
				break;
			case KeyEvent.VK_SPACE:
				break;
			default:
				break;
			}
		}
	}
}
