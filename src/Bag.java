import java.util.ArrayList;
import java.util.Iterator;

public class Bag{

	private ArrayList<Item> itemList;

	public Bag(){
		itemList = new ArrayList<Item>();
	}

	public Item chooseItem(){
		removeEmptyItems();
		if(itemList.size() < 1){
			System.out.println("The bag is empty!");
			return null;
		}
		LostMethods.printReturnOption();
		printBagContents();
		Item item = null;
		while(item == null){
			int index = LostMethods.chooseOption(0, itemList.size());
			if(index == 0){
				return null;
			}
			item = itemList.get(index - 1); // User input selects item
		}
		return item;
	}

	public void add(Item item){
		ItemType type = item.getItemType();
		for(Item i : itemList){ // checks if the bag already contains the ItemType
			if(i.getItemType() == type){
				i.count += item.count;
				return;
			}
		}
		itemList.add(item);
	}

	public void removeEmptyItems(){
		for(Iterator<Item> itemIterator = itemList.iterator(); itemIterator.hasNext();){ // iterator needed to remove items
			if(itemIterator.next().count <= 0){
				itemIterator.remove();
			}
		}
	}

	public void printBagContents(){
		removeEmptyItems();
		System.out.println("..... Bag Contents .....");
		int i = 1;
		for(Item item : itemList){
			System.out.println("[" + i + "] " + item + " (" + item.count + ")");
			i++;
		}
		System.out.println("........................");
	}
}
