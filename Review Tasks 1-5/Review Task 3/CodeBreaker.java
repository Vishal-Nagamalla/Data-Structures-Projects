import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class CodeBreaker {
    public static void main(String[] agrs){
        ArrayList<Colors> colorcode = new ArrayList<Colors>();
        File name = new File("CodeBreaker.txt");
        try{
            BufferedReader input = new BufferedReader(new FileReader(name));
            String text = "";

            while((text = input.readLine()) != null){
                colorcode.add(new Colors(text.charAt(0), text.charAt(1), text.charAt(2), text.charAt(3)));
            }

            for(int i = 0; i<colorcode.size(); i+=2){
                System.out.println("Code:\t\t" + colorcode.get(i));
                System.out.println("Guess:\t\t" + colorcode.get(i+1));
                System.out.println("Correct Letters:\t" + colorcode.get(i).correctlyPlaced(colorcode.get(i+1)));
                System.out.println("Incorrect Letters:\t" + colorcode.get(i).inncorrectlyPlaced(colorcode.get(i+1)));
                System.out.println("");
            }
        }
        
        catch (Exception e){
            System.out.println("Error" + e);
        }
    }

    public static class make{
        public boolean correct;
        char color;
        public make(char color){
            this.color = color;
        }
        public void setTrue(){
            correct = true;
        }

        public boolean getTrue(){return correct;}
        public char getColor(){return color;}

        @Override
        public String toString(){
            return ""+color;
        }
    }

    public static class Colors{
        make []colorsc;
        public Colors(char c1, char c2, char c3, char c4){
            this.colorsc = new make[]{new make(c1), new make(c2), new make(c3), new make(c4)};
        }
        public int correctlyPlaced(Colors colors){
            int correctlyPlaced = 0;
            for (int i = 0; i < 4; i++){
                if(this.colorsc[i].getColor() == colors.colorsc[i].getColor()){
                    correctlyPlaced++;
                    this.colorsc[i].setTrue();
                    colors.setTrue(i);
                }
            }
            return correctlyPlaced;
        }

        public int inncorrectlyPlaced(Colors colors){
            Colors current = new Colors(this.colorsc[0].getColor(), this.colorsc[1].getColor(), this.colorsc[2].getColor(), this.colorsc[3].getColor());
            int x = colors.correctlyPlaced(current);
            int incorrectlyPlaced = 0;
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    char colorsTrue = current.getColor(j);
                    if(colors.getTrue(j) == true || current.getTrue(j) == true){
                        continue;
                    }
                    if(colors.getColor(i) == colorsTrue){
                        incorrectlyPlaced++;
                        colors.setTrue(i);
                        current.setTrue(j);
                    }
                }
            }
            return incorrectlyPlaced;
        }
        @Override
        public String toString(){
            String x = ""+colorsc[0]+colorsc[1]+colorsc[2]+colorsc[3];
            return x;
        }

        public boolean getTrue(int i){return colorsc[i].getTrue();}
        public char getColor(int i){return colorsc[i].getColor();}

        public void setTrue(int i){
            this.colorsc[i].setTrue();
        }
    }
}