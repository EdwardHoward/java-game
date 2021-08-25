package com.edward.game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import com.edward.game.Input;

public class Platform extends Entity {
	Color color = Color.YELLOW;
	
	public int index = 0;
	
	public Platform(double x, double y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void tick(long dt, Input input) {

	}

	@Override
	public void render(Graphics g) {
		Point point = this.screen.camera.getDrawPosition(x, y);
		
		g.setColor(color);
		g.fillRect(point.x, point.y, this.width, this.height);
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
}
