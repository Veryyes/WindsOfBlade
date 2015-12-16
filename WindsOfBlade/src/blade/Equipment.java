package blade;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Equipment extends Item{
	char slot;
	/*
	 * h elmet
	 * c hest
	 * p ants
	 * s hoes
	 * g loves
	 * r ight hand
	 * l eft hand
	 */
	int str, intel, dex, will, agil;
	int defense, magicDefense;
	public Equipment(String name, String description, Animation animation, char slot, int str, int intel, int dex, int will, int agil, int def, int mdef) {
		super(name, description, animation);
		this.slot=slot;
		this.str=str;
		this.intel=intel;
		this.dex=dex;
		this.will=will;
		this.agil=agil;
		defense=def;
		magicDefense=mdef;
	}
	public static Equipment parseEquipment(File f){
		try(BufferedReader br = new BufferedReader(new FileReader(f))){
			String[] data = br.readLine().split("_");
			String name = f.getName().substring(f.getName().indexOf("_")+1,f.getName().indexOf("."));
			return new Equipment(
					name,
					data[0],
					new Animation(ImageManager.get("res/items/"+name+".png")),
					data[1].charAt(0),
					Integer.parseInt(data[2]),
					Integer.parseInt(data[3]),
					Integer.parseInt(data[4]),
					Integer.parseInt(data[5]),
					Integer.parseInt(data[6]),
					Integer.parseInt(data[7]),
					Integer.parseInt(data[8]));
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
	}
	public Window createItemDetailWindow(){
		TypeWriter.setSize(.75f);
		int width = animation.getCurrentFrame().getWidth()+8+Window.BORDER_SIZE*2+192;
		String windowText = description;
		windowText+="\n"+getSlotString()+"\n";
		if(defense<0) windowText+="DEF:-"+defense+"\n";
		else if(defense>0) windowText+="DEF:+"+defense+"\n";
		if(magicDefense<0) windowText+="MDEF:-"+magicDefense+"\n";
		else if(magicDefense>0) windowText+="MDEF:+"+magicDefense+"\n";
		if(str<0) windowText+="STR:-"+str+"\n";
		else if(str>0) windowText+="STR:+"+str+"\n";
		if(intel<0) windowText+="INT:-"+intel+"\n";
		else if(intel>0) windowText+="INT:+"+intel+"\n";
		if(dex<0) windowText+="DEX:-"+dex+"\n";
		else if(dex>0) windowText+="DEX:+"+dex+"\n";
		if(will<0) windowText+="WILL:-"+will+"\n";
		else if(will>0) windowText+="WILL:+"+will+"\n";
		if(agil<0) windowText+="AGIL:-"+agil+"\n";
		else if(agil>0) windowText+="AGIL:+"+agil+"\n";
		int height = animation.getCurrentFrame().getHeight()+32+Window.BORDER_SIZE*2+TypeWriter.calcHeight(windowText, width);
		itemDetail = new Window(Game.frame.getMousePosition().x,Game.frame.getMousePosition().y, width, height, windowText ,animation);
		itemDetail.visible=true;
		itemDetail.backColor = new Color(0,0,0,192);
		TypeWriter.setSize(1);
		return itemDetail;
	}
	public String getSlotString(){
		switch(slot){
		case 'h':
			return "Helmet";
		case 'c':
			return "Chest";
		case 'p':
			return "Pants";
		case 's':
			return "Shoes";
		case 'g':
			return "Gloves";
		case 'l':
			return "Left Hand";
		case 'r':
			return "Right Hand";
		default:
			return "Unequippable";
		}
	}
}
