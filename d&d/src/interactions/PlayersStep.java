package interactions;


import enemyTypes.Monster;
import enemyTypes.Trap;
import tiles.EmptyTile;
import tiles.Tile;
import tiles.Wall;
import units.Enemy;
import units.Player;

public class PlayersStep extends Step {
	protected Player p;

	public PlayersStep(Player p) {
		this.p=p;
	}

	@Override
	public String visit(Player p, Tile[][] board) {
		return "";

	}

	@Override
	public String visit(Monster e, Tile[][] board) {
		String s=p.attack(e, board);
		return s;

	}

	@Override
	public String visit(Trap t, Tile[][] board) {
		String s=p.attack(t, board);
		return s;
	}

	@Override
	public String visit(EmptyTile et, Tile[][] board) {
		if(p.range(p, et)<=1) {
			int tempX=et.getXValue();
			int tempY=et.getYValue();
			et.setXValue(p.getXValue());
			et.setYValue(p.getYValue());
			board[et.getYValue()][et.getXValue()]=et;
			p.setXValue(tempX);
			p.setYValue(tempY);
			board[p.getYValue()][p.getXValue()]=p;
		}
		return "";

	}

	@Override
	public String visit(Wall w, Tile[][] board) {
		return "";

	}

	@Override
	public String visit(Enemy[] enemies, Tile[][] board) {
		String s=p.onAbillityCastAtemp(enemies, board);
		return s;
	}


}
