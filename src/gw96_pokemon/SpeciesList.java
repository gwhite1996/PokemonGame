package gw96_pokemon;

class SpeciesList{

	final static Species bulbasaur = new Species("Bulbasaur", Type.GRASS, Type.POISON);
	final static Species ivysaur = new Species("Ivysaur", Type.GRASS, Type.POISON);
	final static Species venusaur = new Species("Venusaur", Type.GRASS, Type.POISON);
	final static Species charmander = new Species("Charmander", Type.FIRE);
	final static Species charmeleon = new Species("Charmeleon", Type.FIRE);
	final static Species charizard = new Species("Charizard", Type.FIRE, Type.FLYING);
	final static Species squirtle = new Species("Squirtle", Type.WATER);
	final static Species wartortle = new Species("Wartortle", Type.WATER);
	final static Species blastoise = new Species("Blastoise", Type.WATER);
	final static Species caterpie = new Species("Caterpie", Type.BUG);
	final static Species metapod = new Species("Metapod", Type.BUG);
	final static Species butterfree = new Species("Butterfree", Type.BUG, Type.FLYING);
	final static Species pikachu = new Species("Pikachu", Type.ELECTRIC);
	final static Species articuno = new Species("Articuno", Type.ICE, Type.FLYING);

	static void createSpeciesList(){
		setAllEvolutions();
		setAllStats();
	}

	static void setAllEvolutions(){ // temp. there has to be a better way to write this
		bulbasaur.SetEvolution(ivysaur, 16);
		ivysaur.SetEvolution(venusaur, 32);
		charmander.SetEvolution(charmeleon, 16);
		charmeleon.SetEvolution(charizard, 36);
		squirtle.SetEvolution(wartortle, 16);
		wartortle.SetEvolution(blastoise, 36);
		caterpie.SetEvolution(metapod, 7);
		metapod.SetEvolution(butterfree, 10);
	}

	static void setAllStats(){
		bulbasaur.setBaseStats(new Stats(45, 49, 49, 65, 65, 45));
		ivysaur.setBaseStats(new Stats(60, 62, 63, 80, 80, 60));
		venusaur.setBaseStats(new Stats(80, 82, 83, 100, 100, 80));
		charmander.setBaseStats(new Stats(39, 52, 43, 60, 50, 65));
		charmeleon.setBaseStats(new Stats(58, 64, 58, 80, 65, 80));
		charizard.setBaseStats(new Stats(78, 84, 78, 109, 85, 100));
		squirtle.setBaseStats(new Stats(44, 48, 65, 50, 64, 43));
		wartortle.setBaseStats(new Stats(59, 63, 80, 65, 80, 58));
		blastoise.setBaseStats(new Stats(79, 83, 100, 85, 105, 78));
		caterpie.setBaseStats(new Stats(45, 30, 35, 20, 20, 45));
		metapod.setBaseStats(new Stats(50, 20, 55, 25, 25, 30));
		butterfree.setBaseStats(new Stats(60, 45, 50, 90, 80, 70));
		pikachu.setBaseStats(new Stats(35, 55, 40, 50, 50, 90));
		articuno.setBaseStats(new Stats(90, 85, 100, 95, 125, 85));
	}
}
