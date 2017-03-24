package rooms;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.channels.ShutdownChannelGroupException;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;

import main.Game;
import main.Music;

public class KitchenRoom extends Room{

	private Rectangle kettle = new Rectangle(190, 60, 250-190, 140-60);
	private Rectangle pot = new Rectangle(20,300,200-20,360-300 );
	
	public KitchenRoom(Game game, String name) {
		super(game, name);
		try {
		    background = ImageIO.read(new File("kitchen_img.png"));
		} catch (IOException e) {
			System.out.println("UNABLE TO READ FILE");
		}
		
		setMusic(Music.mii_theme);
	}


	@Override
	public void tick() {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//Check if "brain game" is pressed"
		Point coord = e.getPoint();
		int brainPix = new Color(255, 174, 201).getRGB(); //neon green, rgb colour
		int arrowPix = new Color(0, 204, 255).getRGB();
		int pix = background.getRGB((int)coord.getX(),(int)coord.getY());
		if (pix == arrowPix){
			changeRoom("COURTYARDROOM");
		}
		if (pix == brainPix){
			System.out.println("you clicked the brain game");
			try {
			    background = ImageIO.read(new File("kitchen_img_after.png"));
			} catch (IOException q) {
				System.out.println("UNABLE TO READ FILE");
			}
			changeRoom("BRAINGAME");
		}
		if (pot.contains(coord))
			thoughtMessage("Wonder if there's ramen inside.");
		if (kettle.contains(coord))
			thoughtMessage("Boiling water. Woohoo.");
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void drawRoom(Graphics2D g2) {
		g2.drawImage(background, 0, 0, null);
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

}

