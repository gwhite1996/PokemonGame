package gw96_pokemon;

import java.util.Random;

//temp this whole class needs to be changed. not every hiker should be hiker mike
class Hiker extends Trainer{

	private int delay = 0;
	Random rand = new Random();

	Hiker(String name, int xStart, int yStart){
		super(name, xStart, yStart);
		greeting = "Oh shit whatup! I'm Hiker Mike. Lets battle!";
		battleIntro = "This aint my first rodeo kideo.";
		battleDefeated = "Damn you're tough for someone who's balls haven't dropped!";
		afterBattle = "Good luck out there pussy.";
	}

	@Override
	public void update(){
		if(!getInteracting()){
			delay = (delay + 1) % 10;
			if(delay == 0){ // chooses random direction 1/2 of updates
				switch(rand.nextInt(8)){
				case 0:
					setDirection(Direction.EAST);
					break;
				case 1:
					setDirection(Direction.WEST);
					break;
				case 2:
					setDirection(Direction.NORTH);
					break;
				case 3:
					setDirection(Direction.SOUTH);
					break;
				default:
					break;
				}
			}
			else if(delay == 5){ // moves forward 1/3rd of updates
				if(rand.nextInt(3) == 0){
					moveForward();
				}
			}
		}
		super.update();
	}
}
