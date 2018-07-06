import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class Model {
	private final int width;
	private final int height;
	private Player player;
	private Hiker hiker;
	private String message;
	ArrayList<GamePiece> interactableList;

	public Model() {
		this.width = 16;
		this.height = 16;
		interactableList = new ArrayList<GamePiece>();
		player = new Player(8, 8);
		hiker = new Hiker(3,2);
		interactableList.add(hiker);
	}
	public Player getPlayer(){
		return player;
	}
	public Hiker getHiker(){
		return hiker;
	}
	public void update(KeyEvent userInput) {
		player.setTryingToInteract(false);
		if(userInput != null){
			player.update(userInput);
		}
		if(player.getTryingToInteract()){
			for(GamePiece gamePiece: interactableList){
				if(player.getXAhead() == gamePiece.xLoc && player.getYAhead() == gamePiece.yLoc){
					gamePiece.onInteraction(player);
					gamePiece.setInteracting(true);
					player.setInteracting(true); // this pauses (stops the game from updating)
					message = gamePiece.getMessage();
					
					return; //player can't interact with more than one GamePiece at a time)
				}
			}
		}
		hiker.update();
		
	}
	
	public int getWidth(){
		return width;
	}
	public int getHeight() {
		return height;
	}
	public String getMessage() {
		return message;
	}
}
