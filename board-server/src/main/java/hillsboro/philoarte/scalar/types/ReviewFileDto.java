package hillsboro.philoarte.scalar.types;

import hillsboro.philoarte.scalar.entities.Review;
import lombok.*;
import org.springframework.stereotype.Component;

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
