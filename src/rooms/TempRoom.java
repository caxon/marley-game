package rooms;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;

public class TempRoom extends Room{

	double time_seconds = 1.8;
	int max_ticks = (int)(time_seconds * 60);
	int ticks;
	String text;
	Room nextroom;
	public TempRoom(Game game, Room nextroom) {
		super(game);
		this.nextroom= nextroom;
		text = String.format("%s", nextroom.name);
		ticks = 0;
		
		background = createTextImage(Color.black, Color.white, text);
	}

	BufferedImage createTextImage(Color bgcolor, Color textcolor, String printtext){
		BufferedImage ret = new BufferedImage(1280, 720, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = ret.createGraphics();
		g2.setPaint(bgcolor);
		g2.fillRect(0, 0, 1280, 720);
		g2.setFont(new Font("Comic Sans MS", Font.PLAIN, 72));
		g2.setColor(textcolor);
		int fwidth = g2.getFontMetrics().stringWidth(printtext);
		g2.drawString(printtext, 1280/2-fwidth/2, 720/2-1);
		g2.dispose();
		return ret;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		super.mouseClicked(e);
		if (ticks > 10)
			ticks+=60;
	}
	@Override
	public void tick() {
		if (ticks > max_ticks || ticks ==-1){
			game.loadRoom(nextroom);
		}
		ticks++;
	}

	@Override
	public void drawRoom(Graphics2D g2) {
		g2.drawImage(background, 0, 0, null);
	}

}
