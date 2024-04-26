package shop.philoarte.api.review.domain.dto;

import lombok.*;
import org.springframework.stereotype.Component;
import shop.philoarte.api.review.domain.Review;
import shop.philoarte.api.review.domain.Review;

@Component
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewFileDto {

    private Long reviewFileId;
    private String uuid;
    private String imgName;
    private String path;
    private Review review;

}
