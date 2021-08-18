package com.edward.game;

import java.awt.event.KeyEvent;

public class Input {
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int JUMP = 2;
	
	private boolean[] buttons = new boolean[3];
	private boolean[] oldButtons = new boolean[3];
	
	public void tick() {
		for(int i = 0; i < buttons.length; i++) {
			this.oldButtons[i] = this.buttons[i];
		}
	}
	
	public void setKey(int keyCode, boolean down) {
		try {
			if(keyCode == KeyEvent.VK_LEFT) buttons[LEFT] = down;
			if(keyCode == KeyEvent.VK_A) buttons[LEFT] = down;
			
			if(keyCode == KeyEvent.VK_RIGHT) buttons[RIGHT] = down;
			if(keyCode == KeyEvent.VK_D) buttons[RIGHT] = down;
			
			if(keyCode == KeyEvent.VK_SPACE) buttons[JUMP] = down;
		} catch (Exception ex) {
			System.out.println("Key not supported");
		}
	}
	
	public boolean isDown(int key) {
		return buttons[key];
	}
	
	public boolean isDownOnce(int key) {
		return buttons[key] && !oldButtons[key];
	}
}
