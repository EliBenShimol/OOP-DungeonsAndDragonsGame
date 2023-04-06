package tiles;

import interactions.*;

public class Tile  implements Visited{
	protected char tile;
	protected String name;
	protected int xValue; 
	protected int yValue;
	
	
	
	public String description() {
		return "";
	}
	
	public char getSign() {
		return tile;
	}
	

	public int getXValue() {
		return xValue;
	}
	
	
	public int getYValue() {
		return yValue;
	}
	public void setXValue(int xValue) {
		this.xValue=xValue;
	}
	
	public void setYValue(int yValue) {
		this.yValue=yValue;
	}
	public void setSign(char c) {
		this.tile=c;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public String accept(Visitor v, Tile[][] board) {
		return "";
	}


	@Override
	public void gameTick() {
		return;
		
	}
	

}
