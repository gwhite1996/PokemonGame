package main;

//everything that will be displayed on the grid
abstract class GamePiece{

	private int xLoc;
	private int yLoc;
	private Direction dir;
	boolean solid;

	GamePiece(int xStart, int yStart){
		xLoc = xStart;
		yLoc = yStart;
		this.dir = Direction.SOUTH; // default direction
		solid = true; // by default player can't phase through
	}

	int getXLoc(){
		return xLoc;
	}

	void setXLoc(int xLoc){
		this.xLoc = xLoc;
	}

	int getYLoc(){
		return yLoc;
	}

	void setYLoc(int yLoc){
		this.yLoc = yLoc;
	}

	Direction getDirection(){
		return dir;
	}

	void setDirection(Direction dir){
		this.dir = dir;
	}
}
