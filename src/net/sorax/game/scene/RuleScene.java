package net.sorax.game.scene;

import static org.lwjgl.opengl.GL11.*;
import net.sorax.engine.graphics.Image;
import net.sorax.engine.gui.Scene;
import net.sorax.game.Game;
import net.sorax.game.gui.Button;
import net.sorax.game.utils.ResourceManager;

public class RuleScene implements Scene {
	
	private Button back;
	
	private Image title, twitter;
	private String[] texts;
	
	@Override
	public void init() {
		int titleWidth = 128;
		int titleHeight = 72;
		this.title = new Image("/gui/title.png", (Game.instance.getWidth()) / (2 * Game.instance.getScale()) - titleWidth / 2, 10, titleWidth, titleHeight);
		this.twitter = new Image("/gui/twitter.png", 24, Game.instance.getHeight() / Game.instance.getScale() - 50, 32, 32);
	
		texts = new String[]{
			"Spend some souls to spawn monster around egg",
			"You must destroy all campfire to win",
			"Kill human or campfire get some souls",
			"At day time,some human spawn and they want kill you're egg",
			"ARROW move camera,left click select monsters,right click deplace the group"
		};
		
		back = new Button("Back", Game.instance.getWidth() / Game.instance.getScale() - 40, Game.instance.getHeight() / Game.instance.getScale() - 32, 32, 16, 8, 8);
	}

	@Override
	public void render() {
		glClearColor(0.2f, 0.4f, 0.7f, 1.0f);
		this.title.render();
		this.twitter.render();
		
		for(int i = 0; i < texts.length; i++) {
			ResourceManager.font.render(texts[i], 5, i * 8 + 100, 16, 16);
		}
		
		ResourceManager.font.render("@ S0orax", 24 + 32 + 5, Game.instance.getHeight() / Game.instance.getScale() - 35, 16, 16);
	
		this.back.render();
	}

	@Override
	public void update() {
		this.back.update();
		if(this.back.isClicked()) {
			MenuScene scene = new MenuScene();
			Game.instance.setScene(scene);
			scene.init();
		}
	}

	@Override
	public void dispose() {
		
	}
}
