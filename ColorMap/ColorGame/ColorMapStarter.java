package ColorGame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
public class ColorMapStarter extends JPanel implements MouseListener
{
	public static final Color[] COLOR = {Color.WHITE,Color.RED,Color.BLUE,Color.YELLOW, Color.GREEN};
	public static final int[] COLUMNS = {20,180,360};
	public static int MAX_CLICKS = 6;
	public static int MAX_CAPTURES = 6;
	public static int HI_SCORE = 0;


	JFrame frame;
	HashMap<Color,Color> map = new HashMap<Color,Color>();  		// Fill this when you capture
	HashMap<Color,Integer> colorVal = new HashMap<Color,Integer>();	// Use this to give random values to colors
	ArrayList<Color> upList = new ArrayList<Color>();
	ArrayList<Color> downList = new ArrayList<Color>();
	int size = 60;
	Color randColor;
	int captures, clicks;
	boolean showKey;
	int score = 0;
	Properties p;

	public ColorMapStarter()
	{
		frame=new JFrame("Color Map");
		frame.setSize(1000,1000);
		frame.add(this);
		frame.addMouseListener(this);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		randColor = COLOR[(int)(Math.random()*COLOR.length)];
		showKey = false;
		randomizeLists();
		makeKey();
		System.out.println(colorVal);
		try{
			FileReader reader=new FileReader("ColorMap.properties");
			p=new Properties();
			p.load(reader);
			MAX_CLICKS = Integer.parseInt(p.getProperty("MAX_CLICKS"));
			MAX_CAPTURES = Integer.parseInt(p.getProperty("MAX_CAPTURES"));
			HI_SCORE = Integer.parseInt(p.getProperty("HI_SCORE"));
		
		} catch (Exception e){
		System.out.println("Caught =>"+e);
		
		}
		
		captures = 0;
		clicks = MAX_CLICKS;

	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;

		g2.setColor(Color.getHSBColor(100, 150, 200));
		g2.fillRect(0,0,frame.getWidth(),frame.getHeight());

		int x = COLUMNS[0], y = 200+(COLOR.length-1)*size;

		if (showKey){
			

			x = COLUMNS[2]; y = 200;  // Player map
			g2.setColor(Color.BLACK);

			g2.setFont(new Font("Impact", Font.PLAIN, 70));
			g2.drawString("GAME OVER!",20, 100);
	

			g2.setColor(Color.BLACK);
			g2.setFont(new Font("Comic Sans", Font.BOLD, 20));
			g2.drawString("Final Map => "+captures+" captures",x, 170);
			
	
			for (Map.Entry<Color, Color> entry : map.entrySet()){
				g2.setColor(entry.getKey());
				g2.fillRect(x,y,size,size);
				g2.setColor(Color.BLACK);
				g2.drawRect(x,y,size,size);
				g2.setColor(entry.getValue());
				g2.fillRect(x+size,y,size,size);
				g2.setColor(Color.BLACK);
				g2.drawRect(x+size,y,size,size);
				y+=size;
	
			}

			if(scoreMap(map)== HI_SCORE){
				g2.drawString("New High Score = " + HI_SCORE, COLUMNS[2], 640);

			}
			x = 100; y = 700;
			g2.setFont(new Font("Times New Roman", Font.BOLD, 40));

			g2.drawString("Key: ", x,y-size+45);
			for(Map.Entry<Color, Integer> entry : colorVal.entrySet()){
				
				g2.setColor(entry.getKey());
				g2.fillRect(x,y,size,size);
				g2.setColor(Color.BLACK);
				g2.drawRect(x,y,size,size);

				g2.setColor(Color.black);
				g2.setFont(new Font("Times New Roman", Font.BOLD, 30));
				double xSize = x+size/2.5;
				double ySize = y+size/1.5;
				g2.drawString(""+entry.getValue(),(int)xSize, (int)ySize);
				
				x+=size*2;
			}
		}

		else{
			///////////////////////////////////////////////////// HEADER /////////////////
			g2.setColor(Color.black);
			g2.setFont(new Font("Impact", Font.PLAIN, 36));
			g2.drawString("COLOR MAP! - Capture colors to maximize your score",20, 40);
			g2.setFont(new Font("Times New Roman", Font.BOLD, 20));
			g2.drawString("Next Color ==>",105, 110);
			g2.drawString(captures+" captures, "+clicks+" clicks left",420, 110);
			g2.setColor(randColor);
			g2.fillRect(275,70,2*size,size);
			g2.setColor(Color.BLACK);
			g2.drawRect(275,70,2*size,size);



			////////////////////////////////////////////////////// UP LIST  ////////////////
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("Times New Roman", Font.BOLD, 20));
			g2.drawString("Up List",x+30, 160);
			g2.setColor(Color.ORANGE);		// Top Triangle
			int[] xArr1 = {x+20,x+100,x+60};
			int[] yArr1 = {190,190,170};
			g2.fillPolygon(xArr1,yArr1,3);
			g2.setColor(Color.ORANGE);		// Bottom Triangle
			System.out.println(y);
			int[] xArr2 = {x+20,x+100,x+60};
			int[] yArr2 = {y+90,y+90,y+70};
			g2.fillPolygon(xArr2,yArr2,3);
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("Times New Roman", Font.BOLD, 20));
			for (int i = 0; i < upList.size(); i++){
				g2.setColor(upList.get(i));
				g2.fillRect(x,y,2*size,size);
				g2.setColor(Color.BLACK);
				g2.drawRect(x,y,2*size,size);
				y -= size;
			}



