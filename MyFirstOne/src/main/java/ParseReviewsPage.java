import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ParseReviewsPage {
    public static String parse_reviews_page(String url) throws IOException {
        Document sup = Jsoup.connect(url).get();
        Elements reviews = sup.select("article[class*=pp-review-i]");
        int coms = 0;
        String com_star = "";
        String com_texts = "";
        String united = "";
        for(Element rev:reviews){
            Elements star = rev.select("span[class*=g-rating-stars-i]");
            Elements text = rev.select("div[class*=pp-review-text]");
            if(!star.isEmpty()) {
                Elements texts = text.select("div[class*=pp-review-text-i]");
                com_star = star.attr("content");
                com_texts = texts.first().text().trim();
                united += com_star + " " + com_texts + "\n";

            }
            else{
                Elements texts = text.select("div[class*=pp-review-text-i]");
                com_texts = texts.first().text().trim();
                united += "0" + " " + com_texts + "\n";
            }
            coms += 1;
        }
        return(coms + " " + united);
    }
}
