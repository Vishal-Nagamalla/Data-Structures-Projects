import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.awt.image.*;
import javax.imageio.ImageIO;
public class MazeProject extends JPanel implements KeyListener, ActionListener
{
	private JFrame frame;
	private int size = 25, width = 1500, height = 1000, currentMaze=0;
	private char[][] maze;
	private Timer t;
	private MazeElement finish;
	private ArrayList<MazeElement> coins = new ArrayList<MazeElement>(); private ArrayList<Tube> tubes = new ArrayList<Tube>(); private ArrayList<Bowser> bowsers = new ArrayList<Bowser>();
	private boolean done = false, complete=false;
	private Explorer explore;
	private BufferedImage brick;
	private String[] mazes = {"/Users/vishal/Desktop/Programming/Data Structures Projects/MazeProject/bin/mazeTxt", "/Users/vishal/Desktop/Programming/Data Structures Projects/MazeProject/mazeTxt2", "/Users/vishal/Desktop/Programming/Data Structures Projects/MazeProject/mazeTxt3"};

	public static void main(String[] args){
		MazeProject project = new MazeProject();
	}

	public MazeProject(){
		try {
			brick =ImageIO.read(new File("/Users/vishal/Desktop/Programming/Data Structures Projects/MazeProject/floor.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setBoard("//Users/vishal/Desktop/Programming/Data Structures Projects/MazeProject/bin/mazeTxt");
		frame=new JFrame("Mario Maze Game");
		frame.setSize(width,height); frame.add(this); frame.addKeyListener(this); frame.setResizable(false); frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); frame.setVisible(true);
		t = new Timer(1000, this); t.start();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		
		g2.setColor(Color.BLACK);
		g2.fillRect(0,0,frame.getWidth(),frame.getHeight());

		for(int r=0;r<maze.length;r++){
			for(int c=0;c<maze[0].length;c++){
				g2.setColor(Color.GRAY);
				Location locElement = new Location(r,c);
				if (maze[r][c]=='*')
					g2.drawImage(brick,c*size+size,r*size+size,size,size,null, this);

				else if(maze[r][c]==' ')
					g2.drawRect(c*size+size,r*size+size,size,size);

				if(locElement.equals(finish.getLoc()))
					g2.drawImage(finish.getImg(), c*size+size,r*size+size,size,size,null, this);
	
				for(int i = 0; i < tubes.size(); i++) {
					if(locElement.equals(tubes.get(i).getLoc()))
						g2.drawImage(tubes.get(i).getImg(), c*size+size,r*size+size,size,size,null, this);
				}
				for(int i = 0; i < bowsers.size(); i++) {
					if(locElement.equals(bowsers.get(i).getLoc()))
						g2.drawImage(bowsers.get(i).getImg(), c*size+size,r*size+size,size,size,null, this);
				}
				for(int i = 0; i < coins.size(); i++) {
					if(locElement.equals(coins.get(i).getLoc()))
						g2.drawImage(coins.get(i).getImg(), c*size+size,r*size+size,size,size,null, this);
						
					if(explore.intersects(coins.get(i))){coins.remove(i); explore.incRing();}
				}
				if(locElement.equals(explore.getLoc()))
					g2.drawImage(explore.getImg(), c*size+size,r*size+size,size,size,null, this);
			}
		}
		
		int hor = size; int vert = maze.length*size+ 2*size;
		g2.setFont(new Font("Arial",Font.BOLD,20));
		g2.setColor(Color.WHITE);
		g2.drawString("Safe Princess Peach and collect coins! Don't let Bowser get in your way!",hor,vert);
		g2.drawString("Steps taken: "+explore.getSteps(),hor,vert+20);
		g2.drawString("coins collected: "+explore.getRingCount(),hor,vert+40);
	
		if(done)
		{
			for(int i = 0; i < coins.size(); i++){coins.remove(i);}
			for(int i = 0; i < tubes.size(); i++){tubes.remove(i);}
			for(int i = 0; i < bowsers.size(); i++){bowsers.remove(i);}
			if(currentMaze<mazes.length-1)
				g2.drawString("OH NO THEY TOOK PRINCESS PEACH! Press Enter to find her",hor,vert+80);	
			else {
				g2.setFont(new Font("Arial",Font.BOLD,25));
				g2.drawString("YOU WIN!!!!",hor,vert+90);
				complete = true;
			}
		}
	}


	public void keyPressed(KeyEvent e){
		if(!complete)
			explore.move(e.getKeyCode(), maze);

		for(int i = 0; i < tubes.size(); i++){
			if(explore.intersects(tubes.get(i)))
				tubes.get(i).dash(explore);
		}

		for(int i = 0; i < bowsers.size(); i++){
			if(explore.intersects(bowsers.get(i)))
				bowsers.get(i).takecoins(explore);
		}

		if(done&&!complete){
			if(currentMaze<mazes.length-1&&!complete)
				currentMaze++;
			setBoard(mazes[currentMaze]);
			done = false;
		}

		if(explore.intersects(finish)){
			done = true;
			if(currentMaze > 3)
				complete=true;
		}
		repaint();
	}

	public void keyReleased(KeyEvent e){} public void keyTyped(KeyEvent e){}
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < bowsers.size(); i++){bowsers.get(i).moving(0, maze);}
		repaint();
	}

	public void setBoard(String fileName){
		String text;
		int row1 = 0, col1 = 0;
		try{
			try(BufferedReader input = new BufferedReader(new FileReader(fileName))) {
				while((text=input.readLine())!= null){
					int c = 0;
					row1++;
					char [] arr = text.toCharArray();

					for(int i = 0; i < arr.length;i++){c++;}
					if(row1 == 1)
						col1 = c;
					else
						if(c != col1)
							throw new IOException("**NOT ALL LINES THE SAME LENGTH**");
				}
			}
		}catch (IOException e){
			e.printStackTrace();
		}
		maze = new char[row1][col1];

		try{
			char [] arr;
			int row_count = 0;
			try(BufferedReader input = new BufferedReader(new FileReader(fileName))) {
				while((text=input.readLine())!= null)
				{
					arr = text.toCharArray();
					for(int i = 0; i < arr.length; i++){
						maze[row_count][i] = arr[i];
						if(arr[i]!=' ' && arr[i]!='*'){
							if(arr[i] == 'E')
				                finish = new MazeElement(new Location(row_count, i), size, "/Users/vishal/Desktop/Programming/Data Structures Projects/MazeProject/peach.png");
				            if(arr[i] == 'S')
				                explore = new Explorer(new Location(row_count, i), size);
				            if(arr[i] == 'C')
				            	coins.add(new MazeElement(new Location(row_count, i), size, "/Users/vishal/Desktop/Programming/Data Structures Projects/MazeProject/coin.png"));
				            if(arr[i] == 'D')
				            	tubes.add(new Tube(new Location(row_count, i), size, maze));
				            if(arr[i] == 'K')
				            	bowsers.add( new Bowser(new Location(row_count, i), size));
				            arr[i] = ' ';
						}
					}
					maze[row_count] = arr;
					row_count++;
				}
			}
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}