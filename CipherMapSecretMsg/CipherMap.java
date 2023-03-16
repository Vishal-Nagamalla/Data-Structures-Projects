import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class CipherMap {
public HashMap<Character,Character> map;

public CipherMap(){
  ArrayList<Character> abc = new ArrayList<Character>();
  for (char c = 'a'; c <= 'z'; c++)
    abc.add(c);
    
  map = new HashMap<>();

  //load the map with RANDOM recipricol key/value pairings
  Random rand = new Random();
  for (char c : abc) {
      char randomChar = abc.get(rand.nextInt(abc.size()));
      map.put(c, randomChar);
      map.put(randomChar, c);
  }
}

  public CipherMap(String fileName){
  try {
      FileInputStream fileIn = new FileInputStream(fileName);
      ObjectInputStream in = new ObjectInputStream(fileIn);
      map = (HashMap<Character, Character>) in.readObject();
      in.close();
      fileIn.close();
      } catch (IOException i) {
        i.printStackTrace();
      return;
      } catch (ClassNotFoundException c) {
        System.out.println("CipherMap class not found");
        c.printStackTrace();
        return;
    }
  }

  public void serialize(String fileName){
    try {
      FileOutputStream fileOut = new FileOutputStream(fileName);
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(map);
      out.close();
      fileOut.close();
    } catch (IOException i) {
      i.printStackTrace();
    }
  }

  public String encode(String s){
    String encoded = "";
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (map.containsKey(c)) {
        encoded += map.get(c);
      } else {
        encoded += c;
      }
    }
    return encoded;
  }

  public static void main (String[]args){
    CipherMap cm = new CipherMap();
    String cipherText = cm.encode("What is Up");
    System.out.println(cipherText);
  }
}