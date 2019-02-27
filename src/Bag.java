import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
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
	
	public boolean chooseItem(){
		removeEmptyItems();
		if(itemList.size() < 1){
			System.out.println("The bag is empty!");
			return false;
		}
		
		printBagContents();
		System.out.println(" Type the index of the item and then press enter.");
		System.out.println(" Type 0 to exit");
		
		Item item = null;
		while(item == null){
			try{
				int index = in.nextInt();
				if(index == 0) {
					return false;
				}
				item = itemList.get(index-1); //User input selects item
			}
			catch(IndexOutOfBoundsException e){
				System.out.println("Invalid index!");
			}
			catch(InputMismatchException e){
				System.out.println("Input must be an integer.");
				in.next();
			}
		}
		
		item.use();
		return true;
	}
	
	
	public void removeEmptyItems(){
		for(Iterator<Item> itemIterator = itemList.iterator(); itemIterator.hasNext(); ){ //iterator needed to remove items
		    if(itemIterator.next().count <= 0){
		    	itemIterator.remove();
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
		System.out.println("........................");
	}
}

