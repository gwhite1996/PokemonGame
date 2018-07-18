
public class Item {
	private String name;
	int count;

	public Item(String name){
		this.name = name;
		count = 1;
	}
	
	
	
	public boolean use(){
		if(count > 0){
			System.out.println(name + " used.");
			if(count == 1){
				System.out.println("That was the last one!");
			}
			count --;
			return true;
		}
		else{
			System.out.println("No " + name + " left in bag.");
			return false;
		}
	}
	
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	@Override
	public String toString(){
		return getName();
	}
}