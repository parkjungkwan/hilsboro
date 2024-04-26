package hillsboro.philoarte.scalar.components;

import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Builder
@Component
public class MessengerVo {
    private String message;
    private int status;
    private String code;
}
