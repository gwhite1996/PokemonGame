package main;

class ItemList {

	final static ItemType potion = new ItemType("Potion", ItemCategory.USEDONSELF) {

		@Override
		void itemEffect(Pokemon targetPokemon) {
			healItem(targetPokemon, 20);
		}
	};
	final static ItemType superPotion = new ItemType("Super Potion", ItemCategory.USEDONSELF) {

		@Override
		void itemEffect(Pokemon targetPokemon) {
			healItem(targetPokemon, 50);
		}
	};
	final static ItemType hyperPotion = new ItemType("Hyper Potion", ItemCategory.USEDONSELF) {

		@Override
		void itemEffect(Pokemon targetPokemon) {
			healItem(targetPokemon, 200);
		}
	};
	final static ItemType maxPotion = new ItemType("Max Potion", ItemCategory.USEDONSELF) {

		@Override
		void itemEffect(Pokemon targetPokemon) {
			healItem(targetPokemon, targetPokemon.stats.totalHP);
		}
	};
	final static ItemType fullRestore = new ItemType("Full Restore", ItemCategory.USEDONSELF) {

		@Override
		void itemEffect(Pokemon targetPokemon) {
			healItem(targetPokemon, targetPokemon.stats.totalHP);
		}
	};
	final static ItemType fullHeal = new ItemType("Full Heal", ItemCategory.USEDONSELF) {

		@Override
		void itemEffect(Pokemon targetPokemon) {
			Status.removeStatus(targetPokemon);
		}
	};
	final static ItemType pokeBall = new ItemType("Poke Ball", ItemCategory.OTHER) {

		@Override
		void itemEffect() {
			System.out
					.println("You can't catch another trainer's pokemon you thieving bastard! You wasted a Poke Ball.");
		}
	};
	final static ItemType bike = new ItemType("Bike", ItemCategory.OUTOFBATTLE) {

		@Override
		void itemEffect() {
			System.out.println("This is where ya boi would be hoppin on his bike and goin real fast.");
		}
	};
}
