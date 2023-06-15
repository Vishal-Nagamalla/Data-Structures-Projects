import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class SierpinskiStarter extends JPanel implements KeyListener
{
	JFrame frame;
	ArrayList<Point> points;  // Need to wite Point as an internal class
	int currX,currY;
	public SierpinskiStarter()
	{
		frame=new JFrame("SierpinskiStarter Triangle Simulator");
		frame.add(this);
		frame.setSize(1200,700);
		points=sierpinskiPointGenerator();  // generate framework for points
		frame.addKeyListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		// Paint Background  -> g.fillRect(x,y,size,size)
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());

		// Paint all points in ArrayList -> g.fillOval(x,y,size,size);
		g.setColor(Color.BLACK);
		for (Point p : points) {
			g.fillOval(p.x, p.y, 5, 5);
		}
	}
	public ArrayList<Point> sierpinskiPointGenerator()
	{

		ArrayList<Point> points = new ArrayList<Point>();
		// Set initial points as corner of Triangle
		Point p1 = new Point(600, 100);
		Point p2 = new Point(100, 600);
		Point p3 = new Point(1100, 600);

		// Create a polygon with the 3 points of Triangle
		Polygon triangle = new Polygon();
		triangle.addPoint(p1.x, p1.y);
		triangle.addPoint(p2.x, p2.y);
		triangle.addPoint(p3.x, p3.y);

		// Find and add a random point within the Triangle polygon
		Random rand = new Random();
		Point randPoint = new Point(triangle.getBounds().x + rand.nextInt(triangle.getBounds().width),
				triangle.getBounds().y + rand.nextInt(triangle.getBounds().height));
		points.add(randPoint);
		return points;
	}

	public void addPoints(int num)
	{
		// for each of num points
		for (int i = 0; i < num; i++) {
			// ---> Pick a random corner point (first 3 in ArrayList)
			Point randomCorner = points.get(rand.nextInt(3));
			// ---> Add a point halfway between current and corner
			currX = (randomCorner.x + currX) / 2;
			currY = (randomCorner.y + currY) / 2;
			points.add(new Point(currX, currY));
		}
	}

	public void keyPressed(KeyEvent e)
	{
		System.out.println("Key Pressed = "+e.getKeyCode());
		if(e.getKeyCode()==32)// 32 --> space bar
			addPoints(1);

		// You can use other keys to add multiple points for speed
		repaint();
	}
	public void keyReleased(KeyEvent e)
	{
	}
	public void keyTyped(KeyEvent e)
	{
	}

	public static void main(String[] args)
	{
		SierpinskiStarter app=new SierpinskiStarter();
	}

	public class Point
	{
		// Write an internal point class with x-coord, y-coord and color
		int x, y;
		Color color;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
			color = Color.BLACK;
		}
	}
}