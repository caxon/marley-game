package rooms;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Game;
import main.Music;

public class MapRoom extends Room{
	
	BufferedImage compImg = null;

	public MapRoom(Game game, String name) {
		super(game, name);
		// TODO Auto-generated constructor stub
		background = loadImage("map.png");
		compImg = loadImage("mapcomp.png");
		setMusic(Music.map_music);
	}
	
	public void mousePressed(MouseEvent e) {
		//Check if certain areas of map are pressed"
		Point coord = e.getPoint();
		int f2 = new Color(255, 0, 255).getRGB(); //neon pink, rgb colour
		int kit = new Color(255, 255, 0).getRGB(); //yellow, rgb colour
		int topArr = new Color(255, 0, 0).getRGB(); //red, rgb colour
		int belowArr = new Color(0, 0, 255).getRGB(); //blue, rgb colour
		int d2 = new Color(0, 0, 100).getRGB(); //dark blue, rgb colour
		int court = new Color(0, 255, 0).getRGB(); //neon green, rgb colour
		int doug = new Color(0, 0, 0).getRGB(); //black, rgb colour
		int pix = compImg.getRGB((int)coord.getX(),(int)coord.getY());
		if (pix == f2){
			System.out.println("you clicked f2");
			changeRoom("TESTROOM");
		}
		if (pix == kit){
			System.out.println("you clicked kitchen");
			changeRoom("KITCHENROOM");
		}
		if (pix == topArr){
			System.out.println("you clicked top arrow");
			changeRoom("MOUNTROYALROOM");
		}
		if (pix == belowArr){
			System.out.println("you clicked bottom arrow");
			changeRoom("DEBATEROOM");
		}
		if (pix == d2){
			System.out.println("d2 is now bossroom");
			boolean check = true;
			for (int i = 0; i<4; i++){
				if (game.keys[i] == 0) check=false;
			}
			if (check == true) changeRoom("BOSSROOM");

		}
		if (pix == court){
			System.out.println("you clicked courtyard");
			changeRoom("COURTYARDROOM");
		}
		if (pix == doug){
			System.out.println("you clicked doug - hallway");
			changeRoom("HALLROOM");
		}
	}

	@Override
	public void tick() {
	}

	@Override
	public void drawRoom(Graphics2D g2) {
		g2.drawImage(background, 0, 0, null);
	}
	
}