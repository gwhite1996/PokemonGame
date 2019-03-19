package gw96_pokemon;

abstract class InteractablePiece extends GamePiece implements Interactable{

	private boolean interacting;
	private String message; // what is displayed when the player interacts with
							// it
	private PieceStatus pieceStatus;

	InteractablePiece(int xStart, int yStart){
		super(xStart, yStart);
		pieceStatus = PieceStatus.TALK_ON_INTERACT; // default
	}

	@Override
	public void onInteraction(Player p){
		// Most GamePieces will do nothing
	}

	String getMessage(){
		return message;
	}

	void setMessage(String message){
		this.message = message;
	}

	PieceStatus getPieceStatus(){
		return pieceStatus;
	}

	void setPieceStatus(PieceStatus pieceStatus){
		this.pieceStatus = pieceStatus;
	}

	boolean getInteracting(){
		return interacting;
	}

	void setInteracting(boolean interacting){
		this.interacting = interacting;
	}
}
