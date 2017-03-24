package rooms;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Game;
import main.Music;

public class SleddingRoom extends Room {
	
	BufferedImage downhill;
	BufferedImage downhill2;
	BufferedImage downhill3;
	BufferedImage downhill4;
	BufferedImage downhill5;
	BufferedImage marley;
	boolean godown = false;
	int time = 0;
	int imageLoading = 0;
	double x, y;
	
	double period =500.0;
	Point p_start = new Point(1050, 60);
	Point p_1 = new Point (1280+30, -120);
	Point p_2 = new Point (-50+20, 660);
	Point p_end = new Point(480, 350);
	
	BufferedImage dougclick;
	BufferedImage[] bg = {downhill, downhill2, downhill3, downhill4, downhill5};
	String names[] = {"downhill_bg.png", "downhill2_bg.png", "downhill3_bg.png", "downhill4_bg.png", "downhill5_bg.png"};

	public SleddingRoom(Game game, String name) {
		super(game, name);
		init();
		background = downhill;
		setMusic(Music.route_209);

	}
	
	public void init() {
		for (int i = 0; i < names.length; i++){
		try {
			bg[i] = ImageIO.read(new File(names[i]));		
		} catch (IOException e) {
			System.out.println("UNABLE TO READ FILE" + i );
		}
		}
		try{
			marley = ImageIO.read(new File("sled.png"));
		}catch(IOException e){
			System.out.println("UNABLE TO READ FILE why");
		}
		background = bg[0];
		x=p_start.getX(); y=p_start.getY();
		
		dougclick = loadImage("downhill5_click.png");
	}

	@Override
	public void tick() {
		 time++;
		 if (period > 300)
			 period --;
		 if (imageLoading < 5)
			 background = bg[imageLoading];
		 Double t = time/period;
		 if (imageLoading==0){
			 x = p_2.getX()*t + p_start.getX()*(1-t);
			 y = p_2.getY()*t + p_start.getY()*(1-t);}
		 else if (imageLoading == 4){
			 if (true){
				 x = p_end.getX()*t + p_1.getX()*(1-t);
				 y = p_end.getY()*t+ p_1.getY()*(1-t);
			 }
		 }
		 else if (imageLoading>4)
		 {
			 //
		 }
		 else{

			 x = p_2.getX()*t + p_start.getX()*(1-t);
			 y = p_2.getY()*t + p_start.getY()*(1-t);
		 
		 }
		 if (t>1){
			 time = 0;
			 imageLoading++;
		 }
		 
			 
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
	public void mouseClicked(MouseEvent e) {
		godown = true;
		
		if (imageLoading > 4){
			Point p = e.getPoint();
			System.out.println(dougclick.getRGB(p.x,	p.y ));
			if (dougclick.getRGB(p.x, p.y)%8 != 0xffffffff)
				changeRoom("MAPROOM");
		}
	}

	@Override
	public void drawRoom(Graphics2D g2) {
		g2.drawImage(background, 0, 0, null);
		//g2.drawImage(marley, (int)((1030-time)*(0.0002*Math.random()+1)), (int)((100.39+0.6*time)*(0.05*Math.random()+1)), null);
		AffineTransform rotate = new AffineTransform();
		if (imageLoading<=4)
			rotate.rotate(Math.sin(time/10.0)*0.1, 40,40);
		rotate.translate(x,y);
		g2.drawImage(marley, rotate, null);
		if ((1030-time) < 10){
			time = 0;
			imageLoading++;
		}
		
	}

}
