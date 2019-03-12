//everything that will be displayed on the grid
public abstract class GamePiece{

	private int xLoc;
	private int yLoc;
	private Direction dir;
	boolean solid;

	public GamePiece(int xStart, int yStart){
		xLoc = xStart;
		yLoc = yStart;
		this.dir = Direction.SOUTH; // default direction
		solid = true; // by default player can't phase through
	}

	public int getXLoc(){
		return xLoc;
	}

	public void setXLoc(int xLoc){
		this.xLoc = xLoc;
	}

	public int getYLoc(){
		return yLoc;
	}

	public void setYLoc(int yLoc){
		this.yLoc = yLoc;
	}

	public Direction getDirection(){
		return dir;
	}

	public void setDirection(Direction dir){
		this.dir = dir;
	}
}
