package monsterTypes;

import enemyTypes.Monster;

public class WhiteWalker extends Monster{
	
	public WhiteWalker(int xValue, int yValue) {
		super(2000, 150, 50, 6, xValue, yValue, 1000);
		this.setSign('w');
		this.name="White Walker";
	}


}
