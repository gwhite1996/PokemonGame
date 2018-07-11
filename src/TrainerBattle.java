import java.util.Random;


public class TrainerBattle {
	private Player player;
	private Trainer enemy;
	
	private Battle battle;

	
	public TrainerBattle(Player player, Trainer enemy){
		this.player = player;
		this.enemy = enemy;
		resetAllStatStages();
		
		//Battle happens here!
		
		
		resetAllStatStages();
	}
	
	public void resetAllStatStages(){
		for(Pokemon p: player.party.partyArray){
			p.stats.stages.resetAll();
		}
		for(Pokemon p: enemy.party.partyArray){
			p.stats.stages.resetAll();
		}
	}
	
}
