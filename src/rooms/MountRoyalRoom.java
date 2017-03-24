package rooms;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

import main.Game;
import main.Music;

public class MountRoyalRoom extends Room {

	BufferedImage background;
	BufferedImage marmothead;
	BufferedImage marmotbody;
	BufferedImage marmotkey;
	int dx = 0;
	int dy = 0;
	int time = 0;
	int t = 0;
	int pressCounter = 0;
	double distance = 0;
	Point keypos = new Point(0, -200);
	boolean displayed_key  =false;

	boolean behead = false;
	boolean finished = false;
	
	public MountRoyalRoom(Game game, String name) {
		super(game, name);
		init();
		
		setMusic(Music.true_sorry);
	}

	public void init() {

		try {
			background = ImageIO.read(new File("bg_mrr.png"));
			marmothead = ImageIO.read(new File("marhead_crop.png"));
		    marmotbody = ImageIO.read(new File("marbody_crop.png"));
		    marmotkey = ImageIO.read(new File("markey.png"));
		} catch (IOException e) {
			System.out.println("UNABLE TO READ FILE: " +this.getClass());
		}
	}

	@Override
	public void tick() {
		time++;
		if (behead == true && time < 35) {
			dx -= (int) (Math.cos(t)*9);
			dy -= (int) (Math.sin(t)*9);
		}
		if(pressCounter > 2) {
			keypos.move(380+dx, 330+dy);
		}
		distance = Math.sqrt(Math.pow(dx, 2) / 250 + Math.pow(dy, 2) / 250);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_T)
			changeRoom("DININGROOM");
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		behead = true;
		time = 0;
		t++;
		pressCounter++;
	}

	@Override
	public void drawRoom(Graphics2D g2) {
		g2.drawImage(background, 0, 0, null);
		g2.drawImage(marmotbody, 450, 380, null);
		g2.drawImage(marmothead, 405+dx, 285+dy, null);
		g2.drawImage(marmotkey, (int) (keypos.getX()), (int) (keypos.getY()), null);
		
		g2.setFont(new Font ("Comic Sans MS", Font.BOLD, 50));
		g2.drawString("Decapitation", 500, 50);
		
		g2.setFont(new Font ("Comic Sans MS", Font.PLAIN, 20));
		g2.drawString("Distance from body: " + (Double.parseDouble(new DecimalFormat("####.##").format(distance))) + " units", 520, 80);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Point clicked = e.getPoint();
		Rectangle bounds = new Rectangle(keypos.x, keypos.y, marmotkey.getWidth(), marmotkey.getHeight());
		if (bounds.contains(clicked))
		{

			if (displayed_key)
				changeRoom("SLEDDINGROOM");
			if (!displayed_key){
				game.keys[2] =1;
				displayed_key = true;
				keyMessage("A marmot died for this key.");
			}
			
		}
	}
}
