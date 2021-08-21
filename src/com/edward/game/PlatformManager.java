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

	public PlatformManager(Screen screen) {
		this.screen = screen;
	}
	
	public void clear() {
		this.platforms.clear();
		platformCount = 0;
	}
	
	public Platform getHighestPlatform() {
		Platform lowest = null;
		
		for(int i = 0; i < platforms.size(); i++) {
			Platform platform = platforms.get(i);
			
			if(lowest == null || platform.y < lowest.y) {
				lowest = platform;
			}
		}
		
		return lowest;
	}

	public void tick(long dt, Input input) {
		for (int i = 0; i < platforms.size(); i++) {
			Platform platform = platforms.get(i);

			platform.tick(dt, input);
			
			// When a platform goes below the screen send it back to the top
			if(platform.y + screen.camera.y >= (Game.HEIGHT - 5)) {
				platformCount++;
				
				Platform highest = getHighestPlatform();
				platform.y = highest.y - 120;
				
				if(platform.width != Game.WIDTH) {
					platform.x = Math.random() * (Game.WIDTH / 2);
				}
				
				platform.setColor(Color.getHSBColor((platformCount / 10) / 100f, 1.0f, 1.0f));
			}
		}

		// move the camera up automatically
		screen.camera.y += (.05 * (platformCount / 10)) * dt;
	}

	public void render(Graphics g) {
		for (int i = 0; i < platforms.size(); i++) {
			Platform platform = platforms.get(i);
			platform.render(g);
		}
	}

	public void createPlatform(int x, int y, int width, int height) {
		Platform p = new Platform(x, y, width, height);
		p.init(this.screen);
		platforms.add(p);
	}

	public Platform getPlatformAt(double x, double y, int w, int h) {
		for (int i = 0; i < platforms.size(); i++) {
			Platform platform = platforms.get(i);
			
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
}
