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
	public static byte battleState;
	public static LinkedList<Fighter> targets = new LinkedList<Fighter>();
	public static byte currentTurn;
	public static int money;
	public static int exp;
	public static Fighter[] queue;
	private static int textCounter=0;
	private static int textTimer= 1;
	private static int endTimer = 0;
	private static int messageTimer = 0;
	public static Animation actionAnimation;
	@SuppressWarnings("unused")
	private static int animationTimer = 0;
	private static Animation backArrow = new Animation(ImageManager.getImage("res/ui/backArrow.png"));
	public static void render(Graphics g) throws InterruptedException{
		for(int i=1+Game.player.party.size();i<targets.size();i++)
				g.drawImage(ImageManager.getImage("res/enemy/"+targets.get(i).name+".png"),256*(i-Game.player.party.size())-128,128,null);
		if((battleState&1)>0){
			TypeWriter.setSize(.7f);//TODO add padding between name & stats so that they are all flush with each other
			TypeWriter.drawString(Game.player.name+" HP"+Game.player.hp+"/"+Game.player.maxHp+" MP"+Game.player.mp+"/"+Game.player.maxMp+" SP"+Game.player.sp+"/"+Game.player.maxSp,
					298,448,g);
			for(int i=0;i<Game.player.party.size();i++){
				TypeWriter.drawString(Game.player.party.get(i).name+" HP"+Game.player.party.get(i).hp+"/"+Game.player.party.get(i).maxHp+" MP"+Game.player.party.get(i).mp+"/"+Game.player.party.get(i).maxMp+" SP"+Game.player.party.get(i).sp+"/"+Game.player.party.get(i).maxSp,
						298,448+(30*i+30),g);
			}
			TypeWriter.setSize(TypeWriter.MEDIUM);
		}else if((battleState&8)>0){						//Technique List
			UI.drawRectUI(15,372,64,64,true,g);
			g.drawImage(backArrow.getFrame(0),33,384,null);
			TypeWriter.drawMoveName(targets.get(currentTurn).techniques, 0, 32, 448, g);
			TypeWriter.drawMoveName(targets.get(currentTurn).techniques, 1, 362, 448, g);
			TypeWriter.drawMoveName(targets.get(currentTurn).techniques, 2, 692, 448, g);
			                        
			TypeWriter.drawMoveName(targets.get(currentTurn).techniques, 3, 32, 498, g);
			TypeWriter.drawMoveName(targets.get(currentTurn).techniques, 4, 362, 498, g);
			TypeWriter.drawMoveName(targets.get(currentTurn).techniques, 5, 692, 498, g);
			                        
			TypeWriter.drawMoveName(targets.get(currentTurn).techniques, 6, 32, 548, g);
			TypeWriter.drawMoveName(targets.get(currentTurn).techniques, 7, 362, 548, g);
			TypeWriter.drawMoveName(targets.get(currentTurn).techniques, 8, 692, 548, g);
		}else if((battleState&16)>0){						//Item List
			UI.drawRectUI(15,372,64,64,true,g);
			g.drawImage(backArrow.getFrame(0),33,384,null);
			TypeWriter.drawItemName(targets.get(currentTurn).inventory, 0, 32, 448, g);
			TypeWriter.drawItemName(targets.get(currentTurn).inventory, 1, 362, 448, g);
			TypeWriter.drawItemName(targets.get(currentTurn).inventory, 2, 692, 448, g);
			                       
			TypeWriter.drawItemName(targets.get(currentTurn).inventory, 3, 32, 498, g);
			TypeWriter.drawItemName(targets.get(currentTurn).inventory, 4, 362, 498, g);
			TypeWriter.drawItemName(targets.get(currentTurn).inventory, 5, 692, 498, g);
			                        
			TypeWriter.drawItemName(targets.get(currentTurn).inventory, 6, 32, 548, g);
			TypeWriter.drawItemName(targets.get(currentTurn).inventory, 7, 362, 548, g);
			TypeWriter.drawItemName(targets.get(currentTurn).inventory, 8, 692, 548, g);
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
		}else if((battleState&64)>0){	
			if(messageTimer>=150*targets.size()+20){//Post Turn Checks
				//Player||Partner Death Check
				//Game Over Check;
				byte gameover = 0;
				for(int i=0;i<Game.player.party.size()+1;i++){
					if(targets.get(i).hp<=0){
						gameover++;
						targets.get(i).hp=0;
						targets.get(i).isDead=true;
					}
				}
				if(gameover==Game.player.party.size()+1){
					Game.gameStates|=128;
					Game.gameStates&=~32;
				}else{
					//Enemy Death Check
					for(int i=1+Game.player.party.size();i<targets.size();i++){	
						if(targets.get(i).hp<=0){
							money+=((Enemy)targets.get(i)).money;
							exp+=targets.get(i).experience;
							targets.remove(i);
							queue=new Fighter[queue.length-1];
							for(int j=0;j<queue.length;j++)
								queue[j]=targets.get(j);
							for(int k=0;k<queue.length;k++){
								Fighter max = queue[k];
								int maxIndex=0;
								for(int j=k;j<queue.length;j++)//Selection Sort by Agility Stat
									if(max.agil<queue[j].agil){
										max=queue[j];
										maxIndex=j;
									}
								if(max!=BattleManager.queue[k]){
									Fighter temp = BattleManager.queue[k];
									BattleManager.queue[k]=max;
									BattleManager.queue[maxIndex]=temp;
								}
							}
						}
					}
					//Battle End Cond check
					if(targets.size()-Game.player.party.size()-1<=0){
						UI.disableSelectionBtns();
						UI.disableBattleBtns();
						TypeWriter.drawString("Experience Gained "+exp,32, 448, g);
						TypeWriter.drawString("Money Gained "+money,32, 488, g);
						if(endTimer>80){
							currentTurn=0;
							endTimer=0;
							Game.gameStates|=16;
							Game.gameStates&=~32;
							BattleManager.battleState&=~1;
							Game.player.money+=money;
							Game.player.experience+=exp;
							for(Partner p:Game.player.party)
								p.experience+=exp;
							messageTimer=0;
							textTimer=1;
						}else 
							endTimer++;
					}else{
						BattleManager.battleState&=~64;
						BattleManager.battleState|=1;
						UI.enableSelectionBtns();
						UI.backBtn.enabled=true;
						messageTimer=0;
						textTimer=1;
						currentTurn=0;
						for(Fighter f:targets){
							f.resetTurn();
						}
					}
				}
			}else{
				messageTimer++;
				if(textTimer<150*targets.size()){//TODO i think textTimer is redundant.... //Can just use messageTimer for same functionality?
					textTimer++;
					if(textTimer%150==0)
						textCounter++;
					if(textCounter>=queue.length){
						textCounter=queue.length-1;
					}
					Fighter f = queue[textCounter];//TODO Producing java.lang.ArrayIndexOutOfBoundsException error of queue.size()+1
					if(!f.dmgCalc&&f.hp>0){
						f.dmgCalc=true;
						if(f instanceof Enemy){
							Enemy e = (Enemy)f;
							LinkedList<Fighter> targets = new LinkedList<Fighter>(Game.player.party);
							targets.add(Game.player);
							e.pickTarget(targets);
							if(e.hit(e.target)){//Accuracy Check
								e.target.lastDamageTaken = e.getDamageDone(e.target);
								e.target.hp -= e.getDamageDone(e.target);
							}else{
								e.missed=true;
								e.target.lastDamageTaken = 0;
							}
						}else{//Player||Partner
							if(f.selectedTechnique!=null){//Move is used
								f.selectedTechnique.consume(f);
								if(f.selectedTechnique.hit(f, f.target)){//Accuracy Check
									f.target.lastDamageTaken = f.selectedTechnique.getDamageDone(f, f.target);
									f.target.hp -= f.selectedTechnique.getDamageDone(f, f.target);
								}else{
									f.missed=true;
									f.target.lastDamageTaken = 0;
								}
							}else if(f.selectedItem!=null){//Item is used
								//TODO Items lol
							}else{//f is using a regular attack
								if(Move.attackHit(f, f.target)){
									f.target.lastDamageTaken = Move.attackDamageDelt(f, f.target);
									f.target.hp -= Move.attackDamageDelt(f, f.target);
								}else{
									f.missed=true;
									f.target.lastDamageTaken = 0;
								}
							}
						}
					}
					if(f.isDead){//If it's dead...
						TypeWriter.drawMessage(f.name+" no longer has the will to fight!",g);
						f.hp=0;
					}else{
						if(f.missed){
							TypeWriter.drawMessage(f.name+" missed!",g);
						}else if(f.target.lastDamageTaken>0){//TODO null pointer here, fix it
							TypeWriter.drawMessage(f.name+" dealt "+f.target.lastDamageTaken+" damage to "+f.target.name+"!", g);
						}else
							TypeWriter.drawMessage(f.name+"s attack had no effect on "+f.target.name+"!", g);
					}
				}else{
					textTimer=1;
					textCounter=0;
					messageTimer=150*targets.size()+20;
				}
			}			
			
		}
	}
}
