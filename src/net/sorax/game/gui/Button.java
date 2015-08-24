package net.sorax.game.gui;

import static org.lwjgl.opengl.GL11.*;
import net.sorax.engine.graphics.NineSprite;
import net.sorax.game.utils.Input;
import net.sorax.game.utils.ResourceManager;

import org.lwjgl.input.Mouse;

public class Button {
	
	private boolean clicked;
	private int x, y, width, height;
	private NineSprite sprite;
	private int spriteW, spriteH;
	private String text;
	
	public Button(String text, int x, int y, int width, int height, int spriteW, int spriteH) {
		this.text = text;
		this.clicked = false;
		this.spriteW = spriteW;
		this.spriteH = spriteH;
		this.sprite = new NineSprite(ResourceManager.buttonSprite, x, y, spriteW, spriteH);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void update() {
		int mouseX = Input.getMouseX();
		int mouseY = Input.getMouseY();
		
		if(mouseX >= x && mouseY >= y && mouseX <= x + width && mouseY <= y + height && Mouse.isButtonDown(0)) {
			this.clicked = true;
		} else {
			this.clicked = false;
		}
	}
	
	public void render() {
		glPushMatrix();
		glLoadIdentity();
		this.sprite.getSprite(0).setPosition(x, y);
		this.sprite.getSprite(0).render();
		this.sprite.getSprite(2).setPosition(x + width - spriteW, y);
		this.sprite.getSprite(2).render();
		this.sprite.getSprite(6).setPosition(x, y + height - spriteH);
		this.sprite.getSprite(6).render();
		this.sprite.getSprite(8).setPosition(x + width - spriteW, y + height - spriteH);
		this.sprite.getSprite(8).render();
		
		int w = width - spriteW;
		int h = height - spriteH;
		
		for(int i = spriteW; i < w; i+= spriteW) {
			this.sprite.getSprite(1).setPosition(x + i, y);
			this.sprite.getSprite(7).setPosition(x + i, y + height - spriteH);
			this.sprite.getSprite(1).render();
			this.sprite.getSprite(7).render();
		}

		for(int i = spriteH; i < h; i+= spriteH) {
			this.sprite.getSprite(3).setPosition(x, y + i);
			this.sprite.getSprite(5).setPosition(x + width - spriteW, y + i);
			this.sprite.getSprite(3).render();
			this.sprite.getSprite(5).render();
		}
		
		int centerTextX = this.x + this.width / 2 - text.length() * 6 / 2;
		int centerTextY = this.y + this.height / 2 - 6;
		
		ResourceManager.font.render(text, centerTextX, centerTextY, 16, 16);
		glPopMatrix();
	}
	
	public boolean isClicked() {
		return this.clicked;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
}
