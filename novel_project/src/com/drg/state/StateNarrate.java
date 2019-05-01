package com.drg.state;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Stack;

import com.drg.handlers.KeyHandler;
import com.drg.handlers.ResourceHandler;
import com.drg.util.Line;

public class StateNarrate extends State{
	
	private boolean init = false;
	private boolean fade = true;
	private float alpha = 1f;
	private boolean textboxEnabled = true;
	ArrayList<Line> currentScene;
	private int iterator = 0;
	private int i = 0;
	Stack<Integer> previousI = new Stack<Integer>();

	@Override
	public void update() {
		if(!init) {
			init();
			init = true;
		}
		handleInput();
	}

	@Override
	public void render(Graphics2D g) {
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		g.drawImage(ResourceHandler.getBackground("menu"), 0,0,null);
		if(textboxEnabled) {
			createTextBox(g);
		}
		
	}
	
	private void createTextBox(Graphics2D g) {
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g.drawImage(ResourceHandler.getUI("tbox"), 75, 690, null);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.PLAIN, 20));
		String name = currentName(iterator);
		int internal = 0;
		g.drawString(name, 300, 718);
		while(internal < 6 && internal+iterator < currentScene.size() && name.equals(currentName(iterator + internal))) {
			g.drawString(currentLine(iterator+internal), 250, 770+internal*20);
			internal++;
			i = internal;
		}
	}
    
	@Override  
	public void init() {
		currentScene = ResourceHandler.getScene("scene_1");
	}
	
	private String currentLine(int iterator) {
		return currentScene.get(iterator).getLine();
	}
	
	private String currentName(int iterator) {
		return currentScene.get(iterator).getName();
	}

	public void keyEventEscape() {
		textboxEnabled = !textboxEnabled;
		
	}

	@Override
	public void handleInput() {
		
		if(KeyHandler.isPressed(KeyHandler.HIDE)) {
			fade = !fade;
		}
		
		if(KeyHandler.isPressed(KeyHandler.FORWARD)) {
			if(alpha == 1) {
				if(iterator+i < currentScene.size()) {
					iterator+=i;
					previousI.push(i);
					i=0;
				}
			}
		}
		
		if(KeyHandler.isPressed(KeyHandler.BACKWARD)) {
			if(alpha == 1) {
				if(iterator - previousI.peek() >= 0) {
					iterator-=previousI.peek();
					if(iterator - previousI.peek() > 0) {
						previousI.pop();
					}
				}
			}
		}
		
		if(fade) {
			if(alpha < 0.98f) {
				alpha+=0.03f;
			} else {
				alpha = 1f;
			}
		} else {
			if(alpha > 0.03f) {
				alpha-=0.03f;
			} else {
				alpha = 0f;
			}
		}
		
	}
	

}
