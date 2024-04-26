package hillsboro.philoarte.scalar.customs;

import hillsboro.philoarte.scalar.entities.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewCustom {

    Review search();

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);

}
