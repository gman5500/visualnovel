package com.drg.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class StateMenu implements State{
	
	int posx = 0;
	int posy = 100;
	Random rand = new Random();


	@Override
	public void update() {
		posx+=2;
		posy+=2;
		
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
		BufferedImage bg = null;
		try {
			bg = ImageIO.read(new File("resources/img/bg/menu.png"));
		} catch(IOException e ) {
			e.printStackTrace();
		}
		g.drawImage(bg, 0,0,null);
		
		g.drawString("rylan u are a fucking weeb", posx, posy);
		
	}

}
