package wob;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
	/*
	 * Container for buttons and ui stuff...
	 */
public class UI implements MouseListener, MouseWheelListener{
	public static byte buttonShift = 0;
	public static Button quitBtn;
	public static Button startBtn;
	public static Button loadBtn;
	public static Button attackBtn;
	public static Button techniqueBtn;
	public static Button itemBtn;
	public static Button runBtn;
	public static BattleButton[][] battleButtons = new BattleButton[3][3];
	public static Button backBtn;
	public static Button statBtn;
	public static Button[] techListBtn;
	public static Button itemListBtn;
	public static Button saveBtn;
	public static boolean itemWindow;
	public static boolean[] techBtnTextOn;
	/*
	 * Defining what each button should do
	 */
	public static void LoadUI(){
		statBtn = new Button((int)((2f/3f)*Game.frameWidth-8)+16,16,28*5,40){
			public void run(){
				Game.gameStates&=~16;
				Game.gameStates|=4;
				this.enabled=false;
				UI.itemListBtn.enabled=false;
				UI.saveBtn.enabled=false;
				for(int i=0;i<techListBtn.length;i++)
					techListBtn[i].enabled=true;
				Game.menuOn=false;
			}
		};
		statBtn.enabled=false;
		techListBtn = new Button[4];
		techBtnTextOn = new boolean[4];
		techListBtn[0]=new Button(((int)((Game.frameWidth-6 )*1/8f))-(int)((Game.frameWidth-6)*.1875/2),(int)((Game.frameWidth-6)*.1875)+340,28*6,40){
			public void run(){
				UI.techBtnTextOn[0]=!UI.techBtnTextOn[0];
				UI.techBtnTextOn[1]=false;
				UI.techBtnTextOn[2]=false;
				UI.techBtnTextOn[3]=false;
			}
			public void render(Graphics g){
				super.render(g);
				if(this.enabled){
					if(techBtnTextOn[0]){
						UI.drawTechList(g,-1);
						TypeWriter.drawString("Stats", ((int)((Game.frameWidth-6 )*1/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+340, g);
					}else
						TypeWriter.drawString("Skills", ((int)((Game.frameWidth-6 )*1/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+340, g);
				}
			}
		};
		techListBtn[0].enabled=false;
		techListBtn[1]=new Button(((int)((Game.frameWidth-6 )*3/8f))-(int)((Game.frameWidth-6)*.1875/2),(int)((Game.frameWidth-6)*.1875)+340,28*6,40){
			public void run(){
				UI.techBtnTextOn[1]=!UI.techBtnTextOn[1];
				UI.techBtnTextOn[0]=false;
				UI.techBtnTextOn[2]=false;
				UI.techBtnTextOn[3]=false;
			}
			public void render(Graphics g){
				try{
					boolean b = Game.player.party.get(0)!=null;
					super.render(g);
					if(this.enabled){
						if(techBtnTextOn[1]){
							UI.drawTechList(g,0);
							TypeWriter.drawString("Stats", ((int)((Game.frameWidth-6 )*3/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+340, g);
						}else
							TypeWriter.drawString("Skills", ((int)((Game.frameWidth-6 )*3/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+340, g);
					}
				}catch(java.lang.IndexOutOfBoundsException e){
					this.enabled=false;
				}
			}
		};
		techListBtn[1].enabled=false;
		techListBtn[2]=new Button(((int)((Game.frameWidth-6 )*5/8f))-(int)((Game.frameWidth-6)*.1875/2),(int)((Game.frameWidth-6)*.1875)+340,28*6,40){
			public void run(){
				UI.techBtnTextOn[2]=!UI.techBtnTextOn[2];
				UI.techBtnTextOn[1]=false;
				UI.techBtnTextOn[0]=false;
				UI.techBtnTextOn[3]=false;
			}
			public void render(Graphics g){
				try{
					boolean b = Game.player.party.get(1)!=null;
					super.render(g);
					if(this.enabled){
						if(techBtnTextOn[2]){
							UI.drawTechList(g,1);
							TypeWriter.drawString("Stats", ((int)((Game.frameWidth-6 )*(5)/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+340, g);
						}else
							TypeWriter.drawString("Skills", ((int)((Game.frameWidth-6 )*(5)/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+340, g);
					}
				}catch(java.lang.IndexOutOfBoundsException e){
					this.enabled=false;
				}
			}
		};
		techListBtn[2].enabled=false;
		techListBtn[3]=new Button(((int)((Game.frameWidth-6 )*7/8f))-(int)((Game.frameWidth-6)*.1875/2),(int)((Game.frameWidth-6)*.1875)+340,28*6,40){
			public void run(){
				UI.techBtnTextOn[3]=!UI.techBtnTextOn[3];
				UI.techBtnTextOn[1]=false;
				UI.techBtnTextOn[2]=false;
				UI.techBtnTextOn[0]=false;
			}
			public void render(Graphics g){
				try{
					boolean b = Game.player.party.get(2)!=null;
					super.render(g);
					if(this.enabled){
						if(techBtnTextOn[3]){
							UI.drawTechList(g,2);
							TypeWriter.drawString("Stats", ((int)((Game.frameWidth-6 )*7/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+340, g);
						}else
							TypeWriter.drawString("Skills", ((int)((Game.frameWidth-6 )*7/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+340, g);
					}
				}catch(java.lang.IndexOutOfBoundsException e){
					this.enabled=false;
				}
			}
		};
		techListBtn[3].enabled=false;
		itemListBtn = new Button((int)((2f/3f)*Game.frameWidth-8)+16,16+48,28*5,40){
			public void run(){
				this.enabled=false;
				UI.statBtn.enabled=false;
				UI.saveBtn.enabled=false;
				Game.menuOn=false;
				itemWindow=true;
			}
		};
		itemListBtn.enabled=false;
		saveBtn = new Button((int)((2f/3f)*Game.frameWidth-8)+16,16+96,28*4,40){
			public void run(){
				this.enabled=false;
				UI.itemListBtn.enabled=false;
				UI.statBtn.enabled=false;
				Game.menuOn=false;
				//TODO make this better
				try {
					FileWriter fw = new FileWriter(new File("data/save.dat"));
					fw.write(Camera.xShift+"\n"+Camera.yShift+"\n");
					fw.write(Game.map.filename+"\n");
					fw.write(Game.player.level+"\n"+Game.player.experience+"\n"+Game.player.money+"\n");
					fw.write(Game.player.str+"\n"+Game.player.intel+"\n"+Game.player.dex+"\n"+Game.player.will+"\n"+Game.player.agil+"\n");
					fw.write(Game.player.hp+"\n"+Game.player.maxHp+"\n"+Game.player.mp+"\n"+Game.player.maxMp+"\n"+Game.player.sp+"\n"+Game.player.maxSp);
					//Add items, tech, equipment, and party members & their stats
					fw.close();
					System.out.println("[INFO] Saving Complete: \"data/save.dat\" written to disk");
				} catch (IOException e) {
					System.out.println("[ERROR] Problem Writing save file to disk");
					e.printStackTrace();
				}
			}
		};
		loadBtn=new Button(460,500,4*28,40){
			public void run(){
				this.enabled=false;
				startBtn.enabled=false;
				quitBtn.enabled=false;
				Game.gameStates&=~8;
				Game.gameStates|=18;
				Game.gameStates&=~2;
				AudioManager.stopBgm("sound/bgm/ItsAnAdventure.mid");
				//Loading stuff in
				try{
					String rawInput="";
					int item;
					BufferedReader br = new BufferedReader(new FileReader(new File("data/save.dat")));
					while((item=br.read())!=-1)
						rawInput+=(char)item;
					br.close();
					rawInput=rawInput.trim();
					String[] data = rawInput.split("\n");
					Camera.xShift=Integer.parseInt(data[0]);
					Camera.yShift=Integer.parseInt(data[1]);
					Game.map= new Map(data[2]);
					Game.player=new Player();
					Game.player.level=Integer.parseInt(data[3]);
					Game.player.experience=Integer.parseInt(data[4]);
					Game.player.money=Integer.parseInt(data[5]);
					Game.player.str=Integer.parseInt(data[6]);
					Game.player.intel=Integer.parseInt(data[7]);
					Game.player.dex=Integer.parseInt(data[8]);
					Game.player.will=Integer.parseInt(data[9]);
					Game.player.agil=Integer.parseInt(data[10]);
					Game.player.hp=Integer.parseInt(data[11]);
					Game.player.maxHp=Integer.parseInt(data[12]);
					Game.player.mp=Integer.parseInt(data[13]);
					Game.player.maxMp=Integer.parseInt(data[14]);
					Game.player.sp=Integer.parseInt(data[15]);
					Game.player.maxSp=Integer.parseInt(data[16]);
					Game.map.shiftObjects(Camera.xShift, Camera.yShift);
				}catch(Exception e){
					System.out.println("[SEVERE] Problems reading save file");
					System.exit(1);
				}
			}
		};
		saveBtn.enabled=false;
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
				loadBtn.enabled=false;
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
				buttonShift=0;
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
				buttonShift=0;
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
								BattleManager.selectedTechnique=Game.player.techniques.get(this.index+buttonShift*3);
								BattleManager.battleState&=~8;
								BattleManager.battleState|=4;
								//System.out.println(BattleManager.selectedTechnique.name);
							}else if((BattleManager.battleState&16)>0){
								BattleManager.selectedItem=Game.player.inventory.get(this.index+buttonShift*3);
								BattleManager.battleState&=~16;
								BattleManager.battleState|=4;
								//System.out.println(BattleManager.selectedItem.name);
							}else if((BattleManager.battleState&4)>0){
								BattleManager.selectedTarget=BattleManager.targets.get(this.index+buttonShift*3);
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
		BufferedImage t=ImageManager.getImage("res/ui/topBorder.png");
		BufferedImage b=ImageManager.getImage("res/ui/botBorder.png");
		BufferedImage l=ImageManager.getImage("res/ui/leftBorder.png");
		BufferedImage r=ImageManager.getImage("res/ui/rightBorder.png");
		g.drawImage(ImageManager.getImage("res/ui/topLeftBorder.png"),x,y,null);
		g.drawImage(ImageManager.getImage("res/ui/botLeftBorder.png"),x,y+height-15,null);
		g.drawImage(ImageManager.getImage("res/ui/topRightBorder.png"),x+width-15,y,null);
		g.drawImage(ImageManager.getImage("res/ui/botRightBorder.png"),x+width-15,y+height-15,null);
		for(int j = x+15;j<x+width-15;j++){
			g.drawImage(t,j,y,null);
			g.drawImage(b,j,y+height-7,null);
		}
		for(int i = y+15; i<y+height-15;i++){
			g.drawImage(l,x,i,null);
			g.drawImage(r,x+width-7,i,null);
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
			g.drawImage(Game.player.party.get(i).animation.getFrame(0),((int)((Game.frameWidth-6 )*(2*i+3)/8f))-(int)((Game.frameWidth-6)*.1875/2),16,
					(int)((Game.frameWidth-6)*.1875),(int)((Game.frameWidth-6)*.1875),Color.white,null);
			TypeWriter.drawString(Game.player.party.get(i).name,((int)((Game.frameWidth-6 )*(2*i+3)/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+16, g);
			TypeWriter.drawString("Level "+Game.player.party.get(i).level,((int)((Game.frameWidth-6 )*(2*i+3)/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+52, g);
			TypeWriter.drawString("HP "+Game.player.party.get(i).hp+"/"+Game.player.party.get(i).maxHp,((int)((Game.frameWidth-6 )*(2*i+3)/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+88, g);
			TypeWriter.drawString("SP "+Game.player.party.get(i).sp+"/"+Game.player.party.get(i).maxSp,((int)((Game.frameWidth-6 )*(2*i+3)/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+124, g);		
			TypeWriter.drawString("MP "+Game.player.party.get(i).mp+"/"+Game.player.party.get(i).maxMp,((int)((Game.frameWidth-6 )*(2*i+3)/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+160, g);
			TypeWriter.drawString("str "+Game.player.party.get(i).str,(int)((Game.frameWidth-6)*(1f/8f))-((int)((Game.frameWidth-6 )*(2*i+3)/8f)), (int)((Game.frameWidth-6)*.1875)+196, g);
			TypeWriter.drawString("int "+Game.player.party.get(i).intel,(int)((Game.frameWidth-6)*(1f/8f))-((int)((Game.frameWidth-6 )*(2*i+3)/8f)), (int)((Game.frameWidth-6)*.1875)+232, g);
			TypeWriter.drawString("dex "+Game.player.party.get(i).dex,(int)((Game.frameWidth-6)*(1f/8f))-((int)((Game.frameWidth-6 )*(2*i+3)/8f)), (int)((Game.frameWidth-6)*.1875)+268, g);
			TypeWriter.drawString("agil "+Game.player.party.get(i).agil,(int)((Game.frameWidth-6)*(1f/8f))-((int)((Game.frameWidth-6 )*(2*i+3)/8f)), (int)((Game.frameWidth-6)*.1875)+304, g);
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
		for(int i=0;i<techListBtn.length;i++)
			techListBtn[i].render(g);
		startBtn.render(g);
		quitBtn.render(g);
		attackBtn.render(g);
		techniqueBtn.render(g);
		itemBtn.render(g);
		runBtn.render(g);
		backBtn.render(g);
		statBtn.render(g);
		//techListBtn.render(g);
		itemListBtn.render(g);
		saveBtn.render(g);
		loadBtn.render(g);
	}
	public void mousePressed(MouseEvent arg0) {
		for(int i=0;i<battleButtons[0].length;i++){
			for(int j=0;j<battleButtons.length;j++){
				battleButtons[i][j].update();
			}
		}
		for(int i=0;i<techListBtn.length;i++)
			techListBtn[i].update();
		startBtn.update();
		quitBtn.update();
		attackBtn.update();
		techniqueBtn.update();
		itemBtn.update();
		runBtn.update();
		backBtn.update();
		statBtn.update();
		//techListBtn.update();
		itemListBtn.update();
		saveBtn.update();
		loadBtn.update();
	}
	public void mouseWheelMoved(MouseWheelEvent e) {
		buttonShift+=e.getWheelRotation();
		if(buttonShift<0)
			buttonShift=0;
	}
	private static void drawPlayerMenuStats(Graphics g){
		g.drawImage(Game.player.animation.getFrame(0),(int)((Game.frameWidth-6)*(1f/8f))-(int)((Game.frameWidth-6)*.1875/2),16,(int)((Game.frameWidth-6)*.1875),(int)((Game.frameWidth-6)*.1875),Color.white,null);
		TypeWriter.drawString(Game.player.name,(int)((Game.frameWidth-6)*(1f/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+16, g);
		TypeWriter.drawString("Level "+Game.player.level,(int)((Game.frameWidth-6)*(1f/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+52, g);
		//TypeWriter.drawString("Type "+Type.toString(Game.player.type),(int)((Game.frameWidth-6)*(1f/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+88, g);
		TypeWriter.drawString("HP "+Game.player.hp+"/"+Game.player.maxHp,(int)((Game.frameWidth-6)*(1f/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+88, g);
		TypeWriter.drawString("SP "+Game.player.sp+"/"+Game.player.maxSp,(int)((Game.frameWidth-6)*(1f/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+124, g);		
		TypeWriter.drawString("MP "+Game.player.mp+"/"+Game.player.maxMp,(int)((Game.frameWidth-6)*(1f/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+160, g);
		TypeWriter.drawString("str "+Game.player.str,(int)((Game.frameWidth-6)*(1f/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+196, g);
		TypeWriter.drawString("int "+Game.player.intel,(int)((Game.frameWidth-6)*(1f/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+232, g);
		TypeWriter.drawString("dex "+Game.player.dex,(int)((Game.frameWidth-6)*(1f/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+268, g);
		TypeWriter.drawString("agil "+Game.player.agil,(int)((Game.frameWidth-6)*(1f/8f))-(int)((Game.frameWidth-6)*.1875/2), (int)((Game.frameWidth-6)*.1875)+304, g);
	}
	public static void drawMenu(Graphics g){
		drawRectUI((int)((2f/3f)*Game.frameWidth-8),8,(int)((1f/3f)*Game.frameWidth),(int)(.75*Game.frameHeight),true,g);
		TypeWriter.drawString("Stats", (int)((2f/3f)*Game.frameWidth-8)+16,16, g);
		TypeWriter.drawString("Items",(int)((2f/3f)*Game.frameWidth-8)+16,16+48, g);
		TypeWriter.drawString("save",(int)((2f/3f)*Game.frameWidth-8)+16,16+96,g);
		TypeWriter.drawString("Money "+Game.player.money,(int)((2f/3f)*Game.frameWidth-8)+16, 16+144, g);
	
	}
	public static void drawTechList(Graphics g, int partyIndex){
		drawRectUI((int)((1f/3f)*Game.frameWidth-8),32,(int)((1f/3f)*Game.frameWidth-8),(int)(.5f*Game.frameWidth-8),true,g);
		int i=48;
		if(partyIndex==-1){
			for(int index=0;index<Game.player.techniques.size();index++){
				if(i>32+(int)(.5f*Game.frameWidth-8))
					break;
				try{
				TypeWriter.drawString(Game.player.techniques.get(index+buttonShift).toString(), (int)((1f/3f)*Game.frameWidth-8)+16, i, g);
				}catch(java.lang.IndexOutOfBoundsException e){}
				i+=48;
			}
		}else{
			for(int index=0;index<Game.player.party.get(partyIndex).techniques.size();index++){
				if(i>32+(int)(.5f*Game.frameWidth-8))
					break;
				try{
				TypeWriter.drawString(Game.player.party.get(partyIndex).techniques.get(index+buttonShift).toString(), (int)((1f/3f)*Game.frameWidth-8)+16, i, g);
				}catch(java.lang.IndexOutOfBoundsException e){}
				i+=48;
			}
		}
	}
	public static void drawItemList(Graphics g){
		drawRectUI((int)((1f/3f)*Game.frameWidth-8),32,(int)((1f/3f)*Game.frameWidth-8),(int)(.5f*Game.frameWidth-8),true,g);
		int i=48;
		for(int index=0;index<Game.player.inventory.size();index++){
			if(i>32+(int)(.5f*Game.frameWidth-8))
				break;
			try{
			TypeWriter.drawString(Game.player.inventory.get(index+buttonShift).toString(), (int)((1f/3f)*Game.frameWidth-8)+16, i, g);
			}catch(java.lang.IndexOutOfBoundsException e){}
			i+=48;
		}
	}
	public static void toggleMenuButtons(){
		statBtn.enabled=!UI.statBtn.enabled;
		itemListBtn.enabled=!UI.itemListBtn.enabled;
		saveBtn.enabled=!UI.saveBtn.enabled;
		itemWindow=false;
	}
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0)  {}
	public void mouseReleased(MouseEvent arg0){}
}
