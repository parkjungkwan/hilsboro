package philoarte.jaemin.api;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import philoarte.jaemin.api.artist.domain.Artist;
import philoarte.jaemin.api.review.domain.QReview;
import philoarte.jaemin.api.review.domain.Review;
import philoarte.jaemin.api.review.domain.ReviewFile;
import philoarte.jaemin.api.review.repository.ReviewFileRepository;
import philoarte.jaemin.api.review.repository.ReviewRepository;

import javax.swing.text.html.Option;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewFileRepository reviewFileRepository;

    @Test
    public void insertReview() {
        IntStream.rangeClosed(1, 30).forEach(i -> {
            Artist artist = Artist.builder()
                    .artistId(1L + i)
                    .build();

            Review review = Review.builder()
                    .title("title" + i)
                    .content("content" + i)
                    .artist(artist)
                    .build();

            reviewRepository.save(review);

            int count = (int) (Math.random() * 5) + 1;

            for (int j = 0; j < count; j++) {
                ReviewFile reviewFile = ReviewFile.builder()
                        .uuid(UUID.randomUUID().toString())
                        .review(review)
                        .imgName("img" + j + "jpg").build();

                reviewFileRepository.save(reviewFile);
            }
        });
    }

    @Transactional
    @Test
    public void testRead1() {
        Optional<Review> result = reviewRepository.findById(100L);

        Review review = result.get();

        System.out.println(review);
        System.out.println(review.getReviewId());
    }

    @Test
    public void testReadWithWriter() {
        Object result = reviewRepository.getRevieWithWriter(100L);

        Object[] arr = (Object[]) result;

        System.out.println("-----------------------");
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testWithReplyCount() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("reviewId").descending());

        Page<Object[]> result = reviewRepository.getReviewWithReplyCount(pageable);

        result.get().forEach(row -> {
            Object[] arr = (Object[]) row;
            //System.out.println(arr.length);
            System.out.println(Arrays.toString(arr));
        });
    }

    @Test
    public void testRead3() {
        Object result = reviewRepository.getReviewByReviewId(100L);

        Object[] arr = (Object[]) result;

        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testSearchPage() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("reviewId").descending());

        Page<Object[]> result = reviewRepository.searchPage("w", "유아", pageable);
    }


    @Commit
    @Transactional
    @Test
    public void insertReviewFiles() {
        IntStream.rangeClosed(1, 30).forEach(i -> {
            Review review = Review.builder().title("Review title..." + i).build();

            System.out.println("----------------------------------------------------");

            reviewRepository.save(review);

            int count = (int) (Math.random() * 5) + 1;

            for (int j = 0; j < count; j++) {
                ReviewFile reviewFile = ReviewFile.builder()
                        .uuid(UUID.randomUUID().toString())
                        .review(review)
                        .imgName("test img" + j + ".jpg").build();

                reviewFileRepository.save(reviewFile);
            }
        });
    }

    @Test
    public void testGetReviewWithReply() {
        List<Object[]> result = reviewRepository.getRevieWithReply(417L);

        System.out.println(result);

        for (Object[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }
    }


}
