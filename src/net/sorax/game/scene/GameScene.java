package net.sorax.game.scene;

import net.sorax.engine.gui.Scene;
import net.sorax.game.world.Level;

public class GameScene implements Scene {

	private Level level;
	
	@Override
	public void init() {
		this.level = new Level(10, 10);
	}

	@Override
	public void render() {
		this.level.render();
	}

	@Override
	public void update() {
		this.level.update();
	}

	@Override
	public void dispose() {
		
	}
}
