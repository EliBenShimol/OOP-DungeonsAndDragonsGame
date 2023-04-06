package gameCreation;

import interactions.*;
import tiles.Tile;
import units.Enemy;

//the interface visitor is also used as the observer in this situation
public interface Observable {
	public String move(Step s, Visited v,  Tile[][]board);
	public String cast(Step s, Enemy[]enemies, Tile[][]board);
	public void addObservers(Visited v);
	public void notifiyGameTick();

}
