
public class MoveList {

	final static Move tackle = new Move("Tackle", Type.NORMAL, MoveCategory.PHYSICAL, 40, 100, 35);
	final static Move scratch = new Move("Scratch", Type.NORMAL, MoveCategory.PHYSICAL, 40, 100, 35);
	final static Move growl = new Move("Growl", Type.NORMAL, MoveCategory.STATUS, 0, 100, 40){
		public void useMove(Pokemon user, Pokemon target){
			statAttack(user, target, "attack", -1);
		}
	};
	final static Move ember = new Move("Ember", Type.FIRE, MoveCategory.SPECIAL, 40, 100, 25){
		public void useMove(Pokemon user, Pokemon target){
			damageAttack(user, target);
			statusAttack(user, target, Status.BURNED, 10);
		}
	};
	final static Move thunderShock = new Move("Thunder Shock", Type.ELECTRIC, MoveCategory.SPECIAL, 40, 100, 30);
	final static Move waterGun = new Move("Water Gun", Type.WATER, MoveCategory.SPECIAL, 40, 100, 25);
	final static Move vineWhip = new Move("Vine Whip", Type.GRASS, MoveCategory.PHYSICAL, 45, 100, 25);
	final static Move flamethrower = new Move("Flamethrower", Type.FIRE, MoveCategory.SPECIAL, 90, 100, 15){
		public void useMove(Pokemon user, Pokemon target){
			damageAttack(user, target);
			statusAttack(user, target, Status.BURNED, 10);
		}
	};
	final static Move thunder = new Move("Thunder", Type.ELECTRIC, MoveCategory.SPECIAL, 110, 70, 10){
		public void useMove(Pokemon user, Pokemon target){
			damageAttack(user, target);
			statusAttack(user, target, Status.PARALYZED, 30);
			//if raining should always hit and if harsh sunlight, accuracy is 50
		}
	};
	final static Move seedBomb = new Move("Seed Bomb", Type.GRASS, MoveCategory.PHYSICAL, 80, 100, 15);
	final static Move hydroPump = new Move("Hydro Pump", Type.WATER, MoveCategory.SPECIAL, 110, 80, 5);
	final static Move thunderWave = new Move("Thunder Wave", Type.ELECTRIC, MoveCategory.STATUS, 0, 90, 20){
		public void useMove(Pokemon user, Pokemon target){
			statusAttack(user, target, Status.PARALYZED, 100);
		}
	};
	final static Move tailWhip = new Move("Tail Whip", Type.NORMAL, MoveCategory.STATUS, 0, 100, 30){
		public void useMove(Pokemon user, Pokemon target){
			statAttack(user, target, "deffense", -1);
		}
	};
	final static Move zapCannon = new Move("Zap Cannon", Type.ELECTRIC, MoveCategory.SPECIAL, 120, 50, 5){
		public void useMove(Pokemon user, Pokemon target){
			damageAttack(user, target);
			statusAttack(user, target, Status.PARALYZED, 100);
		}
	};
	final static Move gust = new Move("Gust", Type.FLYING, MoveCategory.SPECIAL, 40, 100, 35);
	final static Move iceBeam = new Move("IceBeam", Type.ICE, MoveCategory.SPECIAL, 90, 100, 10){
		public void useMove(Pokemon user, Pokemon target){
			damageAttack(user, target);
			statusAttack(user, target, Status.FROZEN, 10);
		}
	};
	final static Move blizzard = new Move("Blizzard", Type.ICE, MoveCategory.SPECIAL, 110, 70, 5){
		public void useMove(Pokemon user, Pokemon target){
			damageAttack(user, target);
			statusAttack(user, target, Status.FROZEN, 10);
			//if hail, should always hit
		}
	};
	final static Move roost = new Move("Roost", Type.FLYING, MoveCategory.STATUS, 0, 0, 10){
		public void useMove(Pokemon user, Pokemon target){
			healAttack(user, user.stats.totalHP/2);
		}
	};
	
	
	
	final static Move shoot = new Move("Shoot With Gun", Type.DARK, MoveCategory.PHYSICAL, 2000, 100, 3);
}
