package hillsboro.philoarte.scalar.repositories;

import hillsboro.philoarte.scalar.entities.Reply;
import hillsboro.philoarte.scalar.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyDao extends JpaRepository<Reply, Long> {
    @Query("select a from Reply a order by a.rno desc")
    List<Reply> replyFindAll();

    @Modifying
    @Query("Update Reply a set a.text = :text where a.rno = :rno")
    int replyUpdate(@Param("rno") Long rno, @Param("text") String text);

    @Modifying
    @Query("DELETE FROM Reply rp where rp.review.reviewId = :reviewId ")
    void replyDelete(@Param("reviewId") Long reviewId);

    List<Reply> getRepliesByReviewOrderByRegDate(Review review);
}
