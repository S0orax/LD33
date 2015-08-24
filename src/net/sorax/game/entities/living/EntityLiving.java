package net.sorax.game.entities.living;

import net.sorax.engine.graphics.Sprite;
import net.sorax.engine.maths.Vector2f;
import net.sorax.engine.sound.Sound;
import net.sorax.game.entities.Entity;
import net.sorax.game.gui.LifeBar;
import net.sorax.game.utils.Input;
import net.sorax.game.utils.ResourceManager;
import net.sorax.game.world.Level;

public class EntityLiving extends Entity {
	
	protected int health;
	protected int maxHealth;
	protected Sprite sprite;
	
	protected int damage;
	protected float attackRange;
	protected int couldownAttack;
	protected int maxCouldownAttack;
	
	public EntityLiving(Level level) {
		super(level);
		this.health = 20;
		this.maxHealth = health;
		this.sprite = null;
		this.damage = 1;
		this.attackRange = 16;
		this.couldownAttack = 60;
		this.maxCouldownAttack = couldownAttack;
	}
	
	public void render() {
		if(this.sprite != null) {
			this.sprite.setPosition(this.pos.x, this.pos.y);
			this.sprite.render();
		}
		
		float x = Math.abs(level.getCamera().getPosition().x - this.pos.x);
		float y = Math.abs(level.getCamera().getPosition().y - this.pos.y);
		float xx = Math.abs(level.getCamera().getPosition().x - (this.pos.x + this.size.x));
		float yy = Math.abs(level.getCamera().getPosition().y - (this.pos.y + this.size.y));
		
		if(Input.getMouseX() > x && Input.getMouseX() < xx && Input.getMouseY() > y && Input.getMouseY() < yy) {
			LifeBar.render(this.pos.x + this.size.x / 16 , this.pos.y - 6, this.health, this.maxHealth, this.size.x);
		}
	}
	
	public void hit(int damage) {
		this.health -= damage;
		this.getSoundHit().playAt(0.25f, 1.0f, this.pos.x, this.pos.y, 0);
	}
	
	public void attack(EntityLiving entity) {
		Vector2f center = new Vector2f((this.pos.x + this.size.x) / 2, (this.pos.y + this.size.y) / 2);
		Vector2f centerEntity = new Vector2f((entity.pos.x + entity.size.x) / 2, (entity.pos.y + entity.size.y) / 2);
		if(center.distance(centerEntity) <= (float)this.attackRange && couldownAttack == maxCouldownAttack) {
			this.getSoundAttack().playAt(0.25f, 1.0f, this.pos.x, this.pos.y, 0);
			entity.hit(damage);
		}
	}
	
	@Override
	public void update() {
		this.couldownAttack--;
		if(couldownAttack <= 0) couldownAttack = maxCouldownAttack;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public int getMaxHealth() {
		return this.maxHealth;
	}
	
	public Sprite getSprite() {
		return this.sprite;
	}
	
	public String getName() {
		return "living";
	}
	
	public boolean isDead() {
		if(health <= 0) {
			this.level.getPlayer().addSouls(this.getSouls());
			return true;
		}
		return false;
	}
	
	protected Sound getSoundHit() {
		return ResourceManager.hurt;
	}
	
	protected Sound getSoundAttack() {
		return ResourceManager.attack;
	}
	
	public boolean canSpawnHere(float x, float y) {
		return true;
	}
	
	public int getSouls() {
		return 0;
	}
}
