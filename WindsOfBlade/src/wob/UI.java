package wob;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
	/*
	 * Container for buttons and ui stuff...
	 */
public class UI implements MouseListener{
	public static Button quitBtn;
	public static Button startBtn;
	public static Button attackBtn;
	public static Button techniqueBtn;
	public static Button itemBtn;
	public static Button runBtn;
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
		attackBtn=new Button(32,448,168,40){
			public void run(){
				//Switch to target selection menu
				System.out.println("attack");
				BattleManager.battleState|=2;
				BattleManager.battleState|=4;
				BattleManager.battleState&=~1;
				disableBattleBtns();
			}
		};
		attackBtn.enabled=false;
		techniqueBtn=new Button(32,496,252,40){
			public void run(){
				//switch to technique list
				System.out.println("tech");
				BattleManager.battleState|=8;
				BattleManager.battleState&=~1;
				disableBattleBtns();
			}
		};
		techniqueBtn.enabled=false;
		itemBtn=new Button(32,542,112,40){
			public void run(){
				//switch to item list
				System.out.println("item");
				BattleManager.battleState|=16;
				BattleManager.battleState&=~1;
				disableBattleBtns();
			}
		};
		itemBtn.enabled=false;
		runBtn=new Button(176,542,84,40){
			public void run(){
				//%chance to get away
				System.out.println("run");
				BattleManager.battleState|=32;
				BattleManager.battleState&=~1;
				disableBattleBtns();
			}
		};
		runBtn.enabled=false;
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
	public static void enableBattleBtns(){
		attackBtn.enabled=true;
		techniqueBtn.enabled=true;
		itemBtn.enabled=true;
		runBtn.enabled=true;
	}
	public static void disableBattleBtns(){
		attackBtn.enabled=false;
		techniqueBtn.enabled=false;
		itemBtn.enabled=false;
		runBtn.enabled=false;
	}
	public void mousePressed(MouseEvent arg0) {
		startBtn.update();
		quitBtn.update();
		attackBtn.update();
		techniqueBtn.update();
		itemBtn.update();
		runBtn.update();
	}
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0)  {}
	public void mouseReleased(MouseEvent arg0){}

}
