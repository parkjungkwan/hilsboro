package hillsboro.philoarte.scalar.types;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QnaReplyDto {

    private Long reNo;

    private String text;

    private String replyer;

    private Long qnaId;

    private LocalDateTime regDate;
    private LocalDateTime modDate;


}