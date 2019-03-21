package main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Timer;

class Controller {

	private final int frameWidth = 400; // frame is square
	private final static int DRAWDELAY = 150;
	private GameKeyListener keyListener;
	private Model model;
	private View View;
	private boolean start = false;

	Controller() {
		model = new Model();
		View = new View(frameWidth);
		keyListener = new GameKeyListener();
		View.addKeyListener(keyListener);
		start = true;
	}

	void update() {
		if(start) { // the game runs from start until gameOver is true
			if(model.getPlayer().getInteracting()) {
				System.out.println("BattleScreenNow!");
			}
			else {
				model.update(keyListener.getUserInput());
				View.update(model); // Bad practice to pass entire model. What should this be? Writing two new parameters into the constructor each
									// time a trainer is added doesn't seem reasonable.
				keyListener.setUserInput(null); // after each update the input is reset to null
			}
		}
	}

	// MAIN
	public static void main(String[] args) {
		final Controller c = new Controller();
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				Timer t = new Timer(DRAWDELAY, new AbstractAction() {

					@Override
					public void actionPerformed(ActionEvent e) {
						c.update();
					}
				});
				t.start();
			}
		});
	}
}