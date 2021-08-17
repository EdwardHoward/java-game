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
	public void tick(Input input) {
		if(input.buttons[37]) {
			this.xVelocity = -this.speed;
		}
		
		if(input.buttons[39]) {
			this.xVelocity = this.speed;
		}
		
		
		if(input.buttons[32] && !input.oldButtons[32] && onGround) {
			this.yVelocity -= 8;
			onGround = false;
		}
		
		// only check sides if going downwards 
		// (This causes problems if you start going downwards inside a platform)
		if(yVelocity > 0) {
			Platform platformX = this.screen.platformManager.getPlatformAt(x + xVelocity, y, width, height);
			
			if(platformX == null) {
				this.x += this.xVelocity;
			} else {
				if(xVelocity < 0) {
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
		
		if(platform == null) {
			this.y += this.yVelocity;			
		} else {
			if(this.yVelocity < 0) {
				// Go up through platforms
				this.y += this.yVelocity;
			} else {
				this.yVelocity = 0;
				this.y = platform.y - this.height;
				
				onGround = true;
			}
		}
		
		
		this.xVelocity *= Screen.FRICTION;
		
		this.yVelocity += Screen.GRAVITY;
		
		// Move the platforms up when jumping
		// TODO: Move the platforms down so the current one is at a a certain Y position
		if(this.yVelocity < 0) {
			this.screen.platformManager.currentSpeed = 4;
		} else {
			this.screen.platformManager.currentSpeed = 0;
		}
	}

	@Override
	public void render(Graphics g) {
		int xp = (int) x;
		int yp = (int) y;
		
		g.setColor(Color.GREEN);
		g.fillRect(xp, yp, width, height);
	}
}
