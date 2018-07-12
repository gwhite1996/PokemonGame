

public class Move {
	private String name;
	Type type;
	MoveCategory moveCategory;
	Status statusInflicted;
	String statAffected;
	int stageIncrease;
	int power;
	int accuracy;
	int basePP;
	boolean targetsSelf;
	
	//damage dealing moves (physical and special)
	public Move(String name, Type type, MoveCategory moveCategory, int power, int accuracy, int basePP){
		this.name = name;
		this.type = type;
		this.moveCategory = moveCategory;
		this.power = power;
		this.accuracy = accuracy;
		this.basePP = basePP;
		targetsSelf = false;
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
		targetsSelf = false;
	}
	
	//stat moves (raise or lower stats)
	public Move(String name, Type type, String statAffected, int stageIncrease, int accuracy, int basePP, boolean targetsSelf){
		this.name = name;
		this.type = type;
		moveCategory = MoveCategory.STAT;
		this.statAffected = statAffected;
		this.stageIncrease = stageIncrease; //negative if it decreases the stage
		power = 0;
		this.accuracy = accuracy;
		this.basePP = basePP;
		this.targetsSelf = targetsSelf;
	}
	
	
	@Override
	public String toString(){
		return name;
	}
}