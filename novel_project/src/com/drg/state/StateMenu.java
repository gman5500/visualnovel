package com.drg.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Random;


import com.drg.handlers.ResourceHandler;

public class StateMenu extends State{
	
	int posx = 0;
	int posy = 100;
	Random rand = new Random();


	@Override
	public void update() {
		posx+=2;
		posy+=2;
		
	}

	@Override
	public void render(Graphics2D g) {
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
		g.drawImage(ResourceHandler.getBackground("menu"), 0,0,null);
		g.drawString("rylan u are a fucking weeb", posx, posy);
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleInput() {
		// TODO Auto-generated method stub
		
	}


}
