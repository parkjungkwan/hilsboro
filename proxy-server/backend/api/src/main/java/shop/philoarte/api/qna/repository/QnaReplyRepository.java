package shop.philoarte.api.qna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.philoarte.api.qna.domain.Qna;
import shop.philoarte.api.qna.domain.QnaReply;

import java.util.List;

public interface QnaReplyRepository extends JpaRepository<QnaReply, Long> {

    @Query("SELECT a from QnaReply a ORDER BY a.reNo desc ")
    List<QnaReply> qnaReplyFindAll();

    @Modifying
    @Query("DELETE FROM QnaReply qp where qp.qna.qnaId=:qnaId")
    void qnaReplyDelete(@Param("qnaId") Long qnaId);

    List<QnaReply> getQnaRepliesByQnaOrderByRegDate(Qna qna);
}
