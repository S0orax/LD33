package net.sorax.game.entities.particles;

import net.sorax.game.entities.Entity;
import net.sorax.game.world.Level;

public abstract class EntityParticle extends Entity {
	
	protected int time;
	protected int deadTime;
	
	public EntityParticle(Level level, float x, float y) {
		super(level);
		this.time = 0;
		this.deadTime = 60;
		this.setPosition(x, y);
	}
	
	public EntityParticle(Level level, Entity entity) {
		this(level, entity.getPos().x, entity.getPos().y);
	}

	@Override
	public void update() {
		time++;
		if(time > deadTime) {
			time = deadTime;
		}
	}

	@Override
	public void render() {
		if(time < deadTime) {
			renderParticle();
		}
	}
	
	protected abstract void renderParticle();

}
