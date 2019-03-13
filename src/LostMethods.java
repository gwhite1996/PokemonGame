import java.util.InputMismatchException;
import java.util.Scanner;

public class LostMethods{ // these methods don't have a home. Just yet.

	static Scanner in = new Scanner(System.in);
	// I'm not sure where i should put these
	public static final Action swapingPokemon = new Action("Swaping Pokemon", 6);
	public static final Action fleeing = new Action("Fleeing", -7);
	public static final Action none = new Action("None", -10);

	public static void healStatus(Pokemon pokemon){ // Full Heal
		Status.removeStatus(pokemon);
	}

	public static void healHP(Pokemon pokemon){ // Max Potion
		pokemon.stats.hpRemaining = pokemon.stats.totalHP;
		System.out.println(pokemon + " has been restored to full health.");
	}

	public static void fillPP(Pokemon pokemon, int moveNumber){ // Max Ether
		switch(moveNumber){
		case 1:
			pokemon.move1.setPPLeft(pokemon.move1.getTotalPP());
			break;
		case 2:
			pokemon.move2.setPPLeft(pokemon.move2.getTotalPP());
			break;
		case 3:
			pokemon.move3.setPPLeft(pokemon.move3.getTotalPP());
			break;
		case 4:
			pokemon.move4.setPPLeft(pokemon.move4.getTotalPP());
			break;
		default:
			System.out.println("Invalid moveNumber in fillPP()");
		}
	}

	public static void fillAllPP(Pokemon pokemon){ // Max Elixer
		for(int i = 1; i <= 4; i++){
			fillPP(pokemon, i);
		}
	}

	public static void healFull(Pokemon pokemon){ // removes status effects, restores HP and PP
		healStatus(pokemon);
		healHP(pokemon);
		fillAllPP(pokemon);
	}

	public static void pokemonCenter(Party party){ // completely heals entire party
		for(int i = 1; i < party.getPartyCount(); i++){
			Pokemon p = party.getPokemon(i);
			if(p != null){
				healFull(p);
			}
		}
		System.out.println("The entire party has been healed!");
	}

	// prevents need for a new try/catch and scanner each time an integer is needed from the user
	public static int chooseOption(int min, int max){
		int index = -1;
		boolean outOfRange = true;
		while(outOfRange){ // said to be bad practice but seems to be simplest solution here
			System.out.println("Type a number from " + min + " to " + max + " then press enter.");
			try{
				index = in.nextInt();
			}
			catch(InputMismatchException e){
				System.out.println("The input must be an integer.");
				in.next(); // shifts focus to the next thing typed (avoids infinite loop)
				continue;
			}
			if(index >= min && index <= max){
				outOfRange = false;
			}
			else{
				System.out.println("Number out of range.");
			}
		}
		return index;
	}

	public static void printMoveSet(Pokemon pokemon){
		System.out.println("-------" + pokemon + "'s Move Set-------");
		System.out.print("[1] ");
		pokemon.move1.printMove();
		System.out.print("[2] ");
		pokemon.move2.printMove();
		System.out.print("[3] ");
		pokemon.move3.printMove();
		System.out.print("[4] ");
		pokemon.move4.printMove();
		System.out.println("------------------------------");
	}

	public static void printHealth(Pokemon pokemon){
		System.out.println(pokemon + "'s HP is: " + pokemon.stats.hpRemaining + "/" + pokemon.stats.totalHP
				+ ", Status Ailment: " + pokemon.getStatus());
		if(pokemon.getStatus() != Status.NONE){
			System.out.println(" Turns of status ailment left: " + pokemon.statusTurnsRemaining);
		}
	}

	public static void printReturnOption(){
		System.out.println("[0] Return");
	}
}
