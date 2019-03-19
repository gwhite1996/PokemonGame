package gw96_pokemon;

class Pokemon{

	private String name;
	Species species;
	int level;
	int experience;
	Move move1;
	Move move2;
	Move move3;
	Move move4;
	Stats stats;
	private Status status;
	int statusTurnsRemaining;
	boolean canAttack;

	Pokemon(String name, Species species){
		this.name = name;
		this.species = species;
		canAttack = true;
		status = Status.NONE;
		move1 = MoveList.none;
		move2 = MoveList.none;
		move3 = MoveList.none;
		move4 = MoveList.none;
	}

	void swapMoves(int firstMoveNumber, int secondMoveNumber){
		Move firstMove;
		Move secondMove;
		switch(firstMoveNumber){
		case 1:
			firstMove = move1;
			break;
		case 2:
			firstMove = move2;
			break;
		case 3:
			firstMove = move3;
			break;
		case 4:
			firstMove = move4;
			break;
		default:
			System.out.println("Invalid moveNumber in swapMoves()");
			return;
		}
		switch(secondMoveNumber){
		case 1:
			secondMove = move1;
			break;
		case 2:
			secondMove = move2;
			break;
		case 3:
			secondMove = move3;
			break;
		case 4:
			secondMove = move4;
			break;
		default:
			System.out.println("Invalid moveNumber in swapMoves()");
			return;
		}
		Move tempMove = firstMove; // swaps moves
		firstMove = secondMove;
		secondMove = tempMove;
	}

	boolean hasPPLeft(){ // if false, only struggle can be used
		if(move1.getPPLeft() > 0)
			return true;
		if(move2.getPPLeft() > 0)
			return true;
		if(move3.getPPLeft() > 0)
			return true;
		if(move4.getPPLeft() > 0)
			return true;
		return false;
	}

	void viewSummary(){
		System.out.println("=======" + this + "'s Summary=======");
		stats.printStats();
		System.out.println("====================================");
	}

	Status getStatus(){
		return status;
	}

	void setStatus(Status status){
		this.status = status;
	}

	void setMove(MoveName moveName, int moveNumber){
		switch(moveNumber){
		case 1:
			move1 = new Move(moveName);
			break;
		case 2:
			move2 = new Move(moveName);
			break;
		case 3:
			move3 = new Move(moveName);
			break;
		case 4:
			move4 = new Move(moveName);
			break;
		default:
			System.out.println("Invalid moveNumber in setMove()");
			return;
		}
	}

	@Override
	public String toString(){
		return name;
	}
}