package rooms;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import main.Game;
import main.Music;

public class DebateRoom extends Room{

	public DebateRoom(Game game, String name){
		super(game, name);
		background = loadImage("Debate_Room.png");
		setMusic(Music.debate_music);
	}
	
		
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (game.keys[0] == 1)
			changeRoom("MAPROOM");
		//TODO fix changeroom
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_A){
			System.out.println("YOU PRESSED A");
			changeRoom("DEBATEGAME");
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawRoom(Graphics2D g2) {
		 g2.drawImage(background, 0, 0, null);
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
