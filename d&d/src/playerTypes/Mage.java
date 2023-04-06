package playerTypes;

import interactions.PlayersStep;
import tiles.EmptyTile;
import tiles.Tile;
import units.Enemy;
import units.Player;

public class Mage extends Player{
	protected int manaPool;
	protected int currentMana;
	protected int manaCost;
	protected int spellPower;
	protected int hitCount;
	protected int abillityRange;
	
	
	public Mage() {
		
	}
	public Mage( int healthPool, int attack, int defence, int xValue, int yValue, int manaPool, int manaCost, int spellPower, int hitCount, int abillityRange) {
		this.tile='@';
		this.specialAbillity="blizzard";
		this.attack=attack;
		this.defence=defence;
		this.healthPool=healthPool;
		this.healthAmount=healthPool;
		this.xValue=xValue;
		this.yValue=yValue;
		this.manaPool=manaPool;
		this.manaCost=manaCost;
		this.abillityRange=abillityRange;
		this.currentMana=(int)(this.manaPool/4);
		this.spellPower=spellPower;
		this.hitCount=hitCount;
	}
	
	@Override
	public String onAbillityCastAtemp(Enemy[] enemies ,Tile[][] board) {
		String ans="";
		if(currentMana<manaCost)
			ans=this.name+" tried to use "+this.specialAbillity+" but there was mot enough mana: "+currentMana+"/"+manaCost+",";
		else {
			int countInRange=0;
			ans=this.name+" casted "+this.specialAbillity+",";
			for(int i=0;i<enemies.length;i++) 
				if(this.range(this, enemies[i])<abillityRange) {
					countInRange++;
				}
			Enemy[]inRange=new Enemy[countInRange];
			int index=0;
			for(int i=0;i<enemies.length;i++) 
				if(this.range(this, enemies[i])<abillityRange) {
					inRange[index]=enemies[i];
					index++;
				}
			index=0;
			int hits=0;
			while(inRange.length>0&&hits<this.hitCount) {
				ans=ans+this.cast(inRange[index], board)+",";
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
				index++;
				hits++;
				if(index>inRange.length-1)
					index=0;
			}
				
			
			this.currentMana-=this.manaCost;
			
		}
		while(this.exp>=50*this.pLevel)
			ans=ans+this.levelUp()+",";
		return ans;

	}
	@Override
	public String cast(Enemy e ,Tile[][] board) {
		int attPoints=this.spellPower;
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
		this.currentMana+=this.pLevel;
		if(this.currentMana>this.manaPool)
			this.currentMana=this.manaPool;
		
	}
	@Override
	public String levelUp() {
		String ans="";
		if(this.exp>=50*this.pLevel) {
			super.levelUp();
			this.manaPool+=25*this.pLevel;
			this.currentMana+=(int)(this.manaPool/4);
			if(this.currentMana>this.manaPool)
				this.currentMana=this.manaPool;
			this.spellPower+=10*this.pLevel;
			int healthAdd=10*this.pLevel;
			int attAdd=4*this.pLevel;
			int defAdd=1*this.pLevel;
			int manaAdd=25*this.pLevel;
			int spellAdd=10*this.pLevel;
			ans=this.name+" reached level "+this.pLevel+": "+"+"+healthAdd+" Health "+"+"+attAdd+" Attack "+"+"+defAdd+" Defence "+
			"+"+manaAdd+" Maximum Mana "+"+"+spellAdd+" SpellPower";
			
		}
		return ans;
	}
	
	
	@Override
	public String description() {
		String s=this.name+"      HealthAmout:"+this.healthAmount+"/"+this.healthPool+"      AttackPower:"+this.attack+"      DefencePower:"+this.defence+"      Level:"+this.pLevel+"      Experience:"
				+this.exp+"/"+(50*this.pLevel)+"      Mana:"+this.currentMana+"/"+this.manaPool+"      SpellPower:"+this.spellPower;
		return s;

	}
	
	public int getCost() {
		return this.manaCost;
	}
	
	public int getMana() {
		return this.currentMana;
	}
	
	
	
	
	
}
