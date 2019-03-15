import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class TurnablePiecePanel extends GamePiecePanel{

	protected Direction dir;
	private BufferedImage imgNorth;
	private BufferedImage imgSouth;
	private BufferedImage imgEast;
	private BufferedImage imgWest;

	public TurnablePiecePanel(int gridWidth, String imageName, Direction dir){
		super(gridWidth);
		this.imageName = imageName;
		this.dir = dir;
		createDirectionImages();
		this.scaleFactor = (double)gridWidth / imgNorth.getWidth(); // scale factor changed from that of default temporary_image
	}

	void createDirectionImages(){
		try{
			imgNorth = ImageIO.read(new File("images/turnable/" + imageName + "/north.png"));
			imgSouth = ImageIO.read(new File("images/turnable/" + imageName + "/south.png"));
			imgEast = ImageIO.read(new File("images/turnable/" + imageName + "/east.png"));
			imgWest = ImageIO.read(new File("images/turnable/" + imageName + "/west.png"));
		}
		catch(IOException e){
		}
	}

	void update(int x, int y, Direction dir){
		super.update(x, y);
		this.dir = dir;
		switch(dir){
		case EAST:
			img = imgEast;
			break;
		case NORTH:
			img = imgNorth;
			break;
		case SOUTH:
			img = imgSouth;
			break;
		case WEST:
			img = imgWest;
			break;
		default:
			System.out.println("Invalid direction within TurnablePanel");
			break;
		}
	}
}
