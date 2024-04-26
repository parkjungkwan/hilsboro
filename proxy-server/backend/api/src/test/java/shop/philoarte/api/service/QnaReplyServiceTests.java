package shop.philoarte.api.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shop.philoarte.api.qna.domain.dto.QnaReplyDto;
import shop.philoarte.api.qna.service.QnaReplyService;

import java.util.List;
@Log4j2
@SpringBootTest
public class QnaReplyServiceTests {

    @Autowired
    private QnaReplyService qnaReplyService;

    @Test
    public void getQnaReplyList(){
        Long qnaId = 1L;

        List<QnaReplyDto> qnaReplyDtoList  = qnaReplyService.getList(qnaId);

        qnaReplyDtoList.forEach(re->{
            log.info(re);
        });
    }

}
