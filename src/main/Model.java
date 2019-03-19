package main;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

class Model{

	private final int width;
	private final int height;
	private Player player;
	private Hiker hikerMike;
	private String message;
	private ArrayList<InteractablePiece> interactableList;

	Model(){
		this.width = 16;
		this.height = 16;
		interactableList = new ArrayList<InteractablePiece>();
		player = new Player("Player", 8, 8);
		hikerMike = new Hiker("Hiker Mike", 3, 2);
		interactableList.add(hikerMike);
	}

	void update(KeyEvent userInput){
		player.setTryingToInteract(false);
		if(userInput != null){
			player.update(userInput);
		}
		if(player.getTryingToInteract()){
			for(InteractablePiece interactablePiece : interactableList){
				if(player.getXAhead() == interactablePiece.getXLoc()
						&& player.getYAhead() == interactablePiece.getYLoc()){
					interactablePiece.onInteraction(player);
					interactablePiece.setInteracting(true);
					player.setInteracting(true); // this pauses (stops the game from updating)
					message = interactablePiece.getMessage();
					return; // player can't interact with more than one GamePiece at a time)
				}
			}
		}
		hikerMike.update();
	}

	int getWidth(){
		return width;
	}

	int getHeight(){
		return height;
	}

	String getMessage(){
		return message;
	}

	Player getPlayer(){
		return player;
	}

	Hiker getHikerMike(){
		return hikerMike;
	}
}
