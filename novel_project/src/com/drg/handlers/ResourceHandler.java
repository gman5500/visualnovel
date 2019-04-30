package com.drg.handlers;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class ResourceHandler {
	
	private static Map<String, Image> bg = new HashMap<String, Image>();
	private static Map<String, Image> ui = new HashMap<String, Image>();
	private static Image icon = getImage("icon/icon.png");
	
	public static void load() {
		//backgrounds
		bg.put("menu", getImage("bg/menu.png"));
		
		//ui
		ui.put("tbox", getImage("ui/textBox.png"));
	}
	
	public static Image getBackground(String key) {
		return bg.get(key);
	}
	
	public static Image getUI(String key) {
		return ui.get(key);
	}
	
	public static Image getIcon() {
		return icon;
	}
	
	private static Image getImage(String path) {
		try {
		Image i = ImageIO.read(new File("resources/img/" + path));
		System.out.println("Successfully loaded /resources/img/"+path);
		return i;
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
