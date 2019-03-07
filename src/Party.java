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
				updatePartyCount();
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
				if(p.getStatus() != Status.FAINTED){
					return false;
				}
			}
		}
		System.out.println("The party has no more usable pokemon.");
		return true; //no pokemon has hp left
	}

	public boolean indexWithinParty(int index){
		if(partyCount == 0){
			System.out.println("The party is empty!");
			return false;
		}
		else if(index >= 0 && index < partyCount){
			return true;
		}
		else{
			System.out.println("Invalid partyArray index argument given. (Must be 0 through " + (partyCount-1) + ")");
			return false;
		}
	}
	
	public void printParty(){
		for(int i = 0; i < partyArray.length; i++){
			if(partyArray[i] != null){
				System.out.print("["+ (i+1) + "] " + partyArray[i] + ": HP(" + partyArray[i].stats.hpRemaining + "/" + partyArray[i].stats.totalHP + ")");
				if(partyArray[i].getStatus() != Status.NONE){
					System.out.println(" {" + partyArray[i].getStatus() + "}");
				}
				else{
					System.out.println();
				}
			}
		}
	}
	
	public void updatePartyCount(){
		int count = 0;
		for(int i = 0; i < partyArray.length; i++){
			if(partyArray[i] != null){
				count++;
			}
		}
		partyCount = count;
	}
	
	public Pokemon getPokemon(int partyIndex) {
		if(indexWithinParty(partyIndex)){
			return(partyArray[partyIndex]);
		}
		return null;
	}
	
	//this method should only be used to create trainers from nothing
	public boolean setPokemon(Pokemon pokemon, int partyArrayIndex){
		if(partyArrayIndex >= 0 && partyArrayIndex < partyArray.length){
			if(partyArray[partyArrayIndex] == null) {
				partyArray[partyArrayIndex] = pokemon;
				updatePartyCount();
				return true;
			}
			else{
				System.out.println("within setPokemon() partyArray[" + partyArrayIndex + "] already contains a pokemon");
				return false;
			}
		}
		else{
			System.out.println("within setPokemon() invalid index given");
			return false;
		}
	}
}
