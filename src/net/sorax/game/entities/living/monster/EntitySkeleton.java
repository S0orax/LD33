package net.sorax.game.entities.living.monster;

import net.sorax.game.utils.ResourceManager;
import net.sorax.game.world.Level;

public class EntitySkeleton extends EntityMonster {
	
	public EntitySkeleton(Level level) {
		super(level);
		this.health = 50;
		this.maxHealth = health;
		this.sprite = ResourceManager.skeleton;
		
		this.speed = 1.0f;
		this.couldownAttack = 40;
		this.maxCouldownAttack = couldownAttack;
		this.damage = 13;
		this.setSize(32, 32);
	}
	
	public String getName() {
		return "Skeleton";
	}
}
