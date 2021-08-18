package com.edward.game.entities;

import java.awt.Color;
import java.awt.Graphics;

import com.edward.game.Input;
import com.edward.game.Screen;

public class Player extends Entity {
	boolean onGround = false;

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

		// only check sides if going downwards 
		
		if(x + xVelocity + width > 784) {
			// Hit left wall 
			this.x = 784 - width;
		} else if(x + xVelocity < 0) {
			// Hit right wall
			this.x = 0;
		}
		else 
		{
			if (yVelocity > 0) {
				Platform platformX = this.screen.platformManager.getPlatformAt(x + xVelocity, y, width, height);

				if (platformX == null) {
					this.x += this.xVelocity;
				} else {
					if (xVelocity < 0) {
						this.xVelocity = 0;
						this.x = platformX.x + platformX.width; 
					} else {
						this.xVelocity = 0;
						this.x = platformX.x - this.width;
					}
				}
			} else {
				this.x += this.xVelocity;
			}
		}
		
		Platform platform = this.screen.platformManager.getPlatformAt(x, y + yVelocity, width, height);

		if (platform == null) {
			this.y += this.yVelocity;
		} else {
			if (this.yVelocity < 0) {
				// Go up through platforms
				this.y += this.yVelocity;
			} else {
				// Land on top
				this.yVelocity = 0;
				this.y = platform.y - this.height;

				onGround = true;
			}
		}

		this.xVelocity *= Screen.FRICTION;

		if (this.yVelocity < 30) {
			this.yVelocity += Screen.GRAVITY;
		}

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
