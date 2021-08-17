package com.edward.game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.edward.game.entities.Entity;

public class Screen {
	private Game game;
	private List<Entity> entities = new ArrayList<Entity>();
	
	public Screen(Game game) {
		this.game = game;
	}
	
	public void tick(Input input) {
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).tick(input);
		}
	}
	
	public void render(Graphics g) {
		entities.forEach((entity) -> {
			entity.render(g);
		});
	}
	
	public void addEntity(Entity e) {
		this.entities.add(e);
	}
}
