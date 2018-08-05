import java.util.Random;



public class Move extends Action{
	Type type;
	MoveCategory moveCategory;
	int power;
	int accuracy;
	int basePP;
	private Random rand = new Random();
	
	//constructor takes the basic move info
	public Move(String name, Type type, MoveCategory moveCategory, int power, int accuracy, int basePP){
		super(name);
		this.type = type;
		this.moveCategory = moveCategory;
		this.power = power;
		this.accuracy = accuracy;
		this.basePP = basePP;
	}
	
	//this is only the default. this will often be overridden
	public void useMove(Pokemon user, Pokemon target){
		damageAttack(user, target);
	}
		
	void damageAttack(Pokemon user, Pokemon target){
		double typeEffectiveness = Type.typeEffectiveness(this.type, target.species);
		double stab = Type.stab(this.type, user.species);
		double criticalHit = criticalHit();
		//double randomAttackFactor = randomAttackFactor();

		double calculatedAttack;
		double calculatedDeffense;

		if(this.moveCategory == MoveCategory.PHYSICAL){ //physical or special?
			calculatedAttack = user.stats.attack*user.stats.multipliers.attack;
			calculatedDeffense = target.stats.deffense*target.stats.multipliers.deffense;
		}
		else{
			calculatedAttack = user.stats.spAtk*user.stats.multipliers.spAtk;
			calculatedDeffense = target.stats.spDef*user.stats.multipliers.spDef;
		}
		
		double modifier = typeEffectiveness*stab*criticalHit; //can be affected by many things	
		int damage = (int)(((((((2*user.level)/5)+2)*this.power*(calculatedAttack/calculatedDeffense))/50)+2)*modifier + 0.5); //formula from bulbapedia

		System.out.println(user + " hit " + target + " with " + this + " for " + damage + " damage.");
		System.out.println(" It was " + typeEffectivenessMessage(typeEffectiveness) + " and STAB was " + stab + "."); //temporarily here);
		target.hpRemaining -= damage;
	}

	void statusAttack(Pokemon user, Pokemon target, Status statusInflicted, int chancePercentage) {
		System.out.println(user + " used " + this + " on " + target);
		
		if((chancePercentage/100d) > rand.nextDouble()){ //most moves only have a certain chance of inflicting the status
			if(Status.inflictStatus(target, statusInflicted)){
				System.out.println(" " + target + " is now " + statusInflicted);
			}
		}
	}

	void statAttack(Pokemon user, Pokemon target, String statAffected, int stageIncrease){

		switch (statAffected){ //IMPORTANT that statAffected is lowercase
		case "attack":target.stats.stages.attack += stageIncrease;break;
		case "deffense":target.stats.stages.deffense += stageIncrease;break;
		case "spAtk":target.stats.stages.spAtk += stageIncrease;break;
		case "spDef":target.stats.stages.spDef += stageIncrease;break;
		case "speed":target.stats.stages.speed += stageIncrease;break;
		case "accuracy":target.stats.stages.accuracy += stageIncrease;break;
		case "evasion":target.stats.stages.evasion += stageIncrease;break;
		default: System.out.println("Invalid statAffected in statAttack. The move used is " + this);
		}
		System.out.println(user + " used " + this + " on " + target + " it's " + statAffected + " was increased by " + stageIncrease + ".");
		LostMethods.printStats(target);
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
}