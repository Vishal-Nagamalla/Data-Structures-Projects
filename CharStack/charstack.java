import java.util.EmptyStackException;

public class charstack {
    public static void main(String[] args) {
        CharStack cs = new CharStack();
        cs.push('!');
        cs.push('A');
        cs.push('L');
        cs.push('O');
        cs.push('H');
        System.out.println(cs.peek()+"I!");
        while (!cs.empty())
            System.out.print(cs.pop());
        System.out.println();
    }
}

class CharStack {
    private String charstr;

    public CharStack() {
        charstr = "";
    }

    public void push(char c){
        charstr += c;
    }
    public char peek(){
        if(!empty()){
            return ' ';
        }
        return charstr.charAt(charstr.length());
    }
    public char pop(){
        if(empty()){
            throw EmptyStackException();
        }
        char topval = charstr.charAt(charstr.length() -1);
        charstr = charstr.substring(0, charstr.length()-1);
    }
    public boolean empty(){
        if(charstr.length() > 0){
            return false;
        }
        return true;
    }
}