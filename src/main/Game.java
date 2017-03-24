package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.HashMap;

import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import rooms.BossRoom;
import rooms.BrainGame;
import rooms.CourtyardRoom;
import rooms.D2CommonRoom;
import rooms.DebateGame;
import rooms.DebateRoom;
import rooms.KitchenRoom;
import rooms.MountRoyalRoom;
import rooms.Room;
import rooms.SleddingRoom;
import rooms.TempRoom;
import rooms.F2CommonRoom;
import rooms.HallRoom;
import rooms.MapRoom;

public class Game extends JPanel{

	// Constant window dimensions
	public static final int WINDOW_WIDTH = 1280;
	public static final int WINDOW_HEIGHT = 720;
	public static final int UPDATES_PER_SECOND = 60;
	public int[] keys = {0,0,0,0};
	
	public HashMap< String, Room> rooms = new HashMap<>();
	public Room current_room;
	public Clip song;
	
	public void loadRooms(){
		addRoom(new BossRoom(this, "The Final Fight"), "BOSSROOM");
		addRoom(new DebateRoom(this, "Debate"), "DEBATEROOM");
		addRoom(new MountRoyalRoom(this, "Mount Royal"), "MOUNTROYALROOM");
		addRoom(new SleddingRoom(this, "The Mountain"), "SLEDDINGROOM");
		addRoom(new KitchenRoom(this, "The Kitchen"), "KITCHENROOM");
		addRoom(new CourtyardRoom(this, "The Courtyard"), "COURTYARDROOM");

		addRoom(new BrainGame(this, "The Brain"), "BRAINGAME");
		addRoom(new DebateGame(this, "THE DEBATE"), "DEBATEGAME");
		addRoom(new F2CommonRoom(this, "F2 Common Room?"), "TESTROOM");
		
		addRoom(new MapRoom(this, "The Map"), "MAPROOM");
		addRoom(new HallRoom(this, "The halls of Doug"), "HALLROOM");
		
		loadRoom(rooms.get("MAPROOM"));
	}
	
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void tick(){
		current_room.tick();
	}
	
	public void setRoom(String room_key){
		Room new_room = rooms.get(room_key);
		if (new_room != null){
			System.out.println("ENTERING: " + new_room.getClass());
			current_room = new TempRoom(this, new_room);
		}
		else
			System.out.println("ROOM ID: \"" + room_key + "\" IS INVALID.");
	}
	
	public void addRoom(Room r, String room_key){
		rooms.put(room_key, r);
	}
	
	public void changeSong(Clip clip){
		if (clip != null){
			if (song!=null)
				song.stop();
			song = clip;
			clip.start();
		}
	}
	public Game(){
		loadRooms();
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				current_room.mouseClicked(e);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				System.out.println("Mouse clicked at: " + e.getX() + "," + e.getY() );
				current_room.mousePressed(e);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				current_room.mouseReleased(e);
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				super.mouseDragged(e);
				current_room.mouseDragged(e);
			}
				
		});
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e){
				current_room.keyPressed(e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				current_room.keyReleased(e);
			}
		});
		
		new Timer(1000/UPDATES_PER_SECOND, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tick();
				repaint();
			}
		}).start();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2  = (Graphics2D) g;
		current_room.drawRoom(g2);
//		System.out.println(current_room.getClass());
	}

	public void loadRoom(Room room) {
		current_room = room;
		changeSong(room.bgm);
	}


}
