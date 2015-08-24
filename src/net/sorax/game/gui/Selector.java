package net.sorax.game.gui;

import net.sorax.engine.graphics.NineSprite;
import net.sorax.game.utils.ResourceManager;

public class Selector {
	
	private final int spriteSize = 8;
	private final NineSprite sprite = new NineSprite(ResourceManager.selector, 0, 0, spriteSize, spriteSize);
	
	private int x, y, width, height;
	
	public Selector(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void render() {
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
	}
	
	private void placeNine(int x, int y, int frame) {
		sprite.getSprite(frame).setPosition(x * spriteSize, y * spriteSize);
		sprite.getSprite(frame).render();
	}
	
	public void update() {
		
	}
	
	public int getSpriteSize() {
		return this.spriteSize;
	}
	
	public int getX() {
		return this.x * spriteSize;
	}
	
	public int getY() {
		return this.y * spriteSize;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
}
