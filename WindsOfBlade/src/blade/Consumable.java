package blade;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Consumable extends Item{
	int hp, mp, sp;
	boolean teamOnly;
	public Consumable(String name, String windowText, Animation animation, int hp, int mp, int sp, boolean teamOnly){
		super(name, windowText, animation);
		this.hp=hp;
		this.mp=mp;
		this.sp=sp;
		this.teamOnly=teamOnly;
	}
	public void use(Fighter user){
		if(teamOnly == (user instanceof Player || user instanceof Partner)){
			user.hp+=hp;
			user.mp+=mp;
			user.sp+=sp;
			user.clampPoints();
		}
	}
	protected static Consumable parseConsumable(File f){
		try(BufferedReader br = new BufferedReader(new FileReader(f))){
			String[] data = br.readLine().split("_");
			String name = f.getName().substring(f.getName().indexOf("_")+1,f.getName().indexOf("."));
			return new Consumable(
					name,
					data[0],
					new Animation(ImageManager.get("res/items/"+name+".png")),
					Integer.parseInt(data[1]),
					Integer.parseInt(data[2]),
					Integer.parseInt(data[3]),
					Boolean.parseBoolean(data[4]));
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Window createItemDetailWindow(){
		TypeWriter.setSize(.75f);
		int width = animation.getCurrentFrame().getWidth()+8+Window.BORDER_SIZE*2+192;
		String windowText = description;
		if(hp<0) windowText+="\nHP:-"+hp+"\n";
		else if(hp>0) windowText+="\nHP:+"+hp+"\n";
		if(mp<0) windowText+="MP:-"+mp+"\n";
		else if(mp>0) windowText+="MP:+"+mp+"\n";
		if(sp<0) windowText+="SP:-"+sp;
		else if(sp>0) windowText+="SP:+"+sp;
		int height = animation.getCurrentFrame().getHeight()+32+Window.BORDER_SIZE*2+TypeWriter.calcHeight(windowText, width);
		itemDetail = new Window(Game.frame.getMousePosition().x,Game.frame.getMousePosition().y, width, height,windowText ,animation);
		itemDetail.visible=true;
		itemDetail.backColor = new Color(0,0,0,192);
		TypeWriter.setSize(1);
		return itemDetail;
	}
}
