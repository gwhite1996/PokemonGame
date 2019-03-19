package main;

abstract class TurnablePiece extends InteractablePiece{

	private int xAhead;
	private int yAhead;

	TurnablePiece(int xStart, int yStart){
		super(xStart, yStart);
		findSpaceAhead();
	}

	TurnablePiece(int xStart, int yStart, Direction dir){
		super(xStart, yStart);
		this.setDirection(dir);
		findSpaceAhead();
	}

	void moveForward(){
		if(canMoveForward()){
			switch(getDirection()){
			case EAST:
				setXLoc(getXLoc() + 1);
				break;
			case NORTH:
				setYLoc(getYLoc() - 1);
				break;
			case SOUTH:
				setYLoc(getYLoc() + 1);
				break;
			case WEST:
				setXLoc(getXLoc() - 1);
				break;
			default:
				System.out.println("Invalid direction");
				break;
			}
		}
	}

	private boolean canMoveForward(){
		return (willRemainInBounds() && !willCollide());
	}

	boolean willRemainInBounds(){
		if(xAhead < 0 || xAhead > 15 || yAhead < 0 || yAhead > 15){ // temp. should take grid size somehow
			return false;
		}
		return true;
	}

	boolean willCollide(){
		// add stuff later to determine this
		return false;
	}

	void findSpaceAhead(){
		xAhead = getXLoc();
		yAhead = getYLoc();
		switch(getDirection()){
		case EAST:
			xAhead++;
			break;
		case NORTH:
			yAhead--;
			break;
		case SOUTH:
			yAhead++;
			break;
		case WEST:
			xAhead--;
			break;
		}
	}

	void update(){
		findSpaceAhead();
		// more can be done here in the future
	}

	int getXAhead(){
		return xAhead;
	}

	int getYAhead(){
		return yAhead;
	}
}
