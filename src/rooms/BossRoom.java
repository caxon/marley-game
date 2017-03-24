package rooms;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Game;

public class BossRoom extends Room{
	String imageNames[] = {"finalBoss.png", "finalBoss_1.png", "finalBoss_2.png", "finalBoss_3.png", "finalBoss_4.png", "finalBoss_5.png", "finalBoss_6.png"};
	BufferedImage[] backgroundList = new BufferedImage[imageNames.length];
	BufferedImage tylerDoor;
	int imageAt = 0;

	public BossRoom (Game game, String name){
		super(game, name);
		for (int i = 0; i < imageNames.length; i++){
			try{
				backgroundList[i] = ImageIO.read(new File (imageNames[i]));
			}catch(IOException e){
				System.out.println("UNABLE TO READ FILE");
			}
		}
		background = backgroundList[0];
		try{
			tylerDoor = ImageIO.read(new File("tylerDoor.jpg"));
		}catch(IOException e){
			System.out.println("UNABLE TO READ FILE");
		}
		
	}
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if (imageAt < imageNames.length)
			background = backgroundList[imageAt];
		else
			background = backgroundList[imageNames.length-1];
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getX() < 1270 && e.getX() > 865){
			if (e.getY() < 690 && e.getY() > 533)
				imageAt++;
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawRoom(Graphics2D g2) {
		g2.drawImage(background, 0, 0, null);		
		g2.drawImage(tylerDoor, 300, 250, null);
	}

}
