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

public class CourtyardRoom extends Room{

	double exp = 0.65;
	int presize = 60;
	int snowballSize = (int)Math.pow(presize, exp);
	int startX, startY;


	boolean displayed_key = false;
	
	BufferedImage snowball = null;
	BufferedImage newBack = null;
	BufferedImage snowman = null;
	
	public CourtyardRoom(Game game, String name) {
		super(game, name);
		try {
		    background = ImageIO.read(new File("courtyard_img.png"));
		} catch (IOException e) {
			System.out.println("UNABLE TO READ FILE");
		}
		
		try {
		    snowball = ImageIO.read(new File("snowball_img.png"));
		} catch (IOException e) {
			System.out.println("UNABLE TO READ FILE");
		}
		
		try {
		    newBack = ImageIO.read(new File("courtyard2_img.png"));
		} catch (IOException e) {
			System.out.println("UNABLE TO READ FILE");
		}
		try {
		    snowman = ImageIO.read(new File("snowman_img.png"));
		} catch (IOException e) {
			System.out.println("UNABLE TO READ FILE");
		}
		
		setMusic(Music.winter_winds);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		startX = e.getX();
		startY = e.getY();
		
		//check if door is pressed
		Point coord = e.getPoint();
		int doorPixR = new Color(117, 76, 36).getRGB();
		int knobPixR = new Color(164, 164, 164).getRGB();
		int doorPixL = new Color(198, 156, 109).getRGB();
		int knobPixL = new Color(0, 0, 0).getRGB();
		int pix = background.getRGB((int)coord.getX(),(int)coord.getY());
		if (pix == doorPixR || pix == knobPixR){
			System.out.println("you clicked the exit");
			changeRoom("KITCHENROOM");
		}
		if (pix == doorPixL || pix == knobPixL){
			System.out.println("you clicked the exit");
			changeRoom("MAPROOM");
		}

	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		if (snowballSize<400){
			double distance = Math.pow(Math.pow(Math.abs(startX - x), 2) + Math.pow(Math.abs(startY - y), 2), 0.5);
			presize+= distance/100;
			snowballSize = (int)Math.pow(presize, exp);
		}

	}

	@Override
	public void drawRoom(Graphics2D g2) {
		try{
		if (snowballSize>=400){
			background = newBack;
			g2.drawImage(background, 0, 0, null);
			g2.drawImage(snowman, (int) game.getMousePosition().getX()-250, (int) game.getMousePosition().getY()-300, null);
			if (displayed_key==false){
				game.keys[1] = 1;
				keyMessage("You found the snowman key");
				displayed_key = true;
			}
		}
		else{
			g2.drawImage(background, 0, 0, null);
			g2.drawImage(snowball, (int) game.getMousePosition().getX()-(snowballSize/2), (int) game.getMousePosition().getY()-(snowballSize/2), snowballSize, snowballSize, null);
		}
		}catch (NullPointerException e){
			
		}

	}

	@Override
	public void tick() {
	}

}

