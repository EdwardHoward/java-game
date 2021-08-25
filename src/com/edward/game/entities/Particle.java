package com.edward.game.entities;

import java.awt.Graphics;
import java.awt.Point;

import com.edward.game.Input;

public class Particle extends Entity{
	public double angle = 0.0;
	
	public Particle(double x, double y, int w, int h) {
		super(x, y, w, h);
		
		this.speed = 0.5;
	}

	@Override
	public void tick(long dt, Input input) {
		x += speed * Math.cos(angle); 
		y += speed * Math.sin(angle);
	}

	@Override
	public void render(Graphics g) {
		Point point = this.screen.camera.getDrawPosition(x, y);
		
		//this.screen.gfx.setPixel(point.x, point.y, 0xff, 0, 0);
	}
}
