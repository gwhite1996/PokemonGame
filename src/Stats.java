

public class Stats {
	int hp;
	int attack;
	int deffense;
	int spAtk;
	int spDef;
	int speed;
	int evasion;
	int accuracy ;

	//setting permenant stats
	Stats(int hp, int attack, int deffense, int spAtk, int spDef, int speed){
		this.hp = hp;
		this.attack = attack;
		this.deffense = deffense;
		this.spAtk = spAtk;
		this.spDef = spDef;
		this.speed = speed;
		evasion = 100;
		accuracy = 100;
	}
}