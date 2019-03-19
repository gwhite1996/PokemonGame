package main;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

class View extends JFrame{

	private JPanel mainPanel;
	private TextPanel textPanel;
	private StandardViewPanel standardViewPanel;
	// private MainMenuPanel mainMenuPanel;
	// private BattleViewPanel battleViewPanel;

	View(int frameWidth){
		super("Pokemon Game Alpha");
		this.setSize((int)(frameWidth * 1.15), (int)(frameWidth * 1.45));
		this.setLocationRelativeTo(null); // centers window
		// this.setUndecorated(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainPanel = new JPanel(); // holds the current top panel and text panel
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		textPanel = new TextPanel(frameWidth);
		standardViewPanel = new StandardViewPanel(frameWidth);
		mainPanel.add(standardViewPanel);
		mainPanel.add(textPanel);
		this.add(mainPanel);
		this.setVisible(true); // necessary to see it
	}

	void update(Model m){
		standardViewPanel.update(m);
		textPanel.update(m);
	}
}