import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class StandardViewPanel extends JPanel{

	private PlayerPanel playerPanel;
	private TrainerPanel trainerPanel1;
	private int gridWidth;
	
	public StandardViewPanel(int frameWidth) {
		this.setSize(frameWidth, frameWidth);
		gridWidth = frameWidth / 16;
		playerPanel = new PlayerPanel(gridWidth, Direction.SOUTH);
		trainerPanel1 = new TrainerPanel(gridWidth, Direction.SOUTH);
		this.setBackground(new Color(75, 220, 95));
		
		this.add(trainerPanel1);
		this.add(playerPanel);
		this.setVisible(true);
	}

	public void update(Model m){
		trainerPanel1.update(m.getHiker().getXLoc()*gridWidth, m.getHiker().getYLoc()*gridWidth, m.getHiker().getDirection());
		playerPanel.update(m.getPlayer().getXLoc()*gridWidth, m.getPlayer().getYLoc()*gridWidth, m.getPlayer().getDirection());
		this.repaint();
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); //replaces what was painted before
		trainerPanel1.paintComponent(g);
		playerPanel.paintComponent(g);
    }
}