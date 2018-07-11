

public class Move {
	String name;
	Type type;
	MoveCategory moveCategory;
	Status statusInflicted;
	int power;
	int accuracy;
	int basePP;
	
	//damage dealing moves (physical and special)
	public Move(String name, Type type, MoveCategory moveCategory, int power, int accuracy, int basePP){
		this.name = name;
		this.type = type;
		this.moveCategory = moveCategory;
		this.power = power;
		this.accuracy = accuracy;
		this.basePP = basePP;
	}
	
	//status moves
	public Move(String name, Type type, Status statusInflicted, int accuracy, int basePP){
		this.name = name;
		this.type = type;
		moveCategory = MoveCategory.STATUS;
		this.statusInflicted = statusInflicted;
		power = 0;
		this.accuracy = accuracy;
		this.basePP = basePP;
	}
	
	@Override
	public String toString(){
		return name;
	}
}