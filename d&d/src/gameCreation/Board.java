package gameCreation;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.Stack;

import enemyTypes.*;
import hunterTypes.Ygritte;
import mageTypes.Melisandre;
import mageTypes.ThorosOfMyr;
import monsterTypes.*;
import rougeTypes.AryaStark;
import rougeTypes.Bronn;
import trapTypes.*;
import tiles.*;
import units.*;
import warriorTypes.JonSnow;
import warriorTypes.TheHound;

public class Board {
	protected Tile[][]boardOfTileTypes;
	protected char[][]tiles;
	protected char []monsterTypes;
	protected char[]trapTypes;
	protected Stack<Monster>enemies;
	protected Stack<Trap>traps;
	protected File level;
	protected int numOfEnemies;
	protected int numOfTraps;
	protected Player characterChosen=null;
	
	public Board(File f){
		level=f;
	}
	
	public Board(File file, Player p) {
		level=file;
		this.characterChosen=p;
		
	}

	public void createBoard() throws IOException {
		if(level.isFile()){//other condition like name ends in html
    		BufferedReader in = new BufferedReader(new FileReader(level));
    		String str;	
    		int rows=0;
    		int collumns=0;
    		while ((str = in.readLine()) != null) {
    			rows++;
    			collumns=str.length();
    			
    		}
    		enemies=new Stack<>();
    		traps=new Stack<>();
    		tiles=new char[rows][collumns];
    		boardOfTileTypes=new Tile[rows][collumns];
    		BufferedReader in2 = new BufferedReader(new FileReader(level));
    		String str2;	
    		int index=0;
    		while ((str2 = in2.readLine()) != null) {
    			for(int i=0;i<str2.length();i++) {
    				tiles[index][i]=str2.charAt(i);
    				boardOfTileTypes[index][i]=this.theTile(str2.charAt(i), i, index);
    				
    			}
    			index++;
    			
    		}
    		
    		
        }
	
	}
	
	public Player chooseCharacter(int xValue, int yValue) {
		Player[] playerTypes= {new JonSnow(xValue,yValue), new TheHound(xValue,yValue), new Melisandre(xValue,yValue), new ThorosOfMyr(xValue,yValue), 
				new AryaStark(xValue,yValue), new Bronn(xValue,yValue), new Ygritte(xValue, yValue)};
		Scanner in = new Scanner(System.in);
		int playerPick = in.nextInt();
		characterChosen=playerTypes[playerPick-1];
		return characterChosen;
	}
	
	
	public Tile theTile(char c, int xValue, int yValue) {
		Tile t=null;
		if(c=='#')
			t=new Wall(xValue, yValue);
		if(c=='.')
			t=new EmptyTile(xValue, yValue);
		if(c=='@') {
			if(this.characterChosen==null) {
				this.characterChosen=this.chooseCharacter(xValue, yValue);
				t=this.characterChosen;
			}
				
			
			else {
				this.characterChosen.setXValue(xValue);
				this.characterChosen.setYValue(yValue);
				t=this.characterChosen;
			}
		}
		if(c=='s'| c=='k'|c=='q'|c=='z'|c=='b'|c=='g'|c=='w'|c=='M'|c=='C'|c=='K') {
			this.enemies.push(this.theMonster(c, xValue, yValue));
			t=this.enemies.peek();
			numOfEnemies++;
		}
		
		if(c=='B'|c=='Q'|c=='D') {
			this.traps.push(this.theTrap(c, xValue, yValue));
			t=this.traps.peek();
			if(t!=null)
				numOfTraps++;
		}
		
		return t;
			
	}
	public Monster theMonster(char c, int xValue, int yValue) {
		Monster m=null;
		if(c=='s')
			m=new LannisterSolider(xValue, yValue);
		else if(c=='k')
			m=new LannisterKnight(xValue, yValue);
		else if(c=='q')
			m=new QueensGuard(xValue, yValue);
		else if(c=='z')
			m=new Wright(xValue, yValue);
		else if(c=='b')
			m=new BearWright(xValue, yValue);
		else if(c=='g')
			m=new GiantWright(xValue, yValue);
		else if(c=='w')
			m=new WhiteWalker(xValue, yValue);
		else if(c=='M')
			m=new TheMountain(xValue, yValue);
		else if(c=='C')
			m=new QueenCersei(xValue, yValue);
		else if(c=='K')
			m=new NightsKing(xValue, yValue);
		return m;
	}
	
	public Trap theTrap(char c, int xValue, int yValue) {
		Trap t=null;
		if(c=='B')
			t=new BonusTrap(xValue, yValue);
		else if(c=='Q')
			t=new QueensTrap(xValue, yValue);
		else if(c=='D')
			t=new DeathTrap(xValue, yValue);
		return t;
	}
	
	public String printLine(int i) {
		String s="";
		for(int j=0;j<boardOfTileTypes[i].length;j++)
			s+=boardOfTileTypes[i][j].getSign();
		return s;
	}
	
	public Tile[][] getBoard(){
		return boardOfTileTypes;
	}
	
	public Stack<Monster> getEnemies(){
		return enemies;
	}
	
	public Stack<Trap> getTraps(){
		return traps;
	}
	
	public Player getPlayer() {
		return characterChosen;
	}
	
	public int getNumOfEnemies() {
		return this.numOfEnemies;
	}
	
	public int getNumOfTraps() {
		return this.numOfTraps;
	}


}
