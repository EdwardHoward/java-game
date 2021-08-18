package com.edward.game.entities;

import java.awt.Graphics;

import com.edward.game.Input;
import com.edward.game.Screen;

public abstract class Entity {
	public double x, y, xVelocity, yVelocity;
	public int width, height;
	
	double speed;
	
	Screen screen;
	
	public Entity(double x, double y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}
	
	public void init(Screen screen) {
		this.screen = screen;
	}
	
	public abstract void tick(long dt, Input input);
	
	public abstract void render(Graphics g);
}
