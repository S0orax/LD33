package net.sorax.game.world.tiles;

import net.sorax.engine.graphics.Sprite;
import net.sorax.game.utils.ResourceManager;

public class Tile {
	
//	public static final Tile grass = new Tile(0);
	
	public static int SIZE = 32;
	protected Sprite sprite;
	
	public Tile(int frame) {
		this.sprite = ResourceManager.tiles.getSprite(frame);
	}
	
	public void render(int x, int y) {
		sprite.setPosition(x * Tile.SIZE, y * Tile.SIZE);
		sprite.render();
	}
}
