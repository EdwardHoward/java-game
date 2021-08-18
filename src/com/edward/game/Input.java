package com.edward.game;

import java.awt.event.KeyEvent;

public class Input {
	public static int LEFT = 0;
	public static int RIGHT = 1;
	public static int JUMP = 2;
	
	public boolean[] buttons = new boolean[3];
	public boolean[] oldButtons = new boolean[3];
	
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
}
