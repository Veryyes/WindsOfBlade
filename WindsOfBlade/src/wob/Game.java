package wob;

import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel{
	public static final float version = .01f;
	public static JFrame frame;
	public static int frameWidth;
	public static int frameHeight;
	public static KeyInputManager km;
	public static Map map;
	public static int fps;
	public static double frameSkip; // = 1000d/fps;
	public static Game canvas;
	public static Player player;
	public static byte gameStates; //On 1, loading 2, paused 4, main menu 8, world 16, battle 32, cut-scene 64, game-over 128;
	public static long gameTime;
	public static long sleepTime;
	public static Point mousePos;
	public static Npc testNPC;
	/*
	 *  Loads up all my stuff, this thread finishes while the JPanel paintComponent is still going;
	 */
	public static void main(String[] args) throws InterruptedException, IOException {
		System.out.println("[INFO] Winds of Blade v"+version+" is Launching!");
		gameStates|=1;	//Game is now On
		gameStates|=2;	//Game is Loading;
		loadConfigs();
		init();
		System.out.println("[INFO] Winds of Blade v"+version+" Finished Loading!");
		gameStates&=~2; //Game done loading;
	}
	/*
	 *  Draw all mah stuff dawg
	 */
	public void paintComponent(Graphics g){
		gameTime+=frameSkip;
		sleepTime = gameTime - System.currentTimeMillis();
		if(sleepTime>=0)
			try {Thread.sleep(sleepTime);} catch (InterruptedException e) {e.printStackTrace();}
		else{
			//System.out.println("[WARNING] Game is Lagging");
		}
		super.paintComponent(g);
		repaint();
		mousePos=frame.getMousePosition();
		//List of Rendering & Update Methods Here:
		if((gameStates&2)>0)										//Loading Screen;
			g.drawImage(ImageManager.loading,0,0,null);
		else if((gameStates&8)>0){									//Main Menu;
			g.drawImage(ImageManager.wbSepia,0,0,null);
			TypeWriter.drawString("Start",200,500,g);
			TypeWriter.drawString("quit",750,500,g);
			TypeWriter.drawString("Winds Of Blade", 100, 50, g);
		}else if((gameStates&16)>0){								//Field
			Camera.update();
			player.update();
			map.update();
			map.render(g);
			player.worldRender(g);	
		}else if((gameStates&32)>0){								//Battle
			g.drawImage(ImageManager.defaultBackdrop, 0, 0, null);
			UI.drawRectUI(g);
			BattleManager.render(g);
		}else if((gameStates&4)>0){									//Menu/Paused/Inventory
			
		}
	}
	/*
	 *  Loading stuff & Initlizaing variables
	 */
	private static void init() throws IOException{
		frame = new JFrame("Winds of Blade v"+version);			//Setting up the JFrame
		frame.setSize(frameWidth,frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		canvas = new Game();									//JPanel that will handle drawing the graphics
		frame.add(canvas);
		frameSkip = 1000d/fps;									//seconds between each frame
		AudioManager.LoadSounds();
		System.out.println("[INFO] Sounds Loaded");
		ImageManager.LoadImages();
		UI.LoadUI();
		TypeWriter.LoadFont();
		System.out.println("[INFO] Images Loaded");
		Move.loadMoves();
		km = new KeyInputManager();		
		frame.addKeyListener(km);	
		UI mouselistener = new UI();
		frame.addMouseListener(mouselistener);
		mousePos=frame.getMousePosition();	
		gameStates|=8;											//Game is on Main Menu
		gameTime=System.currentTimeMillis();
		AudioManager.playBgm(AudioManager.ItsAnAdventure);
		frame.setVisible(true);
		player = new Player();
	}
	/*
	 * 	Reading config files
	 */
	private static void loadConfigs(){
		try {
			String raw="";
			byte[] buffer = new byte[34];
			FileInputStream configReader = new FileInputStream("cfg/settings.cfg");
			configReader.read(buffer);
			configReader.close();
			for(int i=0;i<buffer.length;i++)
				raw+=(char)buffer[i];
			raw=raw.toLowerCase().trim();
			String[] config = raw.split("\n");
			for(int i=0;i<config.length;i++){
				switch(config[i].substring(0,config[i].indexOf('='))){
				case "width":
					frameWidth = Integer.parseInt(config[i].substring(config[i].indexOf('=')+1));
					break;
				case "height":
					frameHeight = Integer.parseInt(config[i].substring(config[i].indexOf('=')+1));
					break;
				case "fps":
					fps = Integer.parseInt(config[i].substring(config[i].indexOf('=')+1));
					break;
				}
			}
			System.out.println("[INFO] Configs loaded");
		} catch (Exception e) {
			System.out.println("[ERROR] Cannot find file \"cfg/settings.cfg\"\n[INFO] Creating \"cfg/settings.cfg\"...\n[INFO] Using Default Settings");
			frameWidth=1024;
			frameHeight=640;
			fps=30;
			createDefaultConfigs();
		}
		
	}
	/*
	 * Creates a standard/Default value config file if it's missing or something
	 */
	private static void createDefaultConfigs(){
		try {
			FileWriter fw = new FileWriter(new File("cfg/settings.cfg"));
			fw.write("width=1024\nheight=640\nfps=60");
			fw.close();
		} catch (IOException e1) {
			System.out.println("[ERROR] Problem Writing config file to disk");
			e1.printStackTrace();
		}
	}
}