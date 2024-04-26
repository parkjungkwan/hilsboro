//package philoarte.jaemin.api.crawling.service;
//
//import philoarte.jaemin.api.common.domain.Crawler;
//import philoarte.jaemin.api.common.service.AbstractService;
//import philoarte.jaemin.api.common.service.CrawlerServiceImpl;
//import philoarte.jaemin.api.crawling.domain.Review;
//import philoarte.jaemin.api.crawling.repository.FundingCrawlingRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.java.Log;
//import org.jsoup.nodes.Document;
//import org.jsoup.select.Elements;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.data.domain.Page;
//import org.springframework.stereotype.Service;
//
//import java.awt.print.Pageable;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//@Lazy
//@Log
//public class FundingCrawlingServiceImpl extends AbstractService<Review> implements FundingCrawlingService {
//
//    private final FundingCrawlingRepository fundingRepository;
//
//    @Override
//    public List<Review> fundFindAll() {
//        return null;
//    }
//
//    @Override
//    public void crawlingHome() {
//
//    }
//
//    @Override
//    public List<Review> saveAll(Crawler crawler) throws IOException {
//
//        Document document = CrawlerServiceImpl.connectUrl(crawler.getUrl());
//        fundingRepository.deleteAll();
//
//        Elements elements = document.select(crawler.getCssQuery());
//
//        List<Review> list = new ArrayList<>();
//
//        for (int i = 0; i < elements.size(); i++) {
//            Review review = new Review();
//            review.setTitle(elements.get(i).text());
//            review.setAddress(elements.get(i).attr("href"));
//            review.setCategory(crawler.getCategory());
//            list.add(review);
//            fundingRepository.save(review);
//        }
//
//        return list;
//    }
//
//    @Override
//    public Page<Review> findAll(Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public Optional<Review> findById(String tumblebuckId) {
//        return Optional.empty();
//    }
//
//    @Override
//    public void OptionalInit(String tumblebuckId) {
//
//    }
//
//    @Override
//    public Long save(Review review) {
//        return (fundingRepository.save(review)!=null) ? 1L:0L;
//    }
//
//    @Override
//    public Optional<Review> findById(long id) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Page<Review> findAll(org.springframework.data.domain.Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public int count() {
//        return 0;
//    }
//
//    @Override
//    public Optional<Review> getOne(long id) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Long delete(Review review) {
//        return null;
//    }
//
//    @Override
//    public Boolean existsById(long id) {
//        return null;
//    }
//
//    @Override
//    public void deleteById(long id) {
//
//    }
//}
