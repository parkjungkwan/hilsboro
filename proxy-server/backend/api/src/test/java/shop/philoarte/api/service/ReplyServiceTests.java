package shop.philoarte.api.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shop.philoarte.api.review.domain.dto.ReplyDto;
import shop.philoarte.api.review.service.ReplyService;

import java.util.List;

@SpringBootTest
@Log4j2
public class ReplyServiceTests {
    @Autowired
    private ReplyService service;

    @Test
    public void getReplyList(){
        Long reviewId = 10L;

        List<ReplyDto> replyDtoList = service.getList(reviewId);

        replyDtoList.forEach(re-> log.info(re));
    }
}