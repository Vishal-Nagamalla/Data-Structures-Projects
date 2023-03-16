import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
public class Explorer extends MazeElement
{
	private int steps, dir, ringCount;
	String help = "/Users/vishal/Desktop/Programming/Data Structures Projects/MazeProject/";
	private String [] image_name = {help+"mario_up.png",help+"mario_right.png",help+"mario_down.png",help+"mario_left.png"};
	private BufferedImage [] imgs = new BufferedImage[4];

	public Explorer(Location loc, int size)
	{
		super(loc, size);
		steps = 0;
		dir = 1;
		ringCount = 0;
		for(int i = 0; i<imgs.length; i++)
		{
			try {
				imgs[i]=ImageIO.read(new File(image_name[i]));
			}
			catch (IOException io)
			{
				System.err.println("Error reading file => "+io);
			}
		}
	
	}
	
	public int getRingCount(){return ringCount;}
	public void incRing(){ringCount++;}
	public void setRing(int amount){ringCount = amount;}
	public BufferedImage getImg(){return imgs[dir];}
	public int getSteps(){return steps;}


	public void move(int key, char[][] maze) {
		if(key == 37)
		{
			dir--;
			if(dir<0)
				dir=3;
		}
		if(key == 38)
		{
			Location currentLoc = getLoc();
			if(dir==0)
				if(currentLoc.getR()-1 > 0 && maze[currentLoc.getR()-1][currentLoc.getC()] == ' ')
				{
					getLoc().incR(-1);
					steps++;
				}
			if(dir==1)
				if(currentLoc.getC()+1 > 0 && maze[currentLoc.getR()][currentLoc.getC()+1] == ' ')
				{
					getLoc().incC(1);
					steps++;
				}
			if(dir==2)
				if(currentLoc.getR()+1 > 0 && maze[currentLoc.getR()+1][currentLoc.getC()] == ' ')
				{
					getLoc().incR(1);
					steps++;
				}
			if(dir==3)
				if(currentLoc.getC()-1 > 0 && maze[currentLoc.getR()][currentLoc.getC()-1] == ' ')
				{
					getLoc().incC(-1);
					steps++;
				}
		}
		if(key==39)
		{
			dir++;
			if(dir>3)
				dir = 0;
		}
	} 
}