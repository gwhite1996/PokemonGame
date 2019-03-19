package main;

class Party{

	final static int maxPartySize = 6;
	private Pokemon[] partyArray = new Pokemon[maxPartySize];
	private int partyCount;

	Party(){
		partyCount = 0; // empty party
	}

	void add(Pokemon pokemon){
		for(int i = 0; i < partyArray.length; i++){
			if(partyArray[i] == null){
				partyArray[i] = pokemon;
				System.out.println(pokemon + " added.");
				updatePartyCount();
				return;
			}
		}
	}

	// simply selects a pokemon in the party
	Pokemon choosePokemon(){
		Pokemon selectedPokemon = null;
		while(selectedPokemon == null){
			LostMethods.printReturnOption();
			printParty();
			int choice = LostMethods.chooseOption(0, partyCount);
			if(choice == 0){
				return null;
			}
			else{
				selectedPokemon = getPokemon(choice);
			}
		}
		return selectedPokemon;
	}

	void swapSpotsWithinParty(int partyIndex1, int partyIndex2){
		if(indexWithinParty(partyIndex1) && indexWithinParty(partyIndex2)){
			Pokemon temp = partyArray[partyIndex1];
			partyArray[partyIndex1] = partyArray[partyIndex2];
			partyArray[partyIndex2] = temp;
			System.out.println(partyArray[partyIndex2] + " was swapped with " + partyArray[partyIndex2]);
		}
	}

	boolean allFainted(){
		for(Pokemon p : partyArray){
			if(p != null){
				if(p.getStatus() != Status.FAINTED){
					return false;
				}
			}
		}
		return true; // no pokemon has hp left
	}

	boolean indexWithinParty(int index){
		if(index >= 0 && index < partyCount){
			return true;
		}
		else if(partyCount == 0){
			System.out.println("The party is empty!");
		}
		else{
			System.out.println("The party only contains " + partyCount + " pokemon.");
		}
		return false;
	}

	// called ALWAYS when a pokemon is added or removed from a party
	void updatePartyCount(){
		int count = 0;
		for(int i = 0; i < partyArray.length; i++){
			if(partyArray[i] != null){
				count++;
			}
		}
		partyCount = count;
	}

	Pokemon getPokemon(int partyNumber){
		if(indexWithinParty(partyNumber - 1)){
			return (partyArray[partyNumber - 1]);
		}
		return null;
	}

	// this method should only be used to create trainers from nothing
	boolean setPokemon(Pokemon pokemon, int partyArrayIndex){
		if(partyArrayIndex >= 0 && partyArrayIndex < partyArray.length){
			if(partyArray[partyArrayIndex] == null){
				partyArray[partyArrayIndex] = pokemon;
				updatePartyCount();
				return true;
			}
			else{
				System.out
						.println("within setPokemon() partyArray[" + partyArrayIndex + "] already contains a pokemon");
				return false;
			}
		}
		else{
			System.out.println("within setPokemon() invalid index given");
			return false;
		}
	}

	int getPartyCount(){
		return partyCount;
	}

	void printParty(){
		for(int i = 0; i < partyArray.length; i++){
			if(partyArray[i] != null){
				System.out.print("[" + (i + 1) + "] " + partyArray[i] + ": HP(" + partyArray[i].stats.hpRemaining + "/"
						+ partyArray[i].stats.totalHP + ")");
				if(partyArray[i].getStatus() != Status.NONE){
					System.out.println(" {" + partyArray[i].getStatus() + "}");
				}
				else{
					System.out.println();
				}
			}
		}
	}
}
