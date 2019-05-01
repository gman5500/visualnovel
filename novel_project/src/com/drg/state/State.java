package com.drg.state;

import java.awt.Graphics2D;

public abstract class State {
	
	public abstract void update();
	public abstract void render(Graphics2D g);
	public abstract void init();
	
	public abstract void handleInput();
	

}
