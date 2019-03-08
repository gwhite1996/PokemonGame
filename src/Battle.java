import java.util.Random;


public class Battle {
	private Player player;
	private Trainer enemy;
	private Pokemon playerPokemon;
	private Pokemon enemyPokemon;
	boolean playerAttacksFirst;
	private Random rand = new Random();
	

	public Battle(Player player, Trainer enemy){
		this.player = player;
		this.enemy = enemy;
		playerPokemon = player.party.getPokemon(1);
		enemyPokemon = enemy.party.getPokemon(1);

		resetAllStatStages();
		while(!(player.party.allFainted() || enemy.party.allFainted())){
			fullTurn();
		}
		resetAllStatStages();
	}

	private void fullTurn() { //comprised of a half turn for each pokemon
		player.setAction(LostMethods.none);
		enemy.setAction(LostMethods.none); // both trainers have their action reset to none
		selectAction(player);
		selectAction(enemy);

		System.out.println("\n******** Turn Results ********");
		if(playerActsFirst()){
			if(halfTurn(player)){
				halfTurn(enemy);
			}
		}
		else{
			if(halfTurn(enemy)) {
				halfTurn(player);
			}
		}
		System.out.println();
		LostMethods.printHealth(playerPokemon);
		//LostMethods.showStats(playerPokemon);
		LostMethods.printHealth(enemyPokemon);
		//LostMethods.showStats(enemyPokemon);
		//playerPokemon.stats.printStats();
		//enemyPokemon.stats.printStats();
		System.out.println("******************************\n\n");
	}
	
	//returns false if target faints to prevent the next halfturn
	private boolean halfTurn(Trainer trainer){
		Pokemon user;
		Pokemon target;
		Action action = trainer.getAction();
		

		if(trainer == player){
			user = playerPokemon;
			target = enemyPokemon;
		}
		else{
			user = enemyPokemon;
			target = playerPokemon;
		}
		
		if(user.getStatus() != Status.NONE){
			Status.takeEffectOfStatusBeforeAction(user);
		}
		
		
		if(action instanceof Move){//was always told not to use instanceof!!
			fight(user, (Move)action);
		}
		else if(action instanceof UseItem){
			((UseItem)action).getItemUsed().use(((UseItem)action).getTargetPokemon());
		}
		
		if(action instanceof SwapPokemon){
			Pokemon nextPokemon = ((SwapPokemon)action).getNextPokemon();
			
			if(trainer == player){ //temp. there has to be a better way than just constantly checking this
				playerPokemon = nextPokemon;
			}
			else{
				enemyPokemon = nextPokemon;
			}

			
			System.out.println(user + " has been switched out for " + nextPokemon);
		}
		else{ //only swapping the pokemon will prevent damage from a status effect
			if(user.getStatus() != Status.NONE && user.stats.hpRemaining > 0){
				Status.takeEffectOfStatusAfterAction(user);
			}
		}
		
		
		if(isFainted(playerPokemon)){
			if(player.party.allFainted()){
				return false;
			}
			playerPokemon = player.party.swapFromParty(true);
		}
		if(isFainted(enemyPokemon)){
			if(enemy.party.allFainted()){
				return false;
			}
			enemyPokemon = enemy.party.swapFromParty(true);
		}
		if(target.getStatus() == Status.FAINTED){
			return false;
		}
		else{
			return true; //allows the target to carry out their half turn
		}
	}
	

	//called each halfturn to check if either current pokemon has fainted
	public boolean isFainted(Pokemon pokemon){
		if(pokemon.stats.hpRemaining <= 0){
			pokemon.stats.hpRemaining = 0;
			pokemon.setStatus(Status.FAINTED);
			
			System.out.println(pokemon + " has fainted!");
			return true;
		}
		else {
			return false;
		}
	}


	public void selectAction(Trainer trainer){
		Pokemon user;
		if(trainer == player){
			user = playerPokemon;
		}
		else{
			user = enemyPokemon;
		}


		boolean actionSelected = false;

		do{
			System.out.println("What will " + trainer + " do?\n[1] Fight (" + user + ")\n[2] Bag\n[3] Swap Pokemon\n[4] Run");
				switch(LostMethods.chooseOption(1, 4)){
				case 1:actionSelected = trainer.chooseMove(user); break;
				case 2:actionSelected = trainer.willUseItem(); break;
				case 3:actionSelected = trainer.willSwapPokemon(); break;
				case 4:System.out.println("There is no running from a trainer battle you big fat pussy!");break;
				}
		}
		while(!actionSelected);
	}

	
	private void fight(Pokemon user, Move move){ //Basically a turn for a single pokemon
		
		Pokemon target; //sets the other pokemon to the target
		if(user == playerPokemon){
			target = enemyPokemon;
		}
		else{
			target = playerPokemon;
		}
		if(user.canAttack){
			useMove(user, target, move);
		}
	}

	public void useMove(Pokemon user, Pokemon target, Move move){
		move.ppLeft--;

		double chanceOfHit = ((move.moveName.accuracy)/100d)*(user.stats.accuracy.getBattleValue()/target.stats.evasion.getBattleValue());
		if(chanceOfHit > rand.nextDouble() || move.moveName.accuracy == 0){ //the attack hits no matter what if accuracy is 0
			move.useMove(user, target);
		}
		else{
			System.out.println(user + " used " + move + " but it missed!");
		}
	}


	public void resetAllStatStages(){ //resets stages to 0 for both parties before and after battle
		for(Pokemon p: player.party.partyArray){
			if(p != null){
				p.stats.resetAllStages();
			}
		}
		for(Pokemon p: enemy.party.partyArray){
			if(p != null){
				p.stats.resetAllStages();
			}
		}
	}
	

	public boolean playerActsFirst(){
		if(player.getAction().getPriority() == enemy.getAction().getPriority()){ //checks pokemon speed only if priorities are the same
			return playerPokemon.stats.speed.getBattleValue() >= enemyPokemon.stats.speed.getBattleValue();
		}
		else{
			return player.getAction().getPriority() > enemy.getAction().getPriority();
		}
	}


	public boolean PPLeft(Pokemon user, int moveNumber){
		int ppLeft = 0;
		
		switch(moveNumber) {
		case 1: ppLeft = user.move1.ppLeft;break;
		case 2: ppLeft = user.move2.ppLeft;break;
		case 3: ppLeft = user.move3.ppLeft;break;
		case 4: ppLeft = user.move4.ppLeft;break;
		default: System.out.println("Problem in PPLeft()");
		}
		
		if(ppLeft > 0){
			return true;
		}
		System.out.println("No pp left!");
		return false;
	}
	

	public void setPlayerPokemon(Pokemon playerPokemon){
		this.playerPokemon = playerPokemon;
	}
	public void setEnemyPokemon(Pokemon enemyPokemon){
		this.enemyPokemon = enemyPokemon;
	}
}
