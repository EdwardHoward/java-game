package com.edward.cameras;

import com.edward.game.Camera;
import com.edward.game.entities.Entity;

public class TargetCamera extends Camera {
	private Entity _target;
	private double _speed = .1;
	
	public void tick() {
		super.tick();
		
		// Move camera towards target 
		double targetY = -(_target.y - 450 + 250);

		double distance = targetY - y;
		
		if (distance > 1) {
			y += distance * _speed;
		}
	}
	
	// Camera points at target
	public void setTarget(Entity target) {
		_target = target;
	}
	
	public void setSpeed(int speed) {
		_speed = speed;
	}
}
