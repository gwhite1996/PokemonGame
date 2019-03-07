
public class Item {
	private String name;
	private ItemType type;
	int count;

	public Item(String name, ItemType type){
		this.name = name;
		this.type = type;
		count = 1;
	}
	
	
	
	public void use(Pokemon targetPokemon){
		if(targetPokemon != null) {
			System.out.println(this + " was used on " + targetPokemon);
			itemEffect(targetPokemon);
		}
		else {
			System.out.println(this + " was used");
			itemEffect();
		}
		
		if(count == 1){
			System.out.println("That was the last one!");
		}
			count --;
	}
	
	public void itemEffect(){ //items not used on a specific pokemon
		System.out.println(this + " doesn't do anything! You must define it's itemEffect() method");
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
	public ItemType getItemType(){
		return type;
	}
	
	@Override
	public String toString(){
		return getName();
	}
}