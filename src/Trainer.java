
public abstract class Trainer extends TurnablePiece{
	String greeting;
	String battleIntro;
	String battleDefeated;
	String afterBattle;
	
	Party party;
	
	public Trainer(int xStart, int yStart) {
		super(xStart, yStart);
		setPieceStatus(PieceStatus.BATTLE_ON_INTERACT);
	}
	@Override
	public void onInteraction(Player p){
		updateMessage();
		//makes the trainer face the opposite direction of the player
		switch(p.getDirection()){
		case EAST:dir = Direction.WEST;break;
		case NORTH:dir = Direction.SOUTH;break;
		case SOUTH:dir = Direction.NORTH;break;
		case WEST:dir = Direction.EAST;break;
		}
	}
	
	protected void updateMessage(){
		switch(getPieceStatus()){
		case LOOKING_TO_TALK:setMessage(greeting);break;
		case LOOKING_TO_BATTLE:setMessage(greeting);break;
		case TALK_ON_INTERACT:setMessage(greeting);break;
		case BATTLE_ON_INTERACT:setMessage(greeting);break;
		case DEFEATED:setMessage(afterBattle);break;
		default:setMessage("Invalid pieceStatus in updateMessage method");
		}
	}
}
