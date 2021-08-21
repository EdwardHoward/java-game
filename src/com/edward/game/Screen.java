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
	public TargetCamera camera = new TargetCamera();
	
	public Screen(Game game) {
		this.game = game;
		
		Entity player = new Player((Game.WIDTH / 2) - 15, 450, 30, 30);
		this.addEntity(player);
		
		this.camera.setTarget(player);
	}
	
	public void newGame() {
		entities.clear();

		Entity player = new Player((Game.WIDTH / 2) - 15, 450, 30, 30);
		this.addEntity(player);
		
		platformManager.clear();
		
		// Set camera back down to bottom
		this.camera.y = 10;
		
		// Create the first floor
		platformManager.createPlatform(0, Game.HEIGHT - 100, Game.WIDTH, 40);

		// Create 6 platforms equally spaced
		int platformSpacing = 120;
		for(int i = 0; i < 6; i++) { 
			int x = (int)(Math.random() * (Game.WIDTH / 2));
			int y = Game.HEIGHT - 100 - (platformSpacing * i);
			int width = 200;
			int height = 40;
			
			platformManager.createPlatform(x, y, width, height); 
		}
		
		// point the camera at the player
		this.camera.setTarget(player);
	}
	
	public void tick(long dt, Input input) {
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).tick(dt, input);
		}
		
		platformManager.tick(dt, input);
		
		camera.tick();
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
