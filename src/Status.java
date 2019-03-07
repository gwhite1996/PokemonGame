import java.util.Random;


public enum Status{
	ASLEEP,
	BURNED,
	FROZEN,
	PARALYZED,
	POISONED,
	FAINTED,
	NONE;


	// Methods to inflict each status ailment
	private static boolean putToSleep(Pokemon pokemon){
		removeStatus(pokemon);
		pokemon.setStatus(Status.ASLEEP);
		pokemon.canAttack = false;
		pokemon.statusTurnsRemaining = new Random().nextInt(7) + 1; //random int 1 to 7
		return true;
	}	
	private static boolean burn(Pokemon pokemon){
		if(pokemon.species.type1 == Type.FIRE || pokemon.species.type2 == Type.FIRE){
			System.out.println("Fire type pokemon can't be burned!");
			return false;
		}
		else{
			removeStatus(pokemon);
			pokemon.setStatus(Status.BURNED);
			pokemon.stats.deffense.addMultiplier(0.5);
			return true;
		}
	}
	private static boolean freeze(Pokemon pokemon){
		if(pokemon.species.type1 == Type.ICE || pokemon.species.type2 == Type.ICE){
			System.out.println("Ice type pokemon can't be frozen!");
			return false;
		}
		else{
			removeStatus(pokemon);
			pokemon.setStatus(Status.FROZEN);
			pokemon.canAttack = false;
			pokemon.statusTurnsRemaining = 1; //can be infinite really
			return true;
		}
	}
	private static boolean paralyze(Pokemon pokemon){
		if(pokemon.species.type1 == Type.ELECTRIC || pokemon.species.type2 == Type.ELECTRIC){
			System.out.println("Electric type pokemon can't be paralyzed!");
			return false;
		}
		else{
			removeStatus(pokemon);
			pokemon.setStatus(Status.PARALYZED);
			pokemon.stats.speed.addMultiplier(0.25);
			return true;
		}
	}
	private static boolean poison(Pokemon pokemon){
		if(pokemon.species.type1 == Type.POISON || pokemon.species.type2 == Type.POISON){
			System.out.println("Poison type pokemon can't be poisoned!");
			return false;
		}
		else if(pokemon.species.type1 == Type.STEEL || pokemon.species.type2 == Type.STEEL){
			System.out.println("Steel type pokemon can't be poisoned!");
			return false;
		}
		else{
			removeStatus(pokemon);
			pokemon.setStatus(Status.POISONED);
			return true;
		}
	}
	public static boolean inflictStatus(Pokemon pokemon, Status status){
		if(pokemon.getStatus() == status){
			System.out.println(pokemon + " is already " + status + ".");
			return false;
		}
		else{
			switch(status){
			case ASLEEP:return Status.putToSleep(pokemon);
			case BURNED:return Status.burn(pokemon);
			case FROZEN:return Status.freeze(pokemon);
			case PARALYZED:return Status.paralyze(pokemon);
			case POISONED:return Status.poison(pokemon);
			case NONE:return false;
			default: System.out.println("Invalid status in inflictStatus()");
			return false;
			}
		}

	}
	public static boolean removeStatus(Pokemon pokemon){

		Status status = pokemon.getStatus();
		switch(status){ //special action that must be taken for certain statuses when removed
		case ASLEEP:removeSleep(pokemon);break;
		case BURNED:removeBurn(pokemon);break;
		case FROZEN:removeFreeze(pokemon);break;
		case PARALYZED:removeParalysis(pokemon);break;
		case POISONED:removePoison(pokemon);break;
		case NONE:return false;
		default: System.out.println("Invalid status in removeStatus()");return false;
		}
		pokemon.setStatus(NONE);
		return true;
	}

	private static void removePoison(Pokemon pokemon){
		System.out.println(pokemon + " is no longer poisoned.");
	}
	private static void removeParalysis(Pokemon pokemon){
		pokemon.stats.speed.addMultiplier(4);
		pokemon.canAttack = true;
		System.out.println(pokemon + "is no longer paralyzed.");
	}
	private static void removeFreeze(Pokemon pokemon){
		pokemon.canAttack = true;
		System.out.println(pokemon + " thawed.");
	}
	private static void removeBurn(Pokemon pokemon){
		pokemon.stats.deffense.addMultiplier(2);
		System.out.println(pokemon + " is no longer burned.");
	}
	private static void removeSleep(Pokemon pokemon){
		pokemon.canAttack = true;
		System.out.println(pokemon + " woke up.");
	}

	//the following methods run each turn while the pokemon is affected
	private static void takeEffectOfSleep(Pokemon pokemon){
		if(pokemon.statusTurnsRemaining > 0){
			System.out.println(pokemon + " is fast asleep!");
			pokemon.statusTurnsRemaining --;
		}
		else{
			removeSleep(pokemon);
		}
		
	}
	private static void takeEffectOfBurn(Pokemon pokemon){

		int burnDamage = (int)(((double)pokemon.stats.totalHP)/8.0);
		pokemon.stats.hpRemaining -= burnDamage;
		System.out.println(pokemon + " was hurt for " + burnDamage + " HP by it's burn!");
	}
	private static void takeEffectOfFreeze(Pokemon pokemon){

		if(pokemon.statusTurnsRemaining > 0){
			System.out.println(pokemon + " is frozen solid and cannot move!");
			if(new Random().nextInt(5) == 0){
				pokemon.statusTurnsRemaining = 0;
				return; //next turn it will be thawed
			}
		}
		else{
			removeFreeze(pokemon);
		}
	}
	private static void takeEffectOfParalysis(Pokemon pokemon){

		if(new Random().nextInt(4) == 0){
			pokemon.canAttack = false;
			System.out.println(pokemon + " is paralyzed. It can't move!");
		}
		else{
			pokemon.canAttack = true;
		}
	}
	private static void takeEffectOfPoison(Pokemon pokemon){
		int poisonDamage = (int)(((double)pokemon.stats.totalHP)/8.0);
		pokemon.stats.hpRemaining -= poisonDamage;
		System.out.println(pokemon + " was hurt by poison for " + poisonDamage + " HP!");
	}

	public static void takeEffectOfStatus(Pokemon pokemon){
		switch(pokemon.getStatus()){
		case ASLEEP:takeEffectOfSleep(pokemon);
		break;
		case BURNED:takeEffectOfBurn(pokemon);
		break;
		case FROZEN:takeEffectOfFreeze(pokemon);
		break;
		case PARALYZED:takeEffectOfParalysis(pokemon);
		break;
		case POISONED:takeEffectOfPoison(pokemon);
		break;
		case NONE: //Just returns
		break;
		default:System.out.println("Invalid status in TakeEffectOfStatus " + pokemon + ".getStatus() gives: " + pokemon.getStatus());
		break;
		}
	}
}