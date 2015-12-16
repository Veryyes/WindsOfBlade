package blade;

import java.awt.Graphics;
import java.util.Arrays;

public class Field extends Entity {
	Enemy[] enemies;
	float[] frequency;
	float encounterRate;
	/**
	 * Creates a field as a rectangular area
	 * @param x the x coordinate of the upper left corner
	 * @param y the y coordinate of the upper left corner
	 * @param w the width of the field
	 * @param h the height of the field
	 * @param enemies an array of enemy references from the map template
	 * @param frequency the frequency these enemies appear in relation to each other
	 * @throws FieldException 
	 */
	public Field(int x, int y, int w, int h, float encounterRate, Enemy[] enemies, float[] frequency) throws FieldException {
		super(x, y, w, h);
		this.encounterRate=encounterRate;
		if(enemies.length!=frequency.length)
			throw new FieldException(this.toString()+" Enemies("+enemies.length+") and frequency("+frequency.length+") lengths must be equal");
		float sum=0;
		for(float f:frequency)
			sum+=f;
		if(sum!=1)
			throw new FieldException(this.toString()+" frequency of enemies must equal 1");
		this.enemies = enemies;
		this.frequency = frequency;
	}
	private class FieldException extends Exception{
		private static final long serialVersionUID = -1736674635288134563L;
		private FieldException(String s){
			super(s);
		}
	}
	public void update(Graphics g){
		super.update(g);
		if(Camera.isMoving()&&Game.player.box.intersects(box)&&Math.random()<encounterRate){
			Game.gameState = Game.BATTLE;
			Battle.reset();
			//Generates Enemies
			byte numEnemies = (byte)(Math.random()*3+1);
			Battle.enemies = new Enemy[numEnemies];
			for(int i=0;i<numEnemies;i++)
				Battle.enemies[i]=spawnEnemy();
			//Populates Queue
			Battle.queue=new Fighter[Battle.enemies.length+Game.player.party.length];
			for(int i=0;i<Game.player.party.length;i++)
				Battle.queue[i]=Game.player.party[i];
			for(int i=Game.player.party.length;i<Battle.queue.length;i++)
				Battle.queue[i]=Battle.enemies[i-Game.player.party.length];
			Arrays.sort(Battle.queue);
			//Populates friendly
			Battle.friendly=new Fighter[Game.player.party.length];
			for(int i=0;i<Battle.friendly.length;i++)
				Battle.friendly[i]=Game.player.party[i];
			Arrays.sort(Battle.friendly);

		}
	}
	private Enemy spawnEnemy(){
		float rate = 0f;
		float rnd = (float) Math.random();
		for(int i=0;i<enemies.length;i++){
			if(rnd>=rate&&rnd<rate+frequency[i])
				return enemies[i].clone(1.05f);
			rate+=frequency[i];
		}
		System.out.println("[WARNING] Could not spawn enemy in Field.spawnEnemy()");
		return null;
	}
}
