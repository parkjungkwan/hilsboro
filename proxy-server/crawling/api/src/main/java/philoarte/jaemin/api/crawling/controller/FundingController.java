//package philoarte.jaemin.api.crawling.controller;
//
//import philoarte.jaemin.api.common.domain.Crawler;
//import philoarte.jaemin.api.crawling.domain.Review;
//import philoarte.jaemin.api.crawling.service.FundingCrawlingServiceImpl;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.java.Log;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/funding")
//@RequiredArgsConstructor
//@Log
//public class FundingController {
//
//    private final FundingCrawlingServiceImpl service;
//
//    @PostMapping("/scrap")
//    public ResponseEntity<List<Review>> crawling(@RequestBody Crawler crawler) throws Exception {
//        System.out.println("************카테고리 : " + crawler.toString());
//        return ResponseEntity.ok(service.saveAll(crawler));
//    }
//}
