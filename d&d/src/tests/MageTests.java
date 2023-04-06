package tests;

import enemyTypes.Monster;
import interactions.PlayersStep;
import playerTypes.Mage;
import playerTypes.Warrior;
import tiles.Tile;
import units.Enemy;
import units.Player;

public class MageTests {

	public void checkingLevelUpMultipuleTimes() {
		Mage p=new Mage(100, 7, 3, 0, 0, 100, 2, 7, 1, 2);
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
		Mage p=new Mage(100, 7, 3, 0, 0, 100, 20, 7, 1, 2);
		Enemy e=new Monster(10000 ,1, 0, 3,  1, 0, 200);
		Enemy[]en=new Enemy[1];
		en[0]=e;
		Tile[][]b=new Tile[2][2];
		p.onAbillityCastAtemp(en, b);
		String ans=p.onAbillityCastAtemp(en, b);
		if(!ans.equals(p.getName()+" tried to use "+p.getAbillity()+" but there was mot enough mana: "+p.getMana()+"/"+p.getCost()+",")|p.getMana()>=p.getCost())
			System.out.println("ignored the lack of mana");
		else
			System.out.println("passed");	
	}
	
	public void checkingGameTickAfterCast() {
		Mage p=new Mage(100, 7, 3, 0, 0, 100, 20, 7, 1, 2);
		Enemy e=new Monster(10000 ,1, 0, 3,  1, 0, 200);
		Enemy[]en=new Enemy[1];
		en[0]=e;
		Tile[][]b=new Tile[2][2];
		p.onAbillityCastAtemp(en, b);
		int check=p.getMana();
		p.gameTick();
		if(check+p.getLevel()!=p.getMana())
			System.out.println("didnt add to mana");
		else
			System.out.println("passed");
		
	}
	
	public static void main(String[]args) {
		MageTests pt=new MageTests();
		pt.checkingLevelUpMultipuleTimes();
		pt.checkingCastWhenCant();
		pt.checkingGameTickAfterCast();
	}


}
