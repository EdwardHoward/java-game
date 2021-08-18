package com.edward.game.entities;

import java.awt.Color;
import java.awt.Graphics;

import com.edward.game.Input;

public class Platform extends Entity {
	Color color = Color.YELLOW;
	
	public Platform(double x, double y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void tick(long dt, Input input) {

	}

	@Override
	public void render(Graphics g) {
		int xa = (int) (x + this.screen.platformManager.offsetX);
		int ya = (int) (y + this.screen.platformManager.offsetY);
		
		g.setColor(color);
		g.fillRect(xa, ya, this.width, this.height);
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
}
