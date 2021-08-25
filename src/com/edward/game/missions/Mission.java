package com.edward.game.missions;

import java.awt.Graphics;

import com.edward.game.Input;
import com.edward.game.Screen;

enum MissionState {
	PENDING, UNLOCKED, STARTING, IN_PROGRESS, FINISHING, COMPLETED, DONE, CANCELED
}

public class Mission {
	private MissionState state = MissionState.PENDING;

	private ICondition[] conditions;
	private ICondition[] preConditions;

	private Screen screen;

	public String name;
	public String completed;

	int transitionTime = 100;

	public Mission(Screen screen, String name, String completed, ICondition[] preConditions, ICondition[] conditions) {
		this.screen = screen;
		this.name = name;
		this.completed = completed;
		this.preConditions = preConditions;
		this.conditions = conditions;
	}

	public Mission(Screen screen) {
		this.screen = screen;
	}

	public void tick(long dt, Input input) {
		switch (state) {
		case PENDING:
			boolean preConditionsDone = checkPreConditions();

			if (preConditionsDone) {
				state = MissionState.UNLOCKED;
			}
			break;
		case UNLOCKED:
			state = MissionState.STARTING;

			break;
		case STARTING:
			//screen.toastManager.addToast("Mission Started:", completed);
			
			state = MissionState.IN_PROGRESS;
			
			for(ICondition condition : conditions) {
				condition.start(screen);
			}
		case IN_PROGRESS:
			boolean conditionsDone = checkConditions();

			if (conditionsDone) {
				state = MissionState.FINISHING;
			}
			break;
		case FINISHING:
			screen.toastManager.addToast("Mission Complete:", completed);
			
			state = MissionState.COMPLETED;
			
			for(ICondition condition : conditions) {
				condition.dispose(screen);
			}
		case COMPLETED:
			state = MissionState.DONE;
			break;
		case DONE:
			break;
		default:
			break;
		}
	}

	private boolean checkPreConditions() {
		boolean unlocked = true;

		for (int i = 0; i < preConditions.length; i++) {
			ICondition condition = preConditions[i];

			if (!condition.check(this, screen)) {
				unlocked = false;
			}
		}

		return unlocked;
	}

	private boolean checkConditions() {
		boolean unlocked = true;

		for (int i = 0; i < conditions.length; i++) {
			ICondition condition = conditions[i];

			if (!condition.check(this, screen)) {
				unlocked = false;
			}
		}

		return unlocked;
	}

	public void render(Graphics g) {

	}

	public MissionState getState() {
		return state;
	}
}
