package blade;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Inn extends Entity implements Talkable{
	static Window yesNoWindow;
	static byte answer = 0;
	int price;
	int waitTime;
	String text;
	Rectangle conversationBox;
	boolean isTalking;
	private Animation neutral, happy, sad, angry;
	public Inn(int x, int y, String name) {
		super(x, y);
		this.name=name;
		waitTime=0;
		isTalking=false;
		conversationBox = new Rectangle(box.x-8,box.y-8,80,80);
		File file = new File("data/npc"+name+".inpc");
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			price = br.read();
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
			text = br.readLine();//Rest of file is conversation text
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public static void init(){
		yesNoWindow = new Window(Npc.textWindow.x+Npc.textWindow.width-192,Npc.textWindow.y-128,192,128,"");
		yesNoWindow.setLayout(2, 1);
		Button yes = new Button(yesNoWindow,"Yes"){
			public void run(){
				Inn.answer = 1;
			}
		};
		Button no = new Button(yesNoWindow,"No"){
			public void run(){
				Inn.answer = -1;
			}
		};
		yesNoWindow.add(0,0,yes);
		yesNoWindow.add(1,0,no);
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
		if(!yesNoWindow.visible&&answer!=0)//Resets the answer if not talking
			answer=0;
		if(isTalking){
			if(answer==1){ //Yes
				if(Game.player.maxHp>=price){
					Game.player.money-=price;
					for(int i=0;i<Game.player.party.length;i++){//Party rests
						if(Game.player.party[i]==null)
							break;
						Game.player.party[i].fullRestore();
					}
				}else{// No
					Npc.textWindow.text="You don't have enough money!";
				}
			}else if(answer==-1) //No
				Npc.textWindow.text="Alright then.";
			resetConversation();
			answer=0;
		}
	}
	/**
	 * Resets the variables used in a conversation
	 */
	protected void resetConversation(){
		isTalking=false;
		Npc.textWindow.visible=false;
		Npc.speakerWindow.visible=false;
		Inn.yesNoWindow.visible=false;
		Inn.yesNoWindow.setVisibleAllSubWindows(false);
		/*Shop.shopWindow.visible=false;
		Shop.shopWindow.setVisibleAllSubWindows(false);
		Item.itemDetail.visible=false;*/
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
			Game.player.talkingTarget=this;
			isTalking=true;
			Npc.textWindow.visible=true;
			Npc.speakerWindow.visible=true;
			Camera.canMove=false;
			yesNoWindow.visible=true;
			yesNoWindow.setVisibleAllSubWindows(true);
			Npc.textWindow.text = text;
		}
	}
}