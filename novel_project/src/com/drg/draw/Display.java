package com.drg.draw;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Display {
	
	private JFrame frame;
	private Canvas canvas;
	
	private String title;
	private int width, height;
	
	public Display(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.title = title;
		
		this.createDisplay();
	}

	private void createDisplay() {
		Dimension dim = new Dimension(width, height);
		
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(dim);
		canvas.setMaximumSize(dim);
		canvas.setMinimumSize(dim);
		
		frame.add(canvas);
		ImageIcon icon = new ImageIcon("resources/img/icon/icon.png");
		frame.setIconImage(icon.getImage());
		frame.pack();
		
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
}