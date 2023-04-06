package tests;

import enemyTypes.Monster;
import interactions.PlayersStep;
import playerTypes.Warrior;
import tiles.Tile;
import units.Enemy;
import units.Player;

public class WarriorTests {

	public void checkingLevelUpMultipuleTimes() {
		Warrior p=new Warrior(100, 5, 5, 0, 0, 3);
		Enemy e=new Monster(1 ,1, 0, 3,  1, 0, 200);
		Tile[][]b=new Tile[2][2];
		p.attack(e, b);
		if(e.getHealth()<=0) {
			if(p.getLevel()!=3) 
				System.out.println("didn't level up when needed");
			
			else
				System.out.println("passed");
				
		}
		else if(p.getLevel()!=1)
			System.out.println("leveled up when didnt need to");
		else
			System.out.println("passed");
			
	}
	
	public void checkingCastWhenCant() {
		Warrior w=new Warrior(100, 5, 5, 0, 0, 3);
		Enemy e=new Monster(10000 ,1, 0, 3,  1, 0, 200);
		Enemy[]en=new Enemy[1];
		en[0]=e;
		Tile[][]b=new Tile[2][2];
		w.onAbillityCastAtemp(en, b);
		String ans=w.onAbillityCastAtemp(en, b);
		if(!ans.equals(w.getName()+" tried to use "+w.getAbillity()+" but there is a cooldown: "+w.getRemainCool()+"/"+w.getAbilCool()+",")|w.getRemainCool()!=w.getAbilCool())
			System.out.println("ignored the cooldown");
		else
			System.out.println("passed");	
	}
	
	public void checkingGameTickAfterCast() {
		Warrior w=new Warrior(100, 5, 5, 0, 0, 3);
		Enemy e=new Monster(10000 ,1, 0, 3,  1, 0, 200);
		Enemy[]en=new Enemy[1];
		en[0]=e;
		Tile[][]b=new Tile[2][2];
		w.onAbillityCastAtemp(en, b);
		w.gameTick();
		if(w.getRemainCool()!=w.getAbilCool()-1)
			System.out.println("didnt change remainCooldown");
		else
			System.out.println("passed");
		
	}
	
	public static void main(String[]args) {
		WarriorTests pt=new WarriorTests();
		pt.checkingLevelUpMultipuleTimes();
		pt.checkingCastWhenCant();
		pt.checkingGameTickAfterCast();
	}


}
