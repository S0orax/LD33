package net.sorax.game.world;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.sorax.engine.maths.Vector2f;
import net.sorax.engine.utils.Camera;
import net.sorax.game.Game;
import net.sorax.game.entities.Entity;
import net.sorax.game.entities.Player;
import net.sorax.game.entities.living.EntityFireCamp;
import net.sorax.game.entities.living.EntityLiving;
import net.sorax.game.entities.living.human.EntityHuman;
import net.sorax.game.entities.living.monster.EntityGiantEgg;
import net.sorax.game.entities.living.monster.EntityMonster;
import net.sorax.game.scene.GameScene;
import net.sorax.game.utils.Color;
import net.sorax.game.world.tiles.Tile;

public class Level {
	
	private int width, height;
	private Tile[] tiles;
	private List<Entity> entities;
	private EntityGiantEgg egg;
	private Camera camera;
	
	private int time;
	
	private GameScene scene;
	private Random rand;
	
	private int maxSpawnLevel = 10;
	private int nbMob = 0;
	
	private static Color night = new Color(0.5f, 0.5f, 0.5f, 1.0f);
	private Color day = new Color(1.0f, 1.0f, 1.0f, 1.0f);
	public static Color ambient = night.clone();

	private int currentCampFire = 0;
	
	public Level(GameScene scene, int width, int height) {
		this.width = width;
		this.height = height;
		this.scene = scene;
		this.tiles = new Tile[width * height];
		this.entities = new ArrayList<Entity>();
		this.egg = new EntityGiantEgg(this);
		this.egg.setSpawn(new Vector2f(width / 2 * Tile.SIZE - egg.getSize().x / 2, height / 2 * Tile.SIZE - egg.getSize().y / 2));
		this.entities.add(egg);
		this.time = 0;
		this.rand = new Random();
		this.initLevel();
	}
	
	private void initLevel() {
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				tiles[i + j * width] = new Tile(0);
			}
		}
		
		int maxCampFire = 16;
		
		while(currentCampFire < maxCampFire) {
			EntityFireCamp camp = new EntityFireCamp(this);
			float posX = this.rand.nextInt(width * Tile.SIZE);
			float posY = this.rand.nextInt(height * Tile.SIZE);
			if(camp.canSpawnHere(posX, posY)) {
				camp.setPosition(posX, posY);
				this.addEntity(camp);
				currentCampFire++;
			}
		}
	}
	
	public Entity getEntity(float x, float y, float w, float h, Entity entity) {
		for(Entity e: entities) {
			float entityX = e.getPos().x;
			float entityY = e.getPos().y;
			float entityW = entityX + e.getSize().x;
			float entityH = entityY + e.getSize().y;
			if(x + w > entityX && y + h > entityY && x < entityW && y < entityH && !e.equals(entity)) return e;
		}
		
		return null;
	}
	
	public void addEntity(Entity entity) {
		this.entities.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		this.entities.remove(entity);
	}
	
	public void render() {
		glColor4f(ambient.r, ambient.g, ambient.b, ambient.a);
		this.renderTile();
		this.renderEntities();
		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
	}
	
	private void renderTile() {
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				Tile tile = tiles[i + j * width];
				float camX = this.camera.getPosition().x;
				float camY = this.camera.getPosition().y;
				float camW = camX + Game.instance.getWidth() / Game.instance.getScale();
				float camH = camY + Game.instance.getHeight() / Game.instance.getScale();
				
				if((i + 1) * Tile.SIZE < camX) continue;
				if((j + 1) * Tile.SIZE < camY) continue;
				if(i * Tile.SIZE > camW) continue;
				if(j * Tile.SIZE > camH) continue;
				
				if(tile != null) tile.render(i, j);
			}
		}
	}
	
	private void renderEntities() {
		for(Entity e : entities) {
			float entityX = e.getPos().x;
			float entityY = e.getPos().y;
			float entityW = entityX + e.getSize().x;
			float entityH = entityY + e.getSize().y;
			float camX = this.camera.getPosition().x;
			float camY = this.camera.getPosition().y;
			float camW = camX + Game.instance.getWidth() / Game.instance.getScale();
			float camH = camY + Game.instance.getHeight() / Game.instance.getScale();
			
			if(entityX + entityW < camX) continue;
			if(entityY + entityH < camY) continue;
			if(entityX > camW) continue;
			if(entityY > camH) continue;
			
			e.render();
		}
	}
	
	public void update() {
		if(time > 12000 && time < 13330) ambient.lerp(day, (12000f / 13300f) / 360f);
		else if(time > 22700 && time < 24000) ambient.lerp(night, (22700f / 24000f) / 360f);
		
		this.updateEntities();
		time++;
		if(time>=24000) {
			this.nbMob += 5;
			time = 0;
		}
		
		this.generateEntity();
	}
	
	private void updateEntities() {
		for(Entity e : entities) {
			if(e instanceof EntityLiving) {
				EntityLiving entity = (EntityLiving)e;
				if(entity.isDead()) {
					if(entity instanceof EntityMonster) this.getPlayer().removeEntity((EntityMonster)entity);
					this.removeEntity(e);
					if(entity instanceof EntityHuman) nbMob--;
					return;
				}
			}
			
			e.update();
		}
	}
	
	private void generateEntity() {
		float spawnFreq = 0.15f;
		if(this.rand.nextFloat() <= spawnFreq && nbMob <= maxSpawnLevel) {
			Vector2f position = new Vector2f(rand.nextInt(width * Tile.SIZE), rand.nextInt(height * Tile.SIZE));
			EntityHuman human = new EntityHuman(this);
			if(human.canSpawnHere(position.x, position.y)) {
				human.setPosition(position);
				this.addEntity(human);
				this.nbMob++;
			}
		}
	}
	
	public void addHuman(Vector2f position) {
		EntityHuman human = new EntityHuman(this);
		human.setPosition(position);
		this.addEntity(human);
		this.nbMob++;
	}
	
	public List<Entity> getEntities() {
		return this.entities;
	}
	
	public boolean isDay() {
		return time > 12000 && time < 22700;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public EntityGiantEgg getEgg() {
		return this.egg;
	}
	
	public void setCamera(Camera camera) {
		this.camera = camera;
	}
	
	public Camera getCamera() {
		return this.camera;
	}
	
	public Player getPlayer() {
		return this.scene.getPlayer();
	}
	
	public void subNbCamp() {
		this.currentCampFire--;
	}
	
	public int getCurrentCampFire() {
		return this.currentCampFire;
	}
}
