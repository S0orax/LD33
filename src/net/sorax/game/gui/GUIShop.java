package net.sorax.game.gui;

import static org.lwjgl.opengl.GL11.*;
import net.sorax.engine.graphics.NineSprite;
import net.sorax.game.Game;
import net.sorax.game.entities.living.monster.EntitySkeleton;
import net.sorax.game.entities.living.monster.EntitySpider;
import net.sorax.game.entities.living.monster.EntityZombie;
import net.sorax.game.scene.GameScene;
import net.sorax.game.utils.ResourceManager;

public class GUIShop implements GUI {
	
	private NineSprite sprite;
	private final int spriteSize = 8;
	private int width, height, x, y;
	
	private GameScene scene;
	private Card spiderCard;
	private Card zombieCard;
	private Card skeletonCard;
	
	
	public GUIShop(GameScene scene) {
		this.scene = scene;
		this.sprite = new NineSprite(ResourceManager.hudSprite, 0, 0, this.spriteSize, this.spriteSize);
		this.width = Game.instance.getWidth() / Game.instance.getScale() / spriteSize - 10;
		this.height = Game.instance.getHeight() / Game.instance.getScale() / spriteSize - 10;
		this.y = 5;
		this.x = 5;
		
		this.spiderCard = new Card(scene.getPlayer(), new EntitySpider(scene.getLevel()), x + 2, y + height / 4, 10);
		this.zombieCard = new Card(scene.getPlayer(), new EntityZombie(scene.getLevel()), x + 2 + 10, y + height / 4, 20);
		this.skeletonCard = new Card(scene.getPlayer(), new EntitySkeleton(scene.getLevel()), x + 2 + 20, y + height / 4, 50);
	}
	
	public void render() {
		glPushMatrix();
		glLoadIdentity();
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				if(i == 0) {
					if(j == 0) placeNine(x + i, y + j, 0);
					else if(j == height - 1) placeNine(x + i, y + j, 6);
					else placeNine(x + i, y + j, 3);
				} else if(i == width - 1) {
					if(j == 0) placeNine(x + i, y + j, 2);
					else if(j == height - 1) placeNine(x + i, y + j, 8);
					else placeNine(x + i, y + j, 5);
				} else if(j == 0) placeNine(x + i, y + j, 1);
				else if(j == height - 1) placeNine(x + i, y + j, 7);
				else placeNine(x + i, y + j, 4);
			}
		}
		
		ResourceManager.font.render("Shop", x * spriteSize + 5, y * spriteSize + 5, 16, 16);
		
		this.spiderCard.render();
		this.zombieCard.render();
		this.skeletonCard.render();
		glPopMatrix();
	}
	
	private void placeNine(int x, int y, int frame) {
		this.sprite.getSprite(frame).setPosition(x * spriteSize, y * spriteSize);
		this.sprite.getSprite(frame).render();
	}

	@Override
	public void update() {
		this.spiderCard.update();
		this.zombieCard.update();
		this.skeletonCard.update();
	}

	@Override
	public void actionPerformed() {
		this.spiderCard.actionPerformed(new EntitySpider(this.scene.getLevel()));
		this.zombieCard.actionPerformed(new EntityZombie(this.scene.getLevel()));
		this.skeletonCard.actionPerformed(new EntitySkeleton(this.scene.getLevel()));
	}
}
