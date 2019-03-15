public abstract class Trainer extends TurnablePiece{

	private String name;
	private Action action;
	private Pokemon currentPokemon; // the one being used in battle
	private Pokemon pokemonOnDeck; // the pokemon which will be swapped out
	String greeting;
	String battleIntro;
	String battleDefeated;
	String afterBattle;
	Party party;
	Bag bag;

	public Trainer(String name, int xStart, int yStart){
		super(xStart, yStart);
		this.name = name;
		this.action = LostMethods.none;
		bag = new Bag();
	}

	@Override
	public void onInteraction(Player p){
		updateMessage();
		// makes the trainer face the opposite direction of the player
		switch(p.getDirection()){
		case EAST:
			setDirection(Direction.WEST);
			break;
		case NORTH:
			setDirection(Direction.SOUTH);
			break;
		case SOUTH:
			setDirection(Direction.NORTH);
			break;
		case WEST:
			setDirection(Direction.EAST);
			break;
		}
	}

	protected void updateMessage(){
		switch(getPieceStatus()){
		case LOOKING_TO_TALK:
			setMessage(greeting);
			break;
		case LOOKING_TO_BATTLE:
			setMessage(greeting);
			break;
		case TALK_ON_INTERACT:
			setMessage(greeting);
			break;
		case BATTLE_ON_INTERACT:
			setMessage(greeting);
			break;
		case DEFEATED:
			setMessage(afterBattle);
			break;
		default:
			setMessage("Invalid pieceStatus in updateMessage method");
		}
	}

	public boolean chooseMove(){
		if(!currentPokemon.hasPPLeft()){
			System.out.println(currentPokemon + " has no PP left for any move!");
			setAction(MoveList.struggle);
			return true;
		}
		Move moveUsed;
		do{
			moveUsed = MoveList.none;
			LostMethods.printReturnOption();
			LostMethods.printMoveSet(currentPokemon);
			switch(LostMethods.chooseOption(0, 4)){
			case 0:
				return false;
			case 1:
				moveUsed = currentPokemon.move1;
				break;
			case 2:
				moveUsed = currentPokemon.move2;
				break;
			case 3:
				moveUsed = currentPokemon.move3;
				break;
			case 4:
				moveUsed = currentPokemon.move4;
				break;
			}
			if(moveUsed != MoveList.none && moveUsed.getPPLeft() <= 0){
				System.out.println(moveUsed + " has no PP left!");
			}
		}
		while(moveUsed.getPPLeft() <= 0);
		setAction(moveUsed); // sets action so the loop in selectAction() ends
		return true;
	}

	public boolean willUseItem(){
		Item itemUsed;
		Pokemon targetPokemon = null;
		do{
			itemUsed = bag.chooseItem();
			if(itemUsed != null){
				if(itemUsed.getItemType().getItemCategory() == ItemCategory.USEDONSELF){
					System.out.println("Which pokemon will " + itemUsed + " be used on?");
					targetPokemon = party.choosePokemon();
					if(targetPokemon != null){
						setAction(new UsingItem(itemUsed, targetPokemon));
						return true;
					}
				}
				else if(itemUsed.getItemType().getItemCategory() == ItemCategory.OUTOFBATTLE){
					System.out.println(itemUsed + " can only be used outside of battle!");
				}
				else{ // no target pokemon but the move does it's thing
					setAction(new UsingItem(itemUsed, null));
					return true;
				}
			}
			else{
				return false;
			}
		}
		while(targetPokemon == null);
		return false; // temp. this is likely unreachable
	}

	// selects the pokemon to swap out
	public boolean pickPokemonOnDeck(boolean mustSwap){
		Pokemon selectedPokemon = null;
		System.out.println("Select a pokemon to swap out.");
		do{
			selectedPokemon = party.choosePokemon();
			if(selectedPokemon == null){
				if(!mustSwap){
					return false;
				}
				else{
					System.out.println("You must select a pokemon to swap out!");
				}
			}
			else{
				int choice = -1;
				while(choice != 0){
					System.out.println(" What do you want to do with: " + selectedPokemon + "?");
					LostMethods.printReturnOption();
					System.out.println("[1] Summary");
					System.out.println("[2] Swap Out");
					switch(choice = LostMethods.chooseOption(0, 2)){
					case 0:
						selectedPokemon = null;
						break;
					case 1:
						selectedPokemon.viewSummary();
						break;
					case 2:
						if(selectedPokemon.getStatus() == Status.FAINTED){
							System.out.println(selectedPokemon + " has no HP remaining!");
							break;
						}
						else if(selectedPokemon == currentPokemon){
							System.out.println(currentPokemon + " is already out!");
							break;
						}
						else{
							pokemonOnDeck = selectedPokemon;
							setAction(LostMethods.swapingPokemon);
							return true;
						}
					}
				}
			}
		}
		while(selectedPokemon == null);
		System.out.println("Im fairly certain this is impossible to reach");
		return false;
	}

	public Pokemon getCurrentPokemon(){
		return currentPokemon;
	}

	public boolean onDeckToCurrent(){
		if(pokemonOnDeck == null){
			System.out.println("No pokemon on deck to switch out!");
			return false;
		}
		else{
			System.out.println(currentPokemon + " has been switched out for " + pokemonOnDeck);
			currentPokemon = pokemonOnDeck;
			pokemonOnDeck = null;
			return true;
		}
	}

	public Pokemon getPokemonOnDeck(){
		return pokemonOnDeck;
	}

	public void setPokemonOnDeck(Pokemon pokemonOnDeck){
		this.pokemonOnDeck = pokemonOnDeck;
	}

	public Action getAction(){
		return action;
	}

	public void setAction(Action action){
		this.action = action;
	}

	public void takeOutFirstPokemon(){ // first pokemon in party is sent out
		currentPokemon = party.getPokemon(1);
		pokemonOnDeck = null;
	}

	public void putAwayAllPokemon(){
		currentPokemon = null;
		pokemonOnDeck = null;
	}

	@Override
	public String toString(){
		return name;
	}
}
