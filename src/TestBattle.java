public class TestBattle {

	public static void main(String[] args) {

		Player player = new Player(1, 1);
		Trainer mike = new Hiker(4,4); //arbitrary coordinates
		Species pikachu = new Species(Type.ELECTRIC);
		Species squirtle = new Species(Type.WATER);
		Species charmander = new Species(Type.FIRE);
		Species bulbasaur = new Species(Type.GRASS, Type.POISON);

		Pokemon sparky = new Pokemon("Sparky", pikachu );
		Pokemon torcher = new Pokemon("Torcher", charmander);
		Pokemon juicer = new Pokemon("Juicer", squirtle);
		Pokemon buddy = new Pokemon("Buddy", bulbasaur );

		sparky.stats = new Stats(44, 27, 21, 25, 25, 41); //level 20
		juicer.stats = new Stats(47, 24, 31, 25, 30, 22); //level 20
		
		Battle battle1 = new Battle();

		battle1.setPlayerPokemon(sparky);
		battle1.setEnemyPokemon(juicer);

		Move tackle = new Move("Tackle", Type.NORMAL, MoveCategory.PHYSICAL, 40, 100, 35);
		Move thunderShock = new Move("Thunder Shock", Type.ELECTRIC, MoveCategory.SPECIAL, 40, 100, 30);
		Move waterGun = new Move("Water Gun", Type.WATER, MoveCategory.SPECIAL, 40, 100, 25);
		Move bite = new Move("Bite", Type.DARK, MoveCategory.PHYSICAL, 60, 100, 25);
		Move thunder = new Move("Thunder", Type.ELECTRIC, MoveCategory.SPECIAL, 110, 70, 10);
		Move hydroPump = new Move("Hydro Pump", Type.WATER, MoveCategory.SPECIAL, 110, 80, 5);
		Move thunderWave = new Move("Thunder Wave", Type.ELECTRIC, Status.PARALYZED, 90, 20);
		Move tailWhip = new Move("Tail Whip", Type.NORMAL, "deffense", -1, 100, 30, false);
		
		sparky.setMove(thunderShock, 1);
		sparky.setMove(tackle, 2);
		sparky.setMove(thunderWave, 3);
		sparky.setMove(thunder, 4);
		juicer.setMove(waterGun, 1);
		juicer.setMove(tailWhip, 2);
		juicer.setMove(bite, 3);
		juicer.setMove(hydroPump, 4);
		
		LostMethods.healFull(sparky);
		LostMethods.healFull(juicer);
		
		battle1.runTillFaint();
	}

}
