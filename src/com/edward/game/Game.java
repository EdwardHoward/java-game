package com.edward.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.edward.game.entities.Player;

public class Game extends Canvas implements Runnable, KeyListener{
	private static final long serialVersionUID = 1L;
	
	boolean running = false;
	
	Screen screen;
	Input input = new Input();
	
	public Game() {
		this.setPreferredSize(new Dimension(800, 600));
		this.addKeyListener(this);
	}
	
	public static void main(String[] args) { 
		JFrame frame = new JFrame();
		frame.setResizable(false);
		frame.setSize(new Dimension(800, 600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		Game g = new Game();
	
		frame.add(g);
		g.start();
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		long unprocessedTime = 0;

		while(running) {
			long now = System.nanoTime();
			unprocessedTime += now - lastTime;
			lastTime = now;
			
			while(unprocessedTime > 0) {
				unprocessedTime -= 1000000000 / 60;
				tick();
			}
			
			render();
		}
	}
	
	public void tick() {
		this.screen.tick(this.input);
		this.input.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 600);
		
		this.screen.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public void start() {
		this.screen = new Screen(this);
		this.screen.addEntity(new Player(10, 400, 30, 50));
		
		this.running = true;
		new Thread(this).run();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		input.setKey(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		input.setKey(e.getKeyCode(), false);
	}
}
