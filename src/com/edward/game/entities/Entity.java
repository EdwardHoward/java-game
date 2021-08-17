package com.edward.game.entities;

import java.awt.Graphics;

import com.edward.game.Input;

public abstract class Entity {
	int x;
	int y;
	int width;
	int height;
	
	public Entity(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}
	
	public abstract void tick(Input input);
	
	public abstract void render(Graphics g);
}
