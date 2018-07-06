
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

		Battle battle1 = new Battle(player, mike);

		battle1.setPlayerPokemon(sparky);
		battle1.setEnemyPokemon(juicer);

		
		Move thunderShock = new Move("Thunder Shock", Type.ELECTRIC, 40, 100, 30);
		Move waterGun = new Move("Water Gun", Type.WATER, 40, 100, 25);
		Move thunder = new Move("Thunder", Type.ELECTRIC, 110, 70, 10);
		Move hydroPump = new Move("Hydro Pump", Type.WATER, 110, 80, 5);

		sparky.setMove(thunderShock, 1);
		sparky.setMove(thunder, 2);
		juicer.setMove(waterGun, 1);
		juicer.setMove(hydroPump, 2);





		battle1.testTwoTurns();
		//Type.printAllTypeInfo();
	}

}
