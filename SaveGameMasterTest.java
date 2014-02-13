

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class SaveGameMasterTest {

	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<String> names=new ArrayList<String>();
		names.add("fred");
		names.add("george");
		
		GameMaster master=new GameMaster(names,null);
		
		PrintWriter savefile = new PrintWriter("testsavefile");
		master.save(savefile);
		savefile.close();
		
		ArrayList<String> dummynames=new ArrayList<String>();
		dummynames.add("nobody");
		GameMaster master2 = new GameMaster(dummynames,null);
		
		File loaded = new File("testsavefile");
		Scanner scanner = new Scanner(loaded);
		master2.load(scanner);
		scanner.close();
		
		PrintWriter saved_loaded_saved = new PrintWriter("testsavefile2");
		master2.save(saved_loaded_saved);
		saved_loaded_saved.close();
	}
}
