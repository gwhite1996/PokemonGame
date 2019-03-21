package main;

class Move extends Action { // a Move in an instance of a moveName. Each
							// Pokemon has 4

	private MoveName moveName;
	private int totalPP;
	private int ppLeft;
	private boolean disabled;

	Move(MoveName moveName) {
		super(moveName.getName(), moveName.getPriority());
		this.moveName = moveName;
		totalPP = moveName.getBasePP();
		ppLeft = totalPP;
		disabled = false;
	}

	void useMove(Pokemon user, Pokemon target) {
		moveName.useMoveName(user, target);
	}

	MoveName getMoveName() {
		return moveName;
	}

	int getTotalPP() {
		return totalPP;
	}

	void setTotalPP(int totalPP) { // called when PPup is used
		this.totalPP = totalPP;
	}

	int getPPLeft() {
		return ppLeft;
	}

	void setPPLeft(int ppLeft) {
		this.ppLeft = ppLeft;
	}

	boolean isDisabled() {
		return disabled;
	}

	void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	void printMove() {
		System.out.print(this + ": " + moveName.getType() + ", " + moveName.getMoveCategory() + ", PP(" + ppLeft + "/"
				+ totalPP + "), Power(" + moveName.getPower() + "), Accuracy(" + moveName.getAccuracy() + ")");
		if(disabled) {
			System.out.print(", {DISABLED}");
		}
		System.out.println();
	}

	@Override
	public String toString() {
		return getName();
	}
}
