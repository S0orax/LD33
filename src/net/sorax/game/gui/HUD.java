package net.sorax.game.gui;

import static org.lwjgl.opengl.GL11.*;
import net.sorax.engine.graphics.NineSprite;
import net.sorax.game.Game;
import net.sorax.game.entities.Player;
import net.sorax.game.scene.GameScene;
import net.sorax.game.utils.ResourceManager;

public class HUD {
	
	private int width, height;
	private NineSprite sprite;
	private final int spriteSize = 8;
	
	private Player player;
	
	public HUD(GameScene scene) {
		this.player = scene.getPlayer();
		this.width = Game.instance.getWidth();
		this.height = spriteSize;
		sprite = new NineSprite(ResourceManager.hudSprite, 0, 0, spriteSize, spriteSize);
	}
	
	public void render() {
		glPushMatrix();
		glLoadIdentity();
		sprite.getSprite(0).render();
		sprite.getSprite(2).setPosition(width / Game.instance.getScale() - spriteSize, 0);
		sprite.getSprite(2).render();
		sprite.getSprite(6).setPosition(0, height);
		sprite.getSprite(6).render();
		sprite.getSprite(8).setPosition(width / Game.instance.getScale() - spriteSize, height);
		sprite.getSprite(8).render();
		int w = width / Game.instance.getScale() / spriteSize - 1;
		for(int i = 1; i < w; i++) {
			sprite.getSprite(1).setPosition(i * spriteSize, 0);
			sprite.getSprite(1).render();
			sprite.getSprite(7).setPosition(i * spriteSize, spriteSize);
			sprite.getSprite(7).render();
		}
		this.renderRes();
		glPopMatrix();
	}
	
	private void renderRes() {
		int heightPos = this.height / 2;
		ResourceManager.gui.getSprite(0).setPosition(2, 0);
		ResourceManager.gui.getSprite(0).render();
		ResourceManager.font.render("" + player.getSouls(), 20, heightPos - 1, 16, 16);
	}
	
	public void update() {
		
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
}
