package net.sorax.game.scene;

import static org.lwjgl.opengl.GL11.*;
import net.sorax.engine.graphics.Image;
import net.sorax.engine.gui.Scene;
import net.sorax.engine.sound.Music;
import net.sorax.game.Game;
import net.sorax.game.gui.Button;
import net.sorax.game.utils.ResourceManager;

public class MenuScene implements Scene{
	
	private Image title;
	private Image twitter;
	private Button play, rules;
	
	private Music music;

	@Override
	public void init() {
		int titleWidth = 128;
		int titleHeight = 72;
		this.title = new Image("/gui/title.png", (Game.instance.getWidth()) / (2 * Game.instance.getScale()) - titleWidth / 2, 10, titleWidth, titleHeight);
		this.twitter = new Image("/gui/twitter.png", 24, Game.instance.getHeight() / Game.instance.getScale() - 50, 32, 32);
		
		this.play = new Button("Play", Game.instance.getWidth() / (2 * Game.instance.getScale()) - titleWidth / 2, Game.instance.getHeight() / (2 * Game.instance.getScale()), titleWidth, 16, 8, 8);
		this.rules = new Button("Rules", Game.instance.getWidth() / (2 * Game.instance.getScale()) - titleWidth / 2, Game.instance.getHeight() / (2 * Game.instance.getScale()) + 20, titleWidth, 16, 8, 8);
	
		music = new Music("/sounds/title.wav");
		music.play();
	}

	@Override
	public void render() {
		glClearColor(0.2f, 0.4f, 0.7f, 1.0f);
		this.title.render();
		this.twitter.render();
		this.play.render();
		this.rules.render();
		
		ResourceManager.font.render("@ S0orax", 24 + 32 + 5, Game.instance.getHeight() / Game.instance.getScale() - 35, 16, 16);
	}

	@Override
	public void update() {
		this.play.update();
		this.rules.update();
		this.actionPerformed();
	}
	
	private void actionPerformed() {
		if(this.play.isClicked()) {
			this.music.stop();
			Scene scene = new GameScene();
			Game.instance.setScene(scene);
			scene.init();
		} else if(this.rules.isClicked()) {
			Scene scene = new RuleScene();
			Game.instance.setScene(scene);
			scene.init();
		}
	}
	
	@Override
	public void dispose() {
		
	}

}
