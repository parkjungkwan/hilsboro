package shop.philoarte.api.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import shop.philoarte.api.qna.domain.Qna;
import shop.philoarte.api.qna.domain.QnaReply;
import shop.philoarte.api.qna.repository.QnaReplyRepository;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class QnaReplyRepositoryTests {

    @Autowired
    private QnaReplyRepository replyRepository;

    @Test
    public void insertQnaReply(){
        IntStream.rangeClosed(1, 30).forEach(i->{
            long qnaId = (long)(Math.random()*30) + 1;

            Qna qna = Qna.builder().qnaId(qnaId).build();

            QnaReply qnaReply = QnaReply.builder()
                    .text("QnaReply..." + i)
                    .qna(qna)
                    .replyer("guest")
                    .build();

            replyRepository.save(qnaReply);
        });
    }

    @Transactional
    @Test
    public void readReply1(){

        Optional<QnaReply> result = replyRepository.findById(30L);

        QnaReply qnaReply = result.get();

        System.out.println(qnaReply);

        System.out.println(qnaReply.getQna());

    }


}
