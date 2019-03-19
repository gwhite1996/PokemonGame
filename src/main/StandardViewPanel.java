package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

class StandardViewPanel extends JPanel{

	// needs to contain a panel for each piece to be displayed
	private PlayerPanel playerPanel;
	private HikerPanel hikerPanel;
	private int gridWidth;
	private Dimension panelDimensions;

	StandardViewPanel(int frameWidth){
		panelDimensions = new Dimension(frameWidth, frameWidth);
		this.setMinimumSize(panelDimensions);
		this.setMaximumSize(panelDimensions);
		this.setPreferredSize(panelDimensions);
		gridWidth = frameWidth / 16;
		playerPanel = new PlayerPanel(gridWidth, Direction.SOUTH);
		hikerPanel = new HikerPanel(gridWidth, Direction.SOUTH);
		this.setBackground(new Color(75, 220, 95)); // shade of light green
		this.add(hikerPanel);
		this.add(playerPanel);
	}

	void update(Model m){
		hikerPanel.update(m.getHikerMike().getXLoc() * gridWidth, m.getHikerMike().getYLoc() * gridWidth,
				m.getHikerMike().getDirection());
		playerPanel.update(m.getPlayer().getXLoc() * gridWidth, m.getPlayer().getYLoc() * gridWidth,
				m.getPlayer().getDirection());
		this.repaint();
	}

	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g); // replaces what was painted before
		hikerPanel.paintComponent(g);
		playerPanel.paintComponent(g);
	}
}