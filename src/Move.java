

public class Move {
	String name;
	Type type;
	MoveCategory moveCategory;
	int power;
	int accuracy;
	int basePP;
	
	public Move(String name, Type type, MoveCategory moveCategory, int power, int accuracy, int basePP){
		this.name = name;
		this.type = type;
		this.moveCategory = moveCategory;
		this.power = power;
		this.accuracy = accuracy;
		this.basePP = basePP;
	}
}