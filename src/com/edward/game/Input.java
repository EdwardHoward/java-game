package com.edward.game;

public class Input {
	public boolean[] buttons = new boolean[64];
	public boolean[] oldButtons = new boolean[64];
	
	public void tick() {
		for(int i = 0; i < buttons.length; i++) {
			this.oldButtons[i] = this.buttons[i];
		}
	}
}
