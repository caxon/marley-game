package rooms;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Game;

public class HallRoom extends Room{

	int hall_room_id;
	
	public HallRoom(Game game, String name) {
		super(game, name);
		switch ((int)(Math.random()*3+1)) {
		case 1:
			background =loadImage("pipes1.png");
			break;
		case 2:
			background = loadImage("pipes2.png");
			break;
		case 3:
			background = loadImage("pipes3.png");
			break;
		default:
			break;
		}
//		max_ticks = 90000;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		changeRoom("COURTYARDROOM");
	}


	@Override
	public void drawRoom(Graphics2D g2) {
		g2.drawImage(background, 0, 0, null);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	
}
