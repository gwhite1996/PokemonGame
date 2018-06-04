

public abstract class GamePiece {
	protected int xLoc;
	protected int yLoc;
	protected boolean interacting;

	public GamePiece(int xStart, int yStart){
		xLoc = xStart;
		yLoc = yStart;
	}
	
	public int getXLoc() {
		return xLoc;
	}
	public int getYLoc() {
		return yLoc;
	}
}

