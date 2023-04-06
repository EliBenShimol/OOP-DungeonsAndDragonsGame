package ui;

import gameCreation.*;
import hunterTypes.Ygritte;
import interactions.*;
import mageTypes.*;
import playerTypes.*;
import rougeTypes.*;
import units.*;
import warriorTypes.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

import enemyTypes.*;

public class RunGame {
	private moveOrder o=new moveOrder();
	protected final File levelsPath= new File("./levels");
	protected File answer;
	protected File[] levels;
	private int numOfEnemies;
	private int numOfTraps;
	private int numOfTotalEnemies;
	private Player p;
	private Board b;
	private boolean endGame=false;

	public void runGame() throws IOException {
		answer=new File(levelsPath.getCanonicalPath());
		levels = answer.listFiles();//gets All the levels from the folder levels
		for(int k=0;k<levels.length&!endGame;k++) {
			if(k==0)//if its the start of the game we create a new player, if not we stay with the one existing
				b=new Board(levels[k]);
			else
				b=new Board(levels[k], p);
			if(k==0) {//creates the new player if needed
				System.out.println("Select Player: ");
				Player[] playerTypes= {new JonSnow(0,0), new TheHound(0,0), new Melisandre(0,0), new ThorosOfMyr(0,0),
						new AryaStark(0,0), new Bronn(0,0), new Ygritte(0,0)};
				for(int i=0;i<playerTypes.length;i++)
					System.out.println((i+1)+"."+" "+playerTypes[i].description());
				b.createBoard();
				p=b.getPlayer();
				System.out.println("you have selected:");
				System.out.println(p.description());
				System.out.println("");
			}
			else
				b.createBoard();
			numOfTraps=b.getNumOfTraps();
			numOfEnemies=b.getNumOfEnemies();
			numOfTotalEnemies=numOfEnemies+numOfTraps;
			//we add all the traps and the player to the observer(for game ticks) and transfer the enemies into an array to make things easier
			o.addObservers(p);


			Stack<Monster>stackMonsters=b.getEnemies();
			Monster[]monsters=new Monster[numOfEnemies];
			Stack<Trap>stackTraps=b.getTraps();
			Trap[]traps=new Trap[numOfTraps];
			Stack<Trap>temp=new Stack<>();


			while(!stackTraps.isEmpty()) {
				o.addObservers(stackTraps.peek());
				temp.push(stackTraps.pop());
			}
			while(!temp.isEmpty())
				stackTraps.push(temp.pop());
			Enemy[]enemies=new Enemy[stackTraps.size()+stackMonsters.size()];

			int q=0;
			while(!stackMonsters.isEmpty()) {
				monsters[q]=stackMonsters.pop();
				enemies[q]=monsters[q];
				q++;

			}

			int index=q;
			q=0;
			while(!stackTraps.isEmpty()){
				traps[q]=stackTraps.pop();
				enemies[index+q]=traps[q];
				q++;

			}


			System.out.println("Level: "+(k+1));
			for(int i=0;i<b.getBoard().length;i++) 
				System.out.println(b.printLine(i));
			PlayersStep ps=new PlayersStep(p);

			while(numOfTotalEnemies>0&!endGame) {//each level stops if the player dies or when there are no enemies, if the player dies the game also ends completely
				boolean checkCorrectInput=true;
				String move="";
				while(checkCorrectInput) {//the game continues when the right input is added
					Scanner in2 = new Scanner(System.in);
					System.out.println("move in a direction: use w,s,a,d to move, e to cast a special abillity and q to pass your turn on");
					String name = in2.nextLine();
					if(name.equals("w")){
						Visited pi=b.getBoard()[p.getYValue()-1][p.getXValue()];
						if(!b.getBoard()[p.getYValue()-1][p.getXValue()].description().equals("")) {
							System.out.println(p.getName()+" engaged in combat with "+b.getBoard()[p.getYValue()-1][p.getXValue()].getName()+".");
							System.out.println(p.description());
							System.out.println(b.getBoard()[p.getYValue()-1][p.getXValue()].description());
						}
						checkCorrectInput=false;
						move=o.move(ps, pi, b.getBoard());
					}

					else if(name.equals("s")){
						Visited pi=b.getBoard()[p.getYValue()+1][p.getXValue()];
						checkCorrectInput=false;
						move=o.move(ps, pi, b.getBoard());
					}


					else if(name.equals("a")){
						Visited pi=b.getBoard()[p.getYValue()][p.getXValue()-1];
						checkCorrectInput=false;
						move=o.move(ps, pi, b.getBoard());
					}

					else if(name.equals("d")){
						Visited pi=b.getBoard()[p.getYValue()][p.getXValue()+1];
						checkCorrectInput=false;
						move=o.move(ps, pi, b.getBoard());
					}

					else if(name.equals("e")){
						move=o.cast(ps, enemies, b.getBoard());
						checkCorrectInput=false;
					}
					else if(name.equals("q")){
						checkCorrectInput=false;
					}
					else {
						checkCorrectInput=true;
						System.out.println("Illegal input");
					}
				}

				if(!move.equals("")) {//prints the scenerio that happened
					int count=0;
					for(int i=0;i<move.length();i++) 
						if(move.charAt(i)==',') {
							if(!move.substring(count, i).equals(""))
								System.out.println(move.substring(count, i));
							count=i+1;
						}

				}

				for(int i=0;i<monsters.length;i++) {//checks if a monster died
					if(monsters[i].getHealth()<=0) {
						this.numOfTotalEnemies--;
						Monster[]temp2=new Monster[monsters.length-1];
						for(int j=0;j<temp2.length;j++) {
							if(j<i)
								temp2[j]=monsters[j];
							else
								temp2[j]=monsters[j+1];

						}
						monsters=temp2;	
					}

				}

				for(int i=0;i<monsters.length;i++) {//the monsters turn
					int[]place=monsters[i].whereToMove(p);
					Visited pi=b.getBoard()[place[0]][place[1]];
					EnemiesStep es=new EnemiesStep(monsters[i]);
					String move2="";
					move2=o.move(es, pi, b.getBoard());

					if(!move2.equals("")) {
						int count=0;
						for(int j=0;j<move2.length();j++) 
							if(move2.charAt(j)==',') {
								if(!move2.substring(count, j).equals(""))
									System.out.println(move2.substring(count, j));
								if(move2.substring(count, j).equals("Game Over!"))
									this.endGame=true;
								count=j+1;
							}
					}
				}



				for(int i=0;i<traps.length;i++) {//checks if a trap died
					if(traps[i].getHealth()<=0) {
						this.numOfTotalEnemies--;
						Trap[]temp2=new Trap[traps.length-1];
						for(int j=0;j<temp2.length;j++) {
							if(j<i)
								temp2[j]=traps[j];
							else
								temp2[j]=traps[j+1];

						}
						traps=temp2;	
					}

				}

				for(int i=0;i<traps.length;i++) {//the traps turn
					Visited pi=p;
					TrapsStep ts=new TrapsStep(traps[i]);
					String move2="";
					move2=o.move(ts, pi, b.getBoard());
					if(!move2.equals("")) {
						int count=0;
						for(int j=0;j<move2.length();j++) 
							if(move2.charAt(j)==',') {
								if(!move2.substring(count, j).equals(""))
									System.out.println(move2.substring(count, j));
								if(move2.substring(count, j).equals("Game Over!")) 
									this.endGame=true;
								
								count=j+1;
							}
					}
				}

				for(int i=0;i<enemies.length;i++) {//removes all the enemies that died
					if(enemies[i].getHealth()<=0) {
						Enemy[]temp2=new Enemy[enemies.length-1];
						for(int j=0;j<temp2.length;j++) {
							if(j<i)
								temp2[j]=enemies[j];
							else
								temp2[j]=enemies[j+1];

						}
						enemies=temp2;	
					}

				}

					for(int i=0;i<b.getBoard().length;i++) //prints the board
						System.out.println(b.printLine(i));
					System.out.println(p.description());
				
				o.notifiyGameTick();//tells the observers a game tick has ended
			}

			for(int i=0;i<b.getBoard().length&!endGame;i++) 
				System.out.println(b.printLine(i));
			if(!endGame) //if the player didnt die we move a level and inform the player
				System.out.println("The Level is Finished!");

		}
		if(!endGame)//if the loop of the levels has ended and the player didnt die he won
			System.out.println("YOU WON!!!");


	}


	public static void main(String[]args) throws IOException {
		RunGame rg=new RunGame();
		rg.runGame();
	}

}
