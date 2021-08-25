package com.edward.game.managers;

import java.util.ArrayList;
import java.util.List;

import com.edward.game.entities.Platform;

public class EventManager {
	private List<PlatformListener> listeners = new ArrayList<PlatformListener>();
	
	public void addListener(PlatformListener event) {
		listeners.add(event);
	}
	
	public void removeListener(PlatformListener event) {
		listeners.remove(event);
	}
	
	public void platformCreated(Platform platform) {
		for(PlatformListener event : listeners) {
			event.platformCreated(platform);
		}
	}
}
