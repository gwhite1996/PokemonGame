package main;

enum Direction{
	NORTH("north"),
	EAST("east"),
	SOUTH("south"),
	WEST("west");

	private String name = null;

	private Direction(String s){
		name = s;
	}

	String getName(){
		return name;
	}

	@Override
	public String toString(){
		return getName();
	}
}