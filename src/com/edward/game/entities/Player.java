package com.edward.game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.edward.game.Input;
import com.edward.game.Screen;
import com.edward.game.skills.DoubleJumpSkill;
import com.edward.game.skills.JumpSkill;
import com.edward.game.skills.Skill;

enum PlayerState {
	IDLE, WALKING, RUNNING, JUMPING, FLOATING
}

public class Player extends Entity {
	
	// almost an ECS
	List<Skill> skills = new ArrayList<Skill>();
	
	public Player(double x, double y, int w, int h) {
		super(x, y, w, h);

		speed = 5;
	}
	
	@Override
	public void init(Screen screen) {
		super.init(screen);
		
		//skills.add(new DoubleJumpSkill(this));
		skills.add(new JumpSkill(this));
	}

	@Override
	public void tick(long dt, Input input) {
		if (input.isDown(Input.LEFT)) {
			this.xVelocity = -this.speed;
		}

		if (input.isDown(Input.RIGHT)) {
			this.xVelocity = this.speed;
		}
		
		for(Skill skill : skills) {
			skill.tick(input);
		}

		if (input.isMouseDownOnce()) {
			Point mouse = input.getMousePosition();

			double[] center = getCenter();

			Point playerPos = screen.camera.getDrawPosition(center[0], center[1]);

			double angle = Math.atan2(mouse.y - playerPos.y, mouse.x - playerPos.x);
			screen.bulletManager.addBullet(center[0], center[1], angle);
		}

		tryMove();

		this.xVelocity *= Screen.FRICTION;

		if(input.isDown(Input.FLOAT) && this.yVelocity > 0) { 
			this.yVelocity += .1;
		} else {
			this.yVelocity += Screen.GRAVITY;			
		}

		// Player goes below the bottom of screen
		if (y + this.screen.camera.y >= 595) {
			// End game
			this.screen.newGame();
		}
	}

	@Override
	public void render(Graphics g) {
		Point point = this.screen.camera.getDrawPosition(x, y);

		g.setColor(Color.GREEN);
		g.fillRect(point.x, point.y, width, height);
	}
}
