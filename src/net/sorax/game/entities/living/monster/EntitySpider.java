package net.sorax.game.entities.living.monster;

import net.sorax.game.entities.Entity;
import net.sorax.game.utils.ResourceManager;
import net.sorax.game.world.Level;

public class EntitySpider extends EntityMonster {
	
	public EntitySpider(Level level) {
		super(level);
		this.health = 10;
		this.maxHealth = health;
		this.sprite = ResourceManager.spider;
		
		this.speed = 2.5f;
		this.couldownAttack = 30;
		this.maxCouldownAttack = couldownAttack;
		this.damage = 2;
		this.setSize(32, 32);
	}
	
	public void update() {
		super.update();
	}
	
	public String getName() {
		return "Spider";
	}
	
	protected boolean getEntityCanBeAttacked(Entity entity) {
		return super.getEntityCanBeAttacked(entity);
	}
}
