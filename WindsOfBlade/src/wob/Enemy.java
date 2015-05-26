package wob;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Enemy extends Actor implements BattleObject{
	String name;
	int hp, damage, level, money, exp;
	LinkedList<Item> inventory;
	LinkedList<Move> techniques;
	public Enemy(String name) {
		super(0,0);
		this.name=name;
		try {
			animation = new Animation(ImageManager.getImage("res/enemy/"+name+".png"));
			BufferedReader br = new BufferedReader(new FileReader(new File("data/enemy/"+name+".txt")));
			String rawData = "";
			int item;
			while((item=br.read())!=-1)
				rawData+=(char)item;
			br.close();
			String[] lines = rawData.split("\n");
			hp = Integer.parseInt(lines[0].split("=")[1]);
			damage = Integer.parseInt(lines[1].split("=")[1]);
			level = Integer.parseInt(lines[2].split("=")[1]);
			money = Integer.parseInt(lines[3].split("=")[1]);
			exp = Integer.parseInt(lines[4].split("=")[1]);
			
		} catch (IOException e) {
			System.out.println("[WARNING] Missing Enemy Data - \"data/enemy/"+name+".txt\"");
			//TODO Initiate default Enemy
		}
	}
	public void update() {
		
	}
	public void battleRender(Graphics g, int x, int y) {
		
		
	}
}
