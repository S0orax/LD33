package net.sorax.game.world;

import net.sorax.game.world.tiles.Tile;

public class Level {
	
	private int width, height;
	private Tile[] tiles;
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		this.tiles = new Tile[width * height];
		this.initLevel();
	}
	
	private void initLevel() {
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				tiles[i + j * width] = Tile.grass;
			}
		}
	}
	
	public void render() {
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				Tile tile = tiles[i + j * width];
				if(tile != null) tile.render(i, j);
			}
		}
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
