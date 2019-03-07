
public class Item {
	private String name;
	int count;

	public Item(String name){
		this.name = name;
		count = 1;
	}
	
	
	
	public void use(Pokemon targetPokemon){
		
		System.out.println(this + " was used on " + targetPokemon);
		itemEffect(targetPokemon);
		if(count == 1){
			System.out.println("That was the last one!");
		}
			count --;
	}
	
	public void itemEffect(Pokemon targetPokemon){
		System.out.println(this + " doesn't do anything! You must define it's itemEffect() method");
	}
	
	void healItem(Pokemon target, int hpRestored){
		if(target.stats.hpRemaining + hpRestored > target.stats.totalHP){
			hpRestored = target.stats.totalHP - target.stats.hpRemaining;
		}
		target.stats.hpRemaining += hpRestored;
		System.out.println(target + " was healed for for " + hpRestored + " HP.");
	}
	
	
	public String getName(){
		return name;
	}
	
	@Override
	public String toString(){
		return getName();
	}
}