public class Developer {
	Space location;

	
	public Developer() {

	}
	
	public boolean move(Space s) {
		this.location=s;
		return true;
	}
	
	public Space getSpace() {
		return location;
	}
}