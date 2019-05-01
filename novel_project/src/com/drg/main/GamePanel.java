package com.drg.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.drg.handlers.ResourceHandler;
import com.drg.handlers.GameStateManager;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener{

	public int width = 1400;
	public int height = 900;
	
	final int FPS = 60;
	public boolean running = false;
	
	private Graphics g;
	private BufferedImage image;
	
	private Thread thread;
	
	GameStateManager gsm;
	
	public GamePanel() {
		super();
		setPreferredSize(new Dimension(width, height));
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}
	
	private void init() {
		ResourceHandler.load();
		gsm = new GameStateManager();
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		g = (Graphics2D) image.getGraphics();
		running = true;
	}
	
	private void update() {
		gsm.update();
	}
	
	private void render() {
		gsm.render(g);
	}
	
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, width, height, null);
		g2.dispose();
	}
	
	@Override
	public void run() {
		init();
		
		long start;
		long elapsed;
		long wait;
		long targetTime = 1000/FPS;
		while(running) {
			
			start = System.nanoTime();
			
			update();
			render();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			
			wait = targetTime - elapsed / 1000000;
			if(wait < 0) wait = 5;
			
			try {
				Thread.sleep(wait);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
			gsm.keyEventSpace();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			gsm.keyEventBackSpace();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
