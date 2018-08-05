public class Party{
	Pokemon[] partyArray = new Pokemon[6];
	int partyCount;
	
	
	public Party(){
		partyCount = 0; //empty party
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

	public void swapWithinParty(int partyIndex1, int partyIndex2){
		if(indexWithinParty(partyIndex1) && indexWithinParty(partyIndex2)){
			Pokemon temp = partyArray[partyIndex1];
			partyArray[partyIndex1] = partyArray[partyIndex2];
			partyArray[partyIndex2] = temp;
			System.out.println(partyArray[partyIndex2] + " was swapped with " + partyArray[partyIndex2]);
		}
	}
	
	
	public boolean allFainted(){
		for(Pokemon p: partyArray){
			if(p != null){
				if(p.hpRemaining > 0){
					return false;
				}
			}
		}
		System.out.println("The party has no more usable pokemon.");
		return true; //no pokemon has hp left
	}

	public boolean indexWithinParty(int index){
		if(index >= 0 && index <= 5){
			return true;
		}
		else {
			System.out.println("Invalid partyArray index argument given. (Must be 0 through 5)");
			return false;
		}
	}
	
	
	public void printParty(){
		for(int i = 0; i < 6; i++){
			if(partyArray[i] != null){
				System.out.println("["+ (i+1) + "] " + partyArray[i]);
			}
		}
	}
	
	public Pokemon getPokemon(int partyIndex) {
		if(indexWithinParty(partyIndex)){
			return(partyArray[partyIndex]);
		}
		return null;
	}
	public void setPokemon(Pokemon pokemon, int partyArrayIndex){
		if(indexWithinParty(partyArrayIndex)){
			partyArray[partyArrayIndex] = pokemon;
		}
		//Count should increase if a pokemon is added
	}
}
