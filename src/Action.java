
public class Action {
	private String name;
	private int priority;
	
	public Action(String name, int priority){
		this.name = name;
		this.priority = priority;
	}
	public Action(String name){
		this.name = name;
		this.priority = 0;
	}
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public int getPriority(){
		return priority;
	}
	public void setPriority(int priority){
		this.priority = priority;
	}
	
	@Override
	public String toString(){
		return getName();
	}
	
}
