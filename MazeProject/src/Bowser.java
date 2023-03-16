import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
public class Bowser extends MazeElement
{
	private BufferedImage img;

	public Bowser(Location loc, int size)
	{
		super(loc, size);
		try{
			img = ImageIO.read(new File("/Users/vishal/Desktop/Programming/Data Structures Projects/MazeProject/bowser.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Bowser(Location loc, int size,String imgString)
	{
		super(loc, size);
		try {
			img = ImageIO.read(new File(imgString));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void takecoins(Explorer explore)
	{
			explore.setRing(0);
	}
	
	public void moving(int key, char[][] dashArray)
	{
		int r = getLoc().getR();
		int c = getLoc().getC();
		
		boolean moved = false;
		
		while(!moved)
		{
			int dir = (int)(Math.random()*4);
			if(dir==0)
			{
				if(r>0 && dashArray[r-1][c]==' ')
				{
					getLoc().incR(-1);
					moved=true;
				}
			}
			
			if(dir==1)
			{
				if(c<dashArray[0].length-1 && dashArray[r][c+1]==' ')
				{
					getLoc().incC(+1);
					moved=true;
				}
			}
			
			if(dir==2)
			{
				if(r<dashArray.length-1 && dashArray[r+1][c]==' ')
				{
					getLoc().incR(+1);
					moved=true;
				}
			}
			
			if(dir==3)
			{
				if(c>0 && dashArray[r][c-1]==' ')
				{
					getLoc().incC(-1);
					moved=true;
				}
			}
		}
	}
    
	public BufferedImage getImg(){return img;}
}
