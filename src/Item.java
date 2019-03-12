public class Item{ // a specific instance of an ItemType. Bags contain these

	private ItemType itemType;
	int count;

	public Item(ItemType itemType){
		this.itemType = itemType;
		count = 1;
	}

	public Item(ItemType itemType, int count){
		this.itemType = itemType;
		this.count = count;
	}

	public void use(Pokemon targetPokemon){
		if(targetPokemon != null){
			System.out.println(this + " was used on " + targetPokemon);
			itemType.itemEffect(targetPokemon);
		}
		else{
			System.out.println(this + " was used");
			itemType.itemEffect();
		}
		if(count == 1){
			System.out.println("That was the last one!");
		}
		count--;
	}

	public ItemType getItemType(){
		return itemType;
	}

	public String toString(){
		return itemType.getName();
	}
}
