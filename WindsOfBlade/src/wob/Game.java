package wob;

import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel {
	public static JFrame frame;
	public static int frameWidth;
	public static int frameHeight;
	public static AudioManager am;
	public static ImageManager im;
	public static SparseMatrix<Map> world;
	public static int fps;
	public static double frameSkip; // = 1000d/fps;
	public static Game canvas;
	public static byte gameStates; //On 1, loading 2, paused 3,
	public static void main(String[] args) {
		System.out.println("[INFO] Winds of Blade is Launching!");
		loadConfigs();
	}
	public static void init(){
		gameStates|=1;
		frame = new JFrame("Winds of Blade");
	}
	public void paintComponent(Graphics g){
		
	}
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
