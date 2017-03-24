package main;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music {

	public static Clip testsound = loadClip("testaudio.wav");
	public static Clip debate_music = loadClip("ace_attorney.wav");
	public static Clip mii_theme = loadClip("mii_theme.wav");
	public static Clip rainbow_road = loadClip("rainbow_road.wav");
	public static Clip route_209 = loadClip("Route209.wav");
	public static Clip map_music = loadClip("GameOfThrones.wav");
	public static Clip winter_winds = loadClip("winter_winds.wav");
	public static Clip true_sorry = loadClip("true_sorry.wav");
	public static Clip godfather = loadClip("god.wav");
	
			

	private static Clip loadClip(String filename) {
		filename = "music/"+filename;
		try {
			AudioInputStream ad = AudioSystem.getAudioInputStream(
					new File(filename).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(ad);
			return clip;
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			System.out.println("UNSUPPORTED AUDIO");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
