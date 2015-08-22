package net.sorax.game.world.tiles;

import net.sorax.engine.graphics.Renderer;

public class Tile {
	
	public static final Tile grass = new Tile(0x00ff00);
	
	public static int SIZE = 32;
	private float r, g, b;
	
	public Tile(int color) {
		this.r = (float)((color >> 16) & 0xff) / 255.0f;
		this.g = (float)((color >> 8) & 0xff) / 255.0f;
		this.b = (float)((color) & 0xff) / 255.0f;
	}
	
	public void render(int x, int y) {
		Renderer.renderBox(x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE, r, g, b, 1.0f);
	}
}
