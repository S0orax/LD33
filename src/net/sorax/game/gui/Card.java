package net.sorax.game.gui;

import static org.lwjgl.opengl.GL11.*;

import java.util.Random;

import org.lwjgl.input.Mouse;

import net.sorax.engine.graphics.NineSprite;
import net.sorax.engine.graphics.Sprite;
import net.sorax.game.Game;
import net.sorax.game.entities.Player;
import net.sorax.game.entities.living.EntityLiving;
import net.sorax.game.utils.Input;
import net.sorax.game.utils.ResourceManager;

public class Card {
	
	private NineSprite sprite;
	private EntityLiving entity;
	private Sprite entitySprite;
	private int x, y, width, height, spriteSize = 8;
	private int cost;
	private Player player;
	private Random rand;
	private int mouseTime = 0;
	
	public Card(Player player, EntityLiving entity, int x, int y, int cost) {
		this.entity = entity;
		this.player = player;
		this.entitySprite = entity.getSprite();
		this.cost = cost;
		this.x = x;
		this.y = y;
		this.sprite = new NineSprite(ResourceManager.buttonSprite, x, y, spriteSize, spriteSize);
		this.width = (int) (entitySprite.getWidth() / Game.instance.getScale() / 2);
		this.height = (int) (entitySprite.getHeight() / Game.instance.getScale() / 2);
		this.rand = new Random();
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
		
		this.entitySprite.setPosition(x * spriteSize + (width * spriteSize - entitySprite.getWidth()) / 2, y * spriteSize + entitySprite.getHeight() / 2 - 10);
		this.entitySprite.render();
		ResourceManager.gui.getSprite(0).setPosition(x * spriteSize + 5, y  * spriteSize + entitySprite.getHeight() / 2 + 32);
		ResourceManager.gui.getSprite(0).render();
		ResourceManager.font.render(entity.getName(), x * spriteSize + entity.getName().length() * 5 / 2, (int) (y * spriteSize + entitySprite.getHeight() + 8), 16, 16);
		String text = "" + entity.getMaxHealth();
		ResourceManager.font.render(text, x * spriteSize + 5, (int) (y * spriteSize + 2), 16, 16);
		text = " :" + cost;
		ResourceManager.font.render(text, x * spriteSize + text.length() * 8 / 2, (int) (y * spriteSize + entitySprite.getHeight() / 2 + 34), 16, 16);
		
		glPopMatrix();
	}
	
	private void placeNine(int x, int y, int frame) {
		this.sprite.getSprite(frame).setPosition(x * spriteSize, y * spriteSize);
		this.sprite.getSprite(frame).render();
	}

	public void update() {
		if(mouseTime < 0) mouseTime = 0;
		mouseTime--;
	}
	
	public boolean isClicked() {
		int mouseX = Input.getMouseX();
		int mouseY = Input.getMouseY();
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		
		if(mouseTime <= 0 && Mouse.isButtonDown(0) && mouseX >= xx && mouseY >= yy && mouseX <= xx + width * spriteSize && mouseY <= yy + height * spriteSize) {
			mouseTime += 60;
			return true;
		}
		else return false;
	}
	
	public void actionPerformed(EntityLiving entityLiving) {
		if(isClicked()) {
			if(player.subSouls(this.cost)) {
				int sign = rand.nextBoolean() ? -1 : 1;
				float entityX = entity.getLevel().getEgg().getPos().x + sign * 50;
				float entityY = entity.getLevel().getEgg().getPos().x + 50;
				entity.setPosition(entityX, entityY);
				this.entity.getLevel().addEntity(entity);
				this.entity = entityLiving;
			}
		}
	}
	
	public int getCost() {
		return this.cost;
	}
}
