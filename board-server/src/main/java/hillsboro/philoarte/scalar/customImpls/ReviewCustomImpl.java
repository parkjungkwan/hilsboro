package hillsboro.philoarte.scalar.customImpls;

import hillsboro.philoarte.scalar.entities.Review;
import hillsboro.philoarte.scalar.customs.ReviewCustom;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Log4j2
public class ReviewCustomImpl extends QuerydslRepositorySupport implements ReviewCustom {

    public ReviewCustomImpl() {
        super(Review.class);
    }

    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {

        log.info("searchPage...........");
        return null;
    }


    @Override
    public Review search() {
        log.info("search1...........");
        return null;
    }
}
