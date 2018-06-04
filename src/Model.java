
import java.awt.event.KeyEvent;




public class Model {
	GamePiece[][] grid;
	private final int width;
	private final int height;
	private Player player;
	private Hiker hiker;
	boolean showMessage;

	public Model() {
		this.width = 16;
		this.height = 16;
		grid = new GamePiece[width][height];
		player = new Player(8, 8);
		hiker = new Hiker(3,2);
	}
	public Player getPlayer(){
		return player;
	}
	public Hiker getHiker(){
		return hiker;
	}
	public void update(KeyEvent userInput) {
		player.interacting = false;
		if(userInput != null){
			player.update(userInput);
		}
		hiker.update();
		if(player.interacting == true && player.xAhead == hiker.xLoc && player.yAhead == hiker.yLoc){
			//makes the hiker face the opposite direction of the player
			switch(player.getDirection()){
			case EAST:hiker.dir = Direction.WEST;break;
			case NORTH:hiker.dir = Direction.SOUTH;break;
			case SOUTH:hiker.dir = Direction.NORTH;break;
			case WEST:hiker.dir = Direction.EAST;break;
			}
			hiker.interacting = true;
			showMessage = true;
		}
	}
	public int getWidth(){
		return width;
	}
	public int getHeight() {
		return height;
	}
}
