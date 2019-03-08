
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
	
	//simply selects a pokemon in the party
	public Pokemon choosePokemon(){
		Pokemon selectedPokemon = null;

		while(selectedPokemon == null){
			LostMethods.printReturnOption();
			printParty();
			
			int choice = LostMethods.chooseOption(0,partyCount);
			if(choice == 0){
				return null;
			}
			else{
				selectedPokemon = getPokemon(choice);
			}
		}
		return selectedPokemon;
	}
		
	
	//returns the pokemon to be swapped out. null if the user decides to keep the current pokemon
	public Pokemon swapFromParty(boolean mustSwap){
		Pokemon selectedPokemon = null;
		System.out.println("Select a pokemon to swap out.");
		
		while(selectedPokemon == null){
			selectedPokemon = choosePokemon();
			
			if(selectedPokemon == null){
				if(!mustSwap){
					return null;
				}
				else {
					System.out.println("You must select a pokemon to swap out!");
				}
			}
			else{
				int choice = -1;
				while(choice != 0){
					System.out.println(" What do you want to do with: " + selectedPokemon + "?");
					LostMethods.printReturnOption();
					System.out.println("[1] Summary");
					System.out.println("[2] Swap Out");
					
					switch(choice = LostMethods.chooseOption(0, 2)){
					case 0:selectedPokemon = null; break;
					case 1:selectedPokemon.viewSummary(); break;
					case 2:
						if(selectedPokemon.getStatus() != Status.FAINTED){
							return selectedPokemon;
						}
						else {
							System.out.println(selectedPokemon + " has no HP remaining!");
						}
						break;
					}
				}
			}
		}
		System.out.println("Within swapFromParty() Im fairly certain this is impossible to reach");
		return null;
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
		if(indexWithinParty(partyIndex - 1)){
			return(partyArray[partyIndex -1]);
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
