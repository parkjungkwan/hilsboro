//package philoarte.jaemin.api.crawling.service;
//
//
//import philoarte.jaemin.api.common.domain.Crawler;
//import philoarte.jaemin.api.crawling.domain.Review;
//import org.springframework.data.domain.Page;
//
//import java.awt.print.Pageable;
//import java.io.IOException;
//import java.util.List;
//import java.util.Optional;
//
//public interface FundingCrawlingService {
//
//    public List<Review> fundFindAll();
//
//    public void crawlingHome();
//
//    List<Review> saveAll(Crawler crawler) throws IOException;
//
//    Page<Review> findAll(final Pageable pageable);
//
//    public Optional<Review> findById(String tumblebuckId);
//
//    public void OptionalInit(String tumblebuckId);
//}
