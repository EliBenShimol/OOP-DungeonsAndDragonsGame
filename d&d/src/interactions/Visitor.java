package interactions;

import enemyTypes.*;
import tiles.*;
import units.*;

public interface Visitor {
	
	String visit(Player p, Tile[][]board);
    String visit(Monster e, Tile[][]board);
    String visit(Trap t, Tile[][]board);
    String visit(EmptyTile et, Tile[][]board);
    String visit(Wall w, Tile[][]board);
    String visit(Enemy[]enemies, Tile[][]board);

}
