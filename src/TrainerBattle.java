import java.util.Random;

public class TrainerBattle{

	private Player player;
	private Trainer enemy;
	private Random rand = new Random();

	public TrainerBattle(Player player, Trainer enemy){
		this.player = player;
		this.enemy = enemy;
		startBattle();
		while(!(player.party.allFainted() || enemy.party.allFainted())){
			fullTurn();
		}
		endBattle();
	}

	public void startBattle(){
		// todo. opening dialog
		this.player.takeOutFirstPokemon();
		this.enemy.takeOutFirstPokemon();
		resetAllStatStages();
	}

	public void endBattle(){
		Trainer winner, loser;
		player.putAwayAllPokemon();
		enemy.putAwayAllPokemon();
		resetAllStatStages();
		
		if(player.party.allFainted()){
			winner = enemy;
			loser = player;
		}
		else{
			winner = player;
			loser = enemy;
		}
		System.out.println(loser + " has no more usable pokemon!");
		System.out.println(winner + " is the winner.");
		// todo. closing dialog
		// todo. payment to winner
	}

	private void fullTurn(){ // comprised of a half turn for each pokemon
		player.setAction(LostMethods.none);
		enemy.setAction(LostMethods.none); // both trainers have their action reset to none
		selectAction(player);
		selectAction(enemy);
		System.out.println("\n******** Turn Results ********");
		if(playerActsFirst()){
			if(halfTurn(player, enemy)){
				halfTurn(enemy, player);
			}
		}
		else{
			if(halfTurn(enemy, player)){
				halfTurn(player, enemy);
			}
		}
		System.out.println();
		LostMethods.printHealth(player.getCurrentPokemon());
		LostMethods.printHealth(enemy.getCurrentPokemon());
		System.out.println("******************************\n\n");
	}

	// returns false if target faints to prevent the next halfturn
	private boolean halfTurn(Trainer trainer, Trainer opponent){
		Pokemon user = trainer.getCurrentPokemon();
		Pokemon target = opponent.getCurrentPokemon();
		Action action = trainer.getAction();
		if(user.getStatus() != Status.NONE){
			Status.takeEffectOfStatusBeforeAction(user);
		}
		if(action instanceof Move){// was always told not to use instanceof!!
			if(user.canAttack){
				useMove(user, target, (Move)action);
			}
		}
		else if(action instanceof UsingItem){
			((UsingItem)action).getItemUsed().use(((UsingItem)action).getTargetPokemon());
		}
		if(action == LostMethods.swapingPokemon){
			Pokemon oldUser = user;
			trainer.onDeckToCurrent();
			user = trainer.getCurrentPokemon();
		}
		else if(user.getStatus() != Status.NONE && user.stats.hpRemaining > 0){ // only swapping the pokemon prevents damage from a status effect
			Status.takeEffectOfStatusAfterAction(user);
		}
		if(isFainted(user)){
			if(trainer.party.allFainted()){
				return false;
			}
			else{
				trainer.pickPokemonOnDeck(true);
				trainer.onDeckToCurrent();
			}
		}
		if(isFainted(target)){
			if(!opponent.party.allFainted()){
				opponent.pickPokemonOnDeck(true);
				opponent.onDeckToCurrent();
			}
			return false; // target cannot carry out half turn if fainted
		}
		else{
			return true;
		}
	}

	public void selectAction(Trainer trainer){
		Pokemon user = trainer.getCurrentPokemon();
		boolean actionHasBeenSelected = false;
		do{
			System.out.println(
					"What will " + trainer + " do?\n[1] Fight (" + user + ")\n[2] Bag\n[3] Swap Pokemon\n[4] Run");
			switch(LostMethods.chooseOption(1, 4)){
			case 1:
				actionHasBeenSelected = trainer.chooseMove();
				break;
			case 2:
				actionHasBeenSelected = trainer.willUseItem();
				break;
			case 3:
				actionHasBeenSelected = trainer.pickPokemonOnDeck(false);
				break;
			case 4:
				System.out.println("There is no running from a trainer battle you big fat pussy!");
				break;
			}
		}
		while(!actionHasBeenSelected);
	}

	public void useMove(Pokemon user, Pokemon target, Move move){
		double chanceOfHit = ((move.getMoveName().getAccuracy()) / 100d)
				* (user.stats.accuracy.getBattleValue() / target.stats.evasion.getBattleValue());
		if(chanceOfHit > rand.nextDouble() || move.getMoveName().getAccuracy() == 0){ // attack hits no matter what if accuracy is 0
			move.useMove(user, target);
		}
		else{
			System.out.println(user + " used " + move + " but it missed!");
		}
		move.setPPLeft(move.getPPLeft() - 1);
	}

	public boolean playerActsFirst(){
		if(player.getAction().getPriority() == enemy.getAction().getPriority()){ // checks pokemon speed only if priorities are same
			return player.getCurrentPokemon().stats.speed.getBattleValue() >= enemy.getCurrentPokemon().stats.speed
					.getBattleValue();
		}
		else{
			return player.getAction().getPriority() > enemy.getAction().getPriority();
		}
	}

	// called each halfturn to check if either current pokemon has fainted
	public boolean isFainted(Pokemon pokemon){
		if(pokemon.stats.hpRemaining <= 0){
			pokemon.stats.hpRemaining = 0;
			pokemon.setStatus(Status.FAINTED);
			System.out.println(pokemon + " has fainted!");
			return true;
		}
		else{
			return false;
		}
	}

	public boolean PPLeft(Pokemon user, int moveNumber){
		int ppLeft = 0;
		switch(moveNumber){
		case 1:
			ppLeft = user.move1.getPPLeft();
			break;
		case 2:
			ppLeft = user.move2.getPPLeft();
			break;
		case 3:
			ppLeft = user.move3.getPPLeft();
			break;
		case 4:
			ppLeft = user.move4.getPPLeft();
			break;
		default:
			System.out.println("Problem in PPLeft()");
		}
		if(ppLeft > 0){
			return true;
		}
		System.out.println("No pp left!");
		return false;
	}

	public void resetAllStatStages(){ // resets stages to 0 for both parties before and after battle
		for(int i = 1; i <= player.party.getPartyCount(); i++){
			Pokemon p = player.party.getPokemon(i);
			if(p != null){
				p.stats.resetAllStages();
			}
		}
		for(int i = 1; i <= enemy.party.getPartyCount(); i++){
			Pokemon p = enemy.party.getPokemon(i);
			if(p != null){
				p.stats.resetAllStages();
			}
		}
	}
}
