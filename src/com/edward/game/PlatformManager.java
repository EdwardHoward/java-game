package com.edward.game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.edward.game.entities.Platform;

public class PlatformManager {
	public double currentSpeed = .01;
	List<Platform> platforms = new ArrayList<Platform>();
	
	public PlatformManager() {
		// first floor
		createPlatform(0, 600 - 100, 800, 40);
		
		for(int i = 0; i < 5; i++) {
			createPlatform((int)(Math.random()* 400), 600 - 100 - (120 * i), 200, 40);
		}
	}
	
	public void tick(Input input) {
		for(int i = 0; i < platforms.size(); i++) {
			Platform platform = platforms.get(i);
			platform.setSpeed(currentSpeed);
			platform.tick(input);
			
			if(platform.y > 600 - 40) {
				platform.y = 0 - 40;
			}
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < platforms.size(); i++) {
			Platform platform = platforms.get(i);
			platform.render(g);
		}
	}
	
	public void createPlatform(int x, int y, int width, int height) {
		platforms.add(new Platform(x, y, width, height));
	}
	
	public Platform getPlatformAt(double x, double y, int w, int h) {
		for(int i = 0; i < platforms.size(); i++) {
			Platform platform = platforms.get(i);
			
			double xx = platform.x;
			double yy = platform.y;
			int ww = platform.width;
			int hh = platform.height;
			
			if(x < xx + ww && 
			   x + w > xx &&
			   y < yy + hh && 
			   y + h > yy) {
				return platform;
			}
		}
		
		return null;
	}
}
