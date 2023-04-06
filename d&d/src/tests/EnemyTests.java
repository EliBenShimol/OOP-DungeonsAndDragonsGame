package tests;

import java.io.IOException;

import mageTypes.Melisandre;
import monsterTypes.NightsKing;
import units.Enemy;
import units.Player;
import enemyTypes.Monster;

public class EnemyTests {


	public void checkPlayer(){
		Enemy enemy = new NightsKing(1, 1);
		Player player = new Melisandre(6, 5);
		int []  i = ((Monster) enemy).whereToMove(player);

		if (i[0]==1 &i[1]==2) {
			System.out.println("correct movement");
		}else {
			System.out.println("incorrent, suppose to move to 1,2 but want t move to" +i[0]+","+i[1]);
		}
		String s = enemy.description();
		String eString ="Night's King"+"     HealthAmout:"+5000+"/"+5000+"     AttackPower:"+300+"     DefencePower:"+150+"     ExperienceValue:"+5000;
		if (s.equals(eString)) {
			System.out.println("description correct");
		}else {
			System.out.println("expected: " + eString + " but recived: "+s);
		}



	}
	
	public static void main(String[]args) {
		EnemyTests pt=new EnemyTests();
		pt.checkPlayer();
	}

	
	
}
