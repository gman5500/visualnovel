package com.drg.state;

import java.awt.Graphics;

import com.drg.main.GamePanel;

public abstract class State {
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	

}
