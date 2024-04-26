package hillsboro.philoarte.scalar.repositories;

import hillsboro.philoarte.scalar.entities.ReviewFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ReviewFileDao extends JpaRepository<ReviewFile, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM ReviewFile rf where rf.review.reviewId = :reviewId")
    void reviewFileDelete(@Param("reviewId") Long reviewId);



}

