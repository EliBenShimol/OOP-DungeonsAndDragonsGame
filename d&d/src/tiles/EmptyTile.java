package tiles;

import interactions.*;

public class EmptyTile extends Tile implements Visited{
	
	public EmptyTile(int xValue, int yValue) {
		this.tile='.';
		this.xValue=xValue;
		this.yValue=yValue;
		
	}

	@Override
	public String accept(Visitor v, Tile[][] board) {
		String s=v.visit(this, board);
		return s;
		
	}

}
