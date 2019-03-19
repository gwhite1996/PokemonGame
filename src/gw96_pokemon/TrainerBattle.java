package gw96_pokemon;

class TrainerBattle extends Battle{

	private Trainer enemy;

	TrainerBattle(Player player, Trainer enemy){
		this.player = player;
		this.enemy = enemy;
		startBattle();
		while(!(player.party.allFainted() || enemy.party.allFainted())){
			fullTurn();
		}
		endBattle();
	}

	void startBattle(){
		// todo. opening dialog
		this.player.takeOutFirstPokemon();
		this.enemy.takeOutFirstPokemon();
		resetAllStatStages(player.party);
		resetAllStatStages(enemy.party);
	}

	void endBattle(){
		Trainer winner, loser;
		player.putAwayAllPokemon();
		enemy.putAwayAllPokemon();
		resetAllStatStages(player.party);
		resetAllStatStages(enemy.party);
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
		if(playerActsFirst(enemy.getCurrentPokemon(), enemy.getAction())){
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

	@Override // disabled the option to run away
	void selectAction(Trainer trainer){
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
}
