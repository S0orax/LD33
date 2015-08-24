package net.sorax.game.gui;

import net.sorax.engine.graphics.Renderer;
import net.sorax.game.world.Level;

import static org.lwjgl.opengl.GL11.*;

public class LifeBar {
	
	private static float height = 2;
	
	public static void render(float x, float y, int currentHealth, int maxHealth, float width) {
		float ratio = (float)currentHealth / (float)maxHealth;
		if(ratio > 0.5f) Renderer.renderBox(x, y, width * ratio, height, 0.0f, 0.5f, 0.0f, 1.0f);
		else if(ratio > 0.25f) Renderer.renderBox(x, y, width * ratio, height, 0.5f, 1.0f, 0.0f, 1.0f);
		else Renderer.renderBox(x, y, width * ratio, height, 1.0f, 0.0f, 0.0f, 1.0f);
		glColor4f(Level.ambient.r, Level.ambient.g, Level.ambient.b, 1.0f);
	}
}
