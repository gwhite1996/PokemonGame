package gw96_pokemon;

enum MoveCategory{
	PHYSICAL("physical"),
	SPECIAL("special"),
	STATUS("status"),
	NONE("none");

	private String name;

	private MoveCategory(String name){
		this.name = name;
	}

	@Override
	public String toString(){
		return name;
	}
}