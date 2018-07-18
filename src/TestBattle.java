public class TestBattle {

	public static void main(String[] args) {

		Player player = new Player("Player", 1, 1);
		Trainer mike = new Hiker("Hiker Mike", 4,4); //arbitrary coordinates
		
		Species pikachu = new Species(Type.ELECTRIC);
		Species squirtle = new Species(Type.WATER);
		Species charmander = new Species(Type.FIRE);
		Species bulbasaur = new Species(Type.GRASS, Type.POISON);

		Pokemon sparky = new Pokemon("Sparky", pikachu );
		Pokemon torch = new Pokemon("Torch Nigga", charmander);
		Pokemon juicer = new Pokemon("Juicer", squirtle);
		Pokemon butt = new Pokemon("Butt-que", bulbasaur );

		sparky.stats = new Stats(44, 27, 21, 25, 25, 41); //level 20
		juicer.stats = new Stats(47, 24, 31, 22, 30, 22); //level 20
		torch.stats = new Stats(42, 24, 32, 20, 32, 32);
		butt.stats = new Stats(55, 21, 12, 25, 41, 35);
		
		Move tackle = new Move("Tackle", Type.NORMAL, MoveCategory.PHYSICAL, 40, 100, 35);
		Move thunderShock = new Move("Thunder Shock", Type.ELECTRIC, MoveCategory.SPECIAL, 40, 100, 30);
		Move waterGun = new Move("Water Gun", Type.WATER, MoveCategory.SPECIAL, 40, 100, 25);
		Move bite = new Move("Bite", Type.DARK, MoveCategory.PHYSICAL, 60, 100, 25);
		Move thunder = new Move("Thunder", Type.ELECTRIC, MoveCategory.SPECIAL, 110, 70, 10);
		Move hydroPump = new Move("Hydro Pump", Type.WATER, MoveCategory.SPECIAL, 110, 80, 5);
		Move thunderWave = new Move("Thunder Wave", Type.ELECTRIC, Status.PARALYZED, 90, 20);
		Move tailWhip = new Move("Tail Whip", Type.NORMAL, "deffense", -1, 100, 30, false);
		Move shoot = new Move("Shoot With Gun", Type.DARK, MoveCategory.PHYSICAL, 2000, 100, 1);
		
		sparky.setMove(thunderShock, 1);
		sparky.setMove(tackle, 2);
		sparky.setMove(thunderWave, 3);
		sparky.setMove(thunder, 4);
		juicer.setMove(waterGun, 1);
		juicer.setMove(tailWhip, 2);
		juicer.setMove(bite, 3);
		juicer.setMove(hydroPump, 4);
		sparky.setMove(shoot, 3);
		
		
		player.party = new Party();
		mike.party = new Party();
		
		player.party.add(sparky);
		player.party.add(torch);
		mike.party.add(juicer);
		mike.party.add(butt);
		
		
		LostMethods.pokemonCenter(player.party);
		LostMethods.pokemonCenter(mike.party);
		
		Item potion = new Item("Potion");
		Item pokeBall = new Item("Poke Ball");
		
		player.bag.add(potion);
		player.bag.add(pokeBall);
		
		
		Battle battleMike = new Battle(player, mike);
		/*
		Action action = LostMethods.none;
		Move move = hydroPump;
		System.out.println("action is " + action + ". move is " + move);
		action = move;
		System.out.println("action is " + action + ". move is " + move);
		*/
	}
}
