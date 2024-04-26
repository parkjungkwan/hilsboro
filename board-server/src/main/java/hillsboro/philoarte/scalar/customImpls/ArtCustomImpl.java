package hillsboro.philoarte.scalar.customImpls;

import hillsboro.philoarte.scalar.entities.Art;
import hillsboro.philoarte.scalar.customs.ArtCustom;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Log4j2
public class ArtCustomImpl extends QuerydslRepositorySupport implements ArtCustom {

    public ArtCustomImpl() {
        super(Art.class);
    }

    @Override
    public Art search() {

        return null;

    }

    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {

        log.info("searchPage..........");

        return null;
    }
}