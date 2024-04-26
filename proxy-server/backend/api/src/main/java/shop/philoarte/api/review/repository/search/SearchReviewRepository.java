package shop.philoarte.api.review.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.philoarte.api.review.domain.Review;

public interface SearchReviewRepository {

    Review search();

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);

}
