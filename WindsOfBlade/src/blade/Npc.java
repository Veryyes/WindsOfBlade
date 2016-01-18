package blade;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Npc extends Entity{//TODO refactor......, Make Shop & Inn extend directly from Entity
	int conversationIndex;
	int speechIndex;
	boolean isTalking;
	LinkedList<LinkedList<String>> conversation;
	Rectangle conversationBox;
	int waitTime;
	static Window textWindow;
	static Window speakerWindow;
	private Animation walkUp, walkDown, walkLeft, walkRight;
	public Npc(int x, int y, String name){
		super(x,y);
		this.name=name;
		animation = new Animation(ImageManager.get("res/npc/npc.png"));
		conversationBox = new Rectangle(box.x-8,box.y-8,80,80);
		isTalking=false;
		conversationIndex=-1;
		speechIndex=0;
		waitTime=0;
		conversation = new LinkedList<LinkedList<String>>();
		loadData();
	}
	public static void init(){
		textWindow = new Window(16, 720-32, Game.frameWidth-32, 240, "");
		speakerWindow = new Window(16,560,128,128,"");
		speakerWindow.imageVisible=true;
	}
	/**
	 * Loads the rest of the NPC's state from file
	 */
	protected void loadData(){
		try(BufferedReader br = new BufferedReader(new FileReader(new File("data/npc/"+name+".txt")))){
			String line="";
			while(line!=null&&(line=br.readLine())!=null){
				LinkedList<String> text = new LinkedList<String>();
				while(line!=null&&!line.contains("<END>")){
					text.add(line);
					line=br.readLine();
				}
				conversation.add(text);
			}
		}catch(IOException e){
			e.printStackTrace();
			LinkedList<String> defaultText = new LinkedList<String>();
			defaultText.add(name+":Hello");
			conversation.add(defaultText);
		}
	}
	/**
	 * Resets the variables used in a conversation
	 */
	protected void resetConversation(){
		conversationIndex=-1;
		isTalking=false;
		textWindow.visible=false;
		speakerWindow.visible=false;
		Inn.yesNoWindow.visible=false;
		Inn.yesNoWindow.setVisibleAllSubWindows(false);
		Shop.shopWindow.visible=false;
		Shop.shopWindow.setVisibleAllSubWindows(false);
		Item.itemDetail.visible=false;
		Camera.canMove=true;
		Game.player.talkingTarget=null;
	}
	public void update(Graphics g){
		super.update(g);
		animation.getCurrentFrame().draw(box.x,box.y, g);
		conversationBox = new Rectangle(box.x-8,box.y-8,80,80);
		if(conversationBox.intersects(Game.player.box)){
			waitTime--;
			if(waitTime<0)
				waitTime=0;
			if(KeyManager.isPressed(' ')&&waitTime==0){
				if(this instanceof Shop)
					((Shop)this).addBtns=true;
				Game.player.talkingTarget=this;
				isTalking=true;
				textWindow.visible=true;
				speakerWindow.visible=true;
				Camera.canMove=false;
				conversationIndex++;
				if(conversationIndex==conversation.get(speechIndex).size())
					resetConversation();
				else
					textWindow.text = conversation.get(speechIndex).get(conversationIndex);
				waitTime=15;
			}
			
		}
		if(isTalking)
			speakerWindow.animation = animation;
	}
}
