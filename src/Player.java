
import java.awt.event.KeyEvent;


public class Player extends Turnable{

	public Player(int xStart, int yStart) {
		super(xStart, yStart);
	}

	public void setXLoc(int xLoc){
		this.xLoc = xLoc;
	}	
	public void setYLoc(int yLoc){
		this.yLoc = yLoc;
	}
	
	//If arrow key is pressed, player direction/location is updated
	public void update(KeyEvent userInput) {
		Direction moveDirection = null;
		int key = userInput.getKeyCode();
		switch(key){
		case KeyEvent.VK_LEFT:moveDirection = Direction.WEST;break;
		case KeyEvent.VK_RIGHT:moveDirection = Direction.EAST;break;
		case KeyEvent.VK_UP:moveDirection = Direction.NORTH;break;
		case KeyEvent.VK_DOWN:moveDirection = Direction.SOUTH;break;
		case KeyEvent.VK_SPACE:interacting = true;break;
		default:System.out.println("Invalid key input. In Player");return;
		}
		if(moveDirection != null){
			if(moveDirection == dir){
				moveForward();
			}
			else{
				dir = moveDirection;
			}
		}	
		findSpaceAhead();//after moving or turning, the space ahead is found
	}
}
