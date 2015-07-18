package wob;

import java.awt.Color;
import java.awt.Graphics;

public class InnKeeper extends Npc {
	int price;
	public InnKeeper(int x, int y, String name, int price) {
		super(x,y,name);
		this.price=price;
	}
	public void update(){
		super.update();
	}
	public void worldRender(Graphics g){
		g.drawImage(animation.getFrame(0),x,y,null);
		if(isTalking){
			UI.drawRectUI(g);
			if(Game.player.money<price){
				g.drawImage(animation.getFrame(0),23,315,113,113,Color.black,null);
				UI.drawRectUI(15,308,128,128,false,g);
				TypeWriter.drawMessage("Welcome!, One night here costs "+price+". Wait, you dont have enought money!!",g);
			}else{
				if(conversation[conversationIndex].split(":")[0].equals("player"))
					g.drawImage(Game.player.animation.getFrame(0),23,315,113,113,Color.black,null);
				else
					g.drawImage(animation.getFrame(0),23,315,113,113,Color.black,null);
				UI.drawRectUI(15,308,128,128,false,g);
				TypeWriter.drawMessage(conversation[conversationIndex].split(":")[1], g);
				UI.yesBtn.enabled=true;
				UI.noBtn.enabled=true;
			}
		}
		
	}
}
