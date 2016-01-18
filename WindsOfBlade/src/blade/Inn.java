package blade;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Inn extends Entity implements Talkable{
	int price;
	static Window yesNoWindow;
	static byte answer = 0;
	public Inn(int x, int y, String name) {
		super(x, y, name);
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
	protected void loadData(){
		try(BufferedReader br = new BufferedReader(new FileReader(new File("data/npc/"+name+".txt")))){
			String line=br.readLine();
			this.price = Integer.parseInt(line.substring(9));
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
	@Override
	public void update(Graphics g){
		super.update(g);
		if(Game.player.talkingTarget==this){
			yesNoWindow.visible=true;
			Inn.yesNoWindow.setVisibleAllSubWindows(true);
		}
		if(!yesNoWindow.visible&&answer!=0)//Resets the answer if not talking
			answer=0;
		if(isTalking){
			if(answer==1){ //Yes
				if(Game.player.maxHp>=price){
					Game.player.money-=price;
					for(int i=0;i<Game.player.party.length;i++){
						if(Game.player.party[i]==null)
							break;
						Game.player.party[i].fullRestore();
					}
				}else{
					Npc.speakerWindow.text="You don't have enough money!";
				}
				resetConversation();
				answer=0;
			}else if(answer==-1){ //No
				Npc.speakerWindow.text="Alright then.";
				resetConversation();
				answer=0;
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