import java.util.*;
public class Graph
{
    final private HashMap<String,HashSet<String>> g;
    public Graph()
    {
        g = new HashMap<>();
    }
    public void add(String v1,String v2)
    {
        if(!g.containsKey(v1)) {
            g.put(v1, new HashSet<>());
        }
        if(!g.containsKey(v2)) {
            g.put(v2, new HashSet<>());
        }
        g.get(v1).add(v2);
        g.get(v2).add(v1);
    }
    public boolean dfs(String v1, String v2){

        return dfs(v1, v2, new HashSet<String>());
    }

    private boolean dfs(String v1, String v2, Set visited){
        visited.add(v1);
        boolean b;

        if(v1.equals(v2))
            return true;

        for(String s: getNeighbors(v1)){
            if(!visited.contains(s)){
                b = dfs(s, v2, visited);
                if(b)
                    return b;
            }
        }

        return false;
    }
    public String dfsPath(String v1, String v2){
        Stack<String> stack = new Stack<String>();
        Stack<String> visited = new Stack<String>();
        String x;

        stack.add(v1);
        while(stack.size() > 0){
            x = stack.pop();

            if(!visited.contains(x)){
                visited.add(x);

                if(x.equals(v2))
                    return buildPath(visited);

                for(String s: getNeighbors(x)){
                    if(!visited.contains(s))
                        stack.add(s);
                }
            }
        }
        return "No connection";
    }
    private String buildPath(Stack<String> visited){
        Stack<String> stack2 = new Stack<String>();

        String x = visited.pop();
        stack2.add(x);

        while(visited.size() > 0) {
            String y = visited.pop();
            if(getNeighbors(x).contains(y))
                stack2.add(y);

            x = y;
        }
        String ans = "";

        while(stack2.size() > 0)
            ans+= stack2.pop() + " -> ";

        return ans.substring(0, ans.length()-4);
    }
    public Set<String> getVertices() {return g.keySet();}
    public Set<String> getNeighbors(String s) {return g.getOrDefault(s,new HashSet<>());}
    public int getNumVertices() {return g.size();}
    public static void main(String[]args)
    {
        Graph graph = new Graph();
        graph.add("NJ","PA");
        graph.add("PA","NY");
        graph.add("NJ","NY");
        graph.add("PA","DE");
        graph.add("PA","OH");
        graph.add("OH","KY");
        graph.add("SC","NC");
        graph.add("SC","GA");
        graph.add("GA","FL");
        graph.add("FL","AL");
        graph.add("GA","AL");
        System.out.println(graph.dfs("NJ", "OH"));  // true
        System.out.println(graph.dfs("NJ", "GA"));  // false
        System.out.println(graph.dfs("KY", "DE"));	 // true
        System.out.println(graph.dfs("SC", "AL"));  // true
        System.out.println(graph.dfs("FL", "OH"));  // false

    }
}