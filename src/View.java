import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JFrame{

	private JPanel mainPanel;
	private TextPanel textPanel;
	private StandardViewPanel standardViewPanel;
	// private MainMenuPanel mainMenuPanel;
	// private BattleViewPanel battleViewPanel;

	public View(int frameWidth){
		super("Pokemon Game Alpha");
		this.setSize((int) (frameWidth * 1.15), (int) (frameWidth * 1.45));
		this.setLocationRelativeTo(null); // centers window
		// this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel(); // holds the current top panel and text panel
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		textPanel = new TextPanel(frameWidth);
		standardViewPanel = new StandardViewPanel(frameWidth);
		mainPanel.add(standardViewPanel);
		mainPanel.add(textPanel);
		int borderWidth = frameWidth / 64;
		// mainPanel.setBorder(BorderFactory.createEmptyBorder(borderWidth, borderWidth, borderWidth, borderWidth));
		this.add(mainPanel);
		this.setVisible(true); // necessary to see it
	}

	public void update(Model m){
		standardViewPanel.update(m);
		textPanel.update(m);
	}
}