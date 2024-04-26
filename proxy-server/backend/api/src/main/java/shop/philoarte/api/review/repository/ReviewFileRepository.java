package shop.philoarte.api.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import shop.philoarte.api.review.domain.ReviewFile;

public interface ReviewFileRepository extends JpaRepository<ReviewFile, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM ReviewFile rf where rf.review.reviewId = :reviewId")
    void reviewFileDelete(@Param("reviewId") Long reviewId);



}

