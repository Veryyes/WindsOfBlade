package blade;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;

public class Npc extends Entity implements Talkable{//TODO refactor......, Make Shop & Inn extend directly from Entity
	int conversationIndex;
	int speechIndex;
	boolean isTalking;
	LinkedList<Conversation> conversation;
	Rectangle conversationBox;
	int waitTime;
	static Window textWindow;
	static Window speakerWindow;
	private Animation walkUp, walkDown, walkLeft, walkRight;
	private Animation neutral, happy, sad, angry;
	public Npc(int x, int y, String name){
		super(x,y);
		this.name=name;
		//animation = new Animation(ImageManager.get("res/npc/npc.png"));
		conversationBox = new Rectangle(box.x-8,box.y-8,80,80);
		isTalking=false;
		conversationIndex=-1;
		speechIndex=0;
		waitTime=0;
		conversation = new LinkedList<Conversation>();
		int currByte=0;
		File file = new File("data/npc/"+name+".npc");
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String line = br.readLine();
			currByte+=line.length();
			animation = new Animation(ImageManager.getList(line.split(",")));
			
			line = br.readLine();
			currByte+=line.length();
			if(line.charAt(0)==(char)0)
				walkUp=null;
			else
				walkUp = new Animation(ImageManager.getList(line.split(",")));
			line=br.readLine();
			currByte+=line.length();
			if(line.charAt(0)==(char)0)
				walkDown=null;
			else
				walkDown = new Animation(ImageManager.getList(line.split(",")));
			line=br.readLine();
			currByte+=line.length();
			if(line.charAt(0)==(char)0)
				walkLeft=null;
			else
				walkLeft = new Animation(ImageManager.getList(line.split(",")));
			line=br.readLine();
			currByte+=line.length();
			if(line.charAt(0)==(char)0)
				walkRight=null;
			else
				walkRight = new Animation(ImageManager.getList(line.split(",")));
			
			line=br.readLine();
			currByte+=line.length();
			neutral = new Animation(ImageManager.getList(line.split(",")));
			line=br.readLine();
			currByte+=line.length();
			if(line.charAt(0)==(char)0)
				happy=null;
			else
				happy = new Animation(ImageManager.getList(line.split(",")));
			line=br.readLine();
			currByte+=line.length();
			if(line.charAt(0)==(char)0)
				sad=null;
			else
				sad = new Animation(ImageManager.getList(line.split(",")));
			line=br.readLine();
			currByte+=line.length();
			if(line.charAt(0)==(char)0)
				angry=null;
			else
				angry = new Animation(ImageManager.getList(line.split(",")));
			
			char[] raw = new char[(int) (file.length() - currByte)];
			for(int i=0;i<raw.length;i++)
				raw[i] = (char)br.read();
			String text = new String(raw);
			BufferedReader txtReader = new BufferedReader(new StringReader(text));
			while((line=txtReader.readLine())!=null){
				if(line.substring(0,5).matches("<\\d\\d>")){
					Conversation c = new Conversation(Integer.parseInt(line.substring(1, 3)));
					c.add(line.substring(4));//Adds the first line accounting for the <\d> tag
					while(!(line=txtReader.readLine()).substring(line.length()-5).matches("</\\d\\d>"))
						c.add(line); //Adds lines between tags
					c.add(line.substring(0,line.length()-5));//Adds the last line accounting for the tag at the end
					conversation.add(c);
				}
			}
			txtReader.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public static void init(){
		textWindow = new Window(16, 720-32, Game.frameWidth-32, 240, "");
		speakerWindow = new Window(16,560,128,128,"");
		speakerWindow.imageVisible=true;
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
				//if(this instanceof Shop)
				//	((Shop)this).addBtns=true;
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
		
		
	}
}
