package rooms;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.color.*;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import main.Game;

public class DebateGame extends Room{
	
	int timerCounter = 0;
	int pressCounter = 0;
	int imageNumber = 0;
	int pityCounter = 0;
	int x = 0;
	int increment[] = {70, 100, 150};
	boolean displayed_key  =false;
	
	boolean imagePastFour = false, imagePastFive = false;
	public BufferedImage[] backgroundList = new BufferedImage[7];
	public BufferedImage arrow;
	public String imageNames[] = {"argue_1.png", "argue_2.png", "argue_3.png", "argue_4.png", "argue_5.png", "argue_6.png", "argue_7.png"}; 
	
	public DebateGame(Game game, String name){
		super(game, name);
		for (int i = 0; i < 7; i++){
			try{
				backgroundList[i] = ImageIO.read(new File (imageNames[i]));
			} catch(IOException e){
				System.out.println("UNABLE TO READ FILE: " + this.getClass()+ " ARRAYS");
			}
		}
		background = backgroundList[0];
		
		try{
			arrow = ImageIO.read(new File("arrowTip.png"));
		}catch (IOException e){
			System.out.println("UNABLE TO READ FILE: " +this.getClass()+ " ARROWTIP");
		}
	}

	@Override
	public void tick() {
		timerCounter ++; 
		imageNumber = timerCounter/(60*2);
		if (imageNumber < 5)
			background = backgroundList[imageNumber];
		
		if (imageNumber > 3)
			imagePastFour = true;
		if (imagePastFour)
			background = backgroundList[5];
		if (imagePastFive){
			background = backgroundList[6];
			if (!displayed_key){
				game.keys[0] = 1;
				keyMessage("Debate key????");
				displayed_key = true;
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (timerCounter > 120*6)
			changeRoom("DEBATEROOM");
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawRoom(Graphics2D g2) {	
		g2.drawImage(background, 0, 0, null);	
		g2.setFont(new Font ("Comic Sans", Font.PLAIN, 30));
		g2.setColor(new Color (23, 40, 200));
		g2.drawString("Times Pressed: " + Integer.toString(pressCounter), 1000, 700);
	//	g2.setColor(new Color (r, g, b));
		GradientPaint redToGreen = new GradientPaint(0, 0, Color.RED, 1300, 20, Color.GREEN);
		g2.setPaint(redToGreen);
		g2.fillRect(0, 0, 1300, 20);
		g2.setColor(Color.BLACK);
		//if (!imagePastFour){
			g2.fillRect(0, 30, x, 5);
			g2.drawImage(arrow, x-5, 20, null);
		//}
		//g2.fillRect(0,  0, 1300,  20);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_SPACE){
			if (!imagePastFour){
				pressCounter++;
				x += 1300/increment[pityCounter];
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_X){
			if (imagePastFour){
				System.out.println("pityCounter " + pityCounter);
				if (pityCounter > 1)
					imagePastFive = true;
				else
					reset();
			}
		}			
	}
	
	public void reset(){
		timerCounter = 0;
		pressCounter = 0;
		imageNumber = 0;
		x = 0;
		pityCounter++;
		imagePastFour = false;
	}

}
