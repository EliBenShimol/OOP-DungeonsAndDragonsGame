package monsterTypes;

import enemyTypes.Monster;

public class TheMountain extends Monster{
	
	public TheMountain(int xValue, int yValue) {
		super(1000, 60, 25, 6, xValue, yValue, 500);
		this.setSign('M');
		this.name="The Mountain";
	}


}
