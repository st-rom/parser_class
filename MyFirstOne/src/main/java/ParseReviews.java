import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ParseReviews {
    public static String parse_reviews(String url) throws IOException {
        Document meg = Jsoup.connect(url).get();
        Elements nums = meg.select("a[class*=paginator-catalog-l-link]");
        int num = 1;
        for(Element n:nums){
            num += 1;
        }
        int nu;
        int coms = 0;
        String all_coms = "";
        for(nu = 1; nu < num + 1; nu++){
            String pg = url + "page=" + nu + "/";
            ParseReviewsPage p = new ParseReviewsPage();
            String prp = p.parse_reviews_page(pg);
            //String prp = parse_reviews_page(pg);
            coms += Integer.parseInt(prp.substring(0, prp.indexOf(" ")));
            String[] values = prp.split(" ");
            int meh = 0;
            for (String araba:
                    values) {
                if (meh != 0) {
                    all_coms += araba + " ";
                }
                meh += 1;
            }
        }
        return coms + " reviews from " + url + " comments:\n" + all_coms;
    }
}
