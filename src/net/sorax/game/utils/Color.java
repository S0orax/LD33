package net.sorax.game.utils;

public class Color {
	
	public float r, g, b, a;
	
	public Color(int color) {
		this.r = (float)(color >> 16 & 0xFF) / 255.0f;
		this.g = (float)(color >> 8 & 0xFF) / 255.0f;
		this.b = (float)(color & 0xFF) / 255.0f;
		this.a = (float)(color >> 24 & 0xFF) / 255.0f;
	}
	
	public Color(float r, float g, float b) {
		this(r, g, b, 1.0f);
	}
	
	public Color(float r, float g, float b, float a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	public void lerp(Color color, float time) {
		this.r += (color.r - this.r) * time;
		this.g += (color.g - this.g) * time;
		this.b += (color.b - this.b) * time;
	}
	
	public Color clone() {
		return new Color(this.r, this.g, this.b, this.a);
	}
}
