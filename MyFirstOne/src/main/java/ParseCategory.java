import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class ParseCategory {
    public static void parse_category(String url) throws IOException {
        StringBuilder sb = new StringBuilder();
        int maxim = 0;
        int maxtry;
        String inform = "";
        Document counter = Jsoup.connect("https://rozetka.com.ua/ua/mobile-cases/c146229/filter/").get();
        Elements counts = counter.select("a[class*=paginator-catalog-l-link]");
        int pages = Integer.parseInt(counts.last().text().trim());
        for(int i = 1; i < pages; i++){
            Document doc = Jsoup.connect("https://rozetka.com.ua/ua/mobile-cases/c146229/filter/page=" +
                    Integer.toString(i)).get();
            Elements tiles = doc.select("div[class*=g-i-tile-i-title]");
            for(Element tile:tiles) {
                Elements nah = tile.select("a[href]");
                String op = nah.attr("href");
                ParseReviews p = new ParseReviews();
                String parser = p.parse_reviews(op + "comments/");
                //String parser = parse_reviews(op + "comments/");
                maxtry = Integer.parseInt(parser.substring(0, parser.indexOf(" ")));
                if(maxtry > maxim){
                    maxim = maxtry;
                    inform = "\n" + tile.text() + " " + parser;
                }
                System.out.print(tile.text() + "\n" + parser + "\n");
                sb.append(tile.text() + "\n" + parser + "\n");
            }
        }
        PrintWriter pw = new PrintWriter(new File("cases.csv"));
        pw.write(sb.toString());
        pw.close();
        System.out.print("\n*********\nMax number of reviews had:" + inform);
    }
}
