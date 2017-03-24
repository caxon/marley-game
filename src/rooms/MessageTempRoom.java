package rooms;

import java.awt.Color;

import main.Game;

public class MessageTempRoom extends TempRoom {

	String message;
	
	public MessageTempRoom(Game game, Room nextroom, String message) {
		super(game, nextroom);
		background = createTextImage(new Color(255, 215, 0), Color.BLACK, message);
	}
	public MessageTempRoom(Game game, Room nextroom, Color bgcolor, Color textcolor, String message){
		super(game, nextroom);
		background = createTextImage(bgcolor, textcolor, message);
	}
	
	public static MessageTempRoom thoughtRoom(Game game, Room nextroom, String message){
		return new MessageTempRoom(game,nextroom, new Color(91, 136, 246), Color.BLACK, message);
	}

}
