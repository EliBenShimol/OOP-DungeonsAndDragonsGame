package enemyTypes;

import interactions.*;
import tiles.Tile;
import units.*;

public class Monster extends Enemy implements Visited{
	int visionRange;
	
	public Monster() {
		
	}
	public Monster(int healthPool ,int attack, int defence, int visionRange,  int xValue, int yValue, int expValue) {
		this.tile='W';
		this.name="monster";
		this.attack=attack;
		this.defence=defence;
		this.healthPool=healthPool;
		this.healthAmount=healthPool;
		this.expValue=expValue;
		this.xValue=xValue;
		this.yValue=yValue;
		this.visionRange=visionRange;
	}
	@Override
	public String accept(Visitor v, Tile[][] board) {
		String s=v.visit(this, board);
		return s;
		
	}
	public int[] whereToMove(Player p) {
		int[]ans=new int[2];
		if(this.range(p, this)<visionRange) {
			int dx=p.getXValue()-this.xValue;
			int dy=p.getYValue()-this.yValue;
			int tempDx=dx;
			int tempDy=dy;
			if(dx<0)
				tempDx=-1*tempDx;
			if(dy<0)
				tempDy=-1*tempDy;
			if(tempDx>tempDy) {
				if(dx>0) {
					ans[0]=this.yValue;
					ans[1]=this.xValue+1;
				}
				else {
					ans[0]=this.yValue;
					ans[1]=this.xValue-1;
				}
				
			}
			else {
				if(dy>0) {
					ans[0]=this.yValue+1;
					ans[1]=this.xValue;
				}
				else {
					ans[0]=this.yValue-1;
					ans[1]=this.xValue;
				}
				
			}
		}
		else {
			int xValue=(int)(int)(Math.random()*3)-1;
			if(xValue!=0) {
				ans[0]=this.yValue;
				ans[1]=this.xValue+xValue;
			}
			else {
				int yValue=(int)(int)(Math.random()*3)-1;
				ans[0]=this.yValue+yValue;
				ans[1]=this.xValue;
			}
		}
		return ans;
			
	}
}
