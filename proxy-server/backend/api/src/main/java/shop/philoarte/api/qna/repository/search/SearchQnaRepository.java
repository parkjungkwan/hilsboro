package shop.philoarte.api.qna.repository.search;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.philoarte.api.qna.domain.Qna;

public interface SearchQnaRepository {

    Qna search();

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);

}
