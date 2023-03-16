import java.util.ArrayList;
import java.util.Stack;
import java.io.*;

public class Landfill{
  public Landfill(){
    ArrayList<Stack<Municipality>> area = new ArrayList<Stack<Municipality>>();
    Stack<Municipality> space = new Stack<Municipality>();

    for (Stack<Municipality>  mStack : area)
      System.out.println(mStack);

    try{
        BufferedReader input = new BufferedReader(new FileReader("NJMunicipalities.csv"));
        String text;
        input.readLine();
        while((text=input.readLine())!= null){
            if (text.length() > 0){
                String a[] = text.split(",");
                Municipality m  = new Municipality(a[0],a[1],a[2]);
                int i = 0;
                while (i< area.size() && !area.get(i).peek().getCounty().equals(m.getCounty()))
                    i++;
                if (i == area.size()) {
                    space.add(m);
                    area.add(space);
                } else {
                    area.get(i);
                    space.add(m);
                }
            }
        }
    }catch (IOException io){System.out.println("error");}

    int rnd = (int)(Math.random()*area.size());
    space = area.get(rnd);
    System.out.println("Selected "+ space.peek().getCounty() + " County");

    int dice = (int)(Math.random()*6)+ 1;;
    while (dice != 1 && !space.empty()){
      System.out.println(space.pop()+ " rolled "+ dice+". No Landfill");
      dice = (int)(Math.random()*6)+ 1;
    }
    if (space.empty())
      System.out.println("No location");
    else
      System.out.println(space.pop()+ " rolled "+ dice +". Yes Landfill");
  }
}