package wob;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Npc extends Actor implements WorldObject{
	String[] conversation;
	int conversationIndex;
	boolean isTalking;
	Rectangle2D.Double conversationBox;
	int waitTime;
	public Npc(int x, int y, String name) {
		super(x,y);
		this.name = name;
		animation=new Animation(ImageManager.getImage("res/npc/npc.png"));
		hitBox=new Rectangle2D.Double(x,y,64,64);
		isTalking=false;
		conversationIndex=-1;
		conversationBox = new Rectangle2D.Double(x-8,y-8,80,80);
		waitTime=0;
		try{
			BufferedReader br = new BufferedReader(new FileReader(new File("data/npc/"+this.name+".txt")));
			String rawInput = "";
			int item;
			while((item=br.read())!=-1)
				rawInput+=(char)item;
			br.close();
			rawInput=rawInput.trim();
			conversation = rawInput.split("\n");
		}catch(Exception e){
			System.out.println("[WARNING] Cannot Find File \"data/npc/"+this.name+".txt\"");
			conversation = new String[1];
			conversation[0] = name+":Hi";
		}
	}
	public void update() {
		updateLocation();
		conversationBox = new Rectangle2D.Double(x-8,y-8,80,80);
		if(conversationBox.intersects(Game.player.hitBox)){
			waitTime--;
			if(waitTime<0) waitTime=0;
			if(KeyManager.isPressed(' ')&&waitTime==0){
				isTalking=true;
				conversationIndex++;
				if(conversationIndex==conversation.length){
					conversationIndex=-1;
					isTalking=false;
				}
				waitTime=15;
			}
		}else{
			isTalking=false;
			conversationIndex=-1;
		}
	}
	public void worldRender(Graphics g) {
		g.drawImage(animation.getFrame(0),x,y,null);
		if(isTalking){
			UI.drawRectUI(g);
			if(conversation[conversationIndex].split(":")[0].equals("player"))
				g.drawImage(Game.player.animation.getFrame(0),23,315,113,113,Color.black,null);
			else
				g.drawImage(animation.getFrame(0),23,315,113,113,Color.black,null);
			UI.drawRectUI(15,308,128,128,false,g);
			TypeWriter.drawMessage(conversation[conversationIndex].split(":")[1], g);
		}
	}

}
