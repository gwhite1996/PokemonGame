import java.util.Random;


public class Battle {
	private Pokemon playerPokemon;
	private Pokemon enemyPokemon;
	Random rand = new Random();

	boolean playerAttacksFirst;


	public boolean playerAttacksFirst(){
		return playerPokemon.stats.speed*Stats.stageMultiplier(playerPokemon.statStages.speedStage) >= enemyPokemon.stats.speed*Stats.stageMultiplier(enemyPokemon.statStages.speedStage);
	}

	public String useMove(Pokemon user, Pokemon target, int moveNumber){
		
		if(!PPLeft(user, moveNumber)){
			return "There is no pp left for that move!";
		}

		Move move = null;
		switch(moveNumber){
		case 1: move = user.move1;user.move1PP--;break;
		case 2: move = user.move2;user.move2PP--;break;
		case 3: move = user.move3;user.move3PP--;break;
		case 4: move = user.move4;user.move4PP--;break;
		default: System.out.println("Invalid moveNumber given to useMove()");
		}
		
		double chanceOfHit = ((move.accuracy)/100.0)*(Stats.stageMultiplier(user.statStages.accuracyStage)/Stats.stageMultiplier(target.statStages.evasionStage));
		if(chanceOfHit > rand.nextDouble()){ //the attack hits
			damageAttack(user, target, move); //temporary
			return user.name + " used " + move.name + ".";
		}
		else{
			System.out.println(user.name + " used " + move.name + " but it missed!");
			return user.name + " used " + move.name + " but it missed!";
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


	public void damageAttack(Pokemon user, Pokemon target, Move move){
		double typeEffectiveness = Type.typeEffectiveness(move.type, target.species);
		double stab = Type.stab(move.type, user.species);
		double criticalHit = criticalHit();
		//double randomAttackFactor = randomAttackFactor();

		double calculatedAttack;
		double calculatedDeffense;

		if(move.moveCategory == MoveCategory.PHYSICAL){ //physical or special?
			calculatedAttack = user.stats.attack*Stats.stageMultiplier(user.statStages.attackStage);
			calculatedDeffense = target.stats.deffense*Stats.stageMultiplier(target.statStages.deffenseStage);

		}
		else{
			calculatedAttack = user.stats.spAtk*Stats.stageMultiplier(user.statStages.spAtkStage);
			calculatedDeffense = target.stats.spDef*Stats.stageMultiplier(target.statStages.spDefStage);
		}


		double modifier = typeEffectiveness*stab*criticalHit; //can be affected by many things	
		int damage = (int)(((((((2*user.level)/5)+2)*move.power*(calculatedAttack/calculatedDeffense))/50)+2)*modifier + 0.5); //formula from bulbapedia

		System.out.println(user.name + " hit " + target.name + " with " + move.name + " for " + damage + " damage.");
		System.out.println("It was " + typeEffectivenessMessage(typeEffectiveness) + " and STAB was " + stab + "."); //temporarily here);
		target.hpRemaining -= damage;
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

	public void testThreeTurns(){ // temporary test method
		takeTurn();
		useMove(playerPokemon, enemyPokemon, 1);
		useMove(enemyPokemon, playerPokemon, 1);
		takeTurn();
		useMove(playerPokemon, enemyPokemon, 2);
		useMove(enemyPokemon, playerPokemon, 2);
		takeTurn();
		useMove(playerPokemon, enemyPokemon, 3);
		useMove(enemyPokemon, playerPokemon, 3);
		takeTurn();
	}
	
	

	private void takeTurn() {
		if(playerAttacksFirst()){
			System.out.println(" The player attacks first");
		}
		else System.out.println( "The enemy attacks first");
		System.out.println(playerPokemon.name + "'s hpRemaining is: " + playerPokemon.hpRemaining);
		System.out.println(enemyPokemon.name + "'s hpRemaining is: " + enemyPokemon.hpRemaining);
	}

	public void setPlayerPokemon(Pokemon playerPokemon){
		this.playerPokemon = playerPokemon;
	}
	public void setEnemyPokemon(Pokemon enemyPokemon){
		this.enemyPokemon = enemyPokemon;
	}
}
