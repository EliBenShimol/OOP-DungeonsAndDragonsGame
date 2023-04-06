package interactions;

import tiles.Tile;

public abstract class Step implements Visitor {

	public String move(Visited v, Tile[][]board) {	
		String s=v.accept(this, board);
		return s;
	}
	

}
