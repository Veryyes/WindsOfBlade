package wob;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class Npc extends Actor implements WorldObject{
	String name;
	String[] conversation;
	int conversationIndex;
	boolean isTalking;
	Rectangle2D.Double conversationBox;
	int waitTime;
	public Npc(int x, int y, String name) {
		super(x,y);
		hitBox=new Rectangle2D.Double(x,y,64,64);
		isTalking=false;
		conversationIndex=-1;
		conversationBox = new Rectangle2D.Double(x-8,y-8,80,80);
		waitTime=0;
		conversation = new String[5];
		conversation[0]="Hi.";
		conversation[1]="How are you?";
		conversation[2]="Good!";
		conversation[3]="dogs!";
		conversation[4]="food!";
	}
	@Override
	public void update() {
		this.x+=Camera.xVelShift;
		this.y+=Camera.yVelShift;
		hitBox=new Rectangle2D.Double(this.x,this.y,hitBox.getWidth(),hitBox.getHeight());
		conversationBox = new Rectangle2D.Double(x-8,y-8,80,80);
		if(conversationBox.intersects(Game.player.hitBox)){
			waitTime--;
			if(waitTime<0) waitTime=0;
			if(KeyInputManager.isPressed(' ')&&waitTime==0){
				isTalking=true;
				conversationIndex++;
				if(conversationIndex==conversation.length){
					conversationIndex=0;
					isTalking=false;
				}
				waitTime=15;
			}
		}else{
			isTalking=false;
			conversationIndex=-1;
		}
	}
	@Override
	public void worldRender(Graphics g) {
		g.drawImage(ImageManager.tempNPC,x,y,null);
		if(isTalking){
			UI.drawRectUI(g);
			TypeWriter.drawMessage(conversation[conversationIndex], g);
		}
	}

}
