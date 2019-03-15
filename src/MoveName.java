import java.util.Random;

public class MoveName{ // important to realize this does NOT extend Action

	private String name;
	private int priority;
	private Type type;
	private MoveCategory moveCategory;
	private int power;
	private int accuracy;
	private int basePP;
	private Random rand = new Random();

	// constructor takes the basic move info
	public MoveName(String name, Type type, MoveCategory moveCategory, int power, int accuracy, int basePP){
		this.name = name;
		this.type = type;
		this.moveCategory = moveCategory;
		this.power = power;
		this.accuracy = accuracy;
		this.basePP = basePP;
		this.priority = 0; // ALL ARE PRIORITY 0 temp
	}

	// must be overridden for each specific move
	public void useMoveName(Pokemon user, Pokemon target){
		System.out.println(this + " doesn't do anything! You must define it's useMove() method");
	}

	int damageAttack(Pokemon user, Pokemon target){
		double typeEffectiveness = Type.typeEffectiveness(this.type, target.species);
		double stab = Type.stab(this.type, user.species);
		double criticalHit = criticalHit();
		// double randomAttackFactor = randomAttackFactor(); //Real Pokemon games include this
		double calculatedAttack;
		double calculatedDeffense;
		if(this.moveCategory == MoveCategory.PHYSICAL){
			calculatedAttack = user.stats.attack.getBattleValue();
			calculatedDeffense = target.stats.deffense.getBattleValue();
		}
		else{
			calculatedAttack = user.stats.spAtk.getBattleValue();
			calculatedDeffense = target.stats.spDef.getBattleValue();
		}
		double modifier = typeEffectiveness * stab * criticalHit; // can be affected by many things
		int damage = (int)(((((((2 * user.level) / 5) + 2) * this.power * (calculatedAttack / calculatedDeffense)) / 50)
				+ 2) * modifier + 0.5); // formula from bulbapedia
		System.out.println(user + " hit " + target + " with " + this + " for " + damage + " damage.");
		System.out.println(" It was " + typeEffectivenessMessage(typeEffectiveness) + " and STAB was " + stab + "."); // (temporarily here);
		target.stats.hpRemaining -= damage;
		return damage; // the return value is only sometimes used
	}

	void statusAttack(Pokemon user, Pokemon target, Status statusInflicted, int chancePercentage){
		System.out.println(user + " used " + this + " on " + target);
		if((chancePercentage / 100d) > rand.nextDouble()){ // most moves only have a certain chance of inflicting the
															// status
			if(Status.inflictStatus(target, statusInflicted)){
				System.out.println(" " + target + " is now " + statusInflicted);
			}
		}
	}

	void statAttack(Pokemon user, Pokemon target, String statAffected, int stageIncrease){
		switch(statAffected){ // IMPORTANT that statAffected is lowercase
		case "attack":
			target.stats.attack.increaseStage(stageIncrease);
			break;
		case "deffense":
			target.stats.deffense.increaseStage(stageIncrease);
			break;
		case "spAtk":
			target.stats.spAtk.increaseStage(stageIncrease);
			break;
		case "spDef":
			target.stats.spDef.increaseStage(stageIncrease);
			break;
		case "speed":
			target.stats.speed.increaseStage(stageIncrease);
			break;
		case "accuracy":
			target.stats.accuracy.increaseStage(stageIncrease);
			break;
		case "evasion":
			target.stats.evasion.increaseStage(stageIncrease);
			break;
		default:
			System.out.println("Invalid statAffected in statAttack. The move used is " + this);
		}
		System.out.println(user + " used " + this + " on " + target + " it's " + statAffected + " was increased by "
				+ stageIncrease + ".");
		target.stats.printStats();
		;
	}

	void healAttack(Pokemon user, int hpRestored){
		if(user.stats.hpRemaining + hpRestored > user.stats.totalHP){
			hpRestored = user.stats.totalHP - user.stats.hpRemaining;
		}
		user.stats.hpRemaining += hpRestored;
		System.out.println(user + " healed itself with " + this + " for " + hpRestored + " HP.");
	}

	void takeRecoil(Pokemon user, int hpTaken){
		if(user.stats.hpRemaining - hpTaken < 0){
			hpTaken = user.stats.hpRemaining;
		}
		user.stats.hpRemaining -= hpTaken;
		System.out.println(user + " took " + hpTaken + " damage from recoil.");
	}

	public double criticalHit(){
		if(rand.nextInt(16) == 0){
			System.out.println("  A critical hit!!");
			return 1.5;
		}
		return 1;
	}

	// This factor is not included in damage calculation since I don't like the luck involved
	public double randomAttackFactor(){ // gives random number from .85 to 1 (inclusive);
		return (rand.nextInt(16) + 85) / 100.0;
	}

	public String typeEffectivenessMessage(double typeEffectiveness){
		switch((int)(typeEffectiveness * 4)){
		case 0:
			return "Not effective at all";
		case 1:
			return "Minimally effective";
		case 2:
			return "Not very effective";
		case 4:
			return "Normally effective";
		case 8:
			return "Super effective";
		case 16:
			return "Hyper effective";
		default:
			return "Invalid typeEffectiveness double input to typeEffectivenessMessage()";
		}
	}

	public String getName(){
		return name;
	}

	public Type getType(){
		return type;
	}

	public MoveCategory getMoveCategory(){
		return moveCategory;
	}

	public int getPower(){
		return power;
	}

	public int getAccuracy(){
		return accuracy;
	}

	public int getPriority(){
		return priority;
	}

	public int getBasePP(){
		return basePP;
	}

	public String toString(){
		return getName();
	}
}