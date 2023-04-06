package trapTypes;

import enemyTypes.Trap;

public class DeathTrap extends Trap{
	
	public DeathTrap(int xValue, int yValue) {
		super(500, 100, 20, 250, xValue, yValue, 1, 10);
		this.setSign('D');
		this.name="Death Trap";
	}



}
