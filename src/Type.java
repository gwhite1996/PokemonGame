public enum Type{
	
	NORMAL("normal", new String[]{}, new String[]{"ROCK, STEEL"}, new String[]{"GHOST"}),
	FIRE("fire", new String[]{"GRASS", "ICE", "BUG", "STEEL"}, new String[]{"FIRE", "WATER", "ROCK", "DRAGON"}, new String[]{}), //an empty array as opposed to null avoids null pointer exceptions
	FIGHTING("fighting", new String[]{"NORMAL", "ICE", "ROCK", "STEEL"}, new String[]{"POISON", "FLYING", "PSYCHIC", "BUG", "FAIRY"}, new String[]{"GHOST"}),
	WATER("water", new String[]{"FIRE", "GROUND", "ROCK"}, new String[]{"WATER", "GRASS", "DRAGON"}, new String[]{}),
	FLYING("flying", new String[]{"GRASS", "FIGHTING", "BUG"}, new String[]{"ELECTRIC", "ROCK", "STEEL"}, new String[]{}),
	GRASS("grass", new String[]{"WATER", "GROUND", "ROCK"}, new String[]{"FIRE", "GRASS", "POISON", "FLYING", "BUG", "DRAGON", "STEEL"}, new String[]{}),
	POISON("poison", new String[]{"GRASS", "FAIRY"}, new String[]{"POISON", "GROUND", "ROCK", "GHOST"}, new String[]{"STEEL"}),
	ELECTRIC("electric", new String[]{"WATER", "FLYING"}, new String[]{"ELECTRIC", "GRASS", "DRAGON"}, new String[]{"GROUND"}),
	GROUND("ground", new String[]{"FIRE", "ELECTRIC", "POISON", "ROCK", "STEEL"}, new String[]{"GRASS", "BUG"}, new String[]{"FLYING"}),
	PSYCHIC("psychic", new String[]{"FIGHTING", "POISON"}, new String[]{"PSYCHIC", "STEEL"}, new String[]{"DARK"}),
	ROCK("rock", new String[]{"FIRE", "ICE", "FLYING", "BUG"}, new String[]{"FIGHTING", "GROUND", "STEEL"}, new String[]{}),
	ICE("ice", new String[]{"GRASS", "GROUND", "FLYING", "DRAGON"}, new String[]{"FIRE", "WATER", "ICE", "STEEL"}, new String[]{}),
	BUG("bug", new String[]{"GRASS", "PSYCHIC", "DARK"}, new String[]{"FIRE", "FIGHTING", "POISON", "FLYING", "GHOST", "STEEL", "FAIRY"}, new String[]{}),
	DRAGON("dragon", new String[]{"DRAGON"}, new String[]{"STEEL"}, new String[]{"FAIRY"}),
	GHOST("ghost", new String[]{"PSYCHIC", "GHOST"}, new String[]{"DARK"}, new String[]{"NORMAL"}),
	DARK("dark", new String[]{"PSYCHIC", "GHOST"}, new String[]{"FIGHTING", "DARK", "FAIRY"}, new String[]{}),
	STEEL("steel", new String[]{"ICE", "ROCK", "FAIRY"}, new String[]{"FIRE", "WATER", "ELECTRIC", "STEEL"}, new String[]{}),
	FAIRY("fairy", new String[]{"FIGHTING", "DRAGON", "DARK"}, new String[]{"FIRE", "POISON", "STEEL"}, new String[]{}),
	NONE("none", new String[]{}, new String[]{}, new String[]{}); //prevents null pointer exceptions for pokemon with just one type. Also is the type of 'Struggle'
	
	private String name = null;
	private String[] superEffective;
	private String[] notVeryEffective;
	private String[] noEffect;
	
	private Type(String name, String[] superEffective, String[] notVeryEffective, String[] noEffect){
		this.name = name;
		this.superEffective = superEffective;
		this.notVeryEffective = notVeryEffective;
		this.noEffect = noEffect;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public void printTypeInfo(){ //prints out type effectiveness info
		System.out.println(" Type: " + name());
		
		System.out.print("Super effective against: ");
		for(String s: superEffective){
			System.out.print(s + ", ");
		}
		System.out.println();
		System.out.print("Not very effective against: ");
		for(String s: notVeryEffective){
			System.out.print(s + ", ");
		}
		if(noEffect.length > 0){ //not displayed if there are none
			System.out.println();
			System.out.print("No effect against: ");
			for(String s: noEffect){
				System.out.print(s + ", ");
			}
		}
	}
	
	public static void printAllTypeInfo(){
		System.out.println("\n**************** TYPE INFO ****************");
		for(Type t: Type.values()){ //prints for each type in enum
			t.printTypeInfo();
			System.out.println("\n--------------------------");
		}
		System.out.println("*******************************************");
	}
	
	public static double typeEffectiveness(Type moveType, Species targetSpecies){
		double multiplier = 1;
		
		for(String s: moveType.noEffect){
			if(s == targetSpecies.type1.name() || s == targetSpecies.type2.name()){
				return 0;
			}
		}
		for(String s: moveType.superEffective){
			if(s == targetSpecies.type1.name() || s == targetSpecies.type2.name()){
				multiplier *= 2;
			}
		}
		for(String s: moveType.notVeryEffective){
			if(s == targetSpecies.type1.name() || s == targetSpecies.type2.name()){
				multiplier *= 0.5;
			}
		}
		return multiplier;
	}
	
	public static double stab(Type moveType, Species userSpecies){ //Same Type Attack Bonus
		if(moveType == userSpecies.type1 || moveType == userSpecies.type2){
			return 1.5;
		}
		else return 1;
	}
}
