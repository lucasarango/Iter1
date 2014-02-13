public class Controller {

	public static void main(String[] args) {
	    String[] playernames = {"bob","b","e","w"};
		View view =  new View(playernames);
		
		view.updateSpace(1,1, "C", 3, 5);
		view.updateDeveloper(1,1, "bob");
		view.switchToDeveloper(1,1);
		view.highlightSpace(5,5);
		view.unHighlightSpace(5,5);
		//view.removeDeveloper(1,1);
		
		//Here we need to add a scanner to take in input.
		//1 - Place block
		//2 - Place developer
		//8 - Save Game
		//9 - Load Game
		
	}

	
}
