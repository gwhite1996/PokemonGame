
public abstract class Trainer extends TurnablePiece{
	private String name;
	private Action action;
	
	String greeting;
	String battleIntro;
	String battleDefeated;
	String afterBattle;
	
	Party party;
	Bag bag;
	
	public Trainer(String name, int xStart, int yStart) {
		super(xStart, yStart);
		this.name = name;
		this.action = LostMethods.none;
		bag = new Bag();
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
	
	@Override
	public String toString(){
		return name;
	}
	public Action getAction(){
		return action;
	}
	public void setAction(Action action){
		this.action = action;
	}
}
