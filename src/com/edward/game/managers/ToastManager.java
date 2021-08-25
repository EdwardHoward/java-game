package com.edward.game.managers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Queue;

import com.edward.game.Game;
import com.edward.game.Input;
import com.edward.game.Screen;
import com.edward.game.entities.Toast;

public class ToastManager {
	private Queue<Toast> toasts = new LinkedList<Toast>();
	private int showTime = 200;
	Font font = new Font ("Courier New", 1, 24 * 2);
	Font outlineFont = new Font ("Courier New", 1, 24 * 2 + 2);

	public ToastManager(Screen screen) {
		
	}
	
	public void tick(long dt, Input input) {
		
		Toast toast = this.toasts.peek();
		
		if(toast != null) {
			toast.duration--;
			
			if(toast.duration <= 0) {
				this.toasts.poll();
			}
		}
	}
	
	public void render(Graphics g) {
		Toast toast = toasts.peek();
		
		if(toast != null) {
			// outline
			g.setColor(Color.BLACK);
			g.setFont(outlineFont);
			
			int outlineWidth = g.getFontMetrics().stringWidth(toast.firstLine);
			int outlineWidth2 = g.getFontMetrics().stringWidth(toast.secondLine);
			
			g.drawString(toast.firstLine, Game.WIDTH / 2 - (outlineWidth / 2) - 1, Game.HEIGHT / 2 - 100);
			g.drawString(toast.secondLine, Game.WIDTH / 2 - (outlineWidth2 / 2) - 4, Game.HEIGHT / 2 + 1);

			// text
			g.setColor(Color.WHITE);
			g.setFont(font);
			
			int width = g.getFontMetrics().stringWidth(toast.firstLine);
			int width2 = g.getFontMetrics().stringWidth(toast.secondLine);
			
			g.drawString(toast.firstLine, Game.WIDTH / 2 - (width / 2), Game.HEIGHT / 2 - 100);
			g.drawString(toast.secondLine, Game.WIDTH / 2 - (width2 / 2), Game.HEIGHT / 2);
		}
	}
	
	public void addToast(String firstLine, String secondLine) {
		Toast toast = new Toast(firstLine, secondLine);
		if(showTime == 0) {
			showTime = toast.getDuration();
		}
		this.toasts.add(toast);
	}
}
