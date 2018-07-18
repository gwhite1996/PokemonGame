import java.util.Random;


public class Hiker extends Trainer{
	
	
	private int delay = 0;
	Random rand = new Random();


	public Hiker(String name, int xStart, int yStart) {
		super(name, xStart, yStart);
		greeting = "Oh shit whatup! I'm Hiker Mike. Lets battle!";
		battleIntro = "This aint my first rodeo kideo.";
		battleDefeated = "Damn you're tough for someone who's balls haven't dropped!";
		afterBattle = "Good luck out there pussy.";
	}

	public void update(){
		if(!getInteracting()){
			delay = (delay+1) % 10;
			if(delay == 0){ //chooses random direction 1/2 of updates
				switch(rand.nextInt(8)){
				case 0:dir = Direction.EAST;break;
				case 1:dir = Direction.WEST;break;
				case 2:dir = Direction.NORTH;break;
				case 3:dir = Direction.SOUTH;break;
				default:break;
				}
			}
			else if(delay == 5){ //moves forward 1/3rd of updates
				if(rand.nextInt(3) == 0){
					moveForward();
				}
			}
		}
		super.update();
	}
}
