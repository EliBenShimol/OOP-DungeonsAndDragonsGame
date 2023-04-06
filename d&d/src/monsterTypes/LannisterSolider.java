package monsterTypes;

import enemyTypes.Monster;

public class LannisterSolider extends Monster{

	public LannisterSolider(int xValue, int yValue) {
		super(80, 8, 3, 3, xValue, yValue, 25);
		this.setSign('s');
		this.name="Lannister Solider";
	}

}
