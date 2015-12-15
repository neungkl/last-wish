package frame.logic;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

public class HighScore {
	
	public static final HighScore instance = new HighScore();
	
	public static int prevLevelData;
	public static int prevTimeData;
	
	class Stat implements Comparable<Stat> {
		private int time, level;
		private String name;
		
		public Stat(int time, int level, String name) {
			this.time = time;
			this.level = level;
			this.name = name;
		}
		
		public int getTime() { return time; }
		public int getLevel() { return level; }
		public String getName() { return name; }
		
		@Override
		public int compareTo(Stat o) {
			if(level != o.level) {
				return o.level - level;
			}
			return time - o.time;
		}
	}
	
	private ArrayList<Stat> highScore;
	
	private HighScore() {
		
		prevLevelData = prevTimeData = 0;
		
		highScore = new ArrayList<>();
		
		String[] name = {
			"apple",
			"banana",
			"orange",
			"mango",
			"cat",
			"dog",
			"fish",
			"dolphin",
			"shark",
			"fox"
		};
		
		for(int i=1; i<=10; i++) {
			highScore.add(new Stat(120 * i, i * 2, name[i-1]));
		}
		
		Collections.sort(highScore);
		
	}
	
	public void add(String name) {
		highScore.add(new Stat(prevTimeData, prevLevelData, name));
		Collections.sort(highScore);
	}
	
	public void showDialog() {
		JOptionPane.showMessageDialog(null, toString(), "High score", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private String addSpace(String text, int width) {
		for(int i=text.length(); i<=width; i++) {
			text += " ";
		}
		return text;
	}
	
	public String toString() {
		String txt = "";
		int w = 15;
		txt += addSpace("No.", w)+addSpace("Level", w)+addSpace("Time",w)+addSpace("Name", w)+"\n";
		for(int i=0; i<10; i++) {
			Stat s = highScore.get(i);
			txt += addSpace((i+1)+"", w)+addSpace(s.getLevel()+"", w)+addSpace(s.getTime()+"", w)+addSpace(s.getName(), w)+"\n";
		}
		return txt;
	}
}
