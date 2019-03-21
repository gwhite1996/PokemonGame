package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

abstract class GamePiecePanel extends JPanel {

	int xDrawLoc;
	int yDrawLoc;
	BufferedImage img;
	double scaleFactor = 1;
	String imageName;

	GamePiecePanel(int gridWidth) {
		imageName = "temporary_image";
		img = createImage();
		this.scaleFactor = (double) gridWidth / img.getWidth();
	}

	GamePiecePanel(int gridWidth, String imageName) {
		this.imageName = imageName;
		img = createImage();
		this.scaleFactor = (double) gridWidth / img.getWidth();
	}

	void update(int x, int y) {
		xDrawLoc = x;
		yDrawLoc = y;
	}

	// the image is scaled and drawn at the correct x and y
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform at = AffineTransform.getTranslateInstance(xDrawLoc, yDrawLoc);
		at.scale(scaleFactor, scaleFactor);
		g2d.drawImage(img, at, null);
	}

	BufferedImage createImage() {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("images/" + imageName + ".png")); // TEMP. should not be relative to where java.exe is called
		}
		catch(IOException e) {
			System.out.println("Could not locate file within createImage()");
		}
		return img;
	}
}
