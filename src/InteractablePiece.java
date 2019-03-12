public abstract class InteractablePiece extends GamePiece implements Interactable{

	private boolean interacting;
	private String message; // what is displayed when the player interacts with
							// it
	private PieceStatus pieceStatus;

	public InteractablePiece(int xStart, int yStart){
		super(xStart, yStart);
		pieceStatus = PieceStatus.TALK_ON_INTERACT; // default
	}

	public void onInteraction(Player p){
		// Most GamePieces will do nothing
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
}
