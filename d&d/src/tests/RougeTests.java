package tests;

import enemyTypes.Monster;
import interactions.PlayersStep;
import playerTypes.Mage;
import playerTypes.Rouge;
import playerTypes.Warrior;
import tiles.Tile;
import units.Enemy;
import units.Player;

public class RougeTests {

	public void checkingLevelUpMultipuleTimes() {
		Rouge p=new Rouge(100, 10, 5, 0, 0, 100);
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
		Rouge p=new Rouge(100, 10, 5, 0, 0, 100);
		Enemy e=new Monster(10000 ,1, 0, 3,  1, 0, 200);
		Enemy[]en=new Enemy[1];
		en[0]=e;
		Tile[][]b=new Tile[2][2];
		p.onAbillityCastAtemp(en, b);
		String ans=p.onAbillityCastAtemp(en, b);
		if(!ans.equals(p.getName()+" tried to use "+p.getAbillity()+" but there was mot enough energy: "+p.getCurEn()+"/"+p.getCost()+",")|p.getCurEn()>=p.getCost())
			System.out.println("ignored the lack of energy");
		else
			System.out.println("passed");	
	}
	
	public void checkingGameTickAfterCast() {
		Rouge p=new Rouge(100, 10, 5, 0, 0, 100);
		Enemy e=new Monster(10000 ,1, 0, 3,  1, 0, 200);
		Enemy[]en=new Enemy[1];
		en[0]=e;
		Tile[][]b=new Tile[2][2];
		p.onAbillityCastAtemp(en, b);
		int check=p.getCurEn();
		p.gameTick();
		if(check+10!=p.getCurEn())
			System.out.println("didnt add to energy");
		else
			System.out.println("passed");
		
	}
	
	public static void main(String[]args) {
		RougeTests pt=new RougeTests();
		pt.checkingLevelUpMultipuleTimes();
		pt.checkingCastWhenCant();
		pt.checkingGameTickAfterCast();
	}


}
