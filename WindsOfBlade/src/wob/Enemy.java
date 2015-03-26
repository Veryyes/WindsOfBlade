package wob;

import java.awt.Graphics;
import java.util.LinkedList;

public class Enemy extends Actor implements BattleObject{
	int damage;
	int money;
	int level;
	int hp;
	LinkedList<Item> inventory;
	String name;
	public Enemy(int x,int y) {
		super(x,y);
	}
	public void update() {
			
	}
	public void battleRender(Graphics g) {
		
	}
	public void takeDamage(int value) {
		
	}
	public void useMove(Move attack, int index) {
		
	}
}
