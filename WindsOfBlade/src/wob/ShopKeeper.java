package wob;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class ShopKeeper extends Npc{
	PurchaseButton[] purchase;
	public class PurchaseButton extends Button{
		int price;
		Item item;
		public PurchaseButton(Item i, int price, int x, int y){
			super(i.getName(),x,y);
			this.price = price;
			this.item=i;
		}
	}
	public ShopKeeper(int x, int y, String name, Item[] items, int[] prices) {
		super(x,y,name);
		for(int i=0;i<purchase.length;i++){
			purchase[i] = new PurchaseButton(items[i], prices[i], 511, i*40+24){
				public void run(){
					if(Game.player.money>=price){
						Game.player.money=-price;
						Game.player.inventory.add(item);
					}else{
						//TODO play 'no' noise;
					}
				}
			};
		}
	}
	public void update(){
		updateLocation();
		conversationBox = new Rectangle2D.Double(x-8,y-8,80,80);
		if(conversationBox.intersects(Game.player.hitBox)){
			waitTime--;
			if(waitTime<0) waitTime=0;
			if(KeyManager.isPressed(' ')&&waitTime==0){
				isTalking=true;
				conversationIndex++;
				conversationIndex%=conversation.length; //Conversation cycles
				waitTime=15;
			}
		}else{
			isTalking=false;
			conversationIndex=-1;
		}
		for(PurchaseButton pb: purchase)
			pb.update();
		
	}
	public void worldRender(Graphics g){
		super.worldRender(g);
		UI.drawRectUI(505, 16, 505, 420, true, g);
		for(PurchaseButton pb: purchase)
			pb.render(g);
		
	}

}
