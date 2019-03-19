package main;

// these are not types for the user to see
enum ItemCategory{
	USEDONSELF("Used on own pokemon"), // i.e. potions, berries
	OTHER("Other battle item"), // i.e. poke ball
	OUTOFBATTLE("Only used outside of battle"), // evolution stones, escape rope, bike
	NONE("No item type");

	private String name;

	private ItemCategory(String name){
		this.name = name;
	}

	@Override
	public String toString(){
		return name;
	}
}
