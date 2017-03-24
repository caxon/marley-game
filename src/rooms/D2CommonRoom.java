package rooms;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;

public class D2CommonRoom extends Room{

	

	public D2CommonRoom(Game game, String name) {
		super(game, name);
	}

	@Override
	public void tick() {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void drawRoom(Graphics2D g2) {
		g2.drawImage(background, 0, 0, null);
		
	}

}
