package shop.philoarte.api.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import shop.philoarte.api.funding.domain.PageRequestDto;
import shop.philoarte.api.qna.domain.dto.QnaDto;
import shop.philoarte.api.qna.domain.dto.QnaPageRequestDto;
import shop.philoarte.api.qna.domain.dto.QnaPageResultDto;
import shop.philoarte.api.qna.service.QnaService;
import shop.philoarte.api.review.domain.dto.PageResultDto;
import shop.philoarte.api.review.domain.dto.ReviewDto;

@SpringBootTest
public class QnaServiceTests {

    @Autowired
    private QnaService qnaService;

    @Test
    public void testGetList(){
        QnaPageRequestDto qnaPageRequestDto = new QnaPageRequestDto(1, 10, "t", "1");

        QnaPageResultDto<QnaDto, Object[]> result = qnaService.getList(qnaPageRequestDto);

        System.out.println(result.getDtoList());

        for(QnaDto qnaDto : result.getDtoList()){
            System.out.println(qnaDto);
        }
    }

    @Test
    public void testGet(){
        Long qnaId = 27L;
        QnaDto qnaDto = qnaService.get(qnaId);
        System.out.println(qnaDto);
    }

    @Test
    public void testRemove(){
        Long qnaId = 2L;

        qnaService.removeWithQnaReplies(qnaId);
    }

    @Test
    public void testRegister(){
        QnaDto qnaDto = QnaDto.builder()
                .title("test2")
                .content("wq")
                .writerId(2L)
                .build();

        qnaService.save(qnaDto);
    }

    @Transactional
    @Test
    @Commit
    public void testModify(){
        QnaDto qnaDto = QnaDto.builder()
                .qnaId(11L)
                .title("r2")
                .content("s")
                .build();

        qnaService.modify(qnaDto);
    }

    @Test
    public void testSearch(){
        QnaPageRequestDto qnaPageRequestDto = QnaPageRequestDto.builder()
                .page(1)
                .size(10)
                .type("c")
                .keyword("content")
                .build();

        QnaPageResultDto<QnaDto, Object[]> qnaDtoQnaPageResultDto = qnaService.getList(qnaPageRequestDto);

        System.out.println("prev : " + qnaDtoQnaPageResultDto.isPrev());
        System.out.println("next : " + qnaDtoQnaPageResultDto.isNext());
        System.out.println("total : " + qnaDtoQnaPageResultDto.getTotalPage());


        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        for (QnaDto qnaDto : qnaDtoQnaPageResultDto.getDtoList()) {
            System.out.println(qnaDto);
        }

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        qnaDtoQnaPageResultDto.getPageList().forEach(i -> System.out.println(i));

    }

}
