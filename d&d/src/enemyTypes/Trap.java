package enemyTypes;

import interactions.*;
import tiles.*;
import units.*;

public class Trap extends Enemy implements Visited{
	
	protected int visibilityTime; 
	protected int inVisibilityTime; 
	protected int ticksCount=0;
	protected boolean visible=true;
	
	public Trap() {
		
	}
	
	public Trap(int healthPool, int attack, int defence, int expValue, int xValue, int yValue, int vt, int ivt) {
		this.tile='Q';
		this.name="trap";
		this.healthPool=healthPool;
		this.healthAmount=this.healthPool;
		this.attack=attack;
		this.defence=defence;
		this.expValue=expValue;
		this.xValue=xValue;
		this.yValue=yValue;
		this.visibilityTime=vt;
		this.inVisibilityTime=ivt;
	}
	
	public void gameTick() {
		ticksCount++;
		if(ticksCount>=visibilityTime) {
			visible=false;
			ticksCount=-1*inVisibilityTime;
		}
		
		if(ticksCount==0)
			visible=true;
	}
	
	@Override
	public char getSign() {
		if(!visible)
			return '.';
		return tile;
	}
	
	@Override
	public String attack(Player p) {
		String ans="";
		if(this.range(p, this)<2)
			ans=super.attack(p)+",";
		return ans;
	}

	@Override
	public String accept(Visitor v, Tile[][] board) {
		String s=v.visit(this, board);
		return s;
		
	}
	

	@Override
	public String description() {
		String s=this.name+"     HealthAmout:"+this.healthAmount+"     AttackPower:"+this.attack+"     DefencePower:"+this.defence+"     ExperienceValue:"+this.expValue
				+"     VisibilityTime:"+this.visibilityTime+"     InVisibilityTime:"+this.inVisibilityTime;
		return s;
	}


}
