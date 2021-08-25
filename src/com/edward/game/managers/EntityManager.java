package com.edward.game.managers;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.edward.game.Input;
import com.edward.game.Screen;
import com.edward.game.entities.Entity;
import com.edward.game.entities.Platform;

public class EntityManager {
	private Screen _screen;
	private List<Entity> entities = new ArrayList<Entity>();
	
	public EntityManager(Screen screen) {
		this._screen = screen;
	}
	
	public void tick(long dt, Input input) {
		
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			entity.render(g);
		}
	}
}
