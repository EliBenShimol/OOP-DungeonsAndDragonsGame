package gameCreation;

import java.util.Stack;

import interactions.*;
import tiles.Tile;
import units.Enemy;

public class moveOrder implements Observable {
	Stack<Visited> observers=new Stack<>();

	@Override
	public String move(Step s, Visited v, Tile[][] board) {
		String ans=s.move(v, board);
		return ans;
	}

	@Override
	public String cast(Step s, Enemy[] enemies, Tile[][] board) {
		String ans=s.visit(enemies, board);
		return ans;
	}

	@Override
	public void addObservers(Visited v) {
		observers.push(v);
		
	}

	@Override
	public void notifiyGameTick() {
		Stack<Visited>temp=new Stack<>();
		while(!observers.isEmpty()) {
			Visited v=observers.pop();
			v.gameTick();
			temp.push(v);
		}
		while(!temp.isEmpty())
			observers.push(temp.pop());
		
	}
	

}
