package com.edward.game.missions;

import com.edward.game.Screen;

public interface ICondition {
	String type();
	boolean check(Mission mission, Screen screen);
	
	void start(Screen screen);
	void dispose(Screen screen);
}
