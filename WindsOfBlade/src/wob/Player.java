package wob;

import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public class Player extends Fighter implements WorldObject{
	int money;
	LinkedList<Partner> party;
	static Line2D.Double topLine,botLine,leftLine,rightLine;
	public Player() {
		super(Game.frameWidth/2-32,Game.frameHeight/2-32);
		name="Heroine";
		animation = new Animation(ImageManager.getImage("res/sprites/player/tempPlayer.png"));
		hitBox = new Rectangle2D.Double(this.x+8,this.y+16,48,48);
		topLine = new Line2D.Double(x+8,y+16,x+8+48,y+16);
		botLine = new Line2D.Double(x+8,y+16+48,x+8+48,y+16+48);
		leftLine = new Line2D.Double(x+8,y+16,x+8,y+16+48);
		rightLine = new Line2D.Double(x+8+48,y+16,x+8+48,y+16+48);
		inventory = new LinkedList<Item>();
		party = new LinkedList<Partner>();
		level=1;
		experience=0;
		money=0;
		str=10;
		intel=10;
		dex=10;
		will=10;
		agil=10;
		hp=20;
		maxHp=20;
		mp=10;
		maxMp=10;
		sp=10;
		maxSp=10;
		type=Type.NORMAL;
		for(int i=0;i<Move.database.length/2;i++){
			techniques.add(Move.database[i]);
		}
		addItem(Item.createItem("Iron Ingot",5));
	}
	public void worldRender(Graphics g) {
		g.drawImage(animation.getFrame(),x,y,null);
	}
	public void update() {
		animation.update();
	}
	public void addItem(Item item){
		for(int i=0;i<inventory.size();i++){
			if(inventory.get(i).getName().equals(item.getName())){
				inventory.get(i).amount+=item.amount;
				return;
			}
		}
		inventory.add(item);
	}
	public boolean removeItem(Item item){
		return removeItem(item,1);
	}
	public boolean removeItem(Item item, int amount){
		for(int i=0;i<inventory.size();i++){
			if(inventory.get(i).getName().equals(item.getName())){
				inventory.get(i).amount-=amount;
				if(inventory.get(i).amount<0){
					inventory.get(i).amount+=amount;
					return false;
				}else if(inventory.get(i).amount==0)
					inventory.remove(i);
			}
		}
		return true;
	}
}