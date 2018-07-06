

public class Pokemon {

	String name;
	Species species;
	int level;
	int experience;
	Move move1;
	Move move2;
	Move move3;
	Move move4;
	Stats stats;
	StatStages statStages;
	
	public Pokemon(String name,Species species){
		this.name = name;
		this.species = species;
		statStages = new StatStages(); //set to 0 when a pokemon is created and after each battle
	}
	
	public void setMove(Move move, int moveNumber){
		switch(moveNumber){
		case 1: move1 = move;return;
		case 2: move2 = move;return;
		case 3: move3 = move;return;
		case 4: move4 = move;return;
		default: System.out.println("Invalid moveNumber given to useMove()");
		}
	}
}