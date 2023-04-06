package playerTypes;

import interactions.*;
import tiles.*;
import units.*;

public class Rouge extends Player{
	protected int cost;
	protected final int maxEnergy=100;
	protected int currentEnergy;
	protected final int abillityRange=3;
	
	
	public Rouge() {
		
	}
	public Rouge(int healthPool, int attack, int defence, int xValue, int yValue, int cost) {
		this.tile='@';
		this.specialAbillity="Fan of Knives";
		this.attack=attack;
		this.defence=defence;
		this.healthPool=healthPool;
		this.healthAmount=healthPool;
		this.xValue=xValue;
		this.yValue=yValue;
		this.cost=cost;
		this.currentEnergy=this.maxEnergy;
	}
	
	@Override
	public String onAbillityCastAtemp(Enemy[] enemies ,Tile[][] board) {
		String ans="";
		if(currentEnergy<cost)
			ans=this.name+" tried to use "+this.specialAbillity+" but there was mot enough energy: "+currentEnergy+"/"+cost+",";
		else {
			int countInRange=0;
			ans=this.name+" casted "+this.specialAbillity+",";
			for(int i=0;i<enemies.length;i++) 
				if(this.range(this, enemies[i])<abillityRange) {
					countInRange++;
				}
			int index=0;
			Enemy[]inRange=new Enemy[countInRange];
			for(int i=0;i<enemies.length;i++) 
				if(this.range(this, enemies[i])<abillityRange) {
					inRange[index]=enemies[i];
					index++;
				}
			for(int k=0; k<inRange.length;k++) {
				ans=ans+this.cast(inRange[k], board)+",";
				for(int i=0;i<inRange.length;i++) {
					if(inRange[i].getHealth()<=0) {
						Enemy[]temp=new Enemy[inRange.length-1];
						for(int j=0;j<temp.length;j++) {
							if(j<i)
								temp[j]=inRange[j];
							else
								temp[j]=inRange[j+1];
							
						}
						inRange=temp;	
					}		
				}
			}
				
			
			this.currentEnergy-=this.cost;
			
		}
		while(this.exp>=50*this.pLevel)
			ans=ans+this.levelUp()+",";
		return ans;

	}
	@Override
	public String cast(Enemy e ,Tile[][] board) {
		int attPoints=this.attack;
		int defPoints=(int)(Math.random()*e.getDefence()+1)-1;
		String[]ans=new String[3];
		for(int i=0;i<ans.length;i++)
			ans[i]="";
		ans[0]=e.getName()+" rolled "+defPoints+" defence points";
		int[] information=new int[3];
		int damage=attPoints-defPoints;	
		if(damage>0&e.getHealth()>0) {
			e.decreaseHealth(damage);
			if(e.getHealth()<=0) {
				information=e.death();
			}
		}
		else
			damage=0;
		this.exp+=information[0];
		if(information[0]>0) {
			ans[2]=e.getName()+" died. "+this.name+" gained "+information[0]+" experience points";
			PlayersStep ps=new PlayersStep(this);
			int xValue=information[1];
			int yValue=information[2];
			EmptyTile et=new EmptyTile(xValue, yValue);
			board[yValue][xValue]=et;
			ps.move(et, board);
			
		}
		
		ans[1]=this.name+" did "+damage+" damage to "+e.getName();
		String ans2=ans[0]+","+ans[1]+","+ans[2]+",";
		
		return ans2;	
	}
	
	
	@Override
	public void gameTick() {
		this.currentEnergy+=10;
		if(this.currentEnergy>this.maxEnergy)
			this.currentEnergy=this.maxEnergy;
		
	}
	@Override
	public String levelUp() {
		String ans="";
		if(this.exp>=50*this.pLevel) {
			super.levelUp();
			this.currentEnergy=this.maxEnergy;
			this.attack+=3*this.pLevel;
			int healthAdd=10*this.pLevel;
			int attAdd=7*this.pLevel;
			int defAdd=1*this.pLevel;
			ans=this.name+" reached level "+this.pLevel+": "+"+"+healthAdd+" Health "+"+"+attAdd+" Attack "+"+"+defAdd+" Defence ";
		
		}
		return ans;
	}
	
	
	@Override
	public String description() {
		String s=this.name+"      HealthAmout:"+this.healthAmount+"/"+this.healthPool+"      AttackPower:"+this.attack+"      DefencePower:"+this.defence+"      Level:"+this.pLevel+"      Experience:"
				+this.exp+"/"+(50*this.pLevel)+"      Energy:"+this.currentEnergy+"/"+this.maxEnergy;
		return s;

	}
	
	
	public int getCurEn() {
		return this.currentEnergy;
	}
	
	public int getCost() {
		return this.cost;
	}
	
	
	
}
