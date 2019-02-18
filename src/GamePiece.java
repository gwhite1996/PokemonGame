
//everything that will be displayed on the grid
public abstract class GamePiece{
	protected int xLoc;
	protected int yLoc;
	protected Direction dir;
	boolean solid;

	public GamePiece(int xStart, int yStart){
		xLoc = xStart;
		yLoc = yStart;
		this.dir = Direction.SOUTH; //default direction
		solid = true; //by default player can't phase through
	}
	
	public int getXLoc() {
		return xLoc;
	}
	public int getYLoc() {
		return yLoc;
	}
	public void setXLoc(int xLoc){
		this.xLoc = xLoc;
	}	
	public void setYLoc(int yLoc){
		this.yLoc = yLoc;
	}
	public Direction getDirection(){
		return dir;
	}
}

