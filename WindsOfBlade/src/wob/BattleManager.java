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
	public static void render(Graphics g){
		if((battleState&1)>0){								//Menu
			TypeWriter.drawString("attack", 32, 448, g);
			TypeWriter.drawString("technique", 32, 496, g);
			TypeWriter.drawString("item", 32, 542, g);
			TypeWriter.drawString("run", 176, 542, g);
		}else if((battleState&8)>0){						//Technique List
			
		}else if((battleState&16)>0){						//Item List
			
		}else if((battleState&32)>0){						//Run
			
		}else if((battleState&4)>0){						//Target Selection
			
		}
	}

}
