package com.drg.util;

public class Line {
	
	private String name;
	private String line;
	
	public Line(String name, String line) {
		this.name = name;
		this.line = line;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLine() {
		return line;
	}
	
	public void printLine() {
		System.out.println(name+"\n"+line);
	}

}
