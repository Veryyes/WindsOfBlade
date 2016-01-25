package blade;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Shop extends Entity implements Talkable{
	class PurchaseButton extends Button{
		int price;
		Item item;
		public PurchaseButton(Item item, int price) {
			super(null, "$"+price+" "+item.getName());
			if(item.amount>1)
				this.text+=" x"+item.amount;
			this.price=price;
			this.item=item;
		}
		@Override
		public void update(Graphics g){
			TypeWriter.setSize(.75f);
			if(visible){
				drawBackground(g, backColor);
				drawText(g);
				//System.out.println(Item.itemDetail);
				//Item.itemDetail.visible=false;
				for(Cell<Window> w = shopWindow.subWindows.getHead();w!=null;w=w.getNext()){
					Item.itemDetail.visible|=w.getValue().contains(Game.frame.getMousePosition());
					Item.itemDetail.setVisibleAllSubWindows(Item.itemDetail.visible);
				}
			//	System.out.println(Item.itemDetail.visible);
				if(contains(Game.frame.getMousePosition())){
					drawFrame(g);
					//if(Item.itemDetail.text.equals(""))
					Item.itemDetail=item.createItemDetailWindow();
					if(Game.mousePressed)
						run();
				}//else
					//Item.itemDetail.text="";
			}
			updateSubWindows(g);
			TypeWriter.setSize(1);
		}
		@Override
		public void run(){
			if(Game.player.money>=price){
				Game.player.money-=price;
				//TODO give play item
			}
		}
	}
	private Animation neutral, happy, sad, angry;
	boolean isTalking;
	int waitTime;
	Rectangle conversationBox;
	String text;
	static Window shopWindow;
	private LinkedList<PurchaseButton> pBtns;
	public Shop(int x, int y, String name) {
		super(x, y);
		this.name=name;
		waitTime=0;
		isTalking=false;
		conversationBox = new Rectangle(box.x-8,box.y-8,80,80);
		File f = new File("data/npc/"+name+".snpc");
		try(BufferedReader br = new BufferedReader(new FileReader(f))){
			String line = br.readLine();
			animation = new Animation(ImageManager.getList(line.split(",")));//Should always be there
			line=br.readLine();
			neutral = new Animation(ImageManager.getList(line.split(",")));//Should always be there
			line=br.readLine();
			if(line.charAt(0)==(char)0)
				happy=null;
			else
				happy = new Animation(ImageManager.getList(line.split(",")));
			line=br.readLine();
			if(line.charAt(0)==(char)0)
				sad=null;
			else
				sad = new Animation(ImageManager.getList(line.split(",")));
			line=br.readLine();
			if(line.charAt(0)==(char)0)
				angry=null;
			else
				angry = new Animation(ImageManager.getList(line.split(",")));
			text = br.readLine();
			int amount;
			while((amount=br.read())!=-1&&(line=br.readLine())!=null)
				pBtns.add(new PurchaseButton(Item.get(line),amount));
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	@Override
	public void update(Graphics g){
		super.update(g);
		animation.getCurrentFrame().draw(box.x, box.y, g);
		conversationBox = new Rectangle(box.x-8,box.y-8,80,80);
		if(conversationBox.intersects(Game.player.box)){
			waitTime--;
			if(waitTime<0)
				waitTime=0;
			if(KeyManager.isPressed(' ')&&waitTime==0)
				talk();
		}
	}
	public void resetConversation(){
		isTalking=false;
		Npc.textWindow.visible=false;
		Npc.speakerWindow.visible=false;
		//Inn.yesNoWindow.visible=false;
		//Inn.yesNoWindow.setVisibleAllSubWindows(false);
		Shop.shopWindow.visible=false;
		Shop.shopWindow.setVisibleAllSubWindows(false);
		Item.itemDetail.visible=false;
		Camera.canMove=true;
		Game.player.talkingTarget=null;
	}
	@Override
	public Animation getNeutralFace() {
		return neutral;
	}
	@Override
	public Animation getHappyFace() {
		return happy;
	}
	@Override
	public Animation getSadFace() {
		return sad;
	}
	@Override
	public Animation getAngryFace() {
		return angry;
	}
	@Override
	public void talk() {
		if(isTalking)
			resetConversation();
		else{
			isTalking=true;
			shopWindow.visible=true;
			shopWindow.setVisibleAllSubWindows(true);
			shopWindow.setLayout(pBtns.size(), 1);
			for(int i=0;i<pBtns.size();i++){
				pBtns.get(i).setParent(shopWindow, true);
				shopWindow.add(i,0,pBtns.get(i));
			}
			Npc.textWindow.text=text;
		}
		
	}

}
