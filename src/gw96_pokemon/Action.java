package gw96_pokemon;

class Action{

	private String name;
	private int priority;

	Action(String name, int priority){
		this.name = name;
		this.priority = priority;
	}

	String getName(){
		return name;
	}

	void setName(String name){
		this.name = name;
	}

	int getPriority(){
		return priority;
	}

	void setPriority(int priority){
		this.priority = priority;
	}

	@Override
	public String toString(){
		return getName();
	}
}
