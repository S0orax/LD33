package net.sorax.game.entities.living.monster;

import net.sorax.game.entities.Entity;
import net.sorax.game.entities.living.EntityLiving;
import net.sorax.game.world.Level;

public class EntityMonster extends EntityMob {

	public EntityMonster(Level level) {
		super(level);
	}
	
	public void update() {
		super.update();
	}
	
	protected boolean getEntityCanBeAttacked(Entity entity) {
		return entity instanceof EntityLiving && !(entity instanceof EntityMonster) && !(entity instanceof EntityGiantEgg);
	}
}