			////////////////////////////////////////////////////// DOWN LIST  //////////////
			x = COLUMNS[1]; y = 200;
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("Times New Roman", Font.BOLD, 20));
			g2.drawString("Down List",x+10, 160);
			g2.setColor(Color.ORANGE);		// Top Triangle
			int[] xArr = {x+20,x+100,x+60};
			int[] yArr = {170,170,190};
			g2.fillPolygon(xArr,yArr,3);
			for (int i = 0; i < downList.size(); i++){
				g2.setColor(downList.get(i));
				g2.fillRect(x,y,2*size,size);
				g2.setColor(Color.BLACK);
				g2.drawRect(x,y,2*size,size);
				y += size;

			}
			g2.setColor(Color.ORANGE);		// Bottom Triangle
			int[] xArr3 = {x+20,x+100,x+60};
			int[] yArr3 = {y+10,y+10,y+30};
			g2.fillPolygon(xArr3,yArr3,3);


			////////////////////////////////////////////////////// PLAYER MAP  //////////////
			x = COLUMNS[2]; y = 200;  // Player map
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("Comic Sans", Font.BOLD, 20));
			g2.drawString("Saved Map => "+captures+" captures",x, 170);

			if (captures >= 1){  // Player 2 Turn

				for (Map.Entry<Color, Color> entry : map.entrySet()){
					g2.setColor(entry.getKey());
					g2.fillRect(x,y,size,size);
					g2.setColor(Color.BLACK);
					g2.drawRect(x,y,size,size);
					g2.setColor(entry.getValue());
					g2.fillRect(x+size,y,size,size);
					g2.setColor(Color.BLACK);
					g2.drawRect(x+size,y,size,size);
					y+=size;
		
				}
			} else {
				//Intro message please make this better if you have more aesthetic sense than your CS teacher
				g2.setColor(Color.black);
				g2.setFont(new Font("Times New Roman", Font.BOLD, 30));
				g2.drawString("Click HERE To",x, 220);
				g2.drawString("   Capture   ",x, 270);
				g2.setFont(new Font("Times New Roman", Font.BOLD,30));
				g2.setColor(Color.black);
				g2.drawString("   OR    ",x, 330);
				g2.setColor(Color.black);
				g2.setFont(new Font("Times New Roman", Font.BOLD, 30));
				g2.drawString("Click Lists To",x, 380);
				g2.drawString("Add Random ",x, 430);
				g2.drawString("  Color ",x, 480);

			}

			g2.setColor(Color.black);
			g2.setFont(new Font("Times New Roman", Font.BOLD, 20));
			if(captures>= MAX_CAPTURES)
				g2.drawString("FINAL SCORE = "+scoreMap(map),COLUMNS[2], 560);
			else
				g2.drawString("SCORE = "+scoreMap(map),COLUMNS[2], 560);

			g2.drawString("High Score = " + HI_SCORE, COLUMNS[2], 640);
		

			////////////////////////////////////////////////////// CLICK TO PASS  /////////
			if (clicks < 1){
				g2.setColor(Color.YELLOW);
				g2.setFont(new Font("Tims New Roman", Font.BOLD, 20));
				g2.drawString("NO MORE LIST CLICKS - Capture the Map",200, 610);

			}		
		}
	}

	/* YOU WILL NEED TO COMPLETE MOUSE CLICK OPERATIONS FOR SCENERIOS BELOW */

	public void makeKey(){
		int val = 1;
		while(colorVal.size()<5){
			Color c = COLOR[(int)(Math.random()*COLOR.length)];
			if(!colorVal.containsKey(c)){
				colorVal.put(c,val);
				val++;
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
	
		// UP LIST
		if (x > COLUMNS[0] && x < COLUMNS[1] && y < 610 && clicks > 0) {
			upList.remove(upList.size() - 1);
			upList.add(0, randColor);
			randColor = COLOR[(int) (Math.random() * COLOR.length)];
			clicks--;
		}
	
		// DOWN LIST
		if (x > COLUMNS[1] && x < COLUMNS[2] && y < 610 && clicks > 0) {
			downList.remove(downList.size() - 1);
			downList.add(0, randColor);
			randColor = COLOR[(int) (Math.random() * COLOR.length)];
			clicks--;
		}
	
		// PLAYER MAP
		if (x > COLUMNS[2] && y < 610) {
			if (captures < MAX_CAPTURES) {
				for (int i = 0; i < upList.size(); i++) {
					Color upColor = upList.get(i);
					Color downColor = downList.get(i);
					map.put(upColor, downColor);
				}
	
				captures++;
				clicks = MAX_CLICKS;
			}
	
			if (captures == MAX_CAPTURES) {
				showKey = true;
				int score = scoreMap(map);
				if (score > HI_SCORE) {
					try {
						FileWriter writer = new FileWriter("ColorMap.properties");
						HI_SCORE = score;
						p.setProperty("HI_SCORE", HI_SCORE + "");
						p.store(writer, "");
	
					} catch (Exception ex) {
						System.out.println("Caught =>" + ex);
	
					}
				}
			}
		}
	
		repaint();
	}
	

	// Other mouse listener methods we don't need to use
	public void mouseExited(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}

	public void randomizeLists() {
		upList.clear();
		downList.clear();
		Random rand = new Random();
		int colorLength = COLOR.length;
		for (int i = 0; i < 5; i++) {
			upList.add(COLOR[rand.nextInt(colorLength)]);
			downList.add(COLOR[rand.nextInt(colorLength)]);
		}
	}	

	public int scoreMap(HashMap<Color, Color> map) {
		int sum = 0;
	
		for (Map.Entry<Color, Color> entry : map.entrySet()) {
			int mult = entry.getKey().equals(entry.getValue()) ? 2 : 1;
			sum += colorVal.getOrDefault(entry.getKey(), 0) * mult;
			sum += colorVal.getOrDefault(entry.getValue(), 0) * mult;
		}
	
		return sum;
	}	

	public static void main(String[] args)
	{
		ColorMapStarter app = new ColorMapStarter();
	}

}