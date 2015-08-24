package net.sorax.game.entities.living;

import net.sorax.engine.maths.Vector2f;
import net.sorax.game.entities.Entity;
import net.sorax.game.utils.ResourceManager;
import net.sorax.game.world.Level;
import net.sorax.game.world.tiles.Tile;

public class EntityFireCamp extends EntityLiving {

	public EntityFireCamp(Level level) {
		super(level);
		this.health = 500;
		this.maxHealth = health;
	
		this.sprite = ResourceManager.camp;
		this.setSize(64, 64);
	}
	
	public boolean canSpawnHere(float x, float y) {
		float distance = this.level.getEgg().getPos().distance(new Vector2f(x, y));
		Entity entity = level.getEntity(x, y, this.size.x, this.size.y, this);
		if(!level.isDay() && distance > 8 * Tile.SIZE && entity == null 
				&& this.pos.x + this.size.x < level.getWidth() * Tile.SIZE 
				&& this.pos.y + this.size.y < level.getHeight() * Tile.SIZE) return true;
		return false;
	}
	
	public boolean isDead() {
		if(health <= 0) {
			int nbMob = 1 + this.rand.nextInt(5);
			for(int i = 0; i < nbMob; i++) {
				int sign = rand.nextBoolean() ? 1 : -1;
				float posX = this.pos.x + sign * rand.nextInt(32);
				float posY = this.pos.y + sign * rand.nextInt(32);
				Vector2f humanPos = new Vector2f(posX, posY);
				level.addHuman(humanPos);
			}
			
			level.subNbCamp();
			
			if(level.getCurrentCampFire() == 0) level.getPlayer().win();
		}
		return super.isDead();
	}
	
	@Override
	public int getSouls() {
		return 20;
	}

}
