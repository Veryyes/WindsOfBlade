package wob;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
	/*
	 * Container for buttons and ui stuff...
	 */
public class UI implements MouseListener, MouseWheelListener{
	public static Button quitBtn;
	public static Button startBtn;
	public static Button attackBtn;
	public static Button techniqueBtn;
	public static Button itemBtn;
	public static Button runBtn;
	public static BattleButton[][] battleButtons = new BattleButton[3][3];
	public static Button backBtn;
	/*
	 * Defining what each button should do
	 */
	public static void LoadUI(){
		backBtn = new Button(15,372,64,64){
			public void run(){
				if(BattleManager.isAttackPhase()){
					BattleManager.battleState|=1;
					BattleManager.battleState&=~4;
					enableSelectionBtns();
					this.enabled=false;
				}else if(BattleManager.selectedTechnique!=null&&(BattleManager.battleState&4)>0){
					BattleManager.battleState|=8;
					BattleManager.battleState&=~4;
					BattleManager.selectedTechnique=null;
					enableBattleBtns();
				}else if(BattleManager.selectedItem!=null&&(BattleManager.battleState&4)>0){
					BattleManager.battleState|=16;
					BattleManager.battleState&=~4;
					BattleManager.selectedItem=null;
					enableBattleBtns();
				}else{
					BattleManager.battleState|=1;
					BattleManager.battleState&=~24;
					enableSelectionBtns();
					this.enabled=false;
				}
			}
		};
		backBtn.enabled=false;
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
				//Game.gameStates|=6;
				AudioManager.stopBgm("sound/bgm/ItsAnAdventure.mid");
				//Camera.xShift=0;
				//Camera.yShift=0;
				Camera.xShift=(int) -(9*64-Game.player.x+64*Math.cos(Math.toRadians(90)));
				Camera.yShift=(int) -(8*64-Game.player.y+64*Math.sin(Math.toRadians(90)));
				Game.map= new Map("data/maps/alpha.txt");
				Game.map.shiftObjects(Camera.xShift, Camera.yShift);
				Game.gameStates&=~2;
				Game.player=new Player();
				this.enabled=false;
				quitBtn.enabled=false;
			}
		};
		attackBtn=new Button(32,448,168,40){
			public void run(){
				BattleManager.battleState|=2;
				BattleManager.battleState|=4;
				BattleManager.battleState&=~1;
				BattleManager.attack=true;
				BattleManager.selectedItem=null;
				BattleManager.selectedTarget=null;
				BattleManager.selectedTechnique=null;
				disableSelectionBtns();
				enableBattleBtns();
				backBtn.enabled=true;
			}
		};
		attackBtn.enabled=false;
		techniqueBtn=new Button(32,496,252,40){
			public void run(){
				BattleManager.battleState|=8;
				BattleManager.battleState&=~1;
				BattleManager.buttonShift=0;
				disableSelectionBtns();
				enableBattleBtns();
				backBtn.enabled=true;
			}
		};
		techniqueBtn.enabled=false;
		itemBtn=new Button(32,542,112,40){
			public void run(){
				BattleManager.battleState|=16;
				BattleManager.battleState&=~1;
				BattleManager.buttonShift=0;
				disableSelectionBtns();
				enableBattleBtns();
				backBtn.enabled=true;
			}
		};
		itemBtn.enabled=false;
		runBtn=new Button(176,542,84,40){
			public void run(){
				BattleManager.battleState|=32;
				BattleManager.battleState&=~1;
				disableSelectionBtns();
				backBtn.enabled=false;
			}
		};
		runBtn.enabled=false;
		for(int i=0;i<battleButtons[0].length;i++){
			for(int j=0;j<battleButtons.length;j++){
				battleButtons[i][j]=new BattleButton(32+330*j,448+50*i,300,50,j+i*battleButtons.length){
					public void run(){
						try{
							this.selected=true;
							if((BattleManager.battleState&8)>0){
								BattleManager.selectedTechnique=Game.player.techniques.get(this.index+BattleManager.buttonShift*3);
								BattleManager.battleState&=~8;
								BattleManager.battleState|=4;
								//System.out.println(BattleManager.selectedTechnique.name);
							}else if((BattleManager.battleState&16)>0){
								BattleManager.selectedItem=Game.player.inventory.get(this.index+BattleManager.buttonShift*3);
								BattleManager.battleState&=~16;
								BattleManager.battleState|=4;
								//System.out.println(BattleManager.selectedItem.name);
							}else if((BattleManager.battleState&4)>0){
								BattleManager.selectedTarget=BattleManager.targets.get(this.index+BattleManager.buttonShift*3);
								BattleManager.battleState&=~4;
								BattleManager.battleState|=64;
								//System.out.println(BattleManager.selectedTarget.name);
								UI.disableBattleBtns();
								backBtn.enabled=false;
							}
						}catch(java.lang.IndexOutOfBoundsException e){//Catches if you pick something that doesn't exist
							//Do nothing
						}
					}
				};
				battleButtons[i][j].enabled=false;
			}
		}
	}
	public static void drawRectUI(int x, int y, int width, int height, boolean filled, Graphics g){
		if(filled){
			g.setColor(Color.black);
			g.fillRect(x+7,y+7,width-14,height-14);
		}
		g.drawImage(ImageManager.getImage("res/ui/topLeftBorder.png"),x,y,null);
		g.drawImage(ImageManager.getImage("res/ui/botLeftBorder.png"),x,y+height-15,null);
		g.drawImage(ImageManager.getImage("res/ui/topRightBorder.png"),x+width-15,y,null);
		g.drawImage(ImageManager.getImage("res/ui/botRightBorder.png"),x+width-15,y+height-15,null);
		for(int j = x+15;j<x+width-15;j++){
			g.drawImage(ImageManager.getImage("res/ui/topBorder.png"),j,y,null);
			g.drawImage(ImageManager.getImage("res/ui/botBorder.png"),j,y+height-7,null);
		}
		for(int i = y+15; i<y+height-15;i++){
			g.drawImage(ImageManager.getImage("res/ui/leftBorder.png"),x,i,null);
			g.drawImage(ImageManager.getImage("res/ui/rightBorder.png"),x+width-7,i,null);
		}
	}
	public static void drawRectUI(Graphics g){
		drawRectUI(15, 436, 995, 173, true, g);
	}
	public static void drawMenuUI(Graphics g){
		//TypeWriter.setSize(TypeWriter.SMALL);
		drawRectUI(0,0,(int)((Game.frameWidth-6)*.25),Game.frameHeight-28,false,g);
		drawRectUI((int)((Game.frameWidth-6)*.25),0,(int)((Game.frameWidth-6)*.25),Game.frameHeight-28,false,g);
		drawRectUI((int)((Game.frameWidth-6)*.5),0,(int)((Game.frameWidth-6)*.25),Game.frameHeight-28,false,g);
		drawRectUI((int)((Game.frameWidth-6)*.75),0,(int)((Game.frameWidth-6)*.25),Game.frameHeight-28,false,g);
		TypeWriter.setSize(.7f);
		drawPlayerMenuStats(g);
		for(int i = 0;i<Game.player.party.size();i++){
		//	g.drawImage(Game.player.animation.getFrame(0),((int)((Game.frameWidth-6 )*(2*i+3)/8f))-(int)((Game.frameWidth-6)*.1875/2),16,
			//		(int)((Game.frameWidth-6)*.1875),(int)((Game.frameWidth-6)*.1875),Color.white,null);
		}
		TypeWriter.setSize(TypeWriter.MEDIUM);
	}
	public static void enableSelectionBtns(){
		attackBtn.enabled=true;
		techniqueBtn.enabled=true;
		itemBtn.enabled=true;
		runBtn.enabled=true;
	}
	public static void disableSelectionBtns(){
		attackBtn.enabled=false;
		techniqueBtn.enabled=false;
		itemBtn.enabled=false;
		runBtn.enabled=false;
	}
	public static void enableBattleBtns(){
		for(int i=0;i<UI.battleButtons[0].length;i++){
			for(int j=0;j<UI.battleButtons.length;j++){
				battleButtons[i][j].enabled=true;
			}
		}
	}
	public static void disableBattleBtns(){
		for(int i=0;i<UI.battleButtons[0].length;i++){
			for(int j=0;j<UI.battleButtons.length;j++){
				battleButtons[i][j].enabled=false;
			}
		}
	}
	public static void toolbarRender(Graphics g){
		drawRectUI(Game.frameWidth/8,Game.frameHeight-96,(int)((Game.frameWidth*3)/4f),128,true,g);
	}
	public static void buttonRender(Graphics g){
		for(int i=0;i<battleButtons[0].length;i++){
			for(int j=0;j<battleButtons.length;j++){
				battleButtons[i][j].render(g);
			}
		}
		startBtn.render(g);
		quitBtn.render(g);
		attackBtn.render(g);
		techniqueBtn.render(g);
		itemBtn.render(g);
		runBtn.render(g);
		backBtn.render(g);
	}
	public void mousePressed(MouseEvent arg0) {
		for(int i=0;i<battleButtons[0].length;i++){
			for(int j=0;j<battleButtons.length;j++){
				battleButtons[i][j].update();
			}
		}
		startBtn.update();
		quitBtn.update();
		attackBtn.update();
		techniqueBtn.update();
		itemBtn.update();
		runBtn.update();
		backBtn.update();
	}
	public void mouseWheelMoved(MouseWheelEvent e) {
		BattleManager.buttonShift+=e.getWheelRotation();
		if(BattleManager.buttonShift<0)
			BattleManager.buttonShift=0;
	}
	private static void drawPlayerMenuStats(Graphics g){
		g.drawImage(Game.player.animation.getFrame(0),(int)((Game.frameWidth-6)*(1f/8f))-(int)((Game.frameWidth-6)*.1875/2),16,(int)((Game.frameWidth-6)*.1875),(int)((Game.frameWidth-6)*.1875),Color.white,null);
		TypeWriter.drawString(Game.player.name,(int)((Game.frameWidth-6)*(1f/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+16, g);
		TypeWriter.drawString("Level "+Game.player.level,(int)((Game.frameWidth-6)*(1f/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+52, g);
		TypeWriter.drawString("Type "+Type.toString(Game.player.type),(int)((Game.frameWidth-6)*(1f/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+88, g);
		TypeWriter.drawString("HP "+Game.player.hp+"/"+Game.player.maxHp,(int)((Game.frameWidth-6)*(1f/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+124, g);
		TypeWriter.drawString("SP "+Game.player.sp+"/"+Game.player.maxSp,(int)((Game.frameWidth-6)*(1f/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+160, g);		
		TypeWriter.drawString("MP "+Game.player.mp+"/"+Game.player.maxMp,(int)((Game.frameWidth-6)*(1f/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+196, g);
		TypeWriter.drawString("str "+Game.player.str,(int)((Game.frameWidth-6)*(1f/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+232, g);
		TypeWriter.drawString("int "+Game.player.intel,(int)((Game.frameWidth-6)*(1f/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+268, g);
		TypeWriter.drawString("dex "+Game.player.dex,(int)((Game.frameWidth-6)*(1f/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+304, g);
		TypeWriter.drawString("agil "+Game.player.agil,(int)((Game.frameWidth-6)*(1f/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+340, g);
	}
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0)  {}
	public void mouseReleased(MouseEvent arg0){}
}
