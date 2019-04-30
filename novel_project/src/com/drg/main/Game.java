package com.drg.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.drg.draw.Display;
import com.drg.state.State;
import com.drg.state.StateManager;
import com.drg.state.StateMenu;

public class Game implements Runnable{
	
	public static int width = 1400;
	public static int height = 900;
	public String title = "My New Life In The Dungeon With My Sisters";
	final double FPS = 60.0;
	public boolean running = false;
	
	private Graphics g;
	private BufferStrategy bs;
	
	private Thread thread;
	private Display display;
	
	StateManager sManager;
	State sMenu;
	
	private void init() {
		display = new Display(width, height, title);
		sManager = new StateManager();
		
		sMenu = new StateMenu();
		sManager.setState(sMenu);
	}
	
	private void update() {
		if(sManager.getState() != null) {
			sManager.getState().update();
		}
	}
	
	private void render(Graphics g) {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		
		if(sManager.getState() != null) {
			sManager.getState().render(g);
		}
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
		g.drawString("FPS: " + FPS, 4, 12);
		
		bs.show();
		g.dispose();
	}
	
	public synchronized void start() {
		if(running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
		System.out.println("Thread successfully started.");
	}
	
	public synchronized void stop() {
		if(!running) return;
		running = false;
		try {
			thread.join();
			System.out.println("Thread successfully stopped.");
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		this.init();
		
		long lastTime = System.nanoTime();
		double ns = 1000000000/FPS;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime = now;
			while(delta >= 1) {
				update();
				delta--;
			}
			render(g);
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer+=1000;
				frames = 0;
			}
		}
		stop();
	}

}
