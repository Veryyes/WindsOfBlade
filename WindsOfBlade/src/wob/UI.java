package wob;

public class UI {
	/*
	 * Container for buttons and ui stuff...
	 */
	public static Button quitBtn;
	public static void LoadUI(){
		quitBtn = new Button(750,500,112,40){
			public void run(){
				Game.gameStates&=~1;
				System.exit(0);
			}
		};
		
	}

}
