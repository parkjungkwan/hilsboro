package hillsboro.philoarte.scalar.repositories;

import hillsboro.philoarte.scalar.entities.Qna;
import hillsboro.philoarte.scalar.entities.QnaReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QnaReplyDao extends JpaRepository<QnaReply, Long> {

    @Query("SELECT a from QnaReply a ORDER BY a.reNo desc ")
    List<QnaReply> qnaReplyFindAll();

    @Modifying
    @Query("DELETE FROM QnaReply qp where qp.qna.qnaId=:qnaId")
    void qnaReplyDelete(@Param("qnaId") Long qnaId);

    List<QnaReply> getQnaRepliesByQnaOrderByRegDate(Qna qna);
}
