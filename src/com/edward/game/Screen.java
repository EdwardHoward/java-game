package com.edward.game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.edward.game.entities.Entity;
import com.edward.game.entities.Player;

public class Screen {
	public static double GRAVITY = 0.5;
	public static double FRICTION = 0.7;
	
	private Game game;
	private List<Entity> entities = new ArrayList<Entity>();
	public PlatformManager platformManager = new PlatformManager(this);
	
	public Screen(Game game) {
		this.game = game;
		
		Entity player = new Player(400 - 15, 450, 30, 30);
		this.addEntity(player);
		
		this.platformManager.setTarget(player);
	}
	
	public void newGame() {
		this.entities.clear();
		
		Entity player = new Player(400 - 15, 450, 30, 30);
		this.addEntity(player);
		
		// Set camera back down to bottom
		platformManager.offsetY = 10;
		
		// Create the first floor
		platformManager.createPlatform(0, 600 - 100, 800, 40);

		// Create 6 platforms equally spaced
		for(int i = 0; i < 6; i++) { 
			platformManager.createPlatform((int)(Math.random()* 400), 600 - 100 - (120 * i), 200, 40); 
		}
		
		// point the camera at the player
		this.platformManager.setTarget(player);
	}
	
	public void tick(long dt, Input input) {
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).tick(dt, input);
		}
		
		platformManager.tick(dt, input);
	}
	
	public void render(Graphics g) {
		platformManager.render(g);

		entities.forEach((entity) -> {
			entity.render(g);
		});
		
	}
	
	public void addEntity(Entity e) {
		this.entities.add(e);
		e.init(this);
	}
}
