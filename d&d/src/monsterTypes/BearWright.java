package monsterTypes;

import enemyTypes.Monster;

public class BearWright extends Monster{
	public BearWright(int xValue, int yValue) {
		super(1000, 75, 30, 4, xValue, yValue, 250);
		this.setSign('b');
		this.name="Bear-Wright";
	}



}
