package trapTypes;

import enemyTypes.Trap;

public class BonusTrap extends Trap{

	public BonusTrap(int xValue, int yValue) {
		super(1, 1, 1, 250, xValue, yValue, 1, 5);
		this.setSign('B');
		this.name="Bonus Trap";
	}

}
