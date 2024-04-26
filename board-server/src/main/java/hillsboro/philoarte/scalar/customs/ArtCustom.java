package hillsboro.philoarte.scalar.customs;

import hillsboro.philoarte.scalar.entities.Art;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArtCustom {

    Art search();

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);

}
