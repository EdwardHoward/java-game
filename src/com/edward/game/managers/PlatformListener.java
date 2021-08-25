package com.edward.game.managers;

import java.util.EventListener;

import com.edward.game.entities.Platform;

public interface PlatformListener extends EventListener {
	public void platformCreated(Platform platform);
}
