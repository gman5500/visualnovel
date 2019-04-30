package com.drg.state;

import java.util.Stack;

public class StateManager {
	
	private Stack<State> stack = new Stack<State>();
	
	public void setState(State s) {
		stack.push(s);
	}
	
	public State getState() {return stack.peek();}
	
	public void popState() {
		stack.pop();
	}

}
