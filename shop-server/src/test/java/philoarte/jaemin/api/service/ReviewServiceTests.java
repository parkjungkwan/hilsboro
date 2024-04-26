package philoarte.jaemin.api.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import philoarte.jaemin.api.review.domain.Review;
import philoarte.jaemin.api.review.domain.dto.PageRequestDto;
import philoarte.jaemin.api.review.domain.dto.PageResultDto;
import philoarte.jaemin.api.review.domain.dto.ReviewDto;
import philoarte.jaemin.api.review.service.ReviewService;
import philoarte.jaemin.api.review.service.ReviewServiceImpl;

@SpringBootTest
public class ReviewServiceTests {

    @Autowired
    private ReviewService reviewService;

    @Test
    public void testGetList() {
        PageRequestDto pageRequestDto = new PageRequestDto(1, 10, "t", "1");

        PageResultDto<ReviewDto, Object[]> result = reviewService.getList(pageRequestDto);

//        //System.out.println(result);
//
//        System.out.println("===========================");
        System.out.println(result.getDtoList());

        for (ReviewDto reviewDto : result.getDtoList()) {
            System.out.println(reviewDto);
        }
    }

    @Test
    public void testGet() {
        Long reviewId = 30L;
        ReviewDto reviewDto = reviewService.get(reviewId);

        System.out.println(reviewDto);
    }

    @Test
    public void testRemove() {
        Long reviewId = 328L;

        reviewService.removeWithReplies(reviewId);
    }

    @Test
    public void testRegister() {
        ReviewDto dto = ReviewDto.builder()
                .title("test")
                .content("w")
//                .artId(1L)
                .writerId(3L)
                .build();

        reviewService.save(dto);
    }

    @Transactional
    @Test
    @Commit
    public void testModify() {
        ReviewDto reviewDto = ReviewDto.builder()
                .reviewId(52L)
                .title("변경")
                .content("내용 변경")
                .build();

        reviewService.modify(reviewDto);
    }

    @Test
    public void testSearch1() {
        PageRequestDto pageRequestDto = PageRequestDto.builder()
                .page(1)
                .size(10)
                .type("t")
                .keyword("유아연")
                .build();

        PageResultDto<ReviewDto, Object[]> reviewDtoReviewPageResultDto = reviewService.getList(pageRequestDto);

        System.out.println("prev : " + reviewDtoReviewPageResultDto.isPrev());
        System.out.println("next : " + reviewDtoReviewPageResultDto.isNext());
        System.out.println("total : " + reviewDtoReviewPageResultDto.getTotalPage());

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        for (ReviewDto reviewDto : reviewDtoReviewPageResultDto.getDtoList()) {
            System.out.println(reviewDto);
        }

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        reviewDtoReviewPageResultDto.getPageList().forEach(i -> System.out.println(i));


    }

}
