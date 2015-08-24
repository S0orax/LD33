package net.sorax.game.scene;

import static org.lwjgl.opengl.GL11.*;
import net.sorax.engine.graphics.Image;
import net.sorax.engine.gui.Scene;
import net.sorax.game.Game;

public class GameOverMenu implements Scene {
	
	private Image gameover;
	
	@Override
	public void init() {
		int imageWidth = 128;
		int imageHeight = 38;
		
		this.gameover = new Image("/gui/gameover.png", (Game.instance.getWidth()) / (2 * Game.instance.getScale()) - imageWidth / 2, 10, imageWidth, imageHeight);
		
	}

	@Override
	public void render() {
		glClearColor(0.2f, 0.4f, 0.7f, 1.0f);
		this.gameover.render();
	}

	@Override
	public void update() {
		
	}
	
	@Override
	public void dispose() {

	}

}
