import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


class Main{
    public static void main(String[] args) throws IOException {
        String url = "https://rozetka.com.ua/ua/mobile-cases/c146229/filter/";
        ParseCategory parse_category = new ParseCategory();
        parse_category.parse_category(url);
    }
}