package com.drg.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.drg.handlers.ResourceHandler;
import com.drg.util.Line;

public class StateNarrate extends State{
	
	private boolean init = false;
	ArrayList<Line> currentScene;
	int iterator = 0;

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
		g.drawString(currentName(iterator), 300, 718);
		drawLine(currentLine(iterator), g);
	}
	
	private void drawLine(String currentLine, Graphics g) {
		if(currentLine.length() <= 105) {
			g.drawString(currentLine(iterator), 250, 780);
		} else {
			ArrayList<String> paragraph = new ArrayList<String>();
			String[] words = currentLine.split("\\s+");
			int i = 0;
			while(i <= words.length-1) {
				String line="";
				while(line.length() < 105 && i<=words.length-1) {
					line+=words[i] + " ";
					if(i<=words.length-1) {
						i++;
					}
				}
				paragraph.add(line);
				line="";
			}
			
			for(int j = 0; j < paragraph.size(); j++) {
				g.drawString(paragraph.get(j), 250, 780+j*20);
			}
		}
	}

	@Override
	public void keyEventSpace() {
		iterator++;
		
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
	

}
