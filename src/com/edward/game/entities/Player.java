package com.edward.game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

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
			boolean bigJump = Math.random() > 0.9;
			
			this.yVelocity -= bigJump ? 30 : 13;

			double shake = bigJump ? .5 : 0;
			this.screen.camera.shake(shake);
			
			onGround = false;
		}
		
		tryMove();

		this.xVelocity *= Screen.FRICTION;

		if (this.yVelocity < 30) {
			this.yVelocity += Screen.GRAVITY;
		}

		// Player goes below the bottom of screen
		if(y + this.screen.camera.y >= 595) {
			// End game
			this.screen.newGame();
		}
	}

	@Override
	public void render(Graphics g) {
		Point point = this.screen.camera.getDrawPosition(x, y);

		g.setColor(Color.GREEN);
		g.fillRect(point.x, point.y, width, height);
	}
}
