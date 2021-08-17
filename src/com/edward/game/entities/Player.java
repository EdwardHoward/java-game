package com.edward.game.entities;

import java.awt.Color;
import java.awt.Graphics;

import com.edward.game.Input;

public class Player extends Entity {

	public Player(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void tick(Input input) {
		if(input.buttons[37]) {
			this.x--;
		}
		if(input.buttons[39]) {
			this.x++;
		}
		
		if(input.buttons[38]) {
			this.y--;
		}
		if(input.buttons[40]) {
			this.y++;
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
	}
}
