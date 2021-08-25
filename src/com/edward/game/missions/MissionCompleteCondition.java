package com.edward.game.missions;

import com.edward.game.Screen;

public class MissionCompleteCondition implements ICondition {
	
	String name;
	
	public MissionCompleteCondition(String name) {
		this.name = name;
	}
	
	@Override
	public void start(Screen screen) {
		
	}
	
	@Override
	public void dispose(Screen screen) {
		
	}
	
	@Override
	public String type() {
		return "MISSION_COMPLETE";
	}

	@Override
	public boolean check(Mission mission, Screen screen) {
		return screen.missionManager.isComplete(name);
	}
}
