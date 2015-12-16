package blade;

import java.awt.Graphics;

public abstract class Battle {
	public static final byte MENU = 1;
	public static final byte SKILL_SELECT=2;
	public static final byte ITEM_SELECT=3;
	public static final byte TARGET_SELECT=4;
	public static final byte ANIMATE = 5;
	public static byte battleState = MENU;
	public static Enemy[] enemies;//Not sorted
	public static Fighter[] friendly;//Sorted
	private static int playerTurn=0;
	public static Fighter[] queue;//Sorted
	public static Window menuWin;
	public static Window statWin;
	public static Window selectWin;
	private static class SkillButton extends Button{
		Fighter user;
		Skill skill;
		public SkillButton(Window parent, Skill s,Fighter user) {
			super(parent,s.name);
			skill=s;
			this.user=user;
		}
	}
	private static class ItemButton extends Button{
		Fighter user;
		Item item;
		public ItemButton(Window parent, Item i,Fighter user) {
			super(parent, i.name);
			item=i;
			this.user=user;
		}
	}
	private static class TargetButton extends Button{
		Fighter user;
		Fighter target;
		public TargetButton(Window parent, Fighter f,Fighter user) {
			super(parent, f.name);
			target=f;
			this.user=user;
		}
	}
	public static void reset(){
		battleState=MENU;
		playerTurn=0;
		init();
	}
	public static void init(){
		menuWin = new Window(0,Game.frameHeight-224,(int)(Game.frameWidth/5f),192,"");
		statWin = new Window((int)(Game.frameWidth/5f),Game.frameHeight-224,(int)(4*Game.frameWidth/5f),192,"");
		selectWin = new Window(0,Game.frameHeight-224,Game.frameWidth,192,"");
		Button skill = new Button(menuWin,"Skill"){
			public void run(){
				battleState=SKILL_SELECT;
				Fighter current = friendly[playerTurn];
				selectWin.setLayout((int) Math.ceil(current.moves.size()/3), 3);
				for(Skill s: current.moves){//Loads the current ally's moves into the buttons in the window
					selectWin.add(new SkillButton(selectWin,s,current){
						public void run(){
							user.skillUsed=skill;
							battleState=TARGET_SELECT;
							Fighter[] targetlist = new Fighter[Game.player.party.length+enemies.length];
							for(int i=0;i<enemies.length;i++)
								targetlist[i]=enemies[i];
							for(int i=0;i<Game.player.party.length;i++)
								targetlist[i+enemies.length]=Game.player.party[i];
							selectWin.setLayout((int)Math.ceil(targetlist.length/3), 3);
							for(Fighter f:targetlist){
								selectWin.add(new TargetButton(selectWin,f,user){
									public void run(){
										user.target=target;
										battleState=MENU;
										playerTurn++;
									}
								});
							}
						}
					});
				}
			}
		};
		Button item = new Button(menuWin,"Item"){
			public void run(){
				battleState=ITEM_SELECT;
				Fighter current = friendly[playerTurn];
				selectWin.setLayout((int) Math.ceil(Game.player.inventory.size()/3), 3);
				for(Item s: Game.player.inventory){//Loads the current ally's moves into the buttons in the window
					selectWin.add(new ItemButton(selectWin,s,current){
						public void run(){
							user.itemUsed=item;
							battleState=TARGET_SELECT;
							Fighter[] targetlist = new Fighter[Game.player.party.length+enemies.length];
							for(int i=0;i<Game.player.party.length;i++)
								targetlist[i]=Game.player.party[i];
							for(int i=0;i<enemies.length;i++)
								targetlist[i+Game.player.party.length]=enemies[i];
							selectWin.setLayout((int)Math.ceil(targetlist.length/3), 3);
							for(Fighter f:targetlist){
								selectWin.add(new TargetButton(selectWin,f,user){
									public void run(){
										user.target=target;
										battleState=MENU;
										playerTurn++;
									}
								});
							}
						}
					});
				}
			}
		};
		Button run = new Button(menuWin,"Run"){
			public void run(){
				Game.gameState=Game.FIELD;
			}
		};
		menuWin.setLayout(3, 1);
		menuWin.add(skill, item, run);
		menuWin.setVisibleAllSubWindows(true);
		statWin.visible=true;
		selectWin.visible=true;
	}
	public static void update(Graphics g){
		//Draw Enemies
		switch(battleState){
		case MENU:
			menuWin.update(g);
			statWin.update(g);
			if(playerTurn==friendly.length)
				battleState=ANIMATE;
			break;
		case SKILL_SELECT:
		case ITEM_SELECT:
		case TARGET_SELECT:
			selectWin.update(g);
			break;
		case ANIMATE:
			break;
		}
	}
}

