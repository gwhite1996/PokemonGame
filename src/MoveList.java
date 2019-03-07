
public class MoveList {
	
	
	final static MoveName noneMoveName = new MoveName("None", Type.NONE, MoveCategory.NONE, 0, 0, 0){
		public void useMoveName(Pokemon user, Pokemon target){
			System.out.println("MoveName \"None\" used. You shouldn't be able to do this!");
		}
	};
	final static MoveName struggleMoveName = new MoveName("Struggle", Type.NONE, MoveCategory.NONE, 50, 100, 1000) {
		public void useMoveName(Pokemon user, Pokemon target){
			int damageDealt = damageAttack(user, target);
			takeRecoil(user, damageDealt/2);
		}
	};
	final static Move none = new Move(noneMoveName);
	final static Move struggle = new Move(struggleMoveName); //each pokemon uses the same MOVE for 'struggle' and 'none'
	
	
	
	
	final static MoveName tackle = new MoveName("Tackle", Type.NORMAL, MoveCategory.PHYSICAL, 40, 100, 35){
		public void useMoveName(Pokemon user, Pokemon target){
			damageAttack(user, target);
		}
	};
	final static MoveName scratch = new MoveName("Scratch", Type.NORMAL, MoveCategory.PHYSICAL, 40, 100, 35){
		public void useMoveName(Pokemon user, Pokemon target){
			damageAttack(user, target);
		}
	};
	final static MoveName growl = new MoveName("Growl", Type.NORMAL, MoveCategory.STATUS, 0, 100, 40){
		public void useMoveName(Pokemon user, Pokemon target){
			statAttack(user, target, "attack", -1);
		}
	};
	final static MoveName ember = new MoveName("Ember", Type.FIRE, MoveCategory.SPECIAL, 40, 100, 25){
		public void useMoveName(Pokemon user, Pokemon target){
			damageAttack(user, target);
			statusAttack(user, target, Status.BURNED, 10);
		}
	};
	final static MoveName thunderShock = new MoveName("Thunder Shock", Type.ELECTRIC, MoveCategory.SPECIAL, 40, 100, 30){
		public void useMoveName(Pokemon user, Pokemon target){
			damageAttack(user, target);
		}
	};
	final static MoveName waterGun = new MoveName("Water Gun", Type.WATER, MoveCategory.SPECIAL, 40, 100, 25){
		public void useMoveName(Pokemon user, Pokemon target){
			damageAttack(user, target);
		}
	};
	final static MoveName vineWhip = new MoveName("Vine Whip", Type.GRASS, MoveCategory.PHYSICAL, 45, 100, 25){
		public void useMoveName(Pokemon user, Pokemon target){
			damageAttack(user, target);
		}
	};
	final static MoveName flamethrower = new MoveName("Flamethrower", Type.FIRE, MoveCategory.SPECIAL, 90, 100, 15){
		public void useMoveName(Pokemon user, Pokemon target){
			damageAttack(user, target);
			statusAttack(user, target, Status.BURNED, 10);
		}
	};
	final static MoveName thunder = new MoveName("Thunder", Type.ELECTRIC, MoveCategory.SPECIAL, 110, 70, 10){
		public void useMoveName(Pokemon user, Pokemon target){
			damageAttack(user, target);
			statusAttack(user, target, Status.PARALYZED, 30);
			//if raining should always hit and if harsh sunlight, accuracy is 50
		}
	};
	final static MoveName seedBomb = new MoveName("Seed Bomb", Type.GRASS, MoveCategory.PHYSICAL, 80, 100, 15){
		public void useMoveName(Pokemon user, Pokemon target){
			damageAttack(user, target);
		}
	};
	final static MoveName hydroPump = new MoveName("Hydro Pump", Type.WATER, MoveCategory.SPECIAL, 110, 80, 5){
		public void useMoveName(Pokemon user, Pokemon target){
			damageAttack(user, target);
		}
	};
	final static MoveName thunderWave = new MoveName("Thunder Wave", Type.ELECTRIC, MoveCategory.STATUS, 0, 90, 20){
		public void useMoveName(Pokemon user, Pokemon target){
			statusAttack(user, target, Status.PARALYZED, 100);
		}
	};
	final static MoveName tailWhip = new MoveName("Tail Whip", Type.NORMAL, MoveCategory.STATUS, 0, 100, 30){
		public void useMoveName(Pokemon user, Pokemon target){
			statAttack(user, target, "deffense", -1);
		}
	};
	final static MoveName zapCannon = new MoveName("Zap Cannon", Type.ELECTRIC, MoveCategory.SPECIAL, 120, 50, 5){
		public void useMoveName(Pokemon user, Pokemon target){
			damageAttack(user, target);
			statusAttack(user, target, Status.PARALYZED, 100);
		}
	};
	final static MoveName gust = new MoveName("Gust", Type.FLYING, MoveCategory.SPECIAL, 40, 100, 35){
		public void useMoveName(Pokemon user, Pokemon target){
			damageAttack(user, target);
		}
	};
	final static MoveName iceBeam = new MoveName("IceBeam", Type.ICE, MoveCategory.SPECIAL, 90, 100, 10){
		public void useMoveName(Pokemon user, Pokemon target){
			damageAttack(user, target);
			statusAttack(user, target, Status.FROZEN, 10);
		}
	};
	final static MoveName blizzard = new MoveName("Blizzard", Type.ICE, MoveCategory.SPECIAL, 110, 70, 5){
		public void useMoveName(Pokemon user, Pokemon target){
			damageAttack(user, target);
			statusAttack(user, target, Status.FROZEN, 10);
			//if hail, should always hit
		}
	};
	final static MoveName roost = new MoveName("Roost", Type.FLYING, MoveCategory.STATUS, 0, 0, 10){
		public void useMoveName(Pokemon user, Pokemon target){
			healAttack(user, user.stats.totalHP/2);
		}
	};
	final static MoveName stringShot = new MoveName("String Shot", Type.BUG, MoveCategory.STATUS, 0, 95, 40){
		public void useMoveName(Pokemon user, Pokemon target){
			statAttack(user, target, "speed", -2);
		}
	};
	final static MoveName bugBite = new MoveName("Bug Bite", Type.BUG, MoveCategory.PHYSICAL, 60, 100, 20){
		public void useMoveName(Pokemon user, Pokemon target){
			damageAttack(user, target);
		}
	};
	
	
	final static MoveName shoot1 = new MoveName("Shoot With Gun", Type.DARK, MoveCategory.PHYSICAL, 5000, 100, 3){
		public void useMoveName(Pokemon user, Pokemon target){
			damageAttack(user, target);
		}
	};
	final static MoveName shoot2 = new MoveName("Shoot Self", Type.DARK, MoveCategory.PHYSICAL, 5000, 100, 3){
		public void useMoveName(Pokemon user, Pokemon target){
			damageAttack(user, user);
		}
	};
}
