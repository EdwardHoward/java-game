package com.edward.game.skills;

import com.edward.game.Input;
import com.edward.game.entities.Entity;

public class JumpSkill extends Skill {
	public JumpSkill(Entity entity) {
		super(entity);
		
		if(entity != null) {
			entity.screen.toastManager.addToast("Skill Unlocked!", "Jumping");			
		}
	}

	@Override
	public void tick(Input input) {
		if (input.isDownOnce(Input.JUMP) && entity.onGround) {
			boolean bigJump = Math.random() > 0.9;

			entity.yVelocity -= bigJump ? 30 : 13;

			double shake = bigJump ? .5 : 0;
			entity.screen.camera.shake(shake);

			entity.onGround = false;
		}
	}
}
