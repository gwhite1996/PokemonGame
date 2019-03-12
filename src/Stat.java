//updated way of doing stats
public class Stat{

	private String name;
	private int trueValue;
	private int stage;
	private double otherMultipliers;

	public Stat(String name, int trueValue){
		this.name = name;
		this.trueValue = trueValue;
		stage = 0;
		otherMultipliers = 1;
	}

	private double getTotalMultiplier(){
		return getStageMultiplier() * otherMultipliers;
	}

	private double getStageMultiplier(){
		switch(stage){
		case -6:
			return 2 / 8d;
		case -5:
			return 2 / 7d;
		case -4:
			return 2 / 6d;
		case -3:
			return 2 / 5d;
		case -2:
			return 2 / 4d;
		case -1:
			return 2 / 3d;
		case 0:
			return 2 / 2d;
		case 1:
			return 3 / 2d;
		case 2:
			return 4 / 2d;
		case 3:
			return 5 / 2d;
		case 4:
			return 6 / 2d;
		case 5:
			return 7 / 2d;
		case 6:
			return 8 / 2d;
		default:
			return 0d;
		}
	}

	public int getBattleValue(){
		return (int) (trueValue * getTotalMultiplier());
	}

	public int getTrueValue(){
		return trueValue;
	}

	public int getStage(){
		return stage;
	}

	// stacks on another multiplier
	public void addMultiplier(double multiplicationFactor){
		otherMultipliers *= multiplicationFactor;
	}

	public void increaseStage(int increaseAmount){
		stage += increaseAmount;
		if(stage > 6){
			stage = 6; // Doesn't change actual value
		}
		else
			if(stage < -6){
				stage = -6;
			}
		// should probably be a message if the multiplier can't be raised any more
	}

	public void resetStage(){
		stage = 0;
	}

	public void printStat(){
		System.out.println(name + ": Fixed(" + getTrueValue() + "), Stage(" + getStage() + "), Multiplier("
				+ getTotalMultiplier() + "), Battle Value(" + getBattleValue() + ")");
	}

	@Override
	public String toString(){
		return name;
	}
}