package interactions;

import enemyTypes.Monster;
import enemyTypes.Trap;
import tiles.*;
import units.*;

public class EnemiesStep extends Step{
	protected Enemy e;
	
	
	public EnemiesStep(Enemy e) {
		this.e=e;
	}
	@Override
	public String visit(Player p, Tile[][] board) {
		String s=e.attack(p);
		return s;
		
	}

	@Override
	public String visit(Monster e, Tile[][] board) {
		return "";
		
	}

	@Override
	public String visit(Trap t, Tile[][] board) {
		return "";
	}

	@Override
	public String visit(EmptyTile et, Tile[][] board) {
		int tempX=et.getXValue();
		int tempY=et.getYValue();
		et.setXValue(e.getXValue());
		et.setYValue(e.getYValue());
		board[et.getYValue()][et.getXValue()]=et;
		e.setXValue(tempX);
		e.setYValue(tempY);
		board[e.getYValue()][e.getXValue()]=e;
		return "";
		
	}

	@Override
	public String visit(Wall w, Tile[][] board) {
		return "";
		
	}
	@Override
	public String visit(Enemy[] enemies, Tile[][] board) {
		return "";
	}
}
