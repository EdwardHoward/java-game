package com.edward.game.missions;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.edward.game.Input;
import com.edward.game.Screen;

public class MissionManager {
	private Mission currentMission;
	private Screen screen;

	private List<Mission> missions = new ArrayList<Mission>();
	private HashMap<String, Mission> missionsById = new HashMap<String, Mission>();

	public MissionManager(Screen screen) {
		this.screen = screen;

		addMission(new Mission(screen, "Platforms_Mission", "10 platforms!", new ICondition[] {}, new ICondition[] { new PlatformCondition(10) }));
		addMission(new Mission(screen, "Second_Mission", "40 platforms!", new ICondition[] { new MissionCompleteCondition("Platforms_Mission")}, new ICondition[] { new PlatformCondition(30)} ));
	}

	public void tick(long dt, Input input) {
		int size = missions.size();
		for (int i = 0; i < size; i++) {
			missions.get(i).tick(dt, input);
		}
		//		if(currentMission != null) {
		//			currentMission.tick(dt, input);			
		//		}
	}
	
	public void render(Graphics g) {
		int size = missions.size();
		for (int i = 0; i < size; i++) {
			missions.get(i).render(g);
		}
	}

	public void setCurrentMission() {
		currentMission = new Mission(this.screen);
	}

	public Mission getCurrentMission() {
		return currentMission;
	}

	public void addMission(Mission mission) {
		missions.add(mission);
		missionsById.put(mission.name, mission);
	}

	public boolean isComplete(String name) {
		Mission m = missionsById.get(name);

		if (m != null) {
			if (m.getState() == MissionState.DONE) {
				return true;
			}
		}

		return false;
	}
}
