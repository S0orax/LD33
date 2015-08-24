package net.sorax.game.scene;

import java.util.List;

import net.sorax.engine.gui.Scene;
import net.sorax.engine.maths.Vector2f;
import net.sorax.engine.utils.Camera;
import net.sorax.game.Game;
import net.sorax.game.entities.Entity;
import net.sorax.game.entities.Player;
import net.sorax.game.entities.living.monster.EntityMonster;
import net.sorax.game.gui.Button;
import net.sorax.game.gui.GUI;
import net.sorax.game.gui.GUIShop;
import net.sorax.game.gui.HUD;
import net.sorax.game.gui.Selector;
import net.sorax.game.utils.Input;
import net.sorax.game.world.Level;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import static org.lwjgl.opengl.GL11.*;

public class GameScene implements Scene {

	private Level level;
	private Camera camera;
	private HUD hud;
	private Player player;
	private GUI gui;
	
	private Button buy;
	private Button spawn;
	
	private Vector2f mouseStartPos;
	private boolean selectorDrawing;
	private Selector selector;
	
	@Override
	public void init() {
		this.level = new Level(this, 64, 64);
		this.player = new Player(this);
		this.hud = new HUD(this);
		this.selector = null;
		
		int centerCamWidth = Display.getWidth() / (2 * Game.instance.getScale()) - (int)level.getEgg().getSize().x / 2;
		int centerCamHeight = Display.getHeight() / (2 * Game.instance.getScale()) - (int)level.getEgg().getSize().y / 2;
		Vector2f cameraSpawn = this.level.getEgg().getPos().add(new Vector2f(-centerCamWidth, -centerCamHeight));
		this.camera = new Camera(cameraSpawn);
		
		this.level.setCamera(camera);
		
		this.buy = new Button("Buy", this.hud.getWidth() / Game.instance.getScale() - 25, 2, 20, 12, 6, 6);
		this.spawn = new Button("Egg", this.hud.getWidth() / Game.instance.getScale() / 2 - 3, 2, 20, 12, 6, 6);
	}

	@Override
	public void render() {
		glClearColor(0, 0, 0, 1);
		
		this.level.render();
		this.hud.render();
		this.buy.render();
		this.spawn.render();
		
		if(gui != null) gui.render();
		
		if(Input.getMouseY() > 16) {
			if(Mouse.isButtonDown(0) && gui == null) {
				float camX = this.camera.getPosition().x;
				float camY = this.camera.getPosition().y;
				if(!selectorDrawing) {
					player.removeAllEntities();
					this.mouseStartPos = new Vector2f((camX + Input.getMouseX()) / 8, (camY + Input.getMouseY()) / 8);
					selector = new Selector((int)mouseStartPos.x, (int)mouseStartPos.y, 0, 0);
				}
				
				int posX = ((int)camX + Input.getMouseX()) / 8 - (int)mouseStartPos.x;
				int posY = (int)(camY + Input.getMouseY()) / 8 - (int)mouseStartPos.y;
				
				if(posX < 0) selector.setX(((int)camX + Input.getMouseX()) / 8);
				if(posY < 0) selector.setY(((int)camY + Input.getMouseY()) / 8);
				
				selector.setSize(Math.abs(posX), Math.abs(posY));
				
				selector.render();
				selectorDrawing = true;
			} else {
				this.addEntitiesToPlayer();
				selectorDrawing = false;
				this.selector = null;
			}
		}
		
		player.update();
	}
	
	private void addEntitiesToPlayer() {
		if(selector != null) {
			List<Entity> entities = level.getEntities();
			for(Entity e : entities) {
				if(e instanceof EntityMonster) {
					float entityX = e.getPos().x;
					float entityY = e.getPos().y;
					float posX = selector.getX();
					float posY = selector.getY();
					float w = posX + selector.getWidth() * 8;
					float h = posY + selector.getHeight() * 8;
					
					if(entityX > posX && entityX < w && entityY > posY && entityY < h) {
						player.addEntity((EntityMonster)e);
					}
				}
			}
		}
	}

	private void moveCamera(float xa, float ya, float time) {
		xa += (camera.getPosition().x + xa - camera.getPosition().x) * time;
		ya += (camera.getPosition().y + ya - camera.getPosition().y) * time;
		
		this.camera.translate(xa, ya);
	}
	
	@Override
	public void update() {
		this.level.update();
		float xa = 0, ya = 0;
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) xa--;
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) xa++;
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)) ya--;
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) ya++;
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) gui = null;
		
		this.moveCamera(xa, ya, 1f);
		
		this.buy.update();
		this.spawn.update();
		if(gui != null) gui.update();
		
		this.actionPerformed();
	}
	
	private void actionPerformed() {
		if(this.buy.isClicked() && !(gui instanceof GUIShop)) gui = new GUIShop(this);
		else if(this.spawn.isClicked() && gui == null) {
			float eggPosX = level.getEgg().getPos().x;
			float eggPosY = level.getEgg().getPos().y;
			float camX = this.camera.getPosition().x;
			float camY = this.camera.getPosition().y;
			float newX = eggPosX - (camX + Game.instance.getWidth() / Game.instance.getScale() / 2) + 32;
			float newY = eggPosY - camY - Game.instance.getHeight() / Game.instance.getScale() / 2 + 26;
			
			this.moveCamera(newX, newY, 0.01f);
		}
		
		if(gui != null) gui.actionPerformed();
	}

	@Override
	public void dispose() {
		
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public Level getLevel() {
		return this.level;
	}
}
