package com.edward.game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import com.edward.game.Game;
import com.edward.game.Input;

public class Bullet extends Entity {
	private double angle = 0.0;

	public Bullet(double x, double y, int w, int h, double angle) {
		super(x, y, w, h);

		this.angle = angle;
		this.speed = 10;
	}

	@Override
	public void tick(long dt, Input input) {
		xVelocity = speed * Math.cos(angle);
		yVelocity = speed * Math.sin(angle);

		tryMove();

		/*		Point point = screen.camera.getDrawPosition(x, y);
				if(point.y <= 0 || point.y >= Game.HEIGHT || point.x <= 0 || point.x >= Game.WIDTH) {
					this.removed = true;
					this.screen.camera.shake(.5);
				}
				
				this.screen.particleManager.emit(x, y, 10);*/
	}

	@Override
	public void render(Graphics g) {
		Point start = getPointAlongAngle(0);
		Point end = getPointAlongAngle(20);

		g.setColor(Color.ORANGE);
		g.drawLine(start.x, start.y, end.x, end.y);

		g.fillOval(end.x - 2, end.y - 2, 4, 4);
		//g.fillRect(point.x, point.y, width, height);
	}

	public Point getPointAlongAngle(int length) {
		double xx = x + length * Math.cos(angle);
		double yy = y + length * Math.sin(angle);

		return screen.camera.getDrawPosition(xx, yy);
	}

	public void tryMove() {
		if (x + xVelocity + width > (Game.WIDTH - this.width)) {
			// Hit left wall 
			this.x = (Game.WIDTH - this.width) - width;
		} else if (x + xVelocity < 0) {
			// Hit right wall
			this.x = 0;
		} else {
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
		}

		Platform platform = this.screen.platformManager.getPlatformAt(x, y + yVelocity, width, height);

		if (platform == null) {
			this.y += this.yVelocity;
		} else {
			if (this.yVelocity < 0) {
				// Go up through platforms
				this.y += this.yVelocity;
				this.removed = true;
			} else {
				// Land on top
				this.yVelocity = 0;
				this.y = platform.y - this.height;
				this.removed = true;
				onGround = true;
			}
		}
	}
}
