import java.util.Random;


public enum Status{
	ASLEEP(true),
	BURNED(false),
	FROZEN(true),
	PARALYZED(false),
	POISONED(false),
	NONE(false);

	private boolean wearsOff; //the effect goes away after enough turns


	private Status(boolean wearsOff){
		this.wearsOff = wearsOff;
	}


	// Methods to inflict each status ailment
	public static void PutToSleep(Pokemon pokemon){
		removeStatus(pokemon);
		pokemon.setStatus(Status.ASLEEP);
		pokemon.canAttack = false;
		pokemon.statusTurnsRemaining = new Random().nextInt(7) + 1; //random int 1 to 7
		
	}	
	public static void burn(Pokemon pokemon){
		if(pokemon.species.type1 == Type.FIRE || pokemon.species.type2 == Type.FIRE){
			System.out.println("Fire type pokemon can't be burned!");
			return;
		}
		else{
			removeStatus(pokemon);
			pokemon.setStatus(Status.BURNED);
			pokemon.statusTurnsRemaining = 10;
		}
	}
	public static void freeze(Pokemon pokemon){
		if(pokemon.species.type1 == Type.ICE || pokemon.species.type2 == Type.ICE){
			System.out.println("Ice type pokemon can't be frozen!");
			return;
		}
		else{
			removeStatus(pokemon);
			pokemon.setStatus(Status.FROZEN);
			pokemon.statusTurnsRemaining = 10;
			pokemon.canAttack = false;
		}
	}
	public static void paralyze(Pokemon pokemon){
		if(pokemon.species.type1 == Type.ELECTRIC || pokemon.species.type2 == Type.ELECTRIC){
			System.out.println("Electric type pokemon can't be paralyzed!");
			return;
		}
		else{
			removeStatus(pokemon);
			pokemon.setStatus(Status.PARALYZED);
			pokemon.statusTurnsRemaining = 10;
		}
	}
	public static void poison(Pokemon pokemon){
		if(pokemon.species.type1 == Type.POISON || pokemon.species.type2 == Type.POISON){
			System.out.println("Poison type pokemon can't be poisoned!");
			return;
		}
		else if(pokemon.species.type1 == Type.STEEL || pokemon.species.type2 == Type.STEEL){
			System.out.println("Steel type pokemon can't be poisoned!");
			return;
		}
		else{
			removeStatus(pokemon);
			pokemon.setStatus(Status.POISONED);
			pokemon.statusTurnsRemaining = 10;
		}
	}
	public static void removeStatus(Pokemon pokemon){ // Undo's the effect of the status then removes it
		pokemon.canAttack = true;
		pokemon.setStatus(NONE);
		pokemon.statusTurnsRemaining = 0;
	}

	//the following methods run each turn while the pokemon is affected
	private static void takeEffectOfSleep(Pokemon pokemon){
		System.out.println(pokemon.name + " is fast asleep!");
	}
	private static void takeEffectOfBurn(Pokemon pokemon){
		pokemon.stats.multipliers.deffense *= 0.5;
		int burnDamage = (int)(((double)pokemon.stats.totalHP)/8.0);
		pokemon.hpRemaining -= burnDamage;
		System.out.println(pokemon.name + " was hurt for " + burnDamage + " HP by it's burn!");
	}
	private static void takeEffectOfFreeze(Pokemon pokemon){
		if(pokemon.statusTurnsRemaining > 1 && new Random().nextInt(5) == 0){
			pokemon.statusTurnsRemaining = 1; //thawing takes a turn
		}
		System.out.println(pokemon.name + " is frozen solid and cannot move!");
		
	}
	private static void takeEffectOfParalysis(Pokemon pokemon){
		pokemon.stats.multipliers.speed *= 0.25;
		System.out.println(pokemon.name + " is paralyzed. It can't move!");
		if(new Random().nextInt(4) == 0){
			pokemon.canAttack = false;
		}
		else{
			pokemon.canAttack = true;
		}
	}
	private static void takeEffectOfPoison(Pokemon pokemon){
		int poisonDamage = (int)(((double)pokemon.stats.totalHP)/8.0);
		pokemon.hpRemaining -= poisonDamage;
		System.out.println(pokemon.name + " was hurt by poison for " + poisonDamage + " HP!");
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
		default:System.out.println("Invalid status in TakeEffectOfStatus");
		break;
		}
	}

	public boolean wearsOff() {
		return wearsOff;
	}
}