package monsterTypes;

import enemyTypes.Monster;

public class GiantWright extends Monster{
	
	public GiantWright(int xValue, int yValue) {
		super(1500, 100, 40, 5, xValue, yValue, 500);
		this.setSign('g');
		this.name="Giant-Wright";
	}

}
