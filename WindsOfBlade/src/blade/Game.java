package blade;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * An RPG Game
 * @author Brandon Wong
 * @version 1.00
 * @since 3/22/2015
 */
public class Game extends JPanel implements MouseListener{
	private static final long serialVersionUID = -2063351337678928914L;
	private static final float VERSION = .1f;
	public static final byte MAINMENU = 0;
	public static final byte LOADMENU = 1;
	public static final byte MENU = 2;
	public static final byte FIELD = 3;
	public static final byte BATTLE = 4;
	public static final byte CUTSCENE = 5;
	public static final byte GAMEOVER = 6;
	public static final byte GRIDSIZE = 64;
	public static byte gameState = 0;
	public static JFrame frame;
	public static int frameWidth;
	public static int frameHeight;
	public static int fps;
	public static double frameSkip;
	public static long gameTime, sleepTime;
	public static boolean mousePressed;
	public static Map map;
	public static Player player;
	public static void main(String[] args) throws IOException {
		gameState=MAINMENU;
		init();
	///	//ImageManager.printAllImages();
		map = new Map(new File("data/maps/test.txt"));
		gameState=FIELD;
		/*if(args.length>0){
			if(args[0].equals("-mm"))
				tools.MoveMaker.main(null);
			else if(args[0].equals("-em"))
				tools.EnemyMaker.main(null);
			else if(args[0].equals("-im"))
				tools.ItemMaker.main(null);
		}else{}*/
		//init();
	}
	/**
	 * The Main update method
	 */
	@Override
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
		if(gameState==FIELD){
			Camera.update();
			map.update(g);
			player.update(g);
			Npc.speakerWindow.update(g);
			Npc.textWindow.update(g);
			Inn.yesNoWindow.update(g);
			Shop.shopWindow.update(g);
			TypeWriter.setSize(.75f);
			Item.itemDetail.update(g);
			Battle.menuWin.visible=true;
			Battle.menuWin.update(g);
			TypeWriter.setSize(1);
		}
	}
	/**
	 * Game Initialization
	 * @throws IOException
	 */
	public static void init() throws IOException{
		System.out.println("[INFO] Winds of Blade v"+VERSION+" is Launching!");
		loadConfigs();
		gameState=MAINMENU;
		frame = new JFrame("Winds of Blade v."+VERSION);
		frame.setSize(frameWidth,frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(new Game());
		frame.addKeyListener(new KeyManager());
		frame.addMouseListener(new Game());
		//TODO Mouse & MouseWheel
		frameSkip = 1000d/fps;
		ImageManager.loadImages();
		AudioManager.loadMIDIs();
		TypeWriter.loadFont();
		/*
		 * TODO
		 * UI, Skills, Items, Partners, 
		 */
		Item.loadItems();
		Battle.init();
		Npc.init();
		Inn.init();
		Shop.shopWindow = new Window((int)(.625f*frameWidth),0,(int)(.375f*frameWidth)-16,720-32,"");
		player = new Player();
		frame.setVisible(true);
		gameTime=System.currentTimeMillis();
		System.out.println("[INFO] Winds of Blade v"+VERSION+" Finished Loading!");
	}
	/**
	 * Loads and reads the config file
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
			frameWidth=1280;
			frameHeight=960;
			fps=30;
			createDefaultConfigs();
		}
		
	}
	/**
	 * Creates a standard/Default value config file if it's missing or something
	 */
	private static void createDefaultConfigs(){
		try {
			FileWriter fw = new FileWriter(new File("cfg/settings.cfg"));
			fw.write("width=1280\nheight=960\nfps=60");
			fw.close();
		} catch (IOException e1) {
			System.out.println("[ERROR] Problem Writing config file to disk");
			e1.printStackTrace();
		}
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {
		mousePressed=true;
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		mousePressed=false;
	}
}
