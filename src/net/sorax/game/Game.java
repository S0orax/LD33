package net.sorax.game;

import net.sorax.engine.SCEGame;
import net.sorax.engine.gui.Scene;
import net.sorax.game.scene.MenuScene;

public class Game extends SCEGame {
	
	private Scene scene;
	
	public static Game instance;
	
	public Game() {
		this.title = "The Egg";
		this.scale = 2;
	}
	
	public static Game getInstance() {
		if(instance == null) {
			instance = new Game();
		}
		return instance;
	}
	
	protected void init() {
		this.scene = new MenuScene();
		this.setScene(scene);
		
		super.init();
	}
	
	protected void render() {
		super.render();
	}
	
	protected void update() {
		super.update();
	}
	
	public int getScale() {
		return this.scale;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public static void main(String[] argv) {
		Game game = Game.getInstance();
		game.start();
	}
}
