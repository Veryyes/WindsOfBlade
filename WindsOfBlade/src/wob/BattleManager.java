package wob;

import java.awt.Graphics;
import java.util.LinkedList;

public class BattleManager {
	//1		2	     4
	//mm->attack->selection
	//		8			4 
	//mm->techlist->selection
	//		16			4
	//mm->itemlist->selection
	//   	32
	//mm->run screen->back to mm is fail else to field
	//64 - Damage Calc & Animation;
	public static byte battleState = 0;
	public static byte buttonShift = 0;
	public static LinkedList<Enemy> enemies= new LinkedList<Enemy>();
	public static LinkedList<Actor> targets = new LinkedList<Actor>();
	public static Move selectedTechnique;
	public static Item selectedItem;
	public static Actor selectedTarget;
	public static boolean attack = false;
	private static Animation backArrow = new Animation(ImageManager.getImage("res/ui/backArrow.png"));
	public static void render(Graphics g){
		for(int i=0;i<enemies.size();i++){
			g.drawImage(ImageManager.getImage("res/enemy/"+enemies.get(i).name+".png"),256*(i+1)-128,128,null);
		}
		if((battleState&1)>0){								//Menu
			TypeWriter.drawString("attack", 32, 448, g);
			TypeWriter.drawString("technique", 32, 496, g);
			TypeWriter.drawString("item", 32, 542, g);
			TypeWriter.drawString("run", 176, 542, g);
		}else if((battleState&8)>0){						//Technique List
			UI.drawRectUI(15,372,64,64,true,g);
			g.drawImage(backArrow.getFrame(0),33,384,null);
			TypeWriter.drawMoveName(Game.player.techniques, 0, 32, 448, g);
			TypeWriter.drawMoveName(Game.player.techniques, 1, 362, 448, g);
			TypeWriter.drawMoveName(Game.player.techniques, 2, 692, 448, g);
			              
			TypeWriter.drawMoveName(Game.player.techniques, 3, 32, 498, g);
			TypeWriter.drawMoveName(Game.player.techniques, 4, 362, 498, g);
			TypeWriter.drawMoveName(Game.player.techniques, 5, 692, 498, g);
			
			TypeWriter.drawMoveName(Game.player.techniques, 6, 32, 548, g);
			TypeWriter.drawMoveName(Game.player.techniques, 7, 362, 548, g);
			TypeWriter.drawMoveName(Game.player.techniques, 8, 692, 548, g);
		}else if((battleState&16)>0){						//Item List
			UI.drawRectUI(15,372,64,64,true,g);
			g.drawImage(backArrow.getFrame(0),33,384,null);
			TypeWriter.drawItemName(Game.player.inventory, 0, 32, 448, g);
			TypeWriter.drawItemName(Game.player.inventory, 1, 362, 448, g);
			TypeWriter.drawItemName(Game.player.inventory, 2, 692, 448, g);
			              
			TypeWriter.drawItemName(Game.player.inventory, 3, 32, 498, g);
			TypeWriter.drawItemName(Game.player.inventory, 4, 362, 498, g);
			TypeWriter.drawItemName(Game.player.inventory, 5, 692, 498, g);
			               
			TypeWriter.drawItemName(Game.player.inventory, 6, 32, 548, g);
			TypeWriter.drawItemName(Game.player.inventory, 7, 362, 548, g);
			TypeWriter.drawItemName(Game.player.inventory, 8, 692, 548, g);
		}else if((battleState&32)>0){						//Run
			battleState=0;
			Game.gameStates|=16;
			Game.gameStates&=~32;
		}else if((battleState&4)>0){						//Target Selection
			UI.drawRectUI(15,372,64,64,true,g);
			g.drawImage(backArrow.getFrame(0),33,384,null);
			TypeWriter.drawTargetName(targets, 0, 32, 448, g);
			TypeWriter.drawTargetName(targets, 1, 362, 448, g);
			TypeWriter.drawTargetName(targets, 2, 692, 448, g);
			                       
			TypeWriter.drawTargetName(targets, 3, 32, 498, g);
			TypeWriter.drawTargetName(targets, 4, 362, 498, g);
			TypeWriter.drawTargetName(targets, 5, 692, 498, g);
			                         
			TypeWriter.drawTargetName(targets, 6, 32, 548, g);
			TypeWriter.drawTargetName(targets, 7, 362, 548, g);
			TypeWriter.drawTargetName(targets, 8, 692, 548, g);
		}else if((battleState&64)>0){						//Damage Calc & Animation;
			if(attack==true){
				
			}else if(selectedTechnique!=null){
				
			}else if(selectedItem!=null){
				
			}
			//On finish
			//BattleManager.battleState&=~64;
			//BattleManager.battleState|=1;
		}
	}
	public static boolean isAttackPhase(){
		return(selectedTechnique==null&&selectedItem==null&&(battleState&4)>0);
	}
}
