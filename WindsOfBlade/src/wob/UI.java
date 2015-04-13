package wob;

public class UI {
	/*
	 * Container for buttons and ui stuff...
	 */
	public static Button quitBtn;
	public static Button startBtn;
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
				Game.map= new Map("data/maps/test.txt");
				Game.gameStates&=~2;
				Game.player=new Player();
			}
		};
		
	}

}
