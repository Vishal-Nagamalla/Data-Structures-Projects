import javax.lang.model.util.Elements;
import javax.swing.text.Document;
import javax.swing.text.Element;
import java.io.IOException;
public class Main {
    public static void main(String[] args) throws Exception {

        Document doc = Jsoup.connect ("https://www.imdb.com/chart/top").timeout(6000).get();
        Elements body = doc.select ("tbody.lister-list");
        //System.out.println (body. select ("tr") .size () );
        for (Element e: body.select ("tr")) {
            String img = e.select("td.posterColumn img").attr("sre");
            System.out.println(img);
            String title = e.select("td.posterColumn img").attr("alt");
            System.out.println(title);
            String year = e.select("td. titleColumn span.secondaryInfo").text().replaceAll("[^\\d]", "");
            System.out.println(year);
            String rate = e.select("td.ratingColumn.imdbRating").text().trim();
            System.out.println(rate);
        }
    }
}