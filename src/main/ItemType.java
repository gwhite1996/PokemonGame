package main;

class ItemType { // the type of item. i.e. poke ball, escape rope

	private String name;
	private ItemCategory category;

	ItemType(String name, ItemCategory category) {
		this.name = name;
		this.category = category;
	}

	void itemEffect() { // items not used on a specific pokemon
		System.out.println(this + " doesn't do anything! You must define it's itemEffect() method");
	}

	void itemEffect(Pokemon targetPokemon) {
		System.out.println(this + " doesn't do anything! You must define it's itemEffect() method");
	}

	void healItem(Pokemon target, int hpRestored) {
		if(target.getStatus() == Status.FAINTED) {
			System.out.println(this + " had no effect on the fainted " + target);
			return;
		}
		else
			if(target.stats.hpRemaining + hpRestored > target.stats.totalHP) {
				hpRestored = target.stats.totalHP - target.stats.hpRemaining;
			}
		target.stats.hpRemaining += hpRestored;
		System.out.println(target + " was healed for for " + hpRestored + " HP.");
	}

	String getName() {
		return name;
	}

	ItemCategory getItemCategory() {
		return category;
	}

	@Override
	public String toString() {
		return getName();
	}
}