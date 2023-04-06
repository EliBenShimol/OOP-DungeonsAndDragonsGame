package playerTypes;

import interactions.PlayersStep;
import tiles.EmptyTile;
import tiles.Tile;
import units.*;

public class Hunter extends Player{
	protected int abillityRange;
	protected int arrows;
	protected int ticks;
	
	
public Hunter() {
		
	}
	
	public Hunter(int healthPool, int attack, int defence, int xValue, int yValue, int abillityRange) {
		this.tile='@';
		this.specialAbillity="Shoot";
		this.attack=attack;
		this.defence=defence;
		this.healthPool=healthPool;
		this.healthAmount=healthPool;
		this.xValue=xValue;
		this.yValue=yValue;
		this.abillityRange=abillityRange;
		this.arrows=10*this.pLevel;
		this.ticks=0;
	}


	@Override
	public String onAbillityCastAtemp(Enemy[] enemies ,Tile[][] board) {
		String ans="";
		if(0==arrows)
			ans=this.name+" tried to use "+this.specialAbillity+" but he doesn't have any arrows"+",";
		else {
			Enemy e=null;
			boolean found=false;
			for(int i=0;i<enemies.length&!found;i++) 
				if(this.range(this, enemies[i])<=abillityRange) 
					e=enemies[i];
			if(e==null)
				ans=this.name+" tried to use "+this.specialAbillity+" but there aren't any enemies around "+",";
			else {
				ans=ans+this.name+" used "+this.specialAbillity+",";
				ans=ans+this.cast(e, board);
				this.arrows--;
			}

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
		if(this.ticks==10) {
			this.arrows+=this.pLevel;
			this.ticks=0;
		}
		else 
			this.ticks++;
	}
	@Override
	public String levelUp() {
		String ans="";
		if(this.exp>=50*this.pLevel) {
			super.levelUp();
			this.arrows+=10*this.pLevel;
			this.attack+=2*this.pLevel;
			this.defence+=this.pLevel;
			int healthAdd=10*this.pLevel;
			int attAdd=6*this.pLevel;
			int defAdd=2*this.pLevel;
			int arrowAdd=10*this.pLevel;
			ans=this.name+" reached level "+this.pLevel+": "+"+"+healthAdd+" Health "+"+"+attAdd+" Attack "+"+"+defAdd+" Defence "+"+"+arrowAdd+" arrows ";

		}
		return ans;
	}


	@Override
	public String description() {
		String s=this.name+"      HealthAmout:"+this.healthAmount+"/"+this.healthPool+"      AttackPower:"+this.attack+"      DefencePower:"+this.defence+"      Level:"+this.pLevel+"      Experience:"
				+this.exp+"/"+(50*this.pLevel)+"      arrows:"+this.arrows;
		return s;

	}




}
