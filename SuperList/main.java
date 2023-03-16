public class main{
    public static void main(String[] args){
        SuperList<Integer> list = new SuperList<Integer>();

        list.add(3);
        list.add(2);
        list.add(1);

        System.out.println(list.size());
        System.out.println(list);
    }
}