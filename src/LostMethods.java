
public class LostMethods { //these methods don't have a home. Just yet.
	
	//I'm not sure where i should put these
	public static final Action bag = new Action("Bag", 6);
	public static final Action switchPokemon = new Action("Switch Pokemon", 7);
	public static final Action flee = new Action("Flee", -7);
	public static final Action none = new Action("None", -10);
	
	
	public static void showMoveSet(Pokemon pokemon){
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
				System.out.print("[" + i + "] " + move + ": " + move.type + ", " + move.moveCategory); //This part is the same for each moveCategory
				if(move.moveCategory == MoveCategory.STATUS){
					System.out.println(", " + move.statusInflicted + ", PP(" + ppLeft + "/" + move.basePP + "), Accuracy(" + move.accuracy + ")");
				}
				else if(move.moveCategory == MoveCategory.STAT){
					System.out.println(", " + move.statAffected + ", Stage Increase(" + move.stageIncrease + "), PP(" + ppLeft + "/" + move.basePP + "), Accuracy(" + move.accuracy + ")");
				}
				else{
					System.out.println(", PP(" + ppLeft + "/" + move.basePP + "), Power(" + move.power + "), Accuracy(" + move.accuracy + ")");
				}
			}
		}
	}
	
	public static void showStats(Pokemon pokemon){
		System.out.println("..... " + pokemon + "'s Stats .....");
		System.out.println("Attack: Fixed(" + pokemon.stats.attack + "), Stage(" + pokemon.stats.stages.attack + "), Multiplier(" + pokemon.stats.multipliers.attack + ")");
		System.out.println("Deffense: Fixed(" + pokemon.stats.deffense + "), Stage(" + pokemon.stats.stages.deffense + "), Multiplier(" + pokemon.stats.multipliers.deffense + ")");
		System.out.println("SpAtk: Fixed(" + pokemon.stats.spAtk + "), Stage(" + pokemon.stats.stages.spAtk + "), Multiplier(" + pokemon.stats.multipliers.spAtk + ")");
		System.out.println("SpDef: Fixed(" + pokemon.stats.spDef + "), Stage(" + pokemon.stats.stages.spDef + "), Multiplier(" + pokemon.stats.multipliers.spDef + ")");
		System.out.println("Speed: Fixed(" + pokemon.stats.speed + "), Stage(" + pokemon.stats.stages.speed + "), Multiplier(" + pokemon.stats.multipliers.speed + ")");
		System.out.println("Accuracy: Stage(" + pokemon.stats.stages.accuracy + "), Multiplier(" + pokemon.stats.multipliers.accuracy + ")");
		System.out.println("Evasion: Stage(" + pokemon.stats.stages.evasion + "), Multiplier(" + pokemon.stats.multipliers.evasion + ")");
		System.out.println("..........................");
	}
	
	
	public static void showHealth(Pokemon pokemon){
		System.out.println(pokemon + "'s HP is: " + pokemon.hpRemaining + "/" + pokemon.stats.totalHP + ", Status Ailment: " + pokemon.getStatus());
		System.out.println(" Turns of status ailment left: " + pokemon.statusTurnsRemaining);
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
