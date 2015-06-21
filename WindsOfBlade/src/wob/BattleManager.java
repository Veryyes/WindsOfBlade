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
	public static LinkedList<Fighter> targets = new LinkedList<Fighter>();
	public static Move selectedTechnique;
	public static Item selectedItem;
	public static Fighter selectedTarget;
	public static int money;
	public static int exp;
	public static boolean attack = false;
	private static int damageDelt=0;
	private static int endTimer = 0;
	private static int messageTimer = 0;
	@SuppressWarnings("unused")
	private static int animationTimer = 0;
	private static Animation backArrow = new Animation(ImageManager.getImage("res/ui/backArrow.png"));
	public static void render(Graphics g){
		for(int i=1+Game.player.party.size();i<targets.size();i++){
				g.drawImage(ImageManager.getImage("res/enemy/"+targets.get(i).name+".png"),256*(i-Game.player.party.size())-128,128,null);
		}
		if((battleState&1)>0){								//Menu
			TypeWriter.drawString("attack", 32, 448, g);
			TypeWriter.drawString("Skills", 32, 496, g);
			TypeWriter.drawString("item", 32, 542, g);
			TypeWriter.drawString("run", 176, 542, g);
			TypeWriter.setSize(.7f);
			TypeWriter.drawString(Game.player.name+" HP"+Game.player.hp+"/"+Game.player.maxHp+" MP"+Game.player.mp+"/"+Game.player.maxMp+" SP"+Game.player.sp+"/"+Game.player.maxSp,
					298,448,g);
			//y+30
			for(int i=0;i<Game.player.party.size();i++){
				TypeWriter.drawString(Game.player.party.get(i).name+" HP"+Game.player.party.get(i).hp+"/"+Game.player.party.get(i).maxHp+" MP"+Game.player.party.get(i).mp+"/"+Game.player.party.get(i).maxMp+" SP"+Game.player.party.get(i).sp+"/"+Game.player.party.get(i).maxSp,
						298,448+(30*i+30),g);
			}
			TypeWriter.setSize(TypeWriter.MEDIUM);
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
			/*
			 * TODO make this queue up for party member attacks
			 */
			if(attack==true){
				attack=false;
				if(Move.attackHit((Fighter)Game.player, selectedTarget)){
					damageDelt=(Move.attackDamageDelt((Fighter)Game.player, selectedTarget)-selectedTarget.getDefence());
					selectedTarget.hp-=damageDelt;
				}
			}else if(selectedTechnique!=null){
				if(selectedTechnique.hit((Fighter)Game.player, selectedTarget)){
					if(selectedTechnique.physicalDamage((Fighter)Game.player,selectedTarget)-selectedTarget.getDefence()>=0)
						damageDelt=(selectedTechnique.physicalDamage((Fighter)Game.player,selectedTarget)-selectedTarget.getDefence());
					if(selectedTechnique.magicDamage((Fighter)Game.player,selectedTarget)-selectedTarget.getMagicDefence()>=0)
						damageDelt+=(selectedTechnique.magicDamage((Fighter)Game.player,selectedTarget)-selectedTarget.getMagicDefence());
					selectedTarget.hp-=damageDelt;
				}
				selectedTechnique=null;
			}else if(selectedItem!=null){
				selectedItem=null;
			}
			if(messageTimer==100){
				for(int i=1+Game.player.party.size();i<targets.size();i++){	//Enemy death check
					if(targets.get(i).hp<=0){
						money+=((Enemy)targets.get(i)).money;
						exp+=targets.get(i).experience;
						targets.remove(i);
					}
				}
				if(targets.size()-Game.player.party.size()-1<=0){	//Battle End Cond check
					UI.disableSelectionBtns();
					UI.disableBattleBtns();
					//TODO add exp to party members too
					TypeWriter.drawString("Experience Gained "+exp,32, 448, g);
					TypeWriter.drawString("Money Gained "+money,32, 488, g);
					if(endTimer>80){
						endTimer=0;
						Game.gameStates|=16;
						Game.gameStates&=~32;
						BattleManager.battleState&=~1;
						Game.player.money+=money;
						Game.player.experience+=exp;
					}else 
						endTimer++;
				}else{
					BattleManager.battleState&=~64;
					BattleManager.battleState|=1;
					UI.enableSelectionBtns();
					UI.backBtn.enabled=true;
					messageTimer=0;
				}
				attack=false;
				BattleManager.selectedItem=null;
				BattleManager.selectedTechnique=null;
			}else{
				messageTimer++;
				if(damageDelt>0){
					TypeWriter.drawMessage(selectedTarget.name+" took "+damageDelt+" Damage!", g);
				}else{
					TypeWriter.drawMessage("The attack had no effect on "+selectedTarget.name+"!", g);
				}
			}
		}
	}
	public static boolean isAttackPhase(){
		return(selectedTechnique==null&&selectedItem==null&&(battleState&4)>0);
	}
}
