import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Trainer extends TurnablePiece{
	private String name;
	private Action action;
	
	String greeting;
	String battleIntro;
	String battleDefeated;
	String afterBattle;
	
	Scanner in = new Scanner(System.in); // should probably be in a higher up class
	
	
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
		
		LostMethods.printMoveSet(user);
		System.out.println(" Chose the move for " + user + " to use:  (Type 1 through 4 then press Enter)");
		System.out.println(" Type 0 to exit");

		Move moveUsed;
		do{
			moveUsed = MoveList.none;
			try{
				switch(in.nextInt()){ //User input selects move to use
				case 0:return false;
				case 1:moveUsed = user.move1;break;
				case 2:moveUsed = user.move2;break;
				case 3:moveUsed = user.move3;break;
				case 4:moveUsed = user.move4;break;
				default:;System.out.println("Invalid input int! Must be 0 through 4");
				}
				if(!(moveUsed == MoveList.none) && moveUsed.ppLeft <= 0){
					System.out.println(moveUsed + " has no PP left!");
				}
			}
			catch(InputMismatchException e){
				System.out.println("The input must be an integer.");
				in.next(); //shifts focus to the next thing typed (avoids infinite loop)
			}
		}
		while(moveUsed.ppLeft <= 0);
		setAction(moveUsed); //sets action so the loop in selectAction() ends
		return true;
	}
	
	public boolean switchPokemon(){
		int index = -1;
		do{
			Pokemon pokemonSelected;
			try{
				party.printParty();
				System.out.println(" Type the index of the pokemon to bring out and then press enter.");
				System.out.println(" Type 0 to exit");
				
				
				index = in.nextInt();
				switch(index){ //User input selects move to use
				case 0:return false;
				case 1:pokemonSelected = party.getPokemon(1);break;
				case 2:pokemonSelected = party.getPokemon(2);break;
				case 3:pokemonSelected = party.getPokemon(3);break;
				case 4:pokemonSelected = party.getPokemon(4);break;
				case 5:pokemonSelected = party.getPokemon(5);break;
				case 6:pokemonSelected = party.getPokemon(6);break;
				default:;System.out.println("Invalid input int! Must be 0 through 6");
				}
			}
			catch(InputMismatchException e){
				System.out.println("The input must be an integer.");
				in.next(); //shifts focus to the next thing typed (avoids infinite loop)
			}
		}
		while(index < 0 || index > 6);
		//setAction(moveUsed); //sets action so the loop in selectAction() ends
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
	
}
