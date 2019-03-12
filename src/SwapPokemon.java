public class SwapPokemon extends Action{

	private Pokemon nextPokemon;

	public SwapPokemon(Pokemon pokemon){
		super("Swapping out " + pokemon, 6);
		nextPokemon = pokemon;
	}

	Pokemon getNextPokemon(){
		return nextPokemon;
	}
}
