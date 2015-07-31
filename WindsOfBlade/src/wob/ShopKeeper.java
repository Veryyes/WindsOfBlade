package wob;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

public class ShopKeeper extends Npc implements MouseListener{
	PurchaseButton[] purchase;
	public class PurchaseButton extends Button{
		int price;
		Item item;
		public PurchaseButton(Item i, int price, int x, int y){
			super(i.getName()+" $"+price,x,y);
			this.price = price;
			this.item=i;
			this.enabled=false;
		}
	}
	public ShopKeeper(int x, int y, String name, Item[] items, int[] prices) {
		super(x,y,name);
		purchase = new PurchaseButton[items.length];
		for(int i=0;i<purchase.length;i++){
			purchase[i] = new PurchaseButton(items[i], prices[i], 575, i*40+24){
				public void run(){
					if(Game.player.money>=price){
						System.out.println(Game.player.money+"-"+price);
						Game.player.money+= -price;
						System.out.println(Game.player.money);
						Game.player.addItem(Item.createItem(item,1));
						System.out.println("Purchased Item: "+this.item.name);
					}else{
						//TODO play 'no' noise;
					}
				}
			};
			UI.shopBtn.add(purchase[i]);
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

		
		
	}
	public void worldRender(Graphics g){
		super.worldRender(g);
		if(isTalking){
			for(PurchaseButton pb: purchase)
				pb.enabled=true;
			UI.drawRectUI(569, 16, 569, 420, true, g);
			for(PurchaseButton pb: purchase)
				pb.render(g);
		}
	}
	public void mousePressed(MouseEvent arg0) {
		for(PurchaseButton pb: purchase)
			pb.update();
	}
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}

}
