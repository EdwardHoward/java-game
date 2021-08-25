package com.edward.game;

import java.awt.Point;

// To render objects in camera view: g.fillRect(x + offsetX, y + offsetY, w, h);
public class Camera {
	public double x = 0;
	public double y = 10;
	public double angle = 0;
	
	private double shakeIntensity = 0;
	
	public void tick() {
		if(shakeIntensity > 0) {
			shakeIntensity -= .01;
		} else if(shakeIntensity < 0) {
			shakeIntensity = 0;
		}
		
		double shake = Math.pow(shakeIntensity, 3);
		
		this.angle = 0.174533 * shake * (Math.random() * 3 - 1);
	}
	
	public Point getDrawPosition(int x2, int y2) {
		int xp = (int) (x2 + x);
		int yp = (int) (y2 + y);
		
		return new Point(xp, yp);
	}
	
	public Point getDrawPosition(double x2, double y2) {
		double shake = Math.pow(shakeIntensity, 3);
		
		double xpShake = 10 * shake * (Math.random() * 3 - 1);
		double ypShake = 10 * shake * (Math.random() * 3 - 1);

		int xp = (int) (x2 + x + xpShake);
		int yp = (int) (y2 + y + ypShake);
		
		
		return new Point(xp, yp);
	}
	
	public void shake(double amount) {
		shakeIntensity = amount;
	}
}
