package com.edward.game.skills;

import com.edward.game.Input;
import com.edward.game.entities.Entity;

public class DoubleJumpSkill extends Skill {
	private boolean doubleJumping = false;

	public DoubleJumpSkill(Entity entity) {
		super(entity);

		entity.screen.toastManager.addToast("Skill Unlocked!", "Double Jump");
	}

	@Override
	public void tick(Input input) {
		if (entity.onGround) {
			doubleJumping = false;
		}

		if (input.isDownOnce(Input.JUMP) && !entity.onGround && !doubleJumping) {
			boolean bigJump = Math.random() > 0.9;

			entity.yVelocity -= bigJump ? 30 : 13;

			double shake = bigJump ? .5 : 0;
			entity.screen.camera.shake(shake);

			doubleJumping = true;
		}
	}
}
