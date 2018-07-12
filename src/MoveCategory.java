
public enum MoveCategory {
	PHYSICAL("physical"),
	SPECIAL("special"),
	STATUS("status"),
	STAT("stat"),
	NONE("none");
	
	String name;
	
	private MoveCategory(String name){
		this.name= name;
	}
	
	@Override
	public String toString(){
		return name;
	}
}