public class Move extends Action{ // a Move in an instance of a moveName. Each
									// Pokemon has 4

	private MoveName moveName;
	private int totalPP;
	private int ppLeft;
	private boolean disabled;

	public Move(MoveName moveName){
		super(moveName.getName(), moveName.getPriority());
		this.moveName = moveName;
		totalPP = moveName.getBasePP();
		ppLeft = totalPP;
		disabled = false;
	}

	public void useMove(Pokemon user, Pokemon target){
		moveName.useMoveName(user, target);
	}

	public MoveName getMoveName(){
		return moveName;
	}

	public int getTotalPP(){
		return totalPP;
	}

	public void setTotalPP(int totalPP){ // called when PPup is used
		this.totalPP = totalPP;
	}

	public int getPPLeft(){
		return ppLeft;
	}

	public void setPPLeft(int ppLeft){
		this.ppLeft = ppLeft;
	}

	public boolean isDisabled(){
		return disabled;
	}

	public void setDisabled(boolean disabled){
		this.disabled = disabled;
	}

	public void printMove(){
		System.out.print(this + ": " + moveName.getType() + ", " + moveName.getMoveCategory() + ", PP(" + ppLeft + "/"
				+ totalPP + "), Power(" + moveName.getPower() + "), Accuracy(" + moveName.getAccuracy() + ")");
		if(disabled){
			System.out.print(", {DISABLED}");
		}
		System.out.println();
	}

	public String toString(){
		return getName();
	}
}
