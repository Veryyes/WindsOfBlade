package wob;

import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel {
	public static final float version = .01f;
	public static JFrame frame;
	public static int frameWidth;
	public static int frameHeight;
	public static AudioManager am;
	public static ImageManager im;
	public static KeyInputManager km;
	public static SparseMatrix<Map> world;
	public static int fps;
	public static double frameSkip; // = 1000d/fps;
	public static Game canvas;
	public static byte gameStates; //On 1, loading 2, paused 4, main menu 8, world 16, battle 32, cut-scene 64, game-over 128;
	public static long gameTime;
	public static long sleepTime;
	/*
	 *  Loads up all my stuff, this thread finishes while the JPanel paintComponent is still going;
	 */
	public static void main(String[] args) throws InterruptedException {
		System.out.println("[INFO] Winds of Blade v"+version+" is Launching!");
		loadConfigs();
		init();
		System.out.println("[INFO] Winds of Blade v"+version+" Finished Loading!");
	}
	/*
	 *  Draw all mah stuff dawg
	 */
	public void paintComponent(Graphics g){
		gameTime+=frameSkip;
		sleepTime = gameTime - System.currentTimeMillis();
		if(sleepTime>=0)
			try {Thread.sleep(sleepTime);} catch (InterruptedException e) {e.printStackTrace();}
		else
			System.out.println("[WARNING] Game is Lagging");
		super.paintComponent(g);
		repaint();
		//List of Rendering Methods Here:
		System.out.println(KeyInputManager.pressedKeys);
	}
	/*
	 *  Loading stuff & Initlizaing variables
	 */
	private static void init(){
		frame = new JFrame("Winds of Blade v"+version);
		frame.setSize(frameWidth,frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		canvas = new Game();
		frame.add(canvas);
		frameSkip = 1000d/fps;
		am = new AudioManager();
		im = new ImageManager();
		km = new KeyInputManager();
		frame.addKeyListener(km);
		gameStates|=1;
		gameStates|=8;
		gameTime=System.currentTimeMillis();
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
		} catch (Exception e) {
			System.out.println("[ERROR] Cannot find file \"cfg/settings.cfg\"\n[INFO] Creating \"cfg/settings.cfg\"...\n[INFO] Using Default Settings");
			frameWidth=1024;
			frameHeight=640;
			fps=30;
			createDefaultConfigs();
		}
		
	}
	/*
	 * Creates a standard/Default value config file if its missing or something
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
