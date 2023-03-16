import java.util.Stack;

public class stacks {
    public stacks(){
        int decimal = 27;
        Stack<Integer> binarystack= new Stack<Integer>();
        binarystack = new Stack<Integer>();
        while(decimal > 0){
            int x = decimal % 2;
            binarystack.push(x);
            decimal /= 2;
        }

        while(!binarystack.isEmpty()){
            binarystack.peek();
            System.out.print(binarystack.pop());
        }

        System.out.println();

        Stack<Character> revstring = new Stack<>();
        String str = "Hello";
		char[] chars = str.toCharArray();
        
		for (char c: chars) {
			revstring.push(c);
		}
		for (int i = 0; i < str.length(); i++) {
			chars[i] = revstring.pop();
		}
        System.out.println(chars);
    }
}