package net.sorax.game;

import net.sorax.engine.SCEGame;
import net.sorax.engine.gui.Scene;
import net.sorax.game.scene.GameScene;

public class Game extends SCEGame {
	
	private Scene gameScene;
	
	public Game() {
		this.title = "LD33";
	}
	
	protected void init() {
		this.gameScene = new GameScene();
		this.setScene(gameScene);
		super.init();
	}
	
	protected void render() {
		super.render();
	}
	
	protected void update() {
		super.update();
	}
	
	public static void main(String[] argv) {
		Game game = new Game();
		game.start();
	}
}
