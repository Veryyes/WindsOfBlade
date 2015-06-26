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
	public static LinkedList<Fighter> targets;
	//public static LinkedList<Enemy> enemies;
	public static byte currentTurn;
	public static LinkedList<Move> selectedTechnique;
	public static LinkedList<Item> selectedItem;
	public static LinkedList<Fighter> selectedTarget;
	public static LinkedList<Fighter> attackDamageOrder;
	public static int money;
	public static int exp;
	//public static boolean attack = false;
	public static Fighter[] queue;
	public static boolean dmgCalc;
//	private static int damageDelt=0;
	private static int textCounter=0;
	private static int textTimer= 1;
	private static int endTimer = 0;
	private static int messageTimer = 0;
	@SuppressWarnings("unused")
	private static int animationTimer = 0;
	private static Animation backArrow = new Animation(ImageManager.getImage("res/ui/backArrow.png"));
	public static void initialize(){
		dmgCalc=true;
		targets = new LinkedList<Fighter>();
		//enemies = new LinkedList<Enemy>();
		attackDamageOrder = new LinkedList<Fighter>();
		selectedTechnique = new LinkedList<Move>();   
		selectedItem = new LinkedList<Item>();        
		selectedTarget = new LinkedList<Fighter>();
		selectedTechnique.add(null);
		selectedItem.add(null);
		selectedTarget.add(null);
	}
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
			
			if(dmgCalc){
				System.out.println("Doing Dmg Calc");
				dmgCalc=false;
				for(int i=0;i<queue.length;i++){
					if(queue[i] instanceof Enemy){
						System.out.println(queue[i].name);
						Fighter currentTarget = null;
						Enemy attacker = (Enemy)queue[i];
						switch (attacker.aiType){
						case 1://Random Target
							currentTarget=targets.get((int)(Math.random()*(1+Game.player.party.size())));
							break;
						case 2://Lowest HP(by value) Target
							currentTarget=targets.get(0);
							for(int j=0;j<1+Game.player.party.size();j++){
								if(currentTarget.hp>targets.get(i).hp)
									currentTarget=targets.get(i);
							}
							break;
						case 3://Lowest HP(by %) Target
							currentTarget=targets.get(0);
							for(int j=0;j<1+Game.player.party.size();j++){
								if((float)currentTarget.hp/(float)currentTarget.maxHp>(float)(targets.get(i).hp)/(float)(targets.get(i).maxHp))
									currentTarget=targets.get(i);
							}
							break;
						case 4://Highest HP(by value) Target
							currentTarget=targets.get(0);
							for(int j=0;j<1+Game.player.party.size();j++){
								if(currentTarget.hp<targets.get(i).hp)
									currentTarget=targets.get(i);
							}
							break;
						case 5://Highest HP(by %) Target
							currentTarget=targets.get(0);
							for(int j=0;j<1+Game.player.party.size();j++){
								if((float)currentTarget.hp/(float)currentTarget.maxHp<(float)(targets.get(i).hp)/(float)(targets.get(i).maxHp))
									currentTarget=targets.get(i);
							}
							break;
						}
						int damage=0;
						switch (attacker.damageType){
						case 1://Physical Attack
							damage = (attacker.physicalDamage()-currentTarget.getDefence());	
							break;
						case 2://Magic Attack
							damage = (attacker.magicDamage()-currentTarget.getMagicDefence());
							break;
						case 3://Both
							damage = (int)(.5*(attacker.physicalDamage()-currentTarget.getDefence())+(attacker.magicDamage()-currentTarget.getMagicDefence()));
							break;
						}
						currentTarget.lastDamageTaken=(int) (damage*Type.effectiveness(attacker.type, currentTarget.type));
						if(currentTarget.lastDamageTaken>0){
							currentTarget.hp-=currentTarget.lastDamageTaken;
							System.out.println(currentTarget.name+" took "+currentTarget.lastDamageTaken+" damage!");
						}else{
							System.out.println("no damage");
						}
						//attackDamageOrder.add(attacker);
						attackDamageOrder.add(currentTarget);
					}else{
						System.out.println(queue[i].name);
						int turn = getTargetIndex(queue[i]);
						System.out.println(selectedTarget.get(turn));
						if(selectedTechnique.get(turn)!=null){
							if(selectedTechnique.get(turn).hit(targets.get(turn), selectedTarget.get(turn))){
								if(selectedTechnique.get(turn).physicalDamage(targets.get(turn), selectedTarget.get(turn))-selectedTarget.get(turn).getDefence()>=0)
									selectedTarget.get(turn).lastDamageTaken=selectedTechnique.get(turn).physicalDamage(targets.get(turn), selectedTarget.get(turn))-selectedTarget.get(turn).getDefence();
								if(selectedTechnique.get(turn).magicDamage(targets.get(turn), selectedTarget.get(turn))-selectedTarget.get(turn).getMagicDefence()>=0)
									selectedTarget.get(turn).lastDamageTaken+=selectedTechnique.get(turn).magicDamage(targets.get(turn), selectedTarget.get(turn))-selectedTarget.get(turn).getMagicDefence();
								if(selectedTarget.get(turn).lastDamageTaken>0){
									selectedTarget.get(turn).hp-=selectedTarget.get(turn).lastDamageTaken;
									System.out.println(targets.get(turn).name+" did "+selectedTarget.get(turn).lastDamageTaken+" damage to "+selectedTarget.get(turn).name);
								}else
									System.out.println(targets.get(turn).name+" did no damage to "+selectedTarget.get(turn).name);
							}else{
								System.out.println(targets.get(turn).name+" missed!");
							}
							selectedTechnique.set(turn,null);
						}else if(selectedItem.get(turn)!=null){
							//TODO item stuff
							selectedItem.set(turn,null);
						}else{//Attack
							if(Move.attackHit(targets.get(turn), selectedTarget.get(turn))){
								selectedTarget.get(turn).lastDamageTaken=(Move.attackDamageDelt(targets.get(turn), selectedTarget.get(turn))-selectedTarget.get(turn).getDefence());
								if(selectedTarget.get(turn).lastDamageTaken>0){
									selectedTarget.get(turn).hp-=selectedTarget.get(turn).lastDamageTaken;
									System.out.println(targets.get(turn).name+" did "+selectedTarget.get(turn).lastDamageTaken+" damage to "+selectedTarget.get(turn).name);
								}else
									System.out.println(targets.get(turn).name+" did no damage to "+selectedTarget.get(turn).name);
							}else{
								System.out.println(targets.get(turn).name+" missed!");
							}
						}
						//attackDamageOrder.add(targets.get(turn));
						attackDamageOrder.add(selectedTarget.get(turn));
					}
				}
			}
			//TODO Display stuff
			if(messageTimer>=150*targets.size()+20){//TODO make timer based on how many things to display
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
					}else 
						endTimer++;
				}else{
					BattleManager.battleState&=~64;
					BattleManager.battleState|=1;
					UI.enableSelectionBtns();
					UI.backBtn.enabled=true;
					messageTimer=0;
					currentTurn=0;
					for(int i=0;i<selectedItem.size();i++){
						selectedItem.set(i,null);
						selectedTarget.set(i,null);
						selectedTechnique.set(i,null);
					}
					dmgCalc=true;
				}
			}else{
				messageTimer++;
				if(textTimer<150*targets.size()){
					textTimer++;
					if(textTimer%150==0)
						textCounter++;
					if(textCounter<attackDamageOrder.size()){
						if(attackDamageOrder.get(textCounter).lastDamageTaken>0)
							TypeWriter.drawMessage(queue[textCounter].name+" dealt "+attackDamageOrder.get(textCounter).lastDamageTaken+" damage to "+attackDamageOrder.get(textCounter).name+"!",g);
						else
							TypeWriter.drawMessage(queue[textCounter].name+"s attack had no effect on "+attackDamageOrder.get(textCounter).name+"!",g);
					}else{
						textCounter=0;//Break out of this
						messageTimer=150*targets.size()+20;
					}
				}else{
					textTimer=1;
					textCounter=0;
					messageTimer=150*targets.size()+20;
				}
			}			
			
		}
	}
	private static int getTargetIndex(Fighter f){
		int index=-1;
		for(int i=0;i<targets.size();i++){
			if(f==targets.get(i)){
				return(index=i);
			}
		}
		return index;
	}
	private static Fighter[] removeQueueElement(Fighter f){
		for(int i=0;i<queue.length;i++){
			if(f==queue[i]){
				Fighter[] list = new Fighter[queue.length-1];
				int j=0;
				int k=j;
				while(j<queue.length){
					if(j==i)
						k++;
					list[j]=queue[k];
					j++;
					k++;
				}
				return list;
			}
		}
		return queue;
	}
	public static boolean isAttackPhase(){
		return(selectedTechnique.get(currentTurn)==null&&selectedItem.get(currentTurn)==null&&(battleState&4)>0);
	}
}
