import java.io.PrintWriter;
import java.util.Scanner;

abstract class Block {
	protected Tile[][] tileGrid;

	abstract public boolean rotate();
		
	abstract public Tile[][] getGrid();

	abstract public void setGrid(Tile[][] t);
	/*
		one blocks
		it is variable

		two blocks
		rice city

		three blocks
		city and rice on either side
	*/
	
	/**this save function assumes it will be encapsulated in an xml tag*/
	public void save(PrintWriter p){
		for (int y = 0; y < tileGrid.length;y++){
			for (int x = 0; x < tileGrid[0].length; x++){
				Tile t=tileGrid[y][x];
				if (t!=null){
					p.append(""+t.getClass().getName()+":"+x+","+y);
					if (t instanceof PalaceTile){
						p.append(","+((PalaceTile)t).getValue());
					}
					p.append('\n');
				}
			}
		}
	}
	
	/**this load function assumes it will be encapsulated in an xml tag,
	 * and will exit when it sees a closing tag of any kind that it did
	 * not create*/
	public void load(Scanner reader){
		
		//this will be escaped with a break;
		while(true){
			
			String line=reader.nextLine().replace("//s+", "");
			
			if (line.startsWith("</")){
				break;
			
			}else{
				int colonIndex = line.indexOf(':');
				if (colonIndex>0){
					String tag = line.substring(0, colonIndex);
					String value = line.substring(colonIndex + 1, line.length());
					String[] tokens = value.split(",");
					int x = Integer.parseInt(tokens[0]);
					int y = Integer.parseInt(tokens[1]);
					Tile newb=null;
					if (tag.equals(PalaceTile.class.getName())){
						newb = new PalaceTile(Integer.parseInt(tokens[2]));
					}else if (tag.equals(RiceTile.class.getName())){
						newb = new RiceTile();
					}if (tag.equals(IrrigationTile.class.getName())){
						newb = new IrrigationTile();
					}if (tag.equals(VillageTile.class.getName())){
						newb = new VillageTile();
					}
					if (newb!=null){
						tileGrid[y][x]= newb;
					}
				}
			}
		}//end while(true)
	}
}