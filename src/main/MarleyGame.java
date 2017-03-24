package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MarleyGame {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				createAndShowGUI();
			}
		});

	}
	
	static void createAndShowGUI(){
		JFrame f = new JFrame("Marmot");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Game game = new Game();
		game.setFocusable(true);
		f.add((JPanel) game);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
}
