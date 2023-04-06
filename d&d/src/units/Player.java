package units;

import interactions.*;
import tiles.EmptyTile;
import tiles.Tile;

public class Player extends Unit implements Visited{
	protected int exp=0;
	protected int pLevel=1;
	protected String specialAbillity;
	
	
	public Player() {
		
	}
	public Player(int healthPool, int attack, int defence, int xValue, int yValue) {
		this.tile='@';
		this.attack=attack;
		this.defence=defence;
		this.healthPool=healthPool;
		this.healthAmount=healthPool;
		this.xValue=xValue;
		this.yValue=yValue;
	}
	public String onAbillityCastAtemp(Enemy[] enemies ,Tile[][] board) {
		return "";
	}
	
	public String cast(Enemy e ,Tile[][] board) {
		return "";
	}
	
	public String levelUp() {
		if (exp>=50*pLevel) {
			exp-=50*pLevel;
			pLevel+=1;
			healthPool+=10*pLevel;
			attack+=4*pLevel;
			defence+=pLevel;
			this.healthAmount=this.healthPool;
			return this.name+" reached level "+this.pLevel+": "+"+"+10*pLevel+" Health "+"+"+4*pLevel+" Attack "+"+"+pLevel+" Defence";
			
		}
		return "";
	}
	
	public String attack(Enemy e, Tile[][]board) {
		String[]ans=new String[8];
		for(int i=0;i<ans.length;i++)
			ans[i]="";
		ans[0]=this.getName()+" engaged in combat with "+e.getName()+".";
		ans[1]=this.description();
		ans[2]=e.description();
		int attPoints=(int)(int)(Math.random()*this.attack+1)-1;
		int defPoints=(int)(Math.random()*e.defence+1)-1;
		ans[3]=this.name+" rolled "+attPoints+" attack points";
		ans[4]=e.getName()+" rolled "+defPoints+" defence points";
		int[] information=new int[3];
		int damage=attPoints-defPoints;
		if(damage>0) {
			e.decreaseHealth(damage);
			if(e.healthAmount<=0) {
				information=e.death();
			}
		}
		else
			damage=0;
		this.exp+=information[0];
		while(this.exp>=50*this.pLevel)
			ans[7]=ans[7]+this.levelUp()+",";
		if(information[0]>0) {
			ans[6]=e.getName()+" died "+this.name+" gained "+information[0]+" experience points";
			PlayersStep ps=new PlayersStep(this);
			int xValue=information[1];
			int yValue=information[2];
			EmptyTile et=new EmptyTile(xValue, yValue);
			board[yValue][xValue]=et;
			ps.move(et, board);
			
			
		}
		ans[5]=this.name+" did "+damage+" damage to "+e.getName();
		String ans2=ans[0]+","+ans[1]+","+ans[2]+","+ans[3]+","+ans[4]+","+ans[5]+","+ans[6]+","+ans[7]+",";
		
		return ans2;
		
		
		
	}
	@Override
	public String description() {
		String s="HealthAmout:"+this.healthAmount+" AttackPower:"+this.attack+" DefencePower:"+this.defence+" Experience:"+this.exp;
		return s;
	}
	
	@Override
	public String accept(Visitor v, Tile[][] board) {
		String s=v.visit(this, board);
		return s;
		
	}
	
	public int getLevel() {
		return this.pLevel;
	}
	
	public String getAbillity() {
		return this.specialAbillity;
	}
}
