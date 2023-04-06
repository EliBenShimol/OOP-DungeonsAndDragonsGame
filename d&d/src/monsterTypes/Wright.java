package monsterTypes;

import enemyTypes.Monster;

public class Wright extends Monster{
	
	
	public Wright(int xValue, int yValue) {
		super(600, 30, 15, 3, xValue, yValue, 100);
		this.setSign('z');
		this.name="Wright";
	}


}
