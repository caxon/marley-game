package rooms;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Game;

public class BrainGame extends Room{

	int brainCounter=0;
	BufferedImage compImg = null;
	
	public BrainGame(Game game, String name) {
		super(game, name);
		try {
		    background = ImageIO.read(new File("brainB_img0.png"));
		} catch (IOException e) {
			System.out.println("UNABLE TO READ FILE");
		}
	}

	@Override
	public void tick() {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//Check if "brain itself" is pressed"
		Point coord = e.getPoint();
		int inBrainPix = new Color(255, 0, 0).getRGB(); //neon red, rgb colour
		int returnButton = new Color(0, 0, 255).getRGB(); //neon blue, rgb colour
		int pix = compImg.getRGB((int)coord.getX(),(int)coord.getY());
		if (pix == inBrainPix){
			System.out.println("you clicked the brain");
			brainCounter++;
			if (brainCounter == 4){
				keyMessage("You got the bloody key");
				game.keys[3] =1 ;
			}
			System.out.println(brainCounter);
			try {
			    background = ImageIO.read(new File("brainB_img" + brainCounter + ".png"));
			} catch (IOException q) {
				}
			}
		if (pix == returnButton){
			System.out.println("you clicked return");
			changeRoom("KITCHENROOM");
		}

	}


	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void drawRoom(Graphics2D g2) {
		g2.drawImage(background, 0, 0, null);
		
		try {
		    compImg = ImageIO.read(new File("brainA_img" + brainCounter + ".png"));
		} catch (IOException e) {
			System.out.println("UNABLE TO READ FILE");
		}

	
		BufferedImage knife = null;
		try {
		    knife = ImageIO.read(new File("knife_img.png"));
		} catch (IOException e) {
			System.out.println("UNABLE TO READ FILE");
		}
		try{
		g2.drawImage(knife, (int) game.getMousePosition().getX(), (int) game.getMousePosition().getY(), null);
		}catch (NullPointerException e) {
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

}

