package net.sorax.game.entities.living.monster;

import java.util.Random;

import net.sorax.engine.maths.Vector2f;
import net.sorax.game.entities.Entity;
import net.sorax.game.entities.living.EntityLiving;
import net.sorax.game.world.Level;
import net.sorax.game.world.tiles.Tile;

public class EntityMob extends EntityLiving {
	
	protected float speed;
	protected Vector2f target;
	protected int idleTime;
	protected boolean searchTarget = true;
	
	public EntityMob(Level level) {
		super(level);
		this.speed = 1.0f;
		this.target = new Vector2f(0, 0);
		this.rand = new Random();
		this.idleTime = 120 + rand.nextInt(500);
	}
	
	public void searchTarget() {
		if(((int)this.target.x == (int)this.pos.x && (int)this.target.y == (int)this.pos.y) || (target.x == 0 && target.y == 0)) {
			int sign = rand.nextBoolean() ? -1 : 1;
			this.target = new Vector2f(this.pos.x + this.rand.nextInt(8) * Tile.SIZE * sign, this.pos.y + this.rand.nextInt(8) * Tile.SIZE * sign);
			this.idleTime = 120 + rand.nextInt(500);
		}
	}
	
	public void setTarget(Vector2f target) {
		int sign = rand.nextBoolean() ? 1 : -1;
		this.target = new Vector2f(target.x + sign * rand.nextInt(Tile.SIZE) - this.size.x / 2, target.y + sign * rand.nextInt(Tile.SIZE) - this.size.y / 2);
		this.idleTime = 0;
		this.searchTarget = false;
	}
	
	public void setSearchTarget(boolean searchTarget) {
		this.searchTarget = searchTarget;
	}
	
	public void move() {
		if(idleTime > 0) {
			idleTime--;
			return;
		}
		
		if(target.x < 0) target.x = 0;
		if(target.y < 0) target.y = 0;
		if(target.x > level.getWidth() * Tile.SIZE - size.x) target.x = level.getWidth() * Tile.SIZE - size.x;
		if(target.y > level.getHeight() * Tile.SIZE - size.y) target.y = level.getHeight() * Tile.SIZE - size.y;
		
		if(searchTarget) this.searchTarget();
		
		if(((int)this.target.x == (int)this.pos.x && (int)this.target.y == (int)this.pos.y) || (target.x == 0 && target.y == 0)) {
			return;
		}
		
		Vector2f direction = this.target.add(new Vector2f(-pos.x, -pos.y)).normalize().mul(speed);
		this.pos.addSelf(direction);
	}
	
	public void update() {
		super.update();
		this.move();
		
		Entity entity = level.getEntity(this.pos.x, this.pos.y, this.size.x, this.size.y, this);
		
		if(entity != null  && this.getEntityCanBeAttacked(entity)) this.attack((EntityLiving)entity);
	}
	
	protected boolean getEntityCanBeAttacked(Entity entity) {
		return entity instanceof EntityLiving;
	}

}
