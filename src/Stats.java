

public class Stats { //permenant stats
	int totalHP;
	int hpRemaining;
	int attack;
	int deffense;
	int spAtk;
	int spDef;
	int speed;
	
	
	Stats(int totalHP, int attack, int deffense, int spAtk, int spDef, int speed){  //all pokemon are hardy nature with 0 EV and IV
		this.totalHP = totalHP;
		this.attack = attack;
		this.deffense = deffense;
		this.spAtk = spAtk;
		this.spDef = spDef;
		this.speed = speed;
		hpRemaining = totalHP;
	}
	
	public static double stageMultiplier(int statStage){
		if(statStage > 6){
			statStage = 6;
		}
		else if(statStage < -6){
			statStage = -6;
		}
		//should probably be a message if the multiplyer cant be raised any more
		switch(statStage){
		case -6:return 2/8;
		case -5:return 2/7;
		case -4:return 2/6;
		case -3:return 2/5;
		case -2:return 2/4;
		case -1:return 2/3;
		case 0:return 2/2;
		case 1:return 3/2;
		case 2:return 4/2;
		case 3:return 5/2;
		case 4:return 6/2;
		case 5:return 7/2;
		case 6:return 8/2;
		default :return 0;
		}
	}
}