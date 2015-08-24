package net.sorax.game.utils;

import net.sorax.engine.graphics.BitmapFont;
import net.sorax.engine.graphics.Image;
import net.sorax.engine.graphics.Sprite;
import net.sorax.engine.graphics.SpriteSheet;
import net.sorax.engine.sound.Music;
import net.sorax.engine.sound.Sound;
import net.sorax.game.world.tiles.Tile;

public class ResourceManager {
	
	public static final SpriteSheet tiles = new SpriteSheet("/tiles/test.png", 32, 32, Tile.SIZE, Tile.SIZE);
	public static final SpriteSheet gui = new SpriteSheet("/gui/gui.png", 32, 32, 16, 16);
	
	public static final Sprite hudSprite = new Sprite("/gui/hud.png", 0, 0, 32, 32, 32, 32, 0);
	public static final Sprite buttonSprite = new Sprite("/gui/button.png", 0, 0, 32, 32, 32, 32, 0);
	public static final Sprite selector = new Sprite("/gui/selector.png", 0, 0, 64, 64, 64, 64, 0);
	public static final Sprite egg = new Sprite("/entities/egg.png", 0, 0, 64, 55, 64, 55, 0);
	public static final Sprite spider = new Sprite("/entities/spider.png", 0, 0, 32, 32, 64, 64, 0);
	public static final Sprite zombie = new Sprite("/entities/zombie.png", 0, 0, 32, 32, 64, 64, 0);
	public static final Sprite skeleton = new Sprite("/entities/skeleton.png", 0, 0, 32, 32, 64, 64, 0);
	public static final Sprite human = new Sprite("/entities/human.png", 0, 0, 32, 32, 64, 64, 0);
	public static final Sprite camp = new Sprite("/entities/firecamp.png", 0, 0, 64, 64, 64, 64, 0);
	
	public static final BitmapFont font = new BitmapFont("/gui/font.png", 16);
	
	public static final Sound hurt = new Sound("/sounds/hurt.wav");
	public static final Sound attack = new Sound("/sounds/attack.wav");
	
//	public static Shader dayLight = new Shader("/shaders/daylight.vert", "/shaders/daylight.frag");
}
