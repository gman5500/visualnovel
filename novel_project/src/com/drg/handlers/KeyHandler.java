package com.drg.handlers;

import java.awt.event.KeyEvent;

public class KeyHandler {
	
	public static final int NUM_KEYS = 16;
	
	public static boolean[] keyState = new boolean[NUM_KEYS];
	public static boolean[] prevKeyState = new boolean[NUM_KEYS];
	
	public static int FORWARD = 0;
	public static int BACKWARD = 1;
	public static int HIDE = 2;
	public static int FASTFORWARD = 3;
	
	public static void keySet(int i, boolean b) {
		if(i == KeyEvent.VK_ENTER || i == KeyEvent.VK_SPACE) keyState[FORWARD] = b;
		else if(i == KeyEvent.VK_BACK_SPACE) keyState[BACKWARD] = b;
		else if(i == KeyEvent.VK_ESCAPE) keyState[HIDE] = b;
		else if(i == KeyEvent.VK_CONTROL) keyState[FASTFORWARD] = b;
	}
	
	public static void update() {
		for(int i = 0; i < NUM_KEYS; i++) {
			prevKeyState[i] = keyState[i];
		}
	}
	
	public static boolean isPressed(int i) {
		return keyState[i] && !prevKeyState[i];
	}
	
	public static boolean anyKeyPress() {
		for(int i = 0; i < NUM_KEYS; i++) {
			if(keyState[i]) return true;
		}
		return false;
	}

}
