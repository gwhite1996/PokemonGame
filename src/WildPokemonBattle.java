public class WildPokemonBattle extends Battle{

	private Pokemon wildPokemon;
	private Move wildPokemonMove;
	private boolean escaped;
	private int escapeAttempts;

	public WildPokemonBattle(Player player, Pokemon wildPokemon){
		this.player = player;
		this.wildPokemon = wildPokemon;
		escaped = false;
		escapeAttempts = 0;
		startBattle();
		while(!((wildPokemon.getStatus() == Status.FAINTED) || player.party.allFainted() || escaped)){
			fullTurn();
		}
		endBattle();
	}

	public void startBattle(){
		// todo. opening dialog
		this.player.takeOutFirstPokemon();
		resetAllStatStages(player.party);
		wildPokemon.stats.resetAllStages();
	}

	public void endBattle(){
		player.putAwayAllPokemon();
		resetAllStatStages(player.party);
		wildPokemon.stats.resetAllStages();
		if(wildPokemon.getStatus() == Status.FAINTED){
			System.out.println(player + " defeated wild " + wildPokemon);
		}
		else if(escaped){
			System.out.println(player + " got away safely.");
		}
		else{
			System.out.println(player + " has no more usable pokemon!");
		}
	}

	private void fullTurn(){ // comprised of a half turn for each pokemon
		player.setAction(LostMethods.none);
		selectAction(player);
		wildPokemonMove = getRandomMove(wildPokemon);
		System.out.println("\n******** Turn Results ********");
		if(playerActsFirst(wildPokemon, wildPokemonMove)){
			if(playerHalfTurn()){
				wildPokemonHalfTurn();
			}
		}
		else{
			if(wildPokemonHalfTurn()){
				playerHalfTurn();
			}
		}
		System.out.println();
		LostMethods.printHealth(player.getCurrentPokemon());
		LostMethods.printHealth(wildPokemon);
		System.out.println("******************************\n\n");
	}

	private boolean playerHalfTurn(){
		Pokemon user = player.getCurrentPokemon();
		Action action = player.getAction();
		if(user.getStatus() != Status.NONE){
			Status.takeEffectOfStatusBeforeAction(user);
		}
		if(action instanceof Move){// was always told not to use instanceof!!
			if(user.canAttack){
				useMove(user, wildPokemon, (Move)action);
			}
		}
		else if(action == LostMethods.fleeing){
			escapeAttempts++;
			double chanceOfEscape = (((player.getCurrentPokemon().stats.speed.getTrueValue() * 128)
					/ wildPokemon.stats.speed.getTrueValue()) + 30 * escapeAttempts) % 256;
			if(rand.nextInt(256) < chanceOfEscape){
				escaped = true;
				return false;
			}
			else{
				System.out.println("Can't escape!");
			}
		}
		else if(action instanceof UsingItem){
			((UsingItem)action).getItemUsed().use(((UsingItem)action).getTargetPokemon());
		}
		if(action == LostMethods.swapingPokemon){
			player.onDeckToCurrent();
			user = player.getCurrentPokemon();
		}
		else if(user.getStatus() != Status.NONE && user.stats.hpRemaining > 0){ // only swapping the pokemon prevents damage from a status effect
			Status.takeEffectOfStatusAfterAction(user);
		}
		if(isFainted(user)){
			if(player.party.allFainted()){
				return false;
			}
			else{
				player.pickPokemonOnDeck(true);
				player.onDeckToCurrent();
			}
		}
		if(isFainted(wildPokemon)){
			return false; // battle ends once wildPokemon faints
		}
		else{
			return true;
		}
	}

	// returns false if player's currentPokemon faints to prevent their halfTurn
	private boolean wildPokemonHalfTurn(){
		Pokemon target = player.getCurrentPokemon();
		if(wildPokemon.getStatus() != Status.NONE){
			Status.takeEffectOfStatusBeforeAction(wildPokemon);
		}
		if(wildPokemon.canAttack){
			useMove(wildPokemon, target, wildPokemonMove);
		}
		if(wildPokemon.getStatus() != Status.NONE && wildPokemon.stats.hpRemaining > 0){
			Status.takeEffectOfStatusAfterAction(wildPokemon);
		}
		if(isFainted(wildPokemon)){
			return false; // battle ends once wildPokemon faints
		}
		if(isFainted(target)){
			if(!player.party.allFainted()){
				player.pickPokemonOnDeck(true);
				player.onDeckToCurrent();
			}
			return false;
		}
		else{
			return true;
		}
	}
}
