import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public abstract class GamePiecePanel extends JPanel{

	protected int xDrawLoc;
	protected int yDrawLoc;
	protected BufferedImage img;
	protected double scaleFactor = 1;
	protected String imageName;

	public GamePiecePanel(int gridWidth){
		imageName = "temporary_image";
		img = createImage();
		this.scaleFactor = (double) gridWidth / img.getWidth();
	}

	public GamePiecePanel(int gridWidth, String imageName){
		this.imageName = imageName;
		img = createImage();
		this.scaleFactor = (double) gridWidth / img.getWidth();
	}

	void update(int x, int y){
		xDrawLoc = x;
		yDrawLoc = y;
	}

	// the image is scaled and drawn at the correct x and y
	protected void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform at = AffineTransform.getTranslateInstance(xDrawLoc, yDrawLoc);
		at.scale(scaleFactor, scaleFactor);
		g2d.drawImage(img, at, null);
	}

	BufferedImage createImage(){
		BufferedImage img = null;
		try{
			img = ImageIO.read(new File("images/" + imageName + ".png"));
		}
		catch(IOException e){
		}
		return img;
	}
}
