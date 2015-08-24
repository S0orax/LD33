package net.sorax.game.entities.living.monster;

import net.sorax.game.utils.ResourceManager;
import net.sorax.game.world.Level;

public class EntityZombie extends EntityMonster {

	public EntityZombie(Level level) {
		super(level);
		this.health = 20;
		this.maxHealth = health;
		this.sprite = ResourceManager.zombie;
		
		this.speed = 0.5f;
		this.couldownAttack = 80;
		this.maxCouldownAttack = couldownAttack;
		this.damage = 7;
		this.setSize(32, 32);
	}
	
	public String getName() {
		return "Zombie";
	}

}
