import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
public class MazeElement
{
	private Location loc;
	private int size;
	private BufferedImage img;

	public MazeElement(Location loc, int size)
	{
		this.loc=loc;
		this.size=size;

		try {
			img = ImageIO.read(new File("/Users/vishal/Desktop/Programming/Data Structures Projects/MazeProject/mario_right.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public MazeElement(Location loc, int size,String imgString)
	{
		this.loc=loc;
		this.size=size;
		try {
			img = ImageIO.read(new File(imgString));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BufferedImage getImg(){return img;}
	public Location getLoc(){return loc;}
	public int getSize(){return size;}
	public boolean intersects(MazeElement other){return this.loc.equals(other.loc);}
	public void move(int key, char[][] maze){}
}