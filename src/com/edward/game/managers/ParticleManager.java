package com.edward.game.managers;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.edward.game.Input;
import com.edward.game.Screen;
import com.edward.game.entities.Particle;

public class ParticleManager {
	/*Particle[] particles = new Particle[100];*/
	List<Particle> particles = new ArrayList<Particle>();
	Screen screen;
	
	public ParticleManager(Screen screen) {
		this.screen = screen;
	}
	
	public void tick(long dt, Input input) {
		for(int i = 0; i < particles.size(); i++) {
			particles.get(i).tick(dt, input);
		}
	}

	public void render(Graphics g) {
		for(int i = 0; i < particles.size(); i++) {
			particles.get(i).render(g);
		}
	}
	
	public void emit(double x, double y, int amount) {
		for(int i = 0; i < amount; i++) {
			Particle particle = new Particle(x, y, 1, 1);
			particle.init(this.screen);
			particle.angle = Math.random() * (Math.PI * 2);
			particles.add(particle);
		}
	}
}
