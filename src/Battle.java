import java.util.Random;
import java.util.Scanner;


public class Battle {
	private Pokemon playerPokemon;
	private Pokemon enemyPokemon;
	private Random rand = new Random();
	Scanner in = new Scanner(System.in); // should probably be in a higher up class

	boolean playerAttacksFirst;


	public boolean playerAttacksFirst(){
		return playerPokemon.stats.speed*playerPokemon.stats.multipliers.speed >= enemyPokemon.stats.speed*enemyPokemon.stats.multipliers.speed;
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
				statusAttack(target, move);
			}
			else{
				damageAttack(user, target, move);
			}
		}
		else{
			System.out.println(user.name + " used " + move.name + " but it missed!");
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

		System.out.println(user.name + " hit " + target.name + " with " + move.name + " for " + damage + " damage.");
		System.out.println("It was " + typeEffectivenessMessage(typeEffectiveness) + " and STAB was " + stab + "."); //temporarily here);
		target.hpRemaining -= damage;
	}
	
	private void statusAttack(Pokemon target, Move move) {
		target.setStatus(move.statusInflicted);
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
	public void runTillFaint(){
		while(playerPokemon.hpRemaining > 0 && enemyPokemon.hpRemaining > 0){
			fullTurn();
		}
		if(playerPokemon.hpRemaining <= 0){
			System.out.println(playerPokemon.name + " has fainted!");
		}
		if(enemyPokemon.hpRemaining <= 0){
			System.out.println(enemyPokemon.name + " has fainted!");
		}
	}
	
	private void fullTurn() { //A half turn for each pokemon
		if(playerAttacksFirst()){
			System.out.println(" The player attacks first");
			halfTurn(playerPokemon);
			halfTurn(enemyPokemon);
		}
		else{
			System.out.println( "The enemy attacks first");
			halfTurn(enemyPokemon);
			halfTurn(playerPokemon);
		}
		System.out.println("\n");
		LostMethods.showHealth(playerPokemon);
		LostMethods.showHealth(enemyPokemon);
	}
	
	private void halfTurn(Pokemon user){ //Basically a turn for a single pokemon
		user.stats.updateMultipliersFromStages();
		
		Pokemon target;
		if(user == playerPokemon){
			target = enemyPokemon;
		}
		else{
			target = playerPokemon;
		}
		
		int moveNumber;
		System.out.println();
		LostMethods.showMoveSet(user);
		System.out.println("Enter the move number for " + user.name + " to use:  (1 through 4 then Enter");
		
		String moveNumberString = in.nextLine(); //User input selects move to use
		switch(moveNumberString){
		case "1":moveNumber = 1;break;
		case "2":moveNumber = 2;break;
		case "3":moveNumber = 3;break;
		case "4":moveNumber = 4;break;
		default:moveNumber = 0;System.out.println("Invalid input string!");
		}
		useMove(user, target, moveNumber);
		
		if(user.statusTurnsRemaining > 0){
			Status.takeEffectOfStatus(user);
			if(user.getStatus().wearsOff()){
				user.statusTurnsRemaining --;
			}
		}
		else{
			Status.removeStatus(user);
		}
	}
	
	
	public void setPlayerPokemon(Pokemon playerPokemon){
		this.playerPokemon = playerPokemon;
	}
	public void setEnemyPokemon(Pokemon enemyPokemon){
		this.enemyPokemon = enemyPokemon;
	}
}
