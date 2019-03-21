package main;

import java.util.Random;

class Battle {

	Player player;
	Random rand = new Random();

	void selectAction(Trainer trainer) {
		Pokemon user = trainer.getCurrentPokemon();
		boolean actionHasBeenSelected = false;
		do {
			System.out.println(
					"What will " + trainer + " do?\n[1] Fight (" + user + ")\n[2] Bag\n[3] Swap Pokemon\n[4] Run");
			switch(LostMethods.chooseOption(1, 4)) {
			case 1:
				actionHasBeenSelected = trainer.chooseMove();
				break;
			case 2:
				actionHasBeenSelected = trainer.willUseItem();
				break;
			case 3:
				actionHasBeenSelected = trainer.pickPokemonOnDeck(false);
				break;
			case 4:
				actionHasBeenSelected = trainer.willFlee();
				break;
			}
		}
		while(!actionHasBeenSelected);
	}

	void useMove(Pokemon user, Pokemon target, Move move) {
		double chanceOfHit = ((move.getMoveName().getAccuracy()) / 100d)
				* (user.stats.accuracy.getBattleValue() / target.stats.evasion.getBattleValue());
		if(chanceOfHit > rand.nextDouble() || move.getMoveName().getAccuracy() == 0) { // attack hits no matter what if accuracy is 0
			move.useMove(user, target);
		}
		else {
			System.out.println(user + " used " + move + " but it missed!");
		}
		move.setPPLeft(move.getPPLeft() - 1);
	}

	boolean playerActsFirst(Pokemon enemyPokemon, Action enemyAction) {
		if(player.getAction().getPriority() == enemyAction.getPriority()) { // checks pokemon speed only if priorities are same
			return player.getCurrentPokemon().stats.speed.getBattleValue() >= enemyPokemon.stats.speed.getBattleValue();
		}
		else {
			return player.getAction().getPriority() > enemyAction.getPriority();
		}
	}

	// called each halfTurn to check if either current pokemon has fainted
	boolean isFainted(Pokemon pokemon) {
		if(pokemon.stats.hpRemaining <= 0) {
			pokemon.stats.hpRemaining = 0;
			pokemon.setStatus(Status.FAINTED);
			System.out.println(pokemon + " has fainted!");
			return true;
		}
		else {
			return false;
		}
	}

	boolean PPLeft(Pokemon user, int moveNumber) {
		int ppLeft = 0;
		switch(moveNumber) {
		case 1:
			ppLeft = user.move1.getPPLeft();
			break;
		case 2:
			ppLeft = user.move2.getPPLeft();
			break;
		case 3:
			ppLeft = user.move3.getPPLeft();
			break;
		case 4:
			ppLeft = user.move4.getPPLeft();
			break;
		default:
			System.out.println("Problem in PPLeft()");
		}
		if(ppLeft > 0) {
			return true;
		}
		System.out.println("No pp left!");
		return false;
	}

	Move getRandomMove(Pokemon pokemon) {
		Move randomMove = null;
		do {
			switch(rand.nextInt(4) + 1) {
			case 1:
				randomMove = pokemon.move1;
				break;
			case 2:
				randomMove = pokemon.move2;
				break;
			case 3:
				randomMove = pokemon.move3;
				break;
			case 4:
				randomMove = pokemon.move4;
				break;
			}
		}
		while(randomMove == null);
		return randomMove;
	}

	void resetAllStatStages(Party party) { // resets stages to 0 for both parties before and after battle
		for(int i = 1; i <= party.getPartyCount(); i++) {
			Pokemon p = party.getPokemon(i);
			if(p != null) {
				p.stats.resetAllStages();
			}
		}
	}
}
