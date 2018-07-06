

public class Move {
	String name;
	Type type;
	int power;
	int accuracy;
	int basePP;
	int ppRemaining;
	
	public Move(String name, Type type, int power, int accuracy, int basePP){
		this.name = name;
		this.type = type;
		this.power = power;
		this.accuracy = accuracy;
		this.basePP = basePP;
		ppRemaining = basePP;
	}
}