import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
public class Tube extends MazeElement
{
	private BufferedImage img;
	private char[][] dashArray;

	public Tube(Location loc, int size, char[][] dashArray)
	{
		super(loc,size);
		this.dashArray = dashArray;
		try {
			img = ImageIO.read(new File("/Users/vishal/Desktop/Programming/Data Structures Projects/MazeProject/tube.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Tube(Location loc, int size,String imgString)
	{
		super(loc, size);
		try {
			img = ImageIO.read(new File(imgString));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void dash(Explorer explore){while(this.dashArray[explore.getLoc().getR()][explore.getLoc().getC()+1]!='*'){explore.getLoc().incC(1);}}
	public BufferedImage getImg(){return img;}
}