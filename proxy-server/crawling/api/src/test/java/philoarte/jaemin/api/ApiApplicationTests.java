//package philoarte.jaemin.api;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import philoarte.jaemin.api.crawling.domain.Review;
//import philoarte.jaemin.api.crawling.repository.FundingCrawlingRepository;
//
//import java.util.stream.IntStream;
//
//@SpringBootTest
//
//class ApiApplicationTests {
//
//	@Autowired
//	private FundingCrawlingRepository repo;
//
//	@Test
//	void contextLoads() {
//
//		System.out.println("AAAA");
//		System.out.println(repo);
//	}
//
//	@Test
//	public void testInsertDummies() {
//
//		IntStream.rangeClosed(1,100).forEach(i -> {
//
//			Review review = new Review();
//			review.setCategory("IT" + (i %10));
//			review.setAddress("Sample Address  " + i);
//			review.setTitle("Sample Title" + i);
//
//			repo.save(review);
//
//		});
//
//
//
//	}
//
//}
