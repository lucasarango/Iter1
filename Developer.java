public class Developer {
	private Space location;

	
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