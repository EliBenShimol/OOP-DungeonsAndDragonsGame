package monsterTypes;

import enemyTypes.Monster;

public class QueenCersei extends Monster{
	

	public QueenCersei(int xValue, int yValue) {
		super(100, 10, 10, 1, xValue, yValue, 1000);
		this.setSign('C');
		this.name="Queen Cersei";
	}


}
