import java.util.*;

public class Position {

	private static HashMap<Space, Developer> map;

	public Position() {
		Position.map = new HashMap<Space, Developer>();
	}

	public void addPair(Space s, Developer d) {
		map.put(s, d);
	}

	public void removePair(Space s) {
		map.remove(s);
	}

	public void removePair(Developer d) {
		map.remove(d);
	}

	public Developer getDeveloper(Space s) {
		return map.get(s);

	}

	public static boolean isThereDeveloper(Space s) {
		if (map.get(s) != null) {
			return true;
		}
		return false;
	}

}
