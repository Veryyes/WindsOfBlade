package wob;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Enemy extends Fighter{
	int money;
	byte damageType; //1=physical, 2=special 3=mix;
	byte aiType; //1=random //2=lowest hp //3=lowest hp% //4=highest hp //5=highest hp%
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
			level = Integer.parseInt(lines[0].split("=")[1].trim());
			experience = Integer.parseInt(lines[1].split("=")[1].trim());
			money = Integer.parseInt(lines[2].split("=")[1].trim());
			type = Type.parseType(lines[3].split("=")[1].trim());
			damageType = Byte.parseByte(lines[4].split("=")[1].trim());
			aiType = Byte.parseByte(lines[5].split("=")[1].trim());
			hp = Integer.parseInt(lines[6].split("=")[1].trim());
			str = Integer.parseInt(lines[7].split("=")[1].trim());
			intel = Integer.parseInt(lines[8].split("=")[1].trim());
			dex = Integer.parseInt(lines[9].split("=")[1].trim());
			will = Integer.parseInt(lines[10].split("=")[1].trim());
			agil = Integer.parseInt(lines[11].split("=")[1].trim());
		} catch (IOException e) {
			System.out.println("[WARNING] Missing Enemy Data - \"data/enemy/"+name+".txt\"");
		}
	}
	public int physicalDamage(){
		return (int)(str*.5);
	}
	public int magicDamage(){
		return (int) (intel*.5);
	}
	public Enemy(){
		super(0,0);
	}
	public Enemy clone(){
		Enemy e = new Enemy();
		e.x=x;
		e.y=y;
		e.name=name;
		e.animation=animation;
		e.level=level;
		e.experience=experience;
		e.money=money;
		e.str=str;
		e.intel=intel;
		e.dex=dex;
		e.will=will;
		e.agil=agil;
		e.hp=hp;
		e.maxHp=maxHp;
		e.sp=sp;
		e.maxSp=maxSp;
		e.mp=mp;
		e.maxMp=maxMp;
		e.damageType=damageType;
		e.aiType=aiType;
		return e;
	}
	public void update() {
		
	}
}
