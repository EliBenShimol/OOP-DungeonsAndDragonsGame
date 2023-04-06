package units;

import tiles.Tile;

public class Unit extends Tile {
	protected int healthPool;
	protected int healthAmount;
	protected int attack;
	protected int defence;
	
	
	public double range(Player p, Tile e) {
		int a=p.xValue-e.getXValue();
		int b=p.yValue-e.getYValue();
		a*=a;
		b*=b;
		double ans=a+b;
		ans=Math.sqrt(ans);
		return ans;
		
	}
	
	
	public int getAttack() {
		return attack;
	}
	
		
	public int getDefence() {
		return defence;
	}
	
	public int getHealth() {
		return healthAmount;
	}
	

	public void decreaseHealth(int damage) {
		this.healthAmount-=damage;
		if(this.healthAmount<=0)
			this.healthAmount=0;
		
	}

	
	

}
