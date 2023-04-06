package monsterTypes;

import enemyTypes.Monster;

public class LannisterKnight extends Monster{
	
	public LannisterKnight(int xValue, int yValue) {
		super(200, 14, 8, 4, xValue, yValue, 50);
		this.setSign('k');
		this.name="Lannister Knight";
	}


}
