package com.edward.game.entities;

public class Toast {
	public String firstLine;	
	public String secondLine;
	public int duration = 100;
	
	public Toast(String firstLine, String secondLine) {
		this.firstLine = firstLine;
		this.secondLine = secondLine;
	}
	
	public int getDuration() {
		return duration;
	}
}
