package com.edward.game.entities;

import java.awt.Color;
import java.awt.Graphics;

import com.edward.game.Input;
import com.edward.game.Screen;

public class Player extends Entity {
	public Player(double x, double y, int w, int h) {
		super(x, y, w, h);

		speed = 5;
	}

	@Override
	public void tick(long dt, Input input) {
		if (input.isDown(Input.LEFT)) {
			this.xVelocity = -this.speed;
		}

		if (input.isDown(Input.RIGHT)) {
			this.xVelocity = this.speed;
		}

		if (input.isDownOnce(Input.JUMP) && onGround) {
			this.yVelocity -= Math.random() > 0.9 ? 30 : 13;
			onGround = false;
		}
		
		tryMove();

		this.xVelocity *= Screen.FRICTION;

		if (this.yVelocity < 30) {
			this.yVelocity += Screen.GRAVITY;
		}

		// Player goes below the bottom of screen
		if(y + this.screen.platformManager.offsetY >= 595) {
			// End game
			this.screen.newGame();
		}
	}

	@Override
	public void render(Graphics g) {
		int xp = (int) (x + this.screen.platformManager.offsetX);
		int yp = (int) (y + this.screen.platformManager.offsetY);

		g.setColor(Color.GREEN);
		g.fillRect(xp, yp, width, height);
		
		g.fillRect(783, 100, 1, 1);
	}
}
