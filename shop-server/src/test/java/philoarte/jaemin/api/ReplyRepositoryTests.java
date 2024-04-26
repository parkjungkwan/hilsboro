package philoarte.jaemin.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import philoarte.jaemin.api.artist.domain.Artist;
import philoarte.jaemin.api.artist.repository.ArtistRepository;
import philoarte.jaemin.api.review.domain.Reply;
import philoarte.jaemin.api.review.domain.Review;
import philoarte.jaemin.api.review.repository.ReplyRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class ReplyRepositoryTests {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Test
    public void insertReply() {
        IntStream.rangeClosed(1, 30).forEach(i -> {
            long reviewId = (long) (Math.random() * 20) + 1;


            Review review = Review.builder().reviewId(reviewId).build();

            Reply reply = Reply.builder()
                    .text("댓글" + i)
                    .review(review)
                    .replyer("댓글 쓴사람" + i)
                    .build();

            replyRepository.save(reply);
        });

    }

    @Test
    @Transactional
    public void readReply1() {
        Optional<Reply> result = replyRepository.findById(10L);

        Reply reply = result.get();

        System.out.println(reply);
        System.out.println(reply.getReview());
    }

    @Test
    public void testGetReviewReplies() {
        Review review = Review.builder().reviewId(884L).build();

        List<Reply> result = replyRepository.getRepliesByReviewOrderByRegDate(review);

        result.forEach(reviewReply -> {
            System.out.println(reviewReply.getRno());
            System.out.println("\t" + reviewReply.getImageName());
            System.out.println("\t" + reviewReply.getPath());
            System.out.println("\t" + reviewReply.getUuid());
            System.out.println("\t" + reviewReply.getText());
            System.out.println("\t" + reviewReply.getReview().getReviewId());
        });
    }


}
