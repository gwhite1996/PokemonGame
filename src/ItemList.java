
public class ItemList {
	
	
	final static Item potion = new Item("Potion"){
		public void itemEffect(Pokemon targetPokemon){
			healItem(targetPokemon, 20);
		}
	};
	
	final static Item pokeBall = new Item("Poke Ball"){
		public void itemEffect(Pokemon targetPokemon){
			System.out.println("You can't catch another trainer's pokemon you thieving bastard! You wasted a Poke Ball.");
		}
	};
}
