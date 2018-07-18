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
		runUntilEnd();
	}
	
	
	public void runUntilEnd(){
		while(!(player.party.allFainted() || enemy.party.allFainted())){
			if(playerPokemon.hpRemaining <= 0){
				System.out.println(playerPokemon + " has fainted!");
				swapPokemon(player);
			}
			if(enemyPokemon.hpRemaining <= 0){
				System.out.println(enemyPokemon + " has fainted!");
				swapPokemon(enemy);
			}
			else{
				fullTurn();
			}
		}
		
	}
	
	private void fullTurn() { //A half turn for each pokemon
		player.setAction(LostMethods.none);
		enemy.setAction(LostMethods.none); // both trainers have their action reset to none
		selectAction(player);
		selectAction(enemy);

		System.out.println("\n******** Turn Results ********");
		if(playerActsFirst()){
			halfTurn(player);
			if(playerPokemon.hpRemaining > 0 && enemyPokemon.hpRemaining > 0){
				halfTurn(enemy);
			}
		}
		else{
			halfTurn(enemy);
			if(playerPokemon.hpRemaining > 0 && enemyPokemon.hpRemaining > 0){
				halfTurn(player);
			}
			else{
				System.out.println("Someone fainted.");
				return;
			}
		}
		System.out.println();
		LostMethods.showHealth(playerPokemon);
		LostMethods.showStats(playerPokemon);
		LostMethods.showHealth(enemyPokemon);
		LostMethods.showStats(enemyPokemon);
		System.out.println("******************************\n\n");
	}
	
	private void halfTurn(Trainer trainer){
		Pokemon user;
		Action action = trainer.getAction();
		
		if(trainer == player){
			user = playerPokemon;
		}
		else{
			user = enemyPokemon;
		}
				
		if(action instanceof Move){
			fight(user, (Move)action);
		}
		else if(action == LostMethods.bag){
			System.out.println("This is where using an item should happen");
		}
		else if(action == LostMethods.switchPokemon){
			System.out.println("This is where switching pokemon should happen.");
		}
	}
	
	
	public void selectAction(Trainer trainer){
		
		System.out.println("What will " + trainer + " do?\n[1] Fight\n[2] Bag\n[3] Swap Pokemon\n[4] Run");

		while(trainer.getAction() == LostMethods.none){
			switch(in.nextInt()){
			case 1:chooseMove(trainer); break;
			case 2:trainer.setAction(LostMethods.bag); trainer.bag.chooseItem(); break;
			case 3:trainer.setAction(LostMethods.switchPokemon); break;
			case 4:System.out.println("There is no running from a trainer battle you big fat pussy!");break;
			default:System.out.println("1 through 4 must be selected.");
			}
		}
	}
	
	private void chooseMove(Trainer trainer){
		Pokemon user;
		if(trainer == player){
			user = playerPokemon;
		}
		else{
			user = enemyPokemon;
		}
		
		LostMethods.showMoveSet(user);
		System.out.println(" Chose the move for " + user + " to use:  (Type 1 through 4 then press Enter)");
		
		Action action = LostMethods.none;
		while(action == LostMethods.none){
			switch(in.nextInt()){ //User input selects move to use
			case 1:action = user.move1;break;
			case 2:action = user.move2;break;
			case 3:action = user.move3;break;
			case 4:action = user.move4;break;
			default:;System.out.println("Invalid input int! Must be 1 through 4");
			}
		trainer.setAction(action); //sets action so the loop in selectAction() ends
		}
	}
	
	private void fight(Pokemon user, Move move){ //Basically a turn for a single pokemon
		user.stats.updateMultipliersFromStages(); //done before in case enemy halfTurn affected stages
		
		int moveNumber = 0;
		if(move == user.move1)moveNumber = 1;
		else if(move == user.move2)moveNumber = 2;
		else if(move == user.move3)moveNumber = 3;
		else if(move == user.move4)moveNumber = 4;
		else System.out.println("Problem in fight()");
		
		
		Pokemon target; //sets the other pokemon to the target
		if(user == playerPokemon){
			target = enemyPokemon;
		}
		else{
			target = playerPokemon;
		}

		if(user.statusTurnsRemaining > 0){
			Status.takeEffectOfStatus(user);
			if(user.getStatus().wearsOff()){
				user.statusTurnsRemaining --;
			}
		}
		else{
			Status.removeStatus(user);
		}

		if(user.canAttack){
			useMove(user, target, moveNumber);
		}
		user.stats.updateMultipliersFromStages(); //done after in case it's move affected itself
	}
	
	public void useMove(Pokemon user, Pokemon target, int moveNumber){

		if(!PPLeft(user, moveNumber)){
			return;
		}

		Move move = null;
		switch(moveNumber){
		case 1: move = user.move1;user.move1PP--;break;
		case 2: move = user.move2;user.move2PP--;break;
		case 3: move = user.move3;user.move3PP--;break;
		case 4: move = user.move4;user.move4PP--;break;
		default: System.out.println("Invalid moveNumber given to useMove()");
		}

		double chanceOfHit = ((move.accuracy)/100.0)*(user.stats.multipliers.accuracy/target.stats.multipliers.evasion);
		if(chanceOfHit > rand.nextDouble()){ //the attack hits
			if(move.moveCategory == MoveCategory.STATUS){
				statusAttack(user, target, move);
			}
			else if(move.moveCategory == MoveCategory.STAT){
				statAttack(user, target, move);
			}
			else{
				damageAttack(user, target, move);
			}
		}
		else{
			System.out.println(" " + user + " used " + move + " but it missed!");
		}
	}
	
	private void damageAttack(Pokemon user, Pokemon target, Move move){
		double typeEffectiveness = Type.typeEffectiveness(move.type, target.species);
		double stab = Type.stab(move.type, user.species);
		double criticalHit = criticalHit();
		//double randomAttackFactor = randomAttackFactor();

		double calculatedAttack;
		double calculatedDeffense;

		if(move.moveCategory == MoveCategory.PHYSICAL){ //physical or special?
			calculatedAttack = user.stats.attack*user.stats.multipliers.attack;
			calculatedDeffense = target.stats.deffense*target.stats.multipliers.deffense;

		}
		else{
			calculatedAttack = user.stats.spAtk*user.stats.multipliers.spAtk;
			calculatedDeffense = target.stats.spDef*user.stats.multipliers.spDef;
		}


		double modifier = typeEffectiveness*stab*criticalHit; //can be affected by many things	
		int damage = (int)(((((((2*user.level)/5)+2)*move.power*(calculatedAttack/calculatedDeffense))/50)+2)*modifier + 0.5); //formula from bulbapedia

		System.out.println(user + " hit " + target + " with " + move + " for " + damage + " damage.");
		System.out.println(" It was " + typeEffectivenessMessage(typeEffectiveness) + " and STAB was " + stab + "."); //temporarily here);
		target.hpRemaining -= damage;
	}

	private void statusAttack(Pokemon user, Pokemon target, Move move) {
		System.out.println(user + " used " + move + " on " + target);

		if(Status.inflictStatus(target, move.statusInflicted)){
			System.out.println(" " + target + " is now " + move.statusInflicted);
		}
	}

	private void statAttack(Pokemon user, Pokemon target, Move move){
		if(move.targetsSelf){
			target = user;
			System.out.println(user + " used " + move + " on itself. " + move.statAffected + " was increased by " + move.stageIncrease);
		}
		else{
			System.out.println(user + " used " + move + " on " + target + " it's " + move.statAffected + " was increased by " + move.stageIncrease);
		}

		switch (move.statAffected){
		case "attack":target.stats.stages.attack += move.stageIncrease;break;
		case "deffense":target.stats.stages.deffense += move.stageIncrease;break;
		case "spAtk":target.stats.stages.spAtk += move.stageIncrease;break;
		case "spDef":target.stats.stages.spDef += move.stageIncrease;break;
		case "speed":target.stats.stages.speed += move.stageIncrease;break;
		case "accuracy":target.stats.stages.accuracy += move.stageIncrease;break;
		case "evasion":target.stats.stages.evasion += move.stageIncrease;break;
		default: System.out.println("Invalid statAffected in statAttack. The move used is " + move);
		}
	}
	
	
	public void swapPokemon(Trainer trainer){
		System.out.println(trainer + " needs to select the next pokemon to bring out. (1 through 6)");
		
		Pokemon nextPokemon = null;
		
		while(nextPokemon == null || nextPokemon.hpRemaining <= 0){
			trainer.party.printParty();
			
			try{
				nextPokemon = trainer.party.getPokemon(in.nextInt()-1); //User input selects next pokemon
			}
			catch(IndexOutOfBoundsException e){
				System.out.println("Index out of party bounds!");
			}
			
			
			if(nextPokemon == null){
				System.out.println("You must select an index that holds a pokemon.");
			}
			else if(nextPokemon.hpRemaining <= 0){
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
	
	
	
	
	public String typeEffectivenessMessage(double typeEffectiveness){
		switch((int)(typeEffectiveness*4)){
		case 0:return "Not effective at all";
		case 1:return "Minimally effective";
		case 2:return "Not very effective";
		case 4:return "Normally effective";
		case 8:return "Super effective";
		case 16:return "Hyper effective";
		default:return "Invalid typeEffectiveness double input to typeEffectivenessMessage()";
		}
	}
	
	
	public void resetAllStatStages(){ //resets stages to 0 for both parties before and after battle
		for(Pokemon p: player.party.partyArray){
			if(p != null){
				p.stats.stages.resetAll();
			}
		}
		for(Pokemon p: enemy.party.partyArray){
			if(p != null){
				p.stats.stages.resetAll();
			}
		}
	}
	
	
	public boolean playerActsFirst(){
		if(player.getAction().getPriority() == enemy.getAction().getPriority()){ //checks pokemon speed only if priorities are the same
			return playerPokemon.stats.speed*playerPokemon.stats.multipliers.speed >= enemyPokemon.stats.speed*enemyPokemon.stats.multipliers.speed;
		}
		else{
			return player.getAction().getPriority() > enemy.getAction().getPriority();
		}
	}
	
	
	public boolean PPLeft(Pokemon user, int moveNumber){
		int ppLeft;
		switch(moveNumber){
		case 1:ppLeft = user.move1PP;break;
		case 2:ppLeft = user.move2PP;break;
		case 3:ppLeft = user.move3PP;break;
		case 4:ppLeft = user.move4PP;break;
		default:System.out.println("Invalid moveNumber in PPLeft()");
		return false;
		}
		if(ppLeft > 0){
			return true;
		}
		System.out.println("No pp left!");
		return false;
	}
	
	public double criticalHit(){
		if(rand.nextInt(16) == 0){
			System.out.println("  A critical hit!!");
			return 1.5;
		}
		return 1;
	}

	//This factor is not included in damage calculation since I don't like the luck involved
	public double randomAttackFactor(){ //gives random number from .85 to 1 (inclusive);
		return (rand.nextInt(16)+85)/100.0;
	}
	
	public void setPlayerPokemon(Pokemon playerPokemon){
		this.playerPokemon = playerPokemon;
	}
	public void setEnemyPokemon(Pokemon enemyPokemon){
		this.enemyPokemon = enemyPokemon;
	}
}
