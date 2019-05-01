package com.drg.main;

import javax.swing.JFrame;

import com.drg.handlers.ResourceHandler;

public class Main {
	
	public static String title = "My New Life In The Dungeon With My Sisters";
	
	public static void main(String[] args) {
		JFrame window = new JFrame(title);
		window.add(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setIconImage(ResourceHandler.getIcon());
		window.setVisible(true);
	}
	
	

}


