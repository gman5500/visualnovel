package com.drg.handlers;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.drg.util.Line;

public class ResourceHandler {
	
	/*******************************************************************************************
	 *                                                                                         *
	 *                                         MISC                                            * 
	 *                                                                                         * 
	 ********************************************************************************************/
	
	@SuppressWarnings("unused")
	private static void loadFonts() {
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/AronGrotesque-light.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/AronGrotesque-regular.ttf")));
		} catch(IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}
	}
	
	/*******************************************************************************************
	 *                                                                                         *
	 *                                       IMAGES                                            * 
	 *                                                                                         * 
	 ********************************************************************************************/
	 
	
	private static Map<String, BufferedImage> bg = new HashMap<String, BufferedImage>();
	private static Map<String, BufferedImage> ui = new HashMap<String, BufferedImage>();
	private static Image icon = loadImage("icon/icon.png");
	
	public static void load() {
		//backgrounds
		bg.put("menu", loadImage("bg/menu.png"));
		
		//ui
		ui.put("tbox", loadImage("ui/textBox.png"));
		
		//text
		story.put("scene_1", getStory(loadText("scene_1/scene_1.txt")));
		
		//loadFonts();
	}
	
	public static BufferedImage getBackground(String key) {
		return bg.get(key);
	}
	
	public static BufferedImage getUI(String key) {
		return ui.get(key);
	}
	
	public static Image getIcon() {
		return icon;
	}
	
	private static BufferedImage loadImage(String path) {
		try {
		BufferedImage i = ImageIO.read(new File("resources/img/" + path));
		System.out.println("Successfully loaded /resources/img/"+path);
		return i;
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/*******************************************************************************************
	 *                                                                                         *
	 *                                         TEXT                                            * 
	 *                                                                                         * 
	 ********************************************************************************************/
	
	private static Map<String, ArrayList<Line>> story = new HashMap<String, ArrayList<Line>>();
	
	private static ArrayList<String> loadText(String path) {
		ArrayList<String> text = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("resources/story/" + path));
			System.out.println("Successfully loaded /resources/story/"+path);
			String line = br.readLine();

			while(line != null) {
				text.add(line);
				line = br.readLine();
			}
			
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return text;
		
	}
	
	private static ArrayList<String> generateLines(String currentLine) {
		ArrayList<String> paragraph = new ArrayList<String>();
		if(currentLine.length() <= 105) {
			paragraph.add(currentLine);
		} else {
			String[] words = currentLine.split("\\s+");
			int i = 0;
			while(i <= words.length-1) {
				String line="";
				while(i<=words.length-1 && line.length() + words[i].length() < 105) {
					line+=words[i] + " ";
					if(i<=words.length-1) {
						i++;
					}
				}
				paragraph.add(line);
				line="";
			}
		}
		
		return paragraph;
	}
	
	private static ArrayList<Line> getStory(ArrayList<String> text) {
		ArrayList<Line> lines = new ArrayList<Line>();
		for(int i = 0; i < text.size(); i+=3) {
			ArrayList<String> genLines = generateLines(text.get(i+1));
			for(String line : genLines) {
				lines.add(new Line(text.get(i),line));
			}
		}
		
		return lines;
	}
	
	public static ArrayList<Line> getScene(String key) {
		return story.get(key);
	}

}
