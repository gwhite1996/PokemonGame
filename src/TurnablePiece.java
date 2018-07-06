

public abstract class TurnablePiece extends GamePiece{
	protected Direction dir;
	protected int xAhead;
	protected int yAhead;


	public TurnablePiece(int xStart, int yStart) {
		super(xStart, yStart);
		this.dir = Direction.SOUTH; //default direction
		findSpaceAhead();
	}

	public TurnablePiece(int xStart, int yStart, Direction dir) {
		super(xStart, yStart);
		this.dir = dir;
		findSpaceAhead();
	}

	public Direction getDirection(){
		return dir;
	}
	
	public int getXAhead(){
		return xAhead;
	}
	public int getYAhead(){
		return yAhead;
	}

	public void moveForward(){
		if(canMoveForward()){
			switch(dir){
			case EAST:xLoc++;break;
			case NORTH:yLoc--;break;
			case SOUTH:yLoc++;break;
			case WEST:xLoc--;break;
			default:
				System.out.println("Invalid direction");
				break;
			}
		}
	}

	private boolean canMoveForward() {
		return(willRemainInBounds() && !willCollide());
	}

	public boolean willRemainInBounds() {
		if(xAhead<0 || xAhead>15 || yAhead<0 || yAhead>15){ //should take grid size somehow
			return false;
		}
		return true;
	}

	public boolean willCollide(){
		//add stuff later to determine this
		return false;
	}

	public void findSpaceAhead(){
		xAhead = xLoc;
		yAhead = yLoc;
		switch(dir){
		case EAST:xAhead++;break;
		case NORTH:yAhead--;break;
		case SOUTH:yAhead++;break;
		case WEST:xAhead--;break;
		}
	}
	public void update(){
		findSpaceAhead();
		//more can be done here in the future
	}
}
