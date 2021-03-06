package main;

class Species { // all pokemon are hardy nature with 0 EV and IV

	String speciesName;
	Type type1;
	Type type2;
	Species nextEvolution;
	int evolutionLevel;
	Stats baseStats;

	Species(String speciesName, Type type) {
		this.speciesName = speciesName;
		this.type1 = type;
		this.type2 = Type.NONE;
	}

	Species(String speciesName, Type type1, Type type2) {
		this.speciesName = speciesName;
		this.type1 = type1;
		this.type2 = type2;
	}

	String getName() {
		return speciesName;
	}

	void SetEvolution(Species nextEvolution, int evolutionLevel) {
		this.nextEvolution = nextEvolution;
		this.evolutionLevel = evolutionLevel;
	}

	void setBaseStats(Stats baseStats) {
		this.baseStats = baseStats;
	}

	@Override
	public String toString() {
		return getName();
	}
}
