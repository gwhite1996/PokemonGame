import java.util.ArrayList;
import java.util.Scanner;


public class Bag {
	private ArrayList<Item> itemList;
	Scanner in = new Scanner(System.in);
	
	
	public Bag(){
		itemList = new ArrayList<Item>();
	}
	
	public void add(Item item){
		itemList.add(item);
	}
	
	public void chooseItem(){
		printBagContents();
		System.out.println("Type the index of the item and then press enter.");
		
		Item item = null;
		while(item == null){
			try{
				item = itemList.get(in.nextInt()-1); //User input selects item
			}
			catch(IndexOutOfBoundsException e){
				System.out.println("Invalid index!");
			}
		}
		item.use();
	}
	
	public void removeEmptyItems(){
		for(Item item: itemList){
			if(item.count <= 0){
				itemList.remove(item);
			}
		}
	}
	
	
	
	public void printBagContents(){
		removeEmptyItems();
		System.out.println("..... Bag Contents .....");
		int i = 1;
		for(Item item: itemList){
			System.out.println("[" + i + "] " + item + " (" + item.count + ")");
			i++;
		}
	}
}

