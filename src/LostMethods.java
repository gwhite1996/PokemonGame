
public class LostMethods { //these methods don't have a home. Just yet.
	
	static void heal(Pokemon pokemon){ //removes status effects, restores HP and PP
		pokemon.status = Status.NONE;
		pokemon.hpRemaining = pokemon.stats.totalHP;
		pokemon.move1PP = pokemon.move1.basePP;
		pokemon.move2PP = pokemon.move2.basePP;
		pokemon.move3PP = pokemon.move3.basePP;
		pokemon.move4PP = pokemon.move4.basePP;
		System.out.println(pokemon.name + " has been restored to full health.");
	}
	
	static void pokemonCenter(Party party){ //heals entire party
		for(Pokemon p: party.partyArray){
			heal(p);
		}
		System.out.println("The entire party has been healed!");
	}
}
