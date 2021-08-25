package com.edward.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener, MouseMotionListener{
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	boolean running = false;
	
	Screen screen;
	Input input = new Input();
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	public static void main(String[] args) { 
		JFrame frame = new JFrame();
		frame.setResizable(false);
		frame.setSize(new Dimension(WIDTH, HEIGHT));
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
				tick(1);
				unprocessedTime -= 1000000000 / 60;
			}
			
			render();
		}
	}
	
	public void tick(long dt) {
		this.screen.tick(dt, this.input);
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
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		this.screen.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public void start() {
		this.screen = new Screen(this);
		this.screen.newGame();
		
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

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		input.setMousePosition(e.getX(), e.getY());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		input.setMouseDown(true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		input.setMouseDown(false);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
