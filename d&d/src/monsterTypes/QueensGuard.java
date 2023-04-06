package monsterTypes;

import enemyTypes.Monster;

public class QueensGuard extends Monster{
	
	public QueensGuard(int xValue, int yValue) {
		super(400, 20, 15, 5, xValue, yValue, 100);
		this.setSign('q');
		this.name="Queen's Guard";
	}


}
