	package units;

public class Enemy extends Unit {
	protected int expValue;
	
	
	public String attack(Player e) {
		String[]ans=new String[7];
		for(int i=0;i<ans.length;i++)
			ans[i]="";
		ans[0]=this.name+" engaged in combat with "+e.getName();
		ans[1]=this.description();
		ans[2]=e.description();
		int attPoints=(int)(Math.random()*this.attack+1)-1;
		int defPoints=(int)(Math.random()*e.defence+1)-1;
		ans[3]=this.name+" rolled "+attPoints+" attack points";
		ans[4]=e.getName()+" rolled "+defPoints+" defence points";
		int damage=attPoints-defPoints;
		if(damage<0)
			damage=0;
		ans[5]=this.name+" did "+damage+" damage to "+e.getName();
		e.decreaseHealth(damage);
		if(e.getHealth()<=0) {
			ans[6]="Game Over!";
			e.setSign('X');
			}
			
		
		return ans[0]+","+ans[1]+","+ans[2]+","+ans[3]+","+ans[4]+","+ans[5]+","+ans[6]+",";
	}
	public int getExpValue() {
		return expValue;
	}
	public int[] death() {
		int []gains= {expValue, xValue, yValue};
		return gains;
		
		
	}
	

	@Override
	public String description() {
		String s=this.name+"     HealthAmout:"+this.healthAmount+"/"+this.healthPool+"     AttackPower:"+this.attack+"     DefencePower:"+this.defence+"     ExperienceValue:"+this.expValue;
		return s;
	}

}
