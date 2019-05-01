package com.drg.handlers;

import java.awt.Graphics;
import java.util.Stack;

import com.drg.state.State;
import com.drg.state.StateMenu;
import com.drg.state.StateNarrate;

public class GameStateManager {
	
	private Stack<State> stack = new Stack<State>();
	
	//states
	State menu;
	State narrate;
	
	public GameStateManager() {
		menu = new StateMenu();
		narrate = new StateNarrate();
		
		setState(narrate);
	}
	
	public void setState(State s) {
		stack.push(s);
	}
	
	public State getState() {return stack.peek();}
	
	public void popState() {
		stack.pop();
	}
	
	public void render(Graphics g) {
		if(getState() != null) {
			getState().render(g);
		}
	}
	
	public void update() {
		if(getState() != null) {
			getState().update();
		}
	}
	
	public void keyEventSpace() {
		if(getState() != null) {
			getState().keyEventSpace();
		}
	}
	
	public void keyEventBackSpace() {
		if(getState() != null) {
			getState().keyEventBackSpace();
		}
	}

}
