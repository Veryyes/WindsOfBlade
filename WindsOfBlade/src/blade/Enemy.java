package blade;

import java.awt.Graphics;
import java.util.LinkedList;

public class Enemy extends Fighter{
	int money;
	LinkedList<Item> inventory;
	byte damageType; //1=physical, 2=special 3=mix;
	byte aiType; //1=random //2=lowest hp //3=lowest hp% //4=highest hp //5=highest hp%
	public Enemy(String name) {
		super(0, 0, new Animation(ImageManager.get("res/enemy/"+name+".png")));
		inventory=new LinkedList<Item>();
		//TODO Load Enemy
	}
	public Enemy(){
		super(0,0,null);
	}
	public Enemy clone(){
		return clone(1);
	}
	public Enemy clone(float mutateRate){
		if(mutateRate<=0)
			mutateRate=1;
		Enemy e = new Enemy();
		e.money = (int) (money*mutateRate);
		e.inventory=inventory;
		e.damageType = damageType;
		e.aiType = aiType;
		e.level=(int) (level*mutateRate);
		e.exp=(int) (exp*mutateRate);
		e.str=(int) (str*mutateRate);
		e.intel=(int) (intel*mutateRate);
		e.dex=(int) (dex*mutateRate);
		e.will=(int) (will*mutateRate);
		e.agil=(int) (agil*mutateRate);
		e.hp=(int) (hp*mutateRate);
		e.maxHp=(int) (maxHp*mutateRate);
		e.sp=(int) (sp*mutateRate);
		e.maxSp=(int) (maxSp*mutateRate);
		e.mp=(int) (mp*mutateRate);
		e.maxMp=(int) (maxMp*mutateRate);
		e.type=type;
		e.moves = moves;
		e.equips = equips;
		e.name=name;
		e.box=box;
		e.animation=animation;
		return e;
	}
	@Override
	public void update(Graphics g) {
		super.update(g);
	}

}
