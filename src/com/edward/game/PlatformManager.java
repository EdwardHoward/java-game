package com.edward.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.edward.game.entities.Entity;
import com.edward.game.entities.Platform;

public class PlatformManager {
	List<Platform> platforms = new ArrayList<Platform>();
	int platformCount = 0;
	Screen screen;

	// This is like a camera
	// example: g.fillRect(x + offsetX, y + offsetY, w, h);
	public double offsetX = 0;
	public double offsetY = 10;

	Entity target;

	public PlatformManager(Screen screen) {
		this.screen = screen;
	}
	
	public void clear() {
		this.platforms.clear();
		platformCount = 0;
	}

	public void tick(long dt, Input input) {
		for (int i = 0; i < platforms.size(); i++) {
			Platform platform = platforms.get(i);

			platform.tick(dt, input);
			
			// When a platform goes below the screen send it back to the top
			if(platform.y + offsetY >= 595) {
				platformCount++;
				
				platform.y = -offsetY - 120;
				
				if(platform.width != 800) {
					platform.x = Math.random() * 400;
				}
			}
		}

		double targetY = -(this.target.y - 450 + 300);
		
		double distance = targetY - this.offsetY;
		
		if(distance > 1) {
			this.offsetY = lerp(offsetY, targetY, .05);
		}
		
		// move the camera automatically
		this.offsetY += (.05 * (platformCount / 10)) * dt;
	}

	public void render(Graphics g) {
		for (int i = 0; i < platforms.size(); i++) {
			Platform platform = platforms.get(i);
			platform.render(g);
		}
	}

	public double lerp(double a, double b, double t) {
		return a + t * (b - a);
	}

	public void createPlatform(int x, int y, int width, int height) {
		Platform p = new Platform(x, y, width, height);
		p.init(this.screen);
		platforms.add(p);
	}

	public Platform getPlatformAt(double x, double y, int w, int h) {
		for (int i = 0; i < platforms.size(); i++) {
			Platform platform = platforms.get(i);
			
			platform.setColor(Color.getHSBColor((platformCount / 10) / 100f, 1.0f, 1.0f));
			
			double xx = platform.x;
			double yy = platform.y;

			int ww = platform.width;
			// We only want to check for the top pixel of the platform
			int hh = 1;

			if (x < xx + ww && x + w > xx && y < yy + hh && y + h > yy) {
				return platform;
			}
		}

		return null;
	}

	// Camera points at target
	public void setTarget(Entity target) {
		this.target = target;
	}
}
