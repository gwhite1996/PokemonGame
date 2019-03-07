

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
	
	public boolean chooseMove(Pokemon user){
		if(!user.hasPPLeft()){
			System.out.println(user + " has no PP left for any move!");
			setAction(MoveList.struggle);
			return true;
		}
		
		Move moveUsed;
		do{
			moveUsed = MoveList.none;
			LostMethods.printReturnOption();
			LostMethods.printMoveSet(user);
			
			switch(LostMethods.chooseOption(0,4)){
			case 0:return false;
			case 1:moveUsed = user.move1;break;
			case 2:moveUsed = user.move2;break;
			case 3:moveUsed = user.move3;break;
			case 4:moveUsed = user.move4;break;
			}
			if(!(moveUsed == MoveList.none) && moveUsed.ppLeft <= 0){
				System.out.println(moveUsed + " has no PP left!");
			}
		}
		while(moveUsed.ppLeft <= 0);
		setAction(moveUsed); //sets action so the loop in selectAction() ends
		return true;
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
	
	
	
	public boolean willSwapPokemon(){
		Pokemon nextPokemon = party.swapFromParty(false);
		
		if(nextPokemon != null){
			setAction(new SwapPokemon(nextPokemon));
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean willUseItem(){
		Item itemUsed = null;
		Pokemon targetPokemon = null;
		
		while(targetPokemon == null){
			itemUsed = bag.chooseItem();
			
			if(itemUsed != null){
				if(itemUsed.getItemType() == ItemType.USEDONSELF){
					System.out.println("Which pokemon will " + itemUsed + " be used on?");
					targetPokemon = party.choosePokemon();
					
					if(targetPokemon != null){
						setAction(new UseItem(itemUsed, targetPokemon));
						return true;
					}
				}
				else if(itemUsed.getItemType() == ItemType.OUTOFBATTLE){
					System.out.println(itemUsed + " can only be used outside of battle!");
					return false;
				}
				else{ //no target pokemon but the move does it's thing
					setAction(new UseItem(itemUsed, null));
					return true;
				}
			}
			else{
				return false;
			}
		}
		return false; //temp. this is likely unreachable
	}
	
}
