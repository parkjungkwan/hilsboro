package shop.philoarte.api.art.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.philoarte.api.art.domain.Art;

public interface SearchArtRepository {

    Art search();

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);

}
