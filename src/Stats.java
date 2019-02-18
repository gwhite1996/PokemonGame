

public class Stats {
	int totalHP;
	Stat attack;
	Stat deffense;
	Stat spAtk;
	Stat spDef;
	Stat speed;
	Stat accuracy;
	Stat evasion;
	
	//constructor takes the trueValue of each stat
	Stats(int totalHP, int attack, int deffense, int spAtk, int spDef, int speed){  //all pokemon are hardy nature with 0 EV and IV
		this.totalHP = totalHP;
		this.attack = new Stat("Attack", attack);
		this.deffense = new Stat("Deffense", deffense);
		this.spAtk = new Stat("Special Attack", spAtk);
		this.spDef = new Stat("Special Deffense", spDef);
		this.speed = new Stat("Speed", speed);
		accuracy = new Stat("Accuracy", 100); //accuracy and evasion always have 100 base value
		evasion = new Stat("Evasion", 100);
	}
	
	public void resetAllStages(){ //resets each stat back to 0
		attack.resetStage();
		deffense.resetStage();
		spAtk.resetStage();
		spDef.resetStage();
		speed.resetStage();
		accuracy.resetStage();
		evasion.resetStage();
	}
	
	public void printStats(){
		System.out.println("......... Stats ..........");
		System.out.println("Total HP(" + totalHP + ")");
		attack.printStat();
		deffense.printStat();
		spAtk.printStat();
		spDef.printStat();
		speed.printStat();
		accuracy.printStat();
		evasion.printStat();
		System.out.println("..........................");
	}
}