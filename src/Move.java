
public class Move extends Action{ //a Move in an instance of a moveName. Each Pokemon has 4
	MoveName moveName;
	int totalPP;
	int ppLeft;
	boolean disabled;
	
	public Move(MoveName moveName) {
		super(moveName.name, moveName.priority);
		this.moveName = moveName;
		totalPP = moveName.basePP;
		ppLeft = totalPP;
		disabled = false;
	}
	
	
	public void printMove(){
		System.out.print(this + ": " + moveName.type + ", " + moveName.moveCategory + ", PP(" + ppLeft + "/" + totalPP + "), Power(" + moveName.power + "), Accuracy(" + moveName.accuracy + ")");
		if(disabled){
			System.out.print(", DISABLED");
		}
		System.out.println();
	}
	
	public String toString() {
		return moveName.name;
	}


	public void useMove(Pokemon user, Pokemon target){
		moveName.useMoveName(user, target);
	}
}
