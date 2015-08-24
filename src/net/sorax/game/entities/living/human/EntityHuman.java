package net.sorax.game.entities.living.human;

import net.sorax.engine.maths.Vector2f;
import net.sorax.game.entities.Entity;
import net.sorax.game.entities.living.EntityFireCamp;
import net.sorax.game.entities.living.EntityLiving;
import net.sorax.game.entities.living.monster.EntityGiantEgg;
import net.sorax.game.entities.living.monster.EntityMob;
import net.sorax.game.entities.living.monster.EntityMonster;
import net.sorax.game.utils.ResourceManager;
import net.sorax.game.world.Level;
import net.sorax.game.world.tiles.Tile;

public class EntityHuman extends EntityMob {
	
	public EntityHuman(Level level) {
		super(level);
		this.sprite = ResourceManager.human;
		this.setSize(32, 32);
//		this.health = 20;
//		this.maxHealth = health;
	}
	
	public void render() {
		super.render();
	}
	
	public void update() {
		super.update();
		this.searchMonster();
	}
	
	private void searchMonster() {
		if(level.isDay()) {
			target = level.getEgg().getPos();
		} else {
			for(Entity e : level.getEntities()) {
				if(e instanceof EntityMonster || e instanceof EntityGiantEgg) {
					if(this.pos.distance(e.getPos()) <= 4 * Tile.SIZE) {
						this.setTarget(e.getPos());
					}
				}
			}
		}
	}
	
	public boolean canSpawnHere(float x, float y) {
		float distance = this.level.getEgg().getPos().distance(new Vector2f(x, y));
		if(distance > 8 * Tile.SIZE && this.level.isDay()) return true;
		
		return false;
	}

	@Override
	public int getSouls() {
		return 3 + this.rand.nextInt(3);
	}
	
	protected boolean getEntityCanBeAttacked(Entity entity) {
		return entity instanceof EntityLiving && !(entity instanceof EntityFireCamp) && !(entity instanceof EntityHuman);
	}
}
