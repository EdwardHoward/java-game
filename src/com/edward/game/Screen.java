package com.edward.game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import com.edward.cameras.TargetCamera;
import com.edward.game.entities.Entity;
import com.edward.game.entities.Player;
import com.edward.game.managers.BulletManager;
import com.edward.game.managers.EventManager;
import com.edward.game.managers.ParticleManager;
import com.edward.game.managers.PlatformManager;
import com.edward.game.managers.ToastManager;
import com.edward.game.missions.MissionManager;

public class Screen {
	public static double GRAVITY = 0.5;
	public static double FRICTION = 0.7;
	
	private Game game;
	private List<Entity> entities = new ArrayList<Entity>();
	public PlatformManager platformManager = new PlatformManager(this);
	public BulletManager bulletManager = new BulletManager(this);
	public ParticleManager particleManager = new ParticleManager(this);
	
	public MissionManager missionManager = new MissionManager(this);
	public ToastManager toastManager = new ToastManager(this);
	
	public TargetCamera camera = new TargetCamera();
	
	public EventManager events = new EventManager();
	
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
		for(int i = 1; i < 6; i++) { 
			int x = (int)(Math.random() * (Game.WIDTH / 2));
			int y = Game.HEIGHT - 100 - (platformSpacing * i);
			int width = 150;
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
		bulletManager.tick(dt, input);
		particleManager.tick(dt, input);
		missionManager.tick(dt, input);
		toastManager.tick(dt, input);
		
		camera.tick();
	}
	
	public void render(Graphics gg) {
		
		// Rotate screen for camera
		Graphics2D g = (Graphics2D) gg;	
		AffineTransform old = g.getTransform();
		g.rotate(camera.angle, Game.WIDTH / 2, Game.HEIGHT / 2);
		
		platformManager.render(g);

		entities.forEach((entity) -> {
			entity.render(g);
		});
		
		bulletManager.render(g);
		
		particleManager.render(g);
		
		missionManager.render(g);
		
		toastManager.render(g);
		
		g.setTransform(old);
	}
	
	public void addEntity(Entity e) {
		this.entities.add(e);
		e.init(this);
	}
}
