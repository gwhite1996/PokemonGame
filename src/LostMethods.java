
public class LostMethods { //these methods don't have a home. Just yet.
	
	//I'm not sure where i should put these
	public static final Action bag = new Action("Bag", 6);
	public static final Action switchPokemon = new Action("Switch Pokemon", 7);
	public static final Action flee = new Action("Flee", -7);
	public static final Action none = new Action("None", -10);
	
	
	public static void printMoveSet(Pokemon pokemon){
		System.out.println("-------" + pokemon + "'s Move Set-------");
		Move move;
		int ppLeft;
		for(int i=1; i<=4; i ++){ //Prints each move that isn't "None"
			switch(i){
			case 1:move = pokemon.move1; ppLeft = pokemon.move1PP; break;
			case 2:move = pokemon.move2; ppLeft = pokemon.move2PP; break;
			case 3:move = pokemon.move3; ppLeft = pokemon.move3PP; break;
			default :move = pokemon.move4; ppLeft = pokemon.move4PP; break;
			}
			if(!move.toString().equals("None")){
				System.out.println("[" + i + "] " + move + ": " + move.type + ", " + move.moveCategory + ", PP(" + ppLeft + "/" + move.basePP + "), Power(" + move.power + "), Accuracy(" + move.accuracy + ")");
			}
		}
	}
	
	
	
	
	public static void printHealth(Pokemon pokemon){
		System.out.println(pokemon + "'s HP is: " + pokemon.hpRemaining + "/" + pokemon.stats.totalHP + ", Status Ailment: " + pokemon.getStatus());
		if(pokemon.getStatus() != Status.NONE){
			System.out.println(" Turns of status ailment left: " + pokemon.statusTurnsRemaining);
		}
	}
	
	public static void healStatus(Pokemon pokemon){ //Full Heal
		Status.removeStatus(pokemon);
	}
	
	public static void healHP(Pokemon pokemon){ //Max Potion
		pokemon.hpRemaining = pokemon.stats.totalHP;
		
		System.out.println(pokemon + " has been restored to full health.");
	}
	
	public static void fillPP(Pokemon pokemon, int moveNumber){ //Max Ether
		switch(moveNumber){
		case 1:pokemon.move1PP = pokemon.move1.basePP;break;
		case 2:pokemon.move2PP = pokemon.move2.basePP;break;
		case 3:pokemon.move3PP = pokemon.move3.basePP;break;
		case 4:pokemon.move4PP = pokemon.move4.basePP;break;
		default:System.out.println("Invalid moveNumber in fillPP()");
		}
	}
	
	public static void fillAllPP(Pokemon pokemon){ //Max Elixer
		for(int i=1; i<=4; i++){
			fillPP(pokemon ,i);
		}
	}
	
	public static void healFull(Pokemon pokemon){ //removes status effects, restores HP and PP
		healStatus(pokemon);
		healHP(pokemon);
		fillAllPP(pokemon);
	}
	
	public static void pokemonCenter(Party party){ //completely heals entire party
		for(Pokemon p: party.partyArray){
			if(p != null){
				healFull(p);
			}
		}
		System.out.println("The entire party has been healed!");
	}
}
