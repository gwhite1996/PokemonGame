import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;


public class StandardViewPanel extends JPanel{
	
	//needs to contain a panel for each piece to be displayed
	private PlayerPanel playerPanel;
	private HikerPanel hikerPanel;
	private int gridWidth;
	
	private Dimension panelDimensions;
	
	
	public StandardViewPanel(int frameWidth) {
		panelDimensions = new Dimension(frameWidth, frameWidth);
		this.setMinimumSize(panelDimensions);
		this.setMaximumSize(panelDimensions);
		this.setPreferredSize(panelDimensions);
		
		gridWidth = frameWidth / 16;
		playerPanel = new PlayerPanel(gridWidth, Direction.SOUTH);
		hikerPanel = new HikerPanel(gridWidth, Direction.SOUTH);
		this.setBackground(new Color(75, 220, 95)); //shade of light gree
		
		this.add(hikerPanel);
		this.add(playerPanel);
	}

	public void update(Model m){
		hikerPanel.update(m.getHiker().getXLoc()*gridWidth, m.getHiker().getYLoc()*gridWidth, m.getHiker().getDirection());
		playerPanel.update(m.getPlayer().getXLoc()*gridWidth, m.getPlayer().getYLoc()*gridWidth, m.getPlayer().getDirection());
		this.repaint();
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); //replaces what was painted before
		hikerPanel.paintComponent(g);
		playerPanel.paintComponent(g);
    }
}