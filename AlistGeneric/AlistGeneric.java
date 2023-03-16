public class AlistGeneric<E>{
   public static final int INITAL_CAPACITY = 4;
   private int capacity;
   private int size;
   private Object[] list; //Object is a super class of all classes in java
   
   public AlistGeneric(){
      capacity = INITAL_CAPACITY;
      list = new Object[capacity];
      size = 0;
   }
   
   public void add(E val){
      if(size >= capacity){
      capacity += (int)(capacity * 0.5);
      Object[] tempArr = new Object[capacity];
      
      for(int i = 0; i < size; i++)
         tempArr[i] = list[i];
         
      list = tempArr;
      }
      
      list[size] = val;
      size++;
   }
   
   public E get(int index){
      if(index < 0 || index >= size){
         throw new IndexOutOfBoundsException();
      }
      return (E)list[index];
   }
   
   public String toString(){
      String str = "[";
      
      for(int i = 0; i < size - 1; i++)
         str += list[i] + ", ";
         
      if(size > 0)
         str += list[size -1];
         
         return str + "]";
   }

   public E remove(int index){
      if(index < 0 || index >+ size)
         throw new IndexOutOfBoundsException();

      Object temp = list[index];
            
      for(int i = 0; i < size; i++){
         list[i] = list[i+1];
      }
      
      list[size - 1] = null;

      size--;
      return (E)temp;
   }
   
   public static void main(String[] args){
      AlistGeneric<String> al = new AlistGeneric();
      System.out.println(al);
      al.add("A");
      al.add("B");
      al.add("C");
      al.add("D");
      al.add("E");
      al.add("F");
      System.out.println(al);
      System.out.println(al.get(2));
      System.out.println(al.get(4));
      System.out.println(al.remove(4));
   }
}