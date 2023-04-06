package interactions;

import tiles.Tile;

public interface Visited {

	 String accept(Visitor v, Tile[][]board);
	 void gameTick();
}
