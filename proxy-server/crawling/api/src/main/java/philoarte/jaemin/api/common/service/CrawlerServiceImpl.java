//package philoarte.jaemin.api.common.service;
//
//import philoarte.jaemin.api.common.domain.Crawler;
//import philoarte.jaemin.api.crawling.domain.Review;
//import lombok.extern.java.Log;
//import org.jsoup.Connection;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.select.Elements;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.List;
//
//@Service
//@Log
//public class CrawlerServiceImpl implements CrawlerService {
//
//    public static Document connectUrl(String url) throws IOException {
//        log.info("connectUrl() : " + url);
//
//        return Jsoup.connect(url) // 클래스 안에 이너클래스
//                .method(Connection.Method.GET)
//                .userAgent("Mozilla/5.0 (X11; Linux x86_64; rv:10.0) " +
//                        "Gecko/20100101 Firefox/10.0 " +
//                        "AppleWebKit/537.36 (KHTML, like Gecko) " +
//                        "Chrome/51.0.2704.106 Safari/537.36")
//                .execute().parse();
//
//
//    }
//
//    @Override
//    public List<?> scrapFunding(Crawler crawler) throws IOException {
//        Document fundingdocument = connectUrl(crawler.getUrl()); // jsoup 불변객체, "https://news.daum.net/society"
//        //List<>
//        Elements fundingelements = fundingdocument.select(crawler.getCssQuery());
//        //"div.sect-movie-chart>ol>li>div.box-image>strong"
//        for (int i = 0; i < fundingelements.size(); i++) {
//            Review review = new Review();
//            review.setTitle(fundingelements.get(i).text());
//            review.setAddress(fundingelements.get(i).attr("href"));
//            review.setCategory(crawler.getCategory());
//        }
////        return repository.count() > 0L ? 1L: 0L;
//        return null;
//    }
//}
//
//
