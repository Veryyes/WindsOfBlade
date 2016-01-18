package blade;

import java.awt.Graphics;
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
				
			}
		}
	}
	static Window shopWindow;
	private LinkedList<PurchaseButton> pBtns;
	public boolean addBtns=false;
	public Shop(int x, int y, String name) {
		super(x, y, name);
	}
	@Override
	protected void loadData(){
		pBtns = new LinkedList<PurchaseButton>();
		try(BufferedReader br = new BufferedReader(new FileReader(new File("data/npc/"+name+".txt")))){
			boolean inventoryParse = true;
			String line="";
			while(line!=null&&(line=br.readLine())!=null){
				//Goods
				if(inventoryParse){
					if(line.contains("<SHOP>"))
						inventoryParse=false;
					else{
						String[] buttonData = line.split("=");
						Item product = Item.get(buttonData[0].substring(buttonData[0].indexOf('x')+1));
						product.amount = Integer.parseInt(buttonData[0].substring(0,buttonData[0].indexOf('x')));
						pBtns.add(new PurchaseButton(product,Integer.parseInt(buttonData[1])));
					}
				}else{
					//Text
					LinkedList<String> text = new LinkedList<String>();
					while(line!=null&&!line.contains("<END>")){
						text.add(line);
						line=br.readLine();
					}
					conversation.add(text);
				}
			}
		}catch(IOException e){
			e.printStackTrace();
			LinkedList<String> defaultText = new LinkedList<String>();
			defaultText.add(name+":Hello");
			conversation.add(defaultText);
		}
	}
	@Override
	public void update(Graphics g){
		super.update(g);
		if(Game.player.talkingTarget==this){
			shopWindow.visible=true;
			shopWindow.setVisibleAllSubWindows(true);
			if(addBtns){
				shopWindow.setLayout(pBtns.size(), 1);
				for(int i=0;i<pBtns.size();i++){
					pBtns.get(i).setParent(shopWindow, true);
					shopWindow.add(i,0,pBtns.get(i));
				}
				addBtns=false;
			}
		}
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
