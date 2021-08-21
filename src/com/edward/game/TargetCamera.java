package com.edward.game;

import com.edward.game.entities.Entity;

public class TargetCamera extends Camera {
	private Entity target;
	double speed = .1;
	
	public void tick() {
		// Move camera towards target 
		double targetY = -(this.target.y - 450 + 250);

		double distance = targetY - y;
		
		if (distance > 1) {
			y = lerp(y, targetY, speed);
		}
	}
	
	// Camera points at target
	public void setTarget(Entity target) {
		this.target = target;
	}
	
	public double lerp(double a, double b, double t) {
		return a + t * (b - a);
	}
}
