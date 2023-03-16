import java.util.TreeSet;

public class text {
  public static void main(String[] args) {
    String text = "From the reef-fringed coast of New Caledonia, the Coral Sea stretches into the South Pacific. Slender native pines, listing like whimsical Christmas trees, punctuate the shoreline. The landscape, one of the most biodiverse on the planet, is astonishingly beautiful until the crest of a hill where a different vista unfolds: a gouged red earth pierced by belching smokestacks and giant trucks rumbling across the lunar-like terrain. This is Goro, the largest nickel mine on a tiny French territory suspended between Australia and Fiji that may hold up to a quarter of the world's nickel reserves. It also poses a critical test for Tesla, the world's largest electric vehicle maker, which wants to take control of its supply chain and ensure that the minerals used for its car batteries are mined in an environmentally and socially responsible fashion.";

    TreeSet<String> words = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
    for (String word : text.split("\\s+")) {
      word = word.replaceAll("[^a-zA-Z]", "");
      if (!word.isEmpty()) {
        words.add(word);
      }
    }

    int count = 0;
    for (String word : words) {
      count++;
      if (count == 14) {
        System.out.println("The 14th word alphabetically (ignoring case) is: " + word);
        break;
      }
    }
  }
}
