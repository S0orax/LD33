package net.sorax.game.entities;

import net.sorax.engine.maths.Vector2f;

public class Entity {
	
	protected Vector2f pos;
	protected Vector2f size;
	
	public Entity() {
		this.pos = new Vector2f(0, 0);
		this.size = new Vector2f(0, 0);
	}
	
	public void setPosition(Vector2f newPos) {
		this.setPosition(newPos.x, newPos.y);
	}
	
	public void setPosition(float x, float y) {
		this.pos.set(x, y);
	}
	
	public void setSize(Vector2f newSize) {
		this.setSize(newSize.x, newSize.y);
	}
	
	public void setSize(float width, float height) {
		this.size.set(width, height);
	}
	
	public Vector2f getPos() {
		return this.pos;
	}
	
	public Vector2f getSize() {
		return this.size;
	}
}
