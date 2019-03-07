
public class ItemList {
	
	
	final static Item potion = new Item("Potion", ItemType.USEDONSELF){
		public void itemEffect(Pokemon targetPokemon){
			healItem(targetPokemon, 20);
		}
	};
	
	final static Item pokeBall = new Item("Poke Ball", ItemType.OTHER){
		public void itemEffect(){
			System.out.println("You can't catch another trainer's pokemon you thieving bastard! You wasted a Poke Ball.");
		}
	};
}
