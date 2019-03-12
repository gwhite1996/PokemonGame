public abstract class TurnablePiece extends InteractablePiece{

	private int xAhead;
	private int yAhead;

	public TurnablePiece(int xStart, int yStart){
		super(xStart, yStart);
		findSpaceAhead();
	}

	public TurnablePiece(int xStart, int yStart, Direction dir){
		super(xStart, yStart);
		this.setDirection(dir);
		findSpaceAhead();
	}

	public void moveForward(){
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

	public boolean willRemainInBounds(){
		if(xAhead < 0 || xAhead > 15 || yAhead < 0 || yAhead > 15){ // temp. should take grid size somehow
			return false;
		}
		return true;
	}

	public boolean willCollide(){
		// add stuff later to determine this
		return false;
	}

	public void findSpaceAhead(){
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

	public void update(){
		findSpaceAhead();
		// more can be done here in the future
	}

	public int getXAhead(){
		return xAhead;
	}

	public int getYAhead(){
		return yAhead;
	}
}
