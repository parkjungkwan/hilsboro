package shop.philoarte.api.qna.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import shop.philoarte.api.qna.domain.Qna;
import shop.philoarte.api.qna.repository.search.SearchQnaRepository;
import shop.philoarte.api.qna.repository.search.SearchQnaRepositoryImpl;

import java.util.List;

public interface QnaRepository extends JpaRepository<Qna, Long>, SearchQnaRepository {

    @Transactional
    @Modifying
    @Query("DELETE FROM Qna a where a.qnaId = :qnaId")
    void qnaDelete(@Param("qnaId") Long qnaId);

    @Query("SELECT a, w FROM Qna a LEFT JOIN a.artist w where a.qnaId = :qnaId")
    Object getQnaWithWriter(@Param("qnaId") Long qnaId);

    @Query("SELECT a, w, count(qr) " +
            " FROM Qna a LEFT JOIN a.artist w" +
            " LEFT OUTER JOIN QnaReply qr ON qr.qna = qr " +
            " where a.qnaId = :qnaId group by qr ")
    List<Object[]> getQnaWithReply(@Param("qnaId") Long qnaId);

    @Query("SELECT a, w, count(qr) " +
            " FROM Qna a LEFT JOIN a.artist w " +
            " LEFT OUTER JOIN QnaReply qr ON qr.qna = a " +
            " where a.qnaId = :qnaId")
    Object getQnaByQnaId(@Param("qnaId") Long qnaId);

    @Query(value = "SELECT a, w, count(qr) " +
            " FROM Qna a " +
            " LEFT JOIN a.artist w "
            + " LEFT JOIN QnaReply qr ON qr.qna = a"
            + " GROUP BY a",
            countQuery = "SELECT count(a) FROM Qna a")
    Page<Object[]> getQnaWithReplyCount(Pageable pageable);
}