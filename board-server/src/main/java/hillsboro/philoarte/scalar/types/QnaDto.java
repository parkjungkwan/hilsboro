package hillsboro.philoarte.scalar.types;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class QnaDto {

    private Long qnaId;

    private String title;
    private String content;

    private Long writerId;
    private String writerName;

    private int replyCount;

    private LocalDateTime regDate;
    private LocalDateTime modDate;

}