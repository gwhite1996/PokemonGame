
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class GameKeyListener implements KeyListener{
	private KeyEvent userInput;
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		userInput = e;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
	public KeyEvent getUserInput(){
		return userInput;
	}
	public void setUserInput(KeyEvent e){
		userInput = e;
	}
}
