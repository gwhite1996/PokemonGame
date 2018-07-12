import java.util.ArrayList;


public class Party{
	Pokemon[] partyArray = new Pokemon[6];
	int partyCount;


	public Party(){
		partyCount = 0; //empty party
	}
	public void setPokemon(Pokemon pokemon, int partyArrayIndex){
		if(partyArrayIndex >= 1 && partyArrayIndex <= 6){
			partyArray[partyArrayIndex] = pokemon;
		}
		else {
			System.out.println("Invalid partyNumber argument givent in setPokemon");
		}
		//Count should increase if a pokemon is added
	}
	public void add(Pokemon pokemon){

		for(int i = 0; i < partyArray.length; i++) {
			if(partyArray[i] == null){
				partyArray[i] = pokemon;
				System.out.println(pokemon + " added.");
				return;
			}
		}
	}
	public void printParty(){
		for(Pokemon p: partyArray){
			if(p != null)System.out.print(p + ", ");
		}
		System.out.println(); //prints new line
	}
}
