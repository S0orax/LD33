package net.sorax.game.entities.living.monster;

import net.sorax.engine.graphics.Animation;
import net.sorax.engine.maths.Vector2f;
import net.sorax.game.entities.living.EntityLiving;
import net.sorax.game.utils.ResourceManager;
import net.sorax.game.world.Level;

public class EntityGiantEgg extends EntityLiving {
	
	private Animation anim;
	
	public EntityGiantEgg(Level level) {
		super(level);
		this.health = 500;
		this.maxHealth = health;
		this.sprite = ResourceManager.egg;
		anim = new Animation(0, 4, 15, true);
		this.sprite.playAnimation(anim);
		this.setSize(64, 55);
	}
	
	public void setSpawn(Vector2f spawnPos) {
		this.setPosition(spawnPos);
		System.out.println(this.pos.x + ", " + this.pos.y);
	}
	
	public void render() {
		super.render();
	}
	
	public void update() {
		super.update();
		this.anim.update();
	}
}
