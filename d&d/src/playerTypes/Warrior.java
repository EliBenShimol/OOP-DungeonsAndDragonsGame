package playerTypes;


import interactions.*;
import tiles.*;
import units.*;

public class Warrior extends Player {


	protected int abillityCooldown;
	protected int remainingCooldown;
	
	public Warrior() {
		
	}
	
	public Warrior(int healthPool, int attack, int defence, int xValue, int yValue, int abillityCooldown) {
		this.tile='@';
		this.specialAbillity="Avenger’s Shield";
		this.attack=attack;
		this.defence=defence;
		this.healthPool=healthPool;
		this.healthAmount=healthPool;
		this.xValue=xValue;
		this.yValue=yValue;
		this.abillityCooldown=abillityCooldown;
	}
	@Override
	public String onAbillityCastAtemp(Enemy[] enemies ,Tile[][] board) {
		String ans="";
		boolean found=false;
		if(remainingCooldown>0)
			ans=this.name+" tried to use "+this.specialAbillity+" but there is a cooldown: "+remainingCooldown+"/"+abillityCooldown+",";
		else {
			this.healthAmount+=10*this.defence;
			if(this.healthAmount>=this.healthPool)
				this.healthAmount=this.healthPool;
			ans=this.name+" casted special abillity healing for "+10*this.defence+",";
			for(int i=0;i<enemies.length&!found;i++) 
				if(this.range(this, enemies[i])<3) {
					found=true;
					ans=ans+this.cast(enemies[i], board)+",";
				}
					
			
			remainingCooldown=abillityCooldown;
			
		}
		return ans;

	}
	@Override
	public String cast(Enemy e ,Tile[][] board) {
		int attPoints=(int)(this.healthPool/10);
		int defPoints=(int)(Math.random()*e.getDefence()+1)-1;
		String[]ans=new String[5];
		for(int i=0;i<ans.length;i++)
			ans[i]="";
		ans[0]=this.name+ " used "+this.specialAbillity;
		ans[1]=e.getName()+" rolled "+defPoints+" defence points";
		int[] information=new int[3];
		int damage=attPoints-defPoints;
		
		if(damage>0) {
			e.decreaseHealth(damage);
			if(e.getHealth()<=0) {
				information=e.death();
			}
		}
		else
			damage=0;
		this.exp+=information[0];
		ans[4]=this.levelUp();
		if(information[0]>0) {
			ans[3]=e.getName()+" died. "+this.name+" gained "+information[0]+" experience points";
			PlayersStep ps=new PlayersStep(this);
			int xValue=information[1];
			int yValue=information[2];
			EmptyTile et=new EmptyTile(xValue, yValue);
			board[yValue][xValue]=et;
			ps.move(et, board);
			
		}
		
		ans[2]=this.name+" did "+damage+" damage to "+e.getName();
		String ans2=ans[0]+","+ans[1]+","+ans[2]+","+ans[3]+","+ans[4]+",";
		
		return ans2;
		
		
	}
	
	@Override
	public void gameTick() {
		if(this.remainingCooldown>0)
			this.remainingCooldown=remainingCooldown-1;
		
		
	}
	@Override
	public String levelUp() {
		String ans="";
		if(this.exp>=50*this.pLevel) {
			super.levelUp();
			this.remainingCooldown=0;
			this.healthPool+=5*this.pLevel;
			this.attack+=2*this.pLevel;
			this.defence+=this.pLevel;
			int healthAdd=15*this.pLevel;
			int attAdd=6*this.pLevel;
			int defAdd=2*this.pLevel;
			this.healthAmount=this.healthPool;
			ans=this.name+" reached level "+this.pLevel+": "+"+"+healthAdd+" Health "+"+"+attAdd+" Attack "+"+"+defAdd+" Defence";
			
		}
		return ans;
	}
	
	@Override
	public String description() {
		String s=this.name+"      HealthAmout:"+this.healthAmount+"/"+this.healthPool+"      AttackPower:"+this.attack+"      DefencePower:"+this.defence+"      Level:"+this.pLevel+"       Experience:"
				+this.exp+"/"+(50*this.pLevel)+"      Cooldown:"+this.remainingCooldown+"/"+this.abillityCooldown;
		return s;

	}
	
	public int getRemainCool() {
		return this.remainingCooldown;
	}
	public int getAbilCool() {
		return this.abillityCooldown;
	}
	


}
