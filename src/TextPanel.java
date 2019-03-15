import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TextPanel extends JPanel{

	private Dimension panelDimensions;
	private JLabel message;

	public TextPanel(int frameWidth){
		panelDimensions = new Dimension(frameWidth, (int)(frameWidth / 4.0)); // Makes frame a little bigger than frameWidth for window edges
		this.setMinimumSize(panelDimensions);
		this.setMaximumSize(panelDimensions);
		this.setPreferredSize(panelDimensions);
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(220, 220, 220));
		// this.setVisible(false); //message invisible until interaction
		message = new JLabel("Will be blank", SwingConstants.CENTER);
		message.setPreferredSize(new Dimension(50, 50));
		this.add(message);
	}

	public void update(Model m){
		message.setText(m.getMessage());
		this.repaint();
	}

	protected void paintComponent(Graphics g){
		super.paintComponent(g); // replaces what was painted before
	}
}
