package interactions;

import enemyTypes.Monster;
import enemyTypes.Trap;
import tiles.EmptyTile;
import tiles.Tile;
import tiles.Wall;
import units.Enemy;
import units.Player;

public class TrapsStep extends Step{
	
protected Trap t;
	
	public TrapsStep(Trap t) {
		this.t=t;
	}

	@Override
	public String visit(Player p, Tile[][] board) {
		String s=t.attack(p);
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
