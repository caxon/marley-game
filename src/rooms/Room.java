package rooms;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;

import main.Game;

public abstract class Room {
	
	public Game game;
	public BufferedImage background;
	public String name;
	public Clip bgm;
	
	public Room(Game game, String name){
		this.game = game;
		this.name = name;
	}
	public Room(Game game){
		this(game, "ERROR SHOULD NOT SEE THIS");
	}
	
	public abstract void tick();
	public abstract void drawRoom(Graphics2D g2);
	
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e){
	}
	public void keyPressed(KeyEvent e){
	}
	public void keyReleased(KeyEvent e){
	}
	public void mouseDragged(MouseEvent e){
	}
	public void mouseClicked(MouseEvent e) {
	}
	public void setMusic(Clip clip){
		this.bgm = clip;
	}
	public void changeRoom(String key){
		game.setRoom(key);
	}

	public BufferedImage loadImage(String image_path){
		BufferedImage ret_image = null;
		try {
		    ret_image = ImageIO.read(new File(image_path));
		} catch (IOException e) {
			System.out.println("UNABLE TO READ FILE@" + this.getClass() + ": "+image_path);
		}
		return ret_image;
	}
	
	public void keyMessage(String msg){
		game.loadRoom(new MessageTempRoom(game, this, msg));
	}
	public void thoughtMessage(String msg){
		game.loadRoom(MessageTempRoom.thoughtRoom(game, this, msg));
	}

	
	
	
}
