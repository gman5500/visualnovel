package com.drg.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Stack;

import com.drg.handlers.ResourceHandler;
import com.drg.util.Line;

public class StateNarrate extends State{
	
	private boolean init = false;
	ArrayList<Line> currentScene;
	int iterator = 0;
	int i = 0;
	Stack<Integer> previousI = new Stack<Integer>();

	@Override
	public void update() {
		if(!init) {
			init();
			init = true;
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(ResourceHandler.getBackground("menu"), 0,0,null);
		createTextBox(g);
		
	}
	
	private void createTextBox(Graphics g) {
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
	public void keyEventSpace() {
		iterator+=i;
		previousI.push(i);
		i=0;
		
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

	@Override
	public void keyEventBackSpace() {
		iterator-=previousI.peek();
		previousI.pop();
		
	}
	

}
