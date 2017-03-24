package rooms;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Game;
import main.Music;

public class F2CommonRoom extends Room{


	BufferedImage window_day;
	BufferedImage window_night;//
	
	BufferedImage window;
	BufferedImage lamp;
	BufferedImage lightmap;
	BufferedImage no_rgb;
	BufferedImage normal;
	
	BufferedImage window_gradient;
	GradientPaint gp;
	
	AlphaComposite ac;
	private int time;
	float c= 0;
	
	int clickcount = 0;
	
	public F2CommonRoom(Game game, String name) {
		super(game, name);
		init();
		setMusic(Music.godfather);
	}

	public void init() {
		background = loadImage("test_room_night.png");
		lightmap= loadImage("test_room_lightmap.png");
		window = loadImage("test_room_windows.png");
		no_rgb = loadImage("test_room_no_RGB.png");
		lamp = loadImage("test_room_lamp.png");
		gp = new GradientPaint(new Point(20,20), Color.red, new Point (200,200), Color.blue);
				
		window_day = new BufferedImage(400, 200, BufferedImage.TYPE_INT_RGB);
		window_night = new BufferedImage(400, 200, BufferedImage.TYPE_INT_RGB);
		Graphics2D gday = window_day.createGraphics();
		Graphics2D gnight = window_night.createGraphics();
		gday.setPaint(new Color(140, 120, 0));
		gnight.setPaint(new Color (30, 0, 100));
		gday.fillRect(0, 0, window_day.getWidth(),window_day.getHeight());
		gnight.fillRect(0, 0, window_night.getWidth(), window_night.getHeight());
		gday.dispose();
		gnight.dispose();
	
		ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
	}

	@Override
	public void tick() {
		time++;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		ac = ac.derive(ac.getAlpha()/1.1f);
		clickcount++;

		if (clickcount > 23){
			changeRoom("MAPROOM");
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_R){
			System.out.println("YOU PRESSED R");
		}
		
	}

	@Override
	public void drawRoom(Graphics2D g2) {
	
//		BufferedImage screen = new BufferedImage(1280, 720, BufferedImage.TYPE_INT_ARGB);
//		Graphics2D gs = screen.createGraphics();
//		gs.dispose();
		
		g2.drawImage(background, 0, 0, null);
		g2.setComposite(ac);
		g2.setPaint(Color.black);
		g2.fillRect(0, 0, background.getWidth(), background.getHeight());
//		g2.drawImage(window, 0, 0, null);
//		lamp = colorize(lamp, time);
//		g2.drawImage(lamp, 0, 0, null);
//		g2.drawImage(lightmap, 0, 0, null);
		
	}
	

	@Override
	public void mouseDragged(MouseEvent e) {
	}
	
	BufferedImage colorize(BufferedImage img, int rgb){
		BufferedImage ret_img = new BufferedImage(img.getHeight(), img.getWidth(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g =  ret_img.createGraphics();
		AlphaComposite alf = AlphaComposite.getInstance(AlphaComposite.SRC_ATOP);
		g.drawImage(img, 0, 0, null);
		g.setComposite(alf);
		g.setColor(new Color(rgb));
		g.fillRect(0, 0, img.getHeight(), img.getWidth());
		g.dispose();
		return ret_img;
	}
	
	BufferedImage applyLightmap(BufferedImage src, BufferedImage lightmap, double intensity, int rgb){
		BufferedImage lightmap_mod = colorize(lightmap, rgb);
		BufferedImage ret_img = new BufferedImage(src.getHeight(), src.getWidth(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = ret_img.createGraphics();
		AlphaComposite a2 = AlphaComposite.getInstance(AlphaComposite.SRC_ATOP);
		g2.drawImage(src, 0, 0, null);
		g2.setComposite(a2);
		g2.setColor(new Color((int)(255/intensity), (int)(255/intensity), (int)(255/intensity)));
		g2.drawImage(lightmap_mod, 0, 0, null);
		g2.dispose();
		return ret_img;
	}
}
