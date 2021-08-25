package com.edward.game.managers;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.edward.game.Input;
import com.edward.game.Screen;
import com.edward.game.entities.Bullet;

public class BulletManager {
	private List<Bullet> bullets = new ArrayList<Bullet>();
	private Screen screen;
	
	public BulletManager(Screen screen) {
		this.screen = screen;
	}
	
	public void tick(long dt, Input input) {
		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			
			if(!bullet.removed) {
				bullet.tick(dt, input);				
			} else {
				bullets.remove(i);
			}
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			bullet.render(g);
		}
	}
		
	public void addBullet(double x, double y, double angle) {
		Bullet bullet = new Bullet(x, y, 2, 2, angle);
		bullet.init(screen);
		
		this.bullets.add(bullet);
	}
}
