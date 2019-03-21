package main;

import java.awt.event.KeyEvent;

class Player extends Trainer {

	private boolean tryingToInteract; // Indicates the interact button was pressed

	Player(String name, int xStart, int yStart) {
		super(name, xStart, yStart);
		tryingToInteract = false;
	}

	// If arrow key is pressed, player direction/location is updated
	void update(KeyEvent userInput) {
		Direction moveDirection = null;
		int key = userInput.getKeyCode();
		switch(key) {
		case KeyEvent.VK_LEFT:
			moveDirection = Direction.WEST;
			break;
		case KeyEvent.VK_RIGHT:
			moveDirection = Direction.EAST;
			break;
		case KeyEvent.VK_UP:
			moveDirection = Direction.NORTH;
			break;
		case KeyEvent.VK_DOWN:
			moveDirection = Direction.SOUTH;
			break;
		case KeyEvent.VK_SPACE:
			setTryingToInteract(true);
			break;
		default:
			System.out.println("Invalid key input. In Player");
			return;
		}
		if(moveDirection != null) {
			if(moveDirection == getDirection()) {
				moveForward();
			}
			else {
				setDirection(moveDirection);
			}
		}
		super.update();// after moving or turning, the space ahead is found
	}

	boolean getTryingToInteract() {
		return tryingToInteract;
	}

	void setTryingToInteract(boolean tryingToInteract) {
		this.tryingToInteract = tryingToInteract;
	}
}
