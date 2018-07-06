import java.util.Random;


public class Battle {
	private Player player;
	private Trainer enemy;
	private Pokemon playerPokemon;
	private Pokemon enemyPokemon;
	boolean playerAttacksFirst;
	Random rand = new Random();
	

	public Battle(Player player, Trainer enemy){
		this.player = player;
		this.enemy = enemy;
	}
 
	public boolean playerAttacksFirst(){
		return playerPokemon.stats.speed*Stats.stageMultiplier(playerPokemon.statStages.speedStage) >= enemyPokemon.stats.speed*Stats.stageMultiplier(enemyPokemon.statStages.speedStage);
	}
	
	public String useMove(Pokemon user, Pokemon target, int moveNumber){
		Move move = null;
		switch(moveNumber){
		case 1: move = user.move1;break;
		case 2: move = user.move2;break;
		case 3: move = user.move3;break;
		case 4: move = user.move4;break;
		default: System.out.println("Invalid moveNumber given to useMove()");
		}
		if(!PPLeft(move)){
			return "There is no pp left for that move!";
		} 
		else{ //there is pp left
			move.ppRemaining--;
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
	}
	public void damageAttack(Pokemon user, Pokemon target, Move move){
		double typeEffectiveness = Type.typeEffectiveness(move.type, target.species);
		
		
		double modifier = typeEffectiveness; //can be affected by many things	
		int damage = (int)((((((2*user.level)/5)+2)*move.power*((user.stats.attack*Stats.stageMultiplier(user.statStages.attackStage))/(target.stats.deffense*Stats.stageMultiplier(target.statStages.deffenseStage)))/50)+2)*modifier + 0.5); //formula from bulbapedia
		
		System.out.println(user.name + " hit " + target.name + " with " + move.name + " for " + damage + " damage. It was " + typeEffectivenessMessage(typeEffectiveness)); //temporarily here);
		target.stats.hpRemaining -= damage;
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
	
	public boolean PPLeft(Move move){
		if(move.ppRemaining <= 0){
			// Change message to say no pp remaining.
			return false;
		}
		else{
			return true;
		}
	}
	
	public void setPlayerPokemon(Pokemon playerPokemon){
		this.playerPokemon = playerPokemon;
	}
	public void setEnemyPokemon(Pokemon enemyPokemon){
		this.enemyPokemon = enemyPokemon;
	}
	
	public void testTwoTurns(){

		takeTurn();
		useMove(playerPokemon, enemyPokemon, 1);
		useMove(enemyPokemon, playerPokemon, 1);
		takeTurn();
		useMove(playerPokemon, enemyPokemon, 2);
		useMove(enemyPokemon, playerPokemon, 2);
		takeTurn();
		useMove(playerPokemon, enemyPokemon, 2);
		useMove(enemyPokemon, playerPokemon, 2);
		takeTurn();
	}

	private void takeTurn() {
		if(playerAttacksFirst()){
			System.out.println(" The player attacks first");
		}
		else System.out.println( "The enemy attacks first");
		System.out.println(playerPokemon.name + "'s hpRemaining is: " + playerPokemon.stats.hpRemaining);
		System.out.println(enemyPokemon.name + "'s hpRemaining is: " + enemyPokemon.stats.hpRemaining);
	}
}
