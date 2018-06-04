

public class Pokemon {

	String name;
	Species species;
	int level;
	int experience;
	Move move1;
	Move move2;
	Move move3;
	Move move4;
	Stats permenantStats;
	Stats battleStats;
	
	public Pokemon(String name,Species species){
		this.name = name;
		this.species = species;
	}
}