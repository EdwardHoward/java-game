package com.edward.game.entities;

import java.awt.Graphics;
import java.awt.Point;

import com.edward.game.Game;
import com.edward.game.Input;
import com.edward.game.Screen;

public abstract class Entity {
	public double x, y, xVelocity, yVelocity;
	public int width, height;

	double speed;
	public boolean onGround = false;

	public Screen screen;
	
	int lastLandIndex = -1;
	
	public boolean removed = false;

	public Entity(double x, double y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}

	public void init(Screen screen) {
		this.screen = screen;
	}

	public void tryMove() {
		if (x + xVelocity + width > (Game.WIDTH - this.width)) {
			// Hit left wall 
			this.x = (Game.WIDTH - this.width) - width;
		} else if (x + xVelocity < 0) {
			// Hit right wall
			this.x = 0;
		} else {
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
			onGround = false;
		} else {
			if (this.yVelocity < 0) {
				// Go up through platforms
				this.y += this.yVelocity;
			} else {
				// Land on top
				this.yVelocity = 0;
				this.y = platform.y - this.height;
				
				if(lastLandIndex != platform.index) {
					lastLandIndex = platform.index;
				}
				
				onGround = true;
			}
		}
	}

	public abstract void tick(long dt, Input input);

	public abstract void render(Graphics g);

	public double[] getCenter() {
		return new double[] { x + width / 2, y + height / 2 };
	}
}
