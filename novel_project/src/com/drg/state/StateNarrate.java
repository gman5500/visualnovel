package com.drg.state;

import java.awt.Graphics;

import com.drg.handlers.ResourceHandler;

public class StateNarrate extends State{

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(ResourceHandler.getBackground("menu"), 0,0,null);
		g.drawImage(ResourceHandler.getUI("tbox"), 75, 690, null);
		
	}

}
