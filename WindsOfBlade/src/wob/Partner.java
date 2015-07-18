package wob;

import java.util.LinkedList;

public class Partner extends Fighter{
	Equipment helmate, chest, pants, shoes, gloves, rightHand, leftHand, pendant;
	public static Partner mage;
	public static Partner apollo;
	public static Partner artemis;
	public static Partner lancer;
	public static Partner berzerker;
	public static Partner dual;
	public static Partner warrior;
	public static void loadPartners(){
		mage = new Partner(0,0);
		mage.name="Mage";
		mage.animation = new Animation(ImageManager.getImage("res/sprites/mcircle.png"));
		mage.level=6;
		mage.experience = 0;
		mage.str=10;
		mage.intel=15;
		mage.dex=10;
		mage.will=10;
		mage.agil=10;
		mage.hp=20;
		mage.maxHp=20;	
		mage.mp=10;
		mage.maxMp=10;
		mage.sp=10;
		mage.maxSp=10;
		mage.type=Type.NORMAL;
		for(int i=Move.database.length/2;i<Move.database.length;i++){
			mage.techniques.add(Move.database[i]);
		}

		apollo = new Partner(0,0);
		apollo.name="Bow";
		apollo.level=6;
		apollo.experience = 0;
		apollo.str=10;
		apollo.intel=10;
		apollo.dex=10;
		apollo.will=10;
		apollo.agil=10;
		apollo.hp=20;
		apollo.maxHp=20;
		apollo.mp=10;
		apollo.maxMp=10;
		apollo.sp=10;
		apollo.maxSp=10;
		apollo.type=Type.NORMAL;
		
		artemis = new Partner(0,0);
		artemis.name="Crossbow";
		artemis.level=6;
		artemis.experience = 0;
		artemis.str=10;
		artemis.intel=10;
		artemis.dex=10;
		artemis.will=10;
		artemis.agil=10;
		artemis.hp=20;
		artemis.maxHp=20;
		artemis.mp=10;
		artemis.maxMp=10;
		artemis.sp=10;
		artemis.maxSp=10;
		artemis.type=Type.NORMAL;
		
		lancer = new Partner(0,0);
		lancer.name="Lancer";
		lancer.level=6;
		lancer.experience = 0;
		lancer.str=10;
		lancer.intel=10;
		lancer.dex=10;
		lancer.will=10;
		lancer.agil=10;
		lancer.hp=20;
		lancer.maxHp=20;
		lancer.mp=10;
		lancer.maxMp=10;
		lancer.sp=10;
		lancer.maxSp=10;
		lancer.type=Type.NORMAL;
		
		berzerker = new Partner(0,0);
		berzerker.name="berzerker";
		berzerker.level=6;
		berzerker.experience = 0;
		berzerker.str=10;
		berzerker.intel=10;
		berzerker.dex=10;
		berzerker.will=10;
		berzerker.agil=10;
		berzerker.hp=20;
		berzerker.maxHp=20;
		berzerker.mp=10;
		berzerker.maxMp=10;
		berzerker.sp=10;
		berzerker.maxSp=10;
		berzerker.type=Type.NORMAL;
		
		dual = new Partner(0,0);
		dual.name="Dual Blade";
		dual.level=6;
		dual.experience = 0;
		dual.str=10;
		dual.intel=10;
		dual.dex=10;
		dual.will=10;
		dual.agil=10;
		dual.hp=20;
		dual.maxHp=20;
		dual.mp=10;
		dual.maxMp=10;
		dual.sp=10;
		dual.maxSp=10;
		dual.type=Type.NORMAL;
		
		warrior = new Partner(0,0);
		warrior.name="Warrior";
		warrior.level=6;
		warrior.experience = 0;
		warrior.str=10;
		warrior.intel=10;
		warrior.dex=10;
		warrior.will=10;
		warrior.agil=10;
		warrior.hp=20;
		warrior.maxHp=20;
		warrior.mp=10;
		warrior.maxMp=10;
		warrior.sp=10;
		warrior.maxSp=10;
		warrior.type=Type.NORMAL;
		
	}
	public Partner(int x, int y) {
		super(x,y);
		techniques = new LinkedList<Move>();
	}
	public void update() {
	
		
	}
}
