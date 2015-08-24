package net.sorax.game.utils;

import net.sorax.game.Game;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class Input {
	
	public static int getMouseX() {
		return Mouse.getX() / (2 * Display.getHeight() / Game.instance.getHeight());
	}
	
	public static int getMouseY() {
		return (Display.getHeight() - Mouse.getY()) / (2 * Display.getHeight() / Game.instance.getHeight());
	}
}
