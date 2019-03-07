public class TestBattle {

	public static void main(String[] args) {

		Player player = new Player("Player", 1, 1);
		Trainer mike = new Hiker("Hiker Mike", 4,4); //arbitrary coordinates
		
		

		Pokemon pika = new Pokemon("Pikachu", SpeciesList.pikachu );
		Pokemon charm = new Pokemon("Charmander", SpeciesList.charmander);
		Pokemon squirt = new Pokemon("Squirtle", SpeciesList.squirtle);
		Pokemon bulb = new Pokemon("Bulbasaur", SpeciesList.bulbasaur );
		Pokemon arct = new Pokemon("Articuno", SpeciesList.articuno);
		Pokemon cater = new Pokemon("Caterpie", SpeciesList.caterpie);

		pika.stats = new Stats(44, 27, 21, 25, 25, 41); //each is level 20, serious nature, 0 IVs, O EVs
		squirt.stats = new Stats(47, 24, 31, 25, 30, 22);
		charm.stats = new Stats(45, 25, 22, 29, 25, 31);
		bulb.stats = new Stats(48, 24, 24, 31, 31, 23);
		arct.stats = new Stats(66, 39, 45, 43, 55, 39);
		cater.stats = new Stats(48, 17, 19, 13, 13, 23);
		
		pika.setMove(MoveList.tailWhip, 1);
		pika.setMove(MoveList.thunderShock, 2);
		pika.setMove(MoveList.thunderWave, 3);
		pika.setMove(MoveList.thunder, 4);
		squirt.setMove(MoveList.tackle, 1);
		squirt.setMove(MoveList.tailWhip, 2);
		squirt.setMove(MoveList.waterGun, 3);
		squirt.setMove(MoveList.hydroPump, 4);
		charm.setMove(MoveList.scratch, 1);
		charm.setMove(MoveList.growl, 2);
		charm.setMove(MoveList.ember, 3);
		charm.setMove(MoveList.flamethrower, 4);
		bulb.setMove(MoveList.tackle, 1);
		bulb.setMove(MoveList.growl, 2);
		bulb.setMove(MoveList.vineWhip, 3);
		bulb.setMove(MoveList.seedBomb, 4);
		arct.setMove(MoveList.gust, 1);
		arct.setMove(MoveList.iceBeam, 2);
		arct.setMove(MoveList.blizzard, 3);
		arct.setMove(MoveList.roost, 4);
		cater.setMove(MoveList.tackle, 1);
		cater.setMove(MoveList.stringShot, 2);
		cater.setMove(MoveList.bugBite, 3);
		
		pika.setMove(MoveList.shoot2, 2);
		//pika.setMove(MoveList.zapCannon, 4);
		
		player.party = new Party();
		mike.party = new Party();
		
		player.party.add(pika);
		player.party.add(charm);
		player.party.add(cater);
		mike.party.add(squirt);
		mike.party.add(bulb);
		mike.party.add(arct);
		
		
		LostMethods.pokemonCenter(player.party);
		LostMethods.pokemonCenter(mike.party);
		
		
		
		player.bag.add(ItemList.potion);
		player.bag.add(ItemList.pokeBall);
		
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
		
		
		new Battle(player, mike);
	}
	
	
	///REDO HOW MOVES ARE DONE
	
}
