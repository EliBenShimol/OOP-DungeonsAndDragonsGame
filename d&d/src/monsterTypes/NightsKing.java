package monsterTypes;

import enemyTypes.Monster;

public class NightsKing extends Monster{
	
	public NightsKing(int xValue, int yValue) {
		super(5000, 300, 150, 8, xValue, yValue, 5000);
		this.setSign('K');
		this.name="Night's King";
	}



}
