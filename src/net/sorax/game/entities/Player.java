package net.sorax.game.entities;

import java.util.ArrayList;
import java.util.List;

import net.sorax.engine.maths.Vector2f;
import net.sorax.game.entities.living.monster.EntityMonster;
import net.sorax.game.scene.GameScene;
import net.sorax.game.utils.Input;

import org.lwjgl.input.Mouse;


public class Player {
	
	private List<EntityMonster> entities;
	private int souls;
	
	private GameScene scene;
	
	private boolean win;
	private boolean gameover;
	
	public Player(GameScene scene) {
		this.scene = scene;
		this.souls = 100;
		this.entities = new ArrayList<EntityMonster>();
		this.win = false;
		this.gameover = false;
	}
	
	public void update() {
		if(!this.entities.isEmpty()) {
			if(Mouse.isButtonDown(1)) {				
				float worldMouseX = scene.getLevel().getCamera().getPosition().x + Input.getMouseX();
				float worldMouseY = scene.getLevel().getCamera().getPosition().y + Input.getMouseY();
				Vector2f target = new Vector2f(worldMouseX, worldMouseY);
				
				for(EntityMonster e : entities) {
					e.setTarget(target);
				}
			}
		}
	}
	
	public void addEntity(EntityMonster e) {
		this.entities.add(e);
	}
	
	public void removeEntity(EntityMonster e) {
		if(entities.contains(e)) this.entities.remove(e);
	}
	
	public void removeAllEntities() {
		for(EntityMonster e : entities) {
			e.setSearchTarget(true);
		}
		this.entities.removeAll(entities);
	}
	
	public void addSouls(int value) {
		this.souls += value;
	}
	
	public boolean subSouls(int value) {
		if(this.souls - value >= 0) {
			this.souls -= value;
			return true;
		}
		return false;
	}
	
	public void setSouls(int value) {
		this.souls = value;
	}
	
	public int getSouls() {
		return this.souls;
	}
	
	public boolean win() {
		this.win = true;
		return win;
	}
	
	public boolean gameover() {
		this.gameover = true;
		return gameover;
	}
}
