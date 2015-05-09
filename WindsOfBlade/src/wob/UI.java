package wob;

import java.awt.Color;
import java.awt.Graphics;
	/*
	 * Container for buttons and ui stuff...
	 */
public class UI {
	public static Button quitBtn;
	public static Button startBtn;
	/*
	 * Defining what each button should do
	 */
	public static void LoadUI(){
		quitBtn = new Button(750,500,112,40){
			public void run(){
				Game.gameStates&=~1;
				System.exit(0);
			}
		};
		startBtn=new Button(200,500,140,40){
			public void run(){
				Game.gameStates&=~8;
				Game.gameStates|=18;
				AudioManager.stopBgm();
				Game.map= new Map("data/maps/testobj.txt");
				Game.gameStates&=~2;
				Game.player=new Player();
				this.enabled=false;
				quitBtn.enabled=false;
			}
		};
		
	}
	public static void drawRectUI(int x, int y, int width, int height, boolean filled, Graphics g){
		if(filled){
			g.setColor(Color.black);
			g.fillRect(x+7,y+7,width-14,height-14);
		}
		g.drawImage(ImageManager.topLeftUIBorder,x,y,null);
		g.drawImage(ImageManager.botLeftUIBorder,x,y+height-15,null);
		g.drawImage(ImageManager.topRightUIBorder,x+width-15,y,null);
		g.drawImage(ImageManager.botRightUIBorder,x+width-15,y+height-15,null);
		for(int j = x+15;j<x+width-15;j++){
			g.drawImage(ImageManager.topUIBorder,j,y,null);
			g.drawImage(ImageManager.botUIBorder,j,y+height-7,null);
		}
		for(int i = y+15; i<y+height-15;i++){
			g.drawImage(ImageManager.leftUIBorder,x,i,null);
			g.drawImage(ImageManager.rightUIBorder,x+width-7,i,null);
		}
	}
	public static void drawRectUI(Graphics g){
		UI.drawRectUI(15, 436, 995, 173, true, g);
	}

}
