package com.edward.game.entities;

import java.awt.Color;
import java.awt.Graphics;

import com.edward.game.Input;

public class Platform extends Entity {
	
	public Platform(double x, double y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void tick(Input input) {
		this.yVelocity = this.speed;
		this.y += this.yVelocity;
	}

	@Override
	public void render(Graphics g) {
		int xa = (int) x;
		int ya = (int) y;
		
		g.setColor(Color.YELLOW);
		g.fillRect(xa, ya, this.width, this.height);
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
}
