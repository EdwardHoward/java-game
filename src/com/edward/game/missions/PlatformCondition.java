package com.edward.game.missions;

import com.edward.game.Screen;
import com.edward.game.entities.Platform;
import com.edward.game.managers.PlatformListener;

public class PlatformCondition implements ICondition, PlatformListener {
	private int count = 0;
	private int platformCount = 0;

	public PlatformCondition(int count) {
		this.count = count;
	}

	@Override
	public String type() {
		return "platform_count";
	}

	@Override
	public void start(Screen screen) {
		screen.events.addListener(this);
	}

	@Override
	public void dispose(Screen screen) {
		screen.events.removeListener(this);
	}

	@Override
	public boolean check(Mission mission, Screen screen) {
		if (mission.getState() == MissionState.IN_PROGRESS) {
			if (platformCount >= count) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void platformCreated(Platform platform) {
		this.platformCount++;
	}
}
