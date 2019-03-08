

public class Species { //all pokemon are hardy nature with 0 EV and IV
	String speciesName;
	Type type1;
	Type type2;
	Species nextEvolution;
	int evolutionLevel;
	Stats baseStats;
	
	
	public Species(String speciesName, Type type){
		this.speciesName = speciesName;
		this.type1 = type;
		this.type2 = Type.NONE;
	}
	public Species(String speciesName, Type type1, Type type2){
		this.speciesName = speciesName;
		this.type1 = type1;
		this.type2 = type2;
	}
	
	public void SetEvolution(Species nextEvolution, int evolutionLevel){
		this.nextEvolution = nextEvolution;
		this.evolutionLevel = evolutionLevel;
	}
	public void setBaseStats(Stats baseStats) {
		this.baseStats = baseStats;
	}
	
	public String getName(){
		return speciesName;
	}
	@Override
	public String toString(){
		return getName();
	}
	
}
