

public class Stats { //permenant stats
	int totalHP;
	int attack;
	int deffense;
	int spAtk;
	int spDef;
	int speed;
	StatStages stages;
	StatMultipliers multipliers;
	
	
	Stats(int totalHP, int attack, int deffense, int spAtk, int spDef, int speed){  //all pokemon are hardy nature with 0 EV and IV
		this.totalHP = totalHP;
		this.attack = attack;
		this.deffense = deffense;
		this.spAtk = spAtk;
		this.spDef = spDef;
		this.speed = speed;
		this.stages = new StatStages();
		this.multipliers = new StatMultipliers();
	}
	
	private static double getMultiplier(int statStage){
		if(statStage > 6){
			statStage = 6; //Doesn't change actual value
		}
		else if(statStage < -6){
			statStage = -6;
		}
		//should probably be a message if the multiplyer cant be raised any more
		switch(statStage){
		case -6:return 2/8.0;
		case -5:return 2/7.0;
		case -4:return 2/6.0;
		case -3:return 2/5.0;
		case -2:return 2/4.0;
		case -1:return 2/3.0;
		case 0:return 2/2.0;
		case 1:return 3/2.0;
		case 2:return 4/2.0;
		case 3:return 5/2.0;
		case 4:return 6/2.0;
		case 5:return 7/2.0;
		case 6:return 8/2.0;
		default :return 0.0;
		}
	}
	
	public void updateMultipliersFromStages(){ //this must be run every turn
		multipliers.attack = getMultiplier(stages.attack);
		multipliers.deffense = getMultiplier(stages.deffense);
		multipliers.spAtk = getMultiplier(stages.spAtk);
		multipliers.spDef = getMultiplier(stages.spDef);
		multipliers.speed = getMultiplier(stages.speed);
		multipliers.accuracy = getMultiplier(stages.accuracy);
		multipliers.evasion = getMultiplier(stages.evasion);
	}
}