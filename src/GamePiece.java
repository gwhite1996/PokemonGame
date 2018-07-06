

public abstract class GamePiece implements Interactable{
	protected int xLoc;
	protected int yLoc;
	private boolean interacting;
	private String message;
	private PieceStatus pieceStatus;

	public GamePiece(int xStart, int yStart){
		xLoc = xStart;
		yLoc = yStart;
		pieceStatus = PieceStatus.CANNOT_INTERACT;
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
	public String getMessage(){
		return message;
	}
	public void setMessage(String message){
		this.message = message;
	}
	public PieceStatus getPieceStatus(){
		return pieceStatus;
	}
	public void setPieceStatus(PieceStatus pieceStatus){
		this.pieceStatus = pieceStatus;
	}
	public boolean getInteracting(){
		return interacting;
	}
	public void setInteracting(boolean interacting){
		this.interacting = interacting;
	}
	
	public void onInteraction(Player p){
		//Most GamePieces will do nothing
	}
}

