package trapTypes;

import enemyTypes.Trap;

public class QueensTrap extends Trap{
	

	public QueensTrap(int xValue, int yValue) {
		super(250, 50, 10, 100, xValue, yValue, 3, 7);
		this.setSign('Q');
		this.name="Queen's Trap";
	}


}
