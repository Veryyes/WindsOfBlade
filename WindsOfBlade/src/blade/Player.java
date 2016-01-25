package blade;

import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.util.LinkedList;

public class Player extends Fighter implements Talkable{
	int money;
	public LinkedList<Item> inventory;
	public Fighter[] party;
	static Line2D.Float top, bottom, left, right;
	Talkable talkingTarget;
	private Animation walkUp, walkDown, walkLeft, walkRight;
	public Player(){
		super(Game.frameWidth/2-32,Game.frameHeight/2-32, new Animation(ImageManager.get("res/sprites/player/tempPlayer.png")));
		top = new Line2D.Float(box.x+8,box.y+16,box.x+8+48,box.y+16);
		bottom = new Line2D.Float(box.x+8,box.y+16+48,box.x+8+48,box.y+16+48);
		left = new Line2D.Float(box.x+8,box.y+16,box.x+8,box.y+16+48);
		right = new Line2D.Float(box.x+8+48,box.y+16,box.x+8+48,box.y+16+48);
		party = new Fighter[1];//Max Size 4
		party[0]=this;
		//Battle.statWindow.text=this.getStats()+'\n';
		inventory = new LinkedList<Item>();
		level=1;
		exp=0;
		money=50;
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
	}
	@Override
	public void update(Graphics g){
		animation.update();
		animation.getCurrentFrame().draw(box.x, box.y, g);
		//g.drawRect(box.x,box.y,64,64);
	}
	public void giveItem(Item i){
		for(Item item:inventory)
			if(item.name.equals(i.name)){
				item.amount+=i.amount;
				return;
			}
		inventory.add(i);
	}
	public void removeItem(Item i){
		for(Item item:inventory)
			if(item.name.equals(i.name)){
				item.amount-=i.amount;
				if(item.amount<0)
				return;
			}
		inventory.add(i);
	}
    /**
	 * Adds a new party member to the party & changes the battle stat window accordingly
	 * @param f the new Party member
	 */
	public void addPartyMember(Fighter f){
		if(party.length==4)
			throw new java.lang.IndexOutOfBoundsException("Max party size is four!");
		else{
			Fighter[] newParty = new Fighter[party.length+1];
			for(int i=0;i<party.length;i++)
				newParty[i] = party[i];
			newParty[party.length] = f;
			party=newParty;
		}
	}
	/**
	 * Removes a party member from the party & changes the battle stat window accordingly
	 * @param f the member to be removed
	 */
	public void removePartyMember(Fighter f){
		if(party.length==1)
			throw new java.lang.IndexOutOfBoundsException("A Party must contain at least one person!");
		Fighter[] newParty = new Fighter[party.length-1];
		int i = 0;
		int j = 0;
		for(i=0;i<party.length;i++){
			if(party[i]!=f)
				newParty[i]=party[j];
			else
				j--;
			j++;
		}
		party=newParty;
		
	}
	@Override
	public Animation getNeutralFace() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Animation getHappyFace() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Animation getSadFace() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Animation getAngryFace() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void talk() {
		// TODO Auto-generated method stub
		
	}
}
