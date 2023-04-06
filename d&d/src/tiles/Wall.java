package tiles;

import interactions.*;

public class Wall extends Tile implements Visited{
	
	public Wall(int xValue, int yValue) {
		this.tile='#';
		this.xValue=xValue;
		this.yValue=yValue;
		
	}

	@Override
	public String accept(Visitor v, Tile[][] board) {
		String s=v.visit(this, board);
		return s;
		
	}

}
