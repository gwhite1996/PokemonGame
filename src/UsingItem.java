public class UsingItem extends Action{

	private Item itemUsed;
	private Pokemon targetPokemon;

	public UsingItem(Item item, Pokemon pokemon){
		super("Using " + item + " on " + pokemon, 6);
		itemUsed = item;
		targetPokemon = pokemon;
	}

	Item getItemUsed(){
		return itemUsed;
	}

	Pokemon getTargetPokemon(){
		return targetPokemon;
	}
}