package wob;

import java.awt.Graphics;

public class BattleManager {
	//1		2	     4
	//mm->attack->selection
	//		8			4 
	//mm->techlist->selection
	//		16			4
	//mm->itemlist->selection
	//   	32
	//mm->run screen->back to mm is fail else to field
	public static byte battleState = 0;
	public static byte buttonShift = 0;
	public static Enemy[] enemies= new Enemy[3];
	public static void render(Graphics g){
		if((battleState&1)>0){								//Menu
			TypeWriter.drawString("attack", 32, 448, g);
			TypeWriter.drawString("technique", 32, 496, g);
			TypeWriter.drawString("item", 32, 542, g);
			TypeWriter.drawString("run", 176, 542, g);
		}else if((battleState&8)>0){						//Technique List
			TypeWriter.drawMoveName(Game.player.techniques, 0, 32, 448, g);
			TypeWriter.drawMoveName(Game.player.techniques, 1, 362, 448, g);
			TypeWriter.drawMoveName(Game.player.techniques, 2, 692, 448, g);
			              
			TypeWriter.drawMoveName(Game.player.techniques, 3, 32, 498, g);
			TypeWriter.drawMoveName(Game.player.techniques, 4, 362, 498, g);
			TypeWriter.drawMoveName(Game.player.techniques, 5, 692, 498, g);
			            				//TODO disable the buttons where techniques/items are missing
			TypeWriter.drawMoveName(Game.player.techniques, 6, 32, 548, g);
			TypeWriter.drawMoveName(Game.player.techniques, 7, 362, 548, g);
			TypeWriter.drawMoveName(Game.player.techniques, 8, 692, 548, g);
		}else if((battleState&16)>0){						//Item List
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
			
		}else if((battleState&4)>0){						//Target Selection
			
			for(int i=0;i<enemies.length;i++){
				
			}
		}
	}

}
