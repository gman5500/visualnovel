package com.drg.state;

import java.awt.Graphics;

public abstract class State {
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	
	public abstract void keyEventSpace();
	
	public abstract void init();
	

}
