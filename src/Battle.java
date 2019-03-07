import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class Battle {
	private Player player;
	private Trainer enemy;
	private Pokemon playerPokemon;
	private Pokemon enemyPokemon;
	boolean playerAttacksFirst;
	private Random rand = new Random();
	Scanner in = new Scanner(System.in); // should probably be in a higher up class

	public Battle(Player player, Trainer enemy){
		this.player = player;
		this.enemy = enemy;
		playerPokemon = player.party.getPokemon(0);
		enemyPokemon = enemy.party.getPokemon(0);

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

		if(action instanceof Move){//was always told not to use instanceof!!
			fight(user, (Move)action);
		}
		else if(action == LostMethods.bag){
			System.out.println("This is where using an item should happen");
		}
		else if(action == LostMethods.switchPokemon){
			System.out.println("This is where switching pokemon should happen.");
		}
		
		if(isFainted(playerPokemon)){
			swapPokemon(player);
		}
		if(isFainted(enemyPokemon)){
			swapPokemon(enemy);
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
		Pokemon pokemon;
		if(trainer == player){
			pokemon = playerPokemon;
		}
		else{
			pokemon = enemyPokemon;
		}


		boolean actionSelected = false;

		do{
			System.out.println("What will " + trainer + " do?\n[1] Fight (" + pokemon + ")\n[2] Bag\n[3] Swap Pokemon\n[4] Run");
			try{
				switch(in.nextInt()){
				case 1:actionSelected = trainer.chooseMove(pokemon); break;
				case 2:actionSelected = trainer.bag.chooseItem(); break;
				case 3:actionSelected = trainer.switchPokemon(); break;
				case 4:System.out.println("There is no running from a trainer battle you big fat pussy!");break;
				default:System.out.println("1 through 4 must be selected.");
				}
			}
			catch(InputMismatchException e){
				System.out.println("The input must be an integer."); 
				in.next(); //shifts focus to the next thing typed (avoids infinite loop)
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

		if(user.getStatus() != Status.NONE){
			Status.takeEffectOfStatus(user);
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

	public void swapPokemon(Trainer trainer){
		System.out.println(trainer + " needs to select the next pokemon to bring out. (1 through 6)");

		Pokemon nextPokemon = null;

		while(nextPokemon == null || nextPokemon.getStatus() == Status.FAINTED){
			trainer.party.printParty();

			try{
				nextPokemon = trainer.party.getPokemon(in.nextInt()-1); //User input selects next pokemon
			}
			catch(IndexOutOfBoundsException e){
				System.out.println("Index out of party bounds!");
			}
			catch(InputMismatchException e){
				System.out.println("The input must be an integer."); 
				in.next(); //shifts focus to the next thing typed (avoids infinite loop)
			}


			if(nextPokemon == null){
				System.out.println("You must select an index that holds a pokemon.");
			}
			else if(nextPokemon.getStatus() == Status.FAINTED){
				System.out.println(nextPokemon + " has no HP remaining!");
			}
		}
		if(trainer == player){
			playerPokemon = nextPokemon;
		}
		else{
			enemyPokemon = nextPokemon;
		}
		System.out.println(nextPokemon + " has been switched out!");
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
