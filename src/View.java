import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class View extends JFrame{
	private StandardViewPanel standardViewPanel;
	//private MainMenuPanel mainMenuPanel;
	//private BattleViewPanel battleViewPanel;
	
	JLabel message = new JLabel("o shit waddup!");
	
	View(int width, int height){
		
	}
	
	public View(int frameWidth) {
		this.setSize(frameWidth, frameWidth);
		//this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		standardViewPanel = new StandardViewPanel(frameWidth);
		this.add(standardViewPanel);
		this.setVisible(true);
		
		message.setVisible(false);
		standardViewPanel.add(message, BorderLayout.CENTER);
	}

	public void update(Model m){
		standardViewPanel.update(m);
		if(m.showMessage){
			message.setVisible(true);
		}
	}
}