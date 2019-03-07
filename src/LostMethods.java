
public class LostMethods { //these methods don't have a home. Just yet.
	
	//I'm not sure where i should put these
	public static final Action bag = new Action("Bag", 6);
	public static final Action switchPokemon = new Action("Switch Pokemon", 6);
	public static final Action flee = new Action("Flee", -7);
	public static final Action none = new Action("None", -10);
	
	
	public static void printMoveSet(Pokemon pokemon){
		System.out.println("-------" + pokemon + "'s Move Set-------");
		System.out.print("[1] ");pokemon.move1.printMove();
		System.out.print("[2] ");pokemon.move2.printMove();
		System.out.print("[3] ");pokemon.move3.printMove();
		System.out.print("[4] ");pokemon.move4.printMove();
		System.out.println("------------------------------");
	}
	
	public static void printHealth(Pokemon pokemon){
		System.out.println(pokemon + "'s HP is: " + pokemon.stats.hpRemaining + "/" + pokemon.stats.totalHP + ", Status Ailment: " + pokemon.getStatus());
		if(pokemon.getStatus() != Status.NONE){
			System.out.println(" Turns of status ailment left: " + pokemon.statusTurnsRemaining);
		}
	}
	
	public static void healStatus(Pokemon pokemon){ //Full Heal
		Status.removeStatus(pokemon);
	}
	
	public static void healHP(Pokemon pokemon){ //Max Potion
		pokemon.stats.hpRemaining = pokemon.stats.totalHP;
		
		System.out.println(pokemon + " has been restored to full health.");
	}
	
	public static void fillPP(Pokemon pokemon, int moveNumber){ //Max Ether
		switch(moveNumber){
		case 1:pokemon.move1.ppLeft = pokemon.move1.totalPP;break;
		case 2:pokemon.move1.ppLeft = pokemon.move1.totalPP;break;
		case 3:pokemon.move1.ppLeft = pokemon.move1.totalPP;break;
		case 4:pokemon.move1.ppLeft = pokemon.move1.totalPP;break;
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
