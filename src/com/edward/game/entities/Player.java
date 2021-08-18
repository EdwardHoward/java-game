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
		if (input.buttons[Input.LEFT]) {
			this.xVelocity = -this.speed;
		}

		if (input.buttons[Input.RIGHT]) {
			this.xVelocity = this.speed;
		}

		if (input.buttons[Input.JUMP] && !input.oldButtons[Input.JUMP] && onGround) {
			this.yVelocity -= 17;
			onGround = false;
		}

		// only check sides if going downwards 
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
			this.screen.newGame();
		}
	}

	@Override
	public void render(Graphics g) {
		int xp = (int) (x + this.screen.platformManager.offsetX);
		int yp = (int) (y + this.screen.platformManager.offsetY);

		g.setColor(Color.GREEN);
		g.fillRect(xp, yp, width, height);
	}
}
