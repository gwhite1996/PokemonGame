

public class Species { //all pokemon are hardy nature with 0 EV and IV
	String name;
	Type type1;
	Type type2;
	Species nextEvolution;
	int evolutionLevel;
	Stats baseStats;
	
	public Species(Type type){
		type1 = type;
		type2 = Type.NONE;
	}
	public Species(Type type1, Type type2){
		this.type1 = type1;
		this.type2 = type2;
	}
}
