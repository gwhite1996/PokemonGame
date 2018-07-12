

public class Pokemon {

	private String name;
	Species species;
	int level;
	int experience;
	Move move1;
	Move move2;
	Move move3;
	Move move4;
	int move1PP;
	int move2PP;
	int move3PP;
	int move4PP;
	Stats stats;
	private Status status;
	int statusTurnsRemaining;
	int hpRemaining;
	boolean canAttack;
	
	
	public Pokemon(String name,Species species){
		this.name = name;
		this.species = species;
		canAttack = true;
		
		status = Status.NONE;
		
		setMove(new Move("None", Type.NONE, MoveCategory.NONE, 0, 0, 0), 1); //initializes all moves to NONE
		setMove(new Move("None", Type.NONE, MoveCategory.NONE, 0, 0, 0), 2);
		setMove(new Move("None", Type.NONE, MoveCategory.NONE, 0, 0, 0), 3);
		setMove(new Move("None", Type.NONE, MoveCategory.NONE, 0, 0, 0), 4);
	}
	
	public void setMove(Move move, int moveNumber){
		switch(moveNumber){
		case 1: move1 = move;
		move1PP = move1.basePP;return;
		case 2: move2 = move;
		move2PP = move2.basePP;return;
		case 3: move3 = move;
		move3PP = move3.basePP;return;
		case 4: move4 = move;
		move4PP = move4.basePP;return;
		default: System.out.println("Invalid moveNumber given to useMove()");
		}
	}
	
	public void swapMoves(int firstMoveNumber, int secondMoveNumber){
		Move firstMove;
		Move secondMove;
		
		switch(firstMoveNumber){
		case 1:firstMove = move1;break;
		case 2:firstMove = move2;break;
		case 3:firstMove = move3;break;
		case 4:firstMove = move4;break;
		default: System.out.println("Invalid moveNumber in swapMoves()");return;
		}
		
		switch(secondMoveNumber){
		case 1:secondMove = move1;break;
		case 2:secondMove = move2;break;
		case 3:secondMove = move3;break;
		case 4:secondMove = move4;break;
		default: System.out.println("Invalid moveNumber in swapMoves()");return;
		}
		
		Move tempMove = firstMove; //swaps moves
		firstMove = secondMove;
		secondMove = tempMove;
		
		int tempPP = move1PP; //swaps PP remaining
		move1PP = move2PP;
		move2PP = tempPP;
	}

	public Status getStatus() {
		return status;
	}

	void setStatus(Status status) {
		this.status = status;
	}
	
	@Override
	public String toString(){
		return name;
	}
}